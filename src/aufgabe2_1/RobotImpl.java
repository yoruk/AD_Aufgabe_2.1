package aufgabe2_1;

import java.text.DecimalFormat;
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
	private Random random = new Random();
	private DecimalFormat df = new DecimalFormat("00");

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
     * @param positionY  feld was geprueft werden soll
     * @param positionX  feld was geprueft werden soll
     * @return es wird true zurueck gegeben wenn das feld frei ist sonst false
     */
    private boolean fieldFree(int positionY, int positionX) {
        if (positionY < Simulation.N && positionX < Simulation.N && positionY > - 1 && positionX > -1 &&
         (!field[positionY][positionX].isBoxingPlant() || (positionY == startPosY && positionX == startPosX))&&
                field[positionY][positionX].robotID() == 0) {
            return true;
        }
        return false;
    }
    
    private int rand(){
    	return random.nextBoolean() ? 1: -1;
    }

    private void evade(int y, int x) {
    	int rand = rand();
    	int rand2 = (rand == -1) ? 1 : -1;

    	if (currentPosY != lastPosY && currentPosX + rand != lastPosX && fieldFree(currentPosY, currentPosX + rand)) {
    		moveTo(currentPosY, currentPosX + rand);
    	} else if (currentPosY + rand != lastPosY && currentPosX != lastPosX && fieldFree(currentPosY + rand, currentPosX)) {
    		moveTo(currentPosY + rand, currentPosX);
    	} else if (currentPosY != lastPosY && currentPosX + rand2 != lastPosX && fieldFree(currentPosY, currentPosX + rand2)) {
    		moveTo(currentPosY, currentPosX + rand2);
    	} else if (currentPosY - rand != lastPosY && currentPosX != lastPosX && fieldFree(currentPosY - rand, currentPosX)) {
    		moveTo(currentPosY - rand, currentPosX);
    	} else if (fieldFree(lastPosY, lastPosX)) {
    		moveTo(lastPosY, lastPosX);
    	}
    }

    private void load() {
        System.out.println("Robot [" + df.format(this.id()) + "]: Lade Item bei Y: " + df.format(currentPosY) + " X: " + df.format(currentPosX));
    }

    /**
     * Sucht das sich das erste item aus der Order und gibt davon die position
     * zurueck
     * 
     * @return das naechste Ziel
     */
    public int[] destination() {
        Set<Item> test = order.keySet();
        int[] i = { startPosY, startPosX };
        if (!test.isEmpty()) {
            for (Item elem : test) {
                i[0] = elem.productPosY();
                i[1] = elem.productPosX();
                System.out.println("Robot [" + df.format(this.id()) + "]: " + "Hole Item bei Y: " + df.format(i[0]) + " X: " + df.format(i[1]));
                return i;

            }
        }
        return i;
    }

    /**
     * versucht denn Roboter zu bewegen wenn ein feld nicht frei ist wird eine
     * alternativ route gesucht
     * 
     * @param y -1 nach Norden fahren +1 nach Sueden fahren
     * @param x -1 nach Westen fahren +1 nach Osten fahren
     */
    private boolean tryMove(int y, int x) {
    	if (fieldFree(currentPosY + y, currentPosX + x)) {
    		moveTo(currentPosY + y, currentPosX + x);
    		return true;
    	} else if (currentPosY + 1 <  Simulation.N && field[currentPosY + 1][currentPosX].isBoxingPlant()) {
    		if (id-1 < currentPosX && fieldFree(currentPosY, currentPosX - 1)) {
    			moveTo(currentPosY, currentPosX - 1);
    			return true;
    		}else if (id-1 > currentPosX && fieldFree(currentPosY, currentPosX + 1)) {
    			moveTo(currentPosY, currentPosX + 1);
    			return true;
    		}
        }
    	evade(y, x);
    	return false;
	}

    /**
     * entfernt das erste objekt aus der Order
     * 
     * @return true wenn loeschen erfolgreich sonst false
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

