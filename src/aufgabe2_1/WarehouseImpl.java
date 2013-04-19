package aufgabe2_1;

import java.util.*;

public class WarehouseImpl implements Warehouse {
	private Field[][] warehouse;
	
	private WarehouseImpl(int n, int numBoxingPlants) {
		warehouse = new Field[n][n];
		
		// Alle vorgesehene Fields mit StorageAreaImpl
		// initialisieren und Items zuweisen
		List<Item> itemlist = Item.factory();
		
		for(Item i : itemlist) {
			warehouse[i.productPosX()][i.productPosY()] = new StorageAreaImpl(i);
		}
	}
	
	public static WarehouseImpl factory() {
		return new WarehouseImpl(Simulation.N, Simulation.NUMBOXINGPLANTS);
	}
}
