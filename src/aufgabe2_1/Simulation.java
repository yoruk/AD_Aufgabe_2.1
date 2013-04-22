package aufgabe2_1;

import java.util.*;

public class Simulation {
	public static final int N = 9;
	public static final int NUMBOXINGPLANTS = 9;
	public static final int ORDERMAXSIZE = 50;
	public static final int MAXCAPACITY = ORDERMAXSIZE;
	public static final int NUMROBOTS = NUMBOXINGPLANTS;
	public static final int PPTIME = 5;
	public static boolean TEST = false;
	
	public static void main(String[] args) {
        
        List<Item> item = Item.factory();
        Warehouse wh = new WarehouseImpl(item);
        
        for(int i = 0;i < 30;i++) {
            wh.takeOrder(Order.factory(item));
        }
        
//        Order order = new Order();
//        order.addItem(item.get(0), 1);
////        order.addItem(item.get(8), 1);
//        wh.takeOrder(order.list());
//        
//        Order order2 = new Order();
//        order2.addItem(item.get(2), 1);
//        wh.takeOrder(order2.list());
//        
//        Order order3 = new Order();
//        order3.addItem(item.get(3), 1);
//        wh.takeOrder(order3.list());
        
//        System.out.println(order.toString());
//        wh.toStringSuper();
        
      int i=0;
        while (!wh.done() && i < 3000) {
            wh.action();
            System.out.print(wh.toString());
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }        }
        System.out.println(i);
        System.out.println(wh.done());
    }
}