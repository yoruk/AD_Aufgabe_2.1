package aufgabe2_1;

import java.util.*;

public class Simulation {
	public static final int N = 9;
	public static final int NUMBOXINGPLANTS = 4;
	public static final int ORDERMAXSIZE = 50;
	public static final int MAXCAPACITY = ORDERMAXSIZE;
	public static final int NUMROBOTS = NUMBOXINGPLANTS;
	public static final int CLTIME = 1;
	public static final int PPTIME = 5;
	public static boolean TEST = false;
	
	public static void main(String[] args) throws Exception {
        for (int k = 0; k < 1;k++) { 
	        System.out.println("Durchlauf:"+k);
	       
	        List<Item> item = Item.factory();
	        Warehouse wh = new WarehouseImpl(item);
        
//	        for(int i = 0;i < 30;i++) {
//	        	wh.takeOrder(Order.factory(item));
//	        }
        
       Order order = new Order();
       order.addItem(item.get(0), 1);
       wh.takeOrder(order.list());
       
       Order order2 = new Order();
       order2.addItem(item.get(0), 1);
       wh.takeOrder(order2.list());
        
       Order order3 = new Order();
       order3.addItem(item.get(4), 1);
       wh.takeOrder(order3.list());
              
       Order order4 = new Order();
       order4.addItem(item.get(8), 1);
       wh.takeOrder(order4.list());
       
       System.out.print(wh.toString());
       
       int i=0;
       while (!wh.done() && i < 3000000) {
    	   wh.action();
//           	 System.out.println(i);
    	   System.out.print(wh.toString());
    	   System.out.println();
    	   System.out.println();

    	   Thread.sleep(100);

    	   i++;
       }
        
       System.out.println(i);
       System.out.println(wh.done());
       System.out.println(wh);
        
       }
    }
}