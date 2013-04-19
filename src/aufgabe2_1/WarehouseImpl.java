package aufgabe2_1;

import java.util.*;

public class WarehouseImpl implements Warehouse {
	private Field[][] warehouse;
	
	private WarehouseImpl(int n, int numBoxingPlants) {
		warehouse = new Field[n][n];
		
		// Alle vorgesehene Fields mit StorageAreaImpl initialisieren
		// und Items zuweisen
		List<Item> itemlist = Item.factory();
		for(Item i : itemlist) {
			warehouse[i.productPosX()][i.productPosY()] = new StorageAreaImpl(i);
		}
		
		// Alle Robots initialisieren
		Robot[] botList = new Robot[Simulation.NUMROBOTS];
		for(int i=0; i<botList.length; i++) {
			botList[i] = new RobotImpl(i+1);
		}
		
		// Alle vorgesehene Fields mit BoxingPlantImpl initialisieren
		// und RobotImpl zuweisen
		int count = 0;
		for(int i=0; i<warehouse[Simulation.N-1].length; i++) {
//			if(warehouse[Simulation.N-1][i] == null) {
//				warehouse[Simulation.N-1][i] = new BoxingPlantImpl(count+1, i, Simulation.N-1, botList[0]);
//				System.out.println(i);
//			}
//			
//			count++;
			
//			System.out.println(warehouse[Simulation.N-1][i]);
		}
		
		//BoxingPlantImpl(int id, int x, int y, Robot bot)
	}
	
	public static WarehouseImpl factory() {
		return new WarehouseImpl(Simulation.N, Simulation.NUMBOXINGPLANTS);
	}
	
	// nur zum debuggen
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int y=0; y<warehouse.length; y++) {
			for(int x=0; x<warehouse[y].length; x++) {
				if((warehouse[y][x] != null) && (!warehouse[y][x].isBoxingPlant())) {
					sb.append(((StorageArea)warehouse[y][x]).item().id());
					
				}
				
				sb.append('\n');
			}
			
			sb.append("|\n");
		}
		
		return sb.toString();
	}
}
