package aufgabe2_1;

import java.util.*;

public class Item implements Comparable<Item> {
    private int productPosX;
    private int productPosY;
    private int productSize;
    private int item_id;
    private static int idCounter;

    public Item(int productPosX, int productPosY, int productSize, int item_id) {
        this.productPosX = productPosX;
        this.productPosY = productPosY;
        this.productSize = productSize;
        this.item_id = item_id;
    }

    public int productPosX() {
        return productPosX;
    }

    public int productPosY() {
        return productPosY;
    }

    public int size() {
        return productSize;
    }

    public int id() {
        return item_id;
    }

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  Eine Liste von Items. Die Anzahl entspricht der Anzahl in
     *           Warehouse freien Plaetze btw der Storage-Areas.
     *           Die Gewichtsangaben sind random.
     *           Die Ids und die Koordinaten sind fortlaufen 
     *           und decken sich mit den fuer die Items erstellten Storageareas. 
     */
    public static List<Item> factory() {

        int temp_N = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
        int temp_NUMBOXINGPLANTS = (Simulation.TEST) ? JUnitTestframe.NUMBOXINGPLANTS : Simulation.NUMBOXINGPLANTS;
        int temp_ORDERMAXSIZE = (Simulation.TEST) ? JUnitTestframe.ORDERMAXSIZE : Simulation.ORDERMAXSIZE;
        int maxSize;
        List<Item> itemList = new ArrayList<Item>();
        idCounter = 1;

        for (int y = 0; y < temp_N - 1; y++) {
            for (int x = 0; x < temp_N; x++) {
                if ((x >= temp_N - temp_NUMBOXINGPLANTS) && y == temp_N - 1) {
                    break;
                }

                maxSize = (int) ((Math.random()) * temp_ORDERMAXSIZE + 1);

                itemList.add(new Item(x, y, maxSize, idCounter));

                idCounter++;
            }
        }

        return itemList;
    }

    @Override
    public String toString() {
        return "Item ID: " + item_id + " Size: " + productSize + " XY-Koordinaten: " + productPosX + "/" + productPosY;
    }

    @Override
    public int compareTo(Item other) {
        return (this.item_id - other.item_id);
    }

}
