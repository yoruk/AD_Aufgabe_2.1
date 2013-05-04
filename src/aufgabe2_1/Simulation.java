package aufgabe2_1;

import java.util.*;

public class Simulation {
    public static final int N = 20;
    public static final int NUMBOXINGPLANTS = 20;
    public static final int ORDERMAXSIZE = 50;
    public static final int MAXCAPACITY = ORDERMAXSIZE;
    public static final int NUMROBOTS = NUMBOXINGPLANTS;
    public static final int CLTIME = 1;
    public static final int PPTIME = 5;
    public static boolean TEST = false;

    public static void main(String[] args) throws Exception {

        int takt = 1;

        // Kompletter Durchlauf eines gesamten Bestellvorgangs
        for (int k = 0; k < 1; k++) {
            System.out.println("Durchlauf: " + k);

            List<Item> item = Item.factory(); // Liste von Items generieren
            Warehouse wh = new WarehouseImpl(item); // Warehouse mit Item Liste befuellen

//            // i Order mit Item Liste generieren lassen und an das Warehouse uebergeben
//            for (int i = 0; i < 300; i++) {
//                wh.takeOrder(Order.factory(item));
//            }

            // Manuelle Order
            Order order1 = new Order();
            order1.addItem(new Item(0, 0, 1, 1), 3);
            order1.addItem(new Item(0, 1, 1, 6), 3);
            order1.addItem(new Item(0, 2, 1, 11), 3);
            wh.takeOrder(order1.getMap());

            Order order2 = new Order();
            order2.addItem(new Item(0, 1, 1, 6), 3);
            order2.addItem(new Item(0, 2, 1, 11), 3);
            order2.addItem(new Item(0, 0, 1, 1), 3);
            wh.takeOrder(order2.getMap());

            Order order3 = new Order();
            order3.addItem(new Item(0, 2, 1, 11), 3);
            order3.addItem(new Item(0, 1, 1, 6), 3);
            order3.addItem(new Item(0, 0, 1, 1), 3);
            wh.takeOrder(order3.getMap());

            Order order4 = new Order();
            order4.addItem(new Item(0, 2, 1, 11), 3);
            order4.addItem(new Item(0, 0, 1, 1), 3);
            order4.addItem(new Item(0, 1, 1, 6), 3);
            wh.takeOrder(order4.getMap());

            System.out.println(wh.toString());

            while (!wh.done() && takt < 30000) {

//                // Step-by-step mit ENTER durch die Schleife
//                new Scanner(System.in).nextLine();

                wh.action();
                System.out.println();
                System.out.println();
                System.out.println("Takt: " + takt);
                System.out.print(wh.toString());

                Thread.sleep(500);

                takt++;
            }
            System.out.println();
            System.out.println();
            System.out.println("Benoetigte Takte: " + takt);
            System.out.println("Order erfolgreich abgearbeitet: " + wh.done());
            System.out.println(wh);
        }
    }
}
