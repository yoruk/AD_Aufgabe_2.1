package aufgabe2_1;

/*
 * Diese Klasse soll nicht zum fertigen Programm gehoeren.
 * Ihr koennte hier schnell mal Quick'N'Dirty eure Methoden testen.
 */

import java.util.*;

public class Test {
	public static void testItem_factory() {
		System.out.println("\ntestItem_factory()\n");
		
		List<Item> items = Item.factory();
		
		System.out.println("N: " + Simulation.N);
		System.out.println("NUMBOXINGPLANTS: " + Simulation.NUMBOXINGPLANTS);
		System.out.println("ORDERMAXSIZE: " + Simulation.ORDERMAXSIZE);
		
		for(Item i: items) {
			System.out.println(i);
		}
 	}
	
	public static void testWarehouseImpl_factory() {
		System.out.println("\ntestWarehouseImpl_factory()\n");
		
		System.out.println(WarehouseImpl.factory());
	}

	public static void main(String[] args) {
		//testItem_factory();
		testWarehouseImpl_factory();
	}
}
