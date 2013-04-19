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
			warehouse[i.productPosY()][i.productPosX()] = new StorageAreaImpl(i);
		}
		
		//public RobotImpl(int id, int startPosX, int startPosY, Field[][] field)
		
		// Alle vorgesehene Fields mit BoxingPlantImpl initialisieren
		// und RobotImpl zuweisen
		Robot tmpBot;
		int count = 0;

		for(int i=0; i<warehouse[Simulation.N-1].length; i++) {
			if(warehouse[Simulation.N-1][i] == null) {
				tmpBot = new RobotImpl(count+1, i, Simulation.N-1, warehouse);
				warehouse[Simulation.N-1][i] = new BoxingPlantImpl(count+1, i, Simulation.N-1, tmpBot);
			}

			count++;
		}
	}
	
	public static WarehouseImpl factory() {
		return new WarehouseImpl(Simulation.N, Simulation.NUMBOXINGPLANTS);
	}
	
	// nur zum debuggen
	public String toStringTest() {
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
	
	public void toStringSuper() {
	    System.out.printf("\n#################################################################\n");
	    for(int y=0; y<warehouse.length; y++) {
	        System.out.printf("#");
	        for(int x=0; x<warehouse[y].length; x++) {
	            if ((warehouse[y][x] == null)){
	                System.out.printf("\tXX\t#");
	            } else if(warehouse[y][x].isBoxingPlant()) {
	                System.out.printf("\tXB %d\t#", ((BoxingPlant)warehouse[y][x]).id());
	            } else {
	                System.out.printf("\tXS %d\t#", ((StorageArea)warehouse[y][x]).item().id());
	            }
	        }
	        System.out.printf("\n#################################################################\n");
	    }
	}
}
