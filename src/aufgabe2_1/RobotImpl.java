package aufgabe2_1;

import java.util.*;

public class RobotImpl implements Robot {
	private final int id;
	private Field[][] field;
	private final int startPosX;
	private final int startPosY;
	private int lastPosX;
    private int lastPosY;	
	private int currentPosX;
	private int currentPosY;
	private boolean busy;
	private Map<Item, Integer> order;
	
public RobotImpl(int id, int startPosX, int startPosY, Field[][] field) {
        this.id = id;
        this.startPosX = startPosX;
        this.startPosY = startPosY;
        this.currentPosX = startPosX;
        this.currentPosY = startPosY;
        this.field = field;
        busy = false;
    }

    public int id() {
        return id;
    }

    public boolean isBusy() {
        return busy;
    }

    public void receiveOrder(Map<Item, Integer> order) {
        this.order = order;
    }

    /**
     * Bewegt sich auf die neue position und belegt sie
     * 
     * @param position
     *            zukuenftige position
     * @return anzahl Roboter auf neuem Feld
     */
    private int moveTo(int positionY, int positionX) {
        field[this.currentPosY][this.currentPosX].unReg();
        lastPosY = this.currentPosY;
        lastPosX = this.currentPosX;
        this.currentPosY = positionY;
        this.currentPosX = positionX;
        field[positionY][positionX].reg(this);

        return field[positionY][positionX].hasRobots();
    }

    /**
     * Findet herraus in welche richtung er gehen muss;
     * 
     * @param destination
     *            Wo der Robot hin will
     * @return Richtung in die er gehen muss (N, S, W, E)
     */
    private char findWay(int destinationY, int destinationX) {
        if (destinationY < currentPosY) {
            return 'N';
        } else if (destinationY > currentPosY) {
            return 'S';
        } else if (destinationX < currentPosX) {
            return 'W';
        } else if (destinationX > currentPosX) {
            return 'E';
        } else {
            return 'A';
        }
    }

    /**
     * Prueft ob auf einem Robot ein Feld ist
     * 
     * @param positionY
     *            feld was geprueft werden soll
     * @param positionX
     *            feld was geprueft werden soll
     * @return es wird true zurueck gegeben wenn das feld frei ist sonst false
     */
    private boolean fieldFree(int positionY, int positionX) {
        if (positionY < Simulation.N && positionX < Simulation.N && positionY >= 0 && positionX >= 0 &&
         (!field[positionY][positionX].isBoxingPlant() || (positionY == startPosY && positionX == startPosX))&&
                field[positionY][positionX].robotID() == 0) {
            return true;
        }
        return false;
    }

    private void evade(int y, int x) {
          if (currentPosY != lastPosY && currentPosX + 1 != lastPosX && fieldFree(currentPosY, currentPosX + 1)) {
            moveTo(currentPosY, currentPosX + 1);
        } else  if (currentPosY + 1 != lastPosY && currentPosX != lastPosX && fieldFree(currentPosY + 1, currentPosX)) {
            moveTo(currentPosY + 1, currentPosX);
        } else if (currentPosY != lastPosY && currentPosX - 1 != lastPosX && fieldFree(currentPosY, currentPosX - 1)) {
            moveTo(currentPosY, currentPosX - 1);
        } else if (currentPosY - 1 != lastPosY && currentPosX != lastPosX && fieldFree(currentPosY - 1, currentPosX)) {
            moveTo(currentPosY - 1, currentPosX);
        } else if (fieldFree(lastPosY, lastPosX)) {
            moveTo(lastPosY, lastPosX);
        }
    }

    private void load() {
        System.out.println("Lade item Robot: " + id);
    }

    /**
     * Sucht das sich das erste item aus der Order und gibt davon die position
     * zurück
     * 
     * @return das nächste Ziel
     */
    public int[] destination() {
        Set<Item> test = order.keySet();
        int[] i = { startPosY, startPosX };
        if (!test.isEmpty()) {
            for (Item ele : test) {
                i[0] = ele.productPosY();
                i[1] = ele.productPosX();
                System.out.printf("%d %d \n", i[0], i[1]);
                return i;

            }
        }
        return i;
    }

    /**
     * versucht denn Roboter zu bewegen wenn ein feld nicht frei ist wird eine
     * alternativ route gesucht(in der Zukunft irgendwann mal)
     * 
     * @param y
     *            -1 nach Norden fahren +1 nach Sueden fahren
     * @param x
     *            -1 nach Westen fahren +1 nach Osten fahren
     */
    private boolean tryMove(int y, int x) {
        if (fieldFree(currentPosY + y, currentPosX + x)) {
            moveTo(currentPosY + y, currentPosX + x);
            return true;
        } else {
            evade(y, x);
            return false;
        }
    }

    /**
     * entfernt das erste objekt aus der Order
     * 
     * @return true wenn löschen erfolgreich sonst false
     */
    public boolean remove() {
        Set<Item> test = order.keySet();
        if (!test.isEmpty()) {
            for (Item ele : test) {
                order.remove(ele);
                return true;
            }
        }
        return false;
    }

    /**
     * macht eine Roboter action
     */
    public void action() {
        // System.out.println("Robot Action");

        if (order != null && !order.isEmpty()) {
            busy = true;

            // Sucht sich das nächste ziel
            int[] i = destination();

            //
            switch (findWay(i[0], i[1])) {
            case 'N':
                tryMove(-1, 0);
                break;
            case 'S':
                tryMove(+1, 0);
                break;
            case 'W':
                tryMove(0, -1);
                break;
            case 'E':
                tryMove(0, +1);
                break;
            case 'A':
                load();
                remove();
                break;
            }
        } else {
            switch (findWay(startPosY, startPosX)) {
            case 'N':
                tryMove(-1, 0);
                break;
            case 'S':
                tryMove(+1, 0);
                break;
            case 'W':
                tryMove(0, -1);
                break;
            case 'E':
                tryMove(0, +1);
                break;
            case 'A':
                busy = false;
                break;
            }
        }
    }
}

