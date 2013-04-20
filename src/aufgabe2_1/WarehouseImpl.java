package aufgabe2_1;

import java.util.*;

public class WarehouseImpl implements Warehouse {
	private Field[][] warehouse;
	
	private WarehouseImpl(int n, int numBoxingPlants) {
		int temp_N = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
		
		warehouse = new Field[n][n];
		
		// Alle vorgesehene Fields mit StorageAreaImpl initialisieren
		// und Items zuweisen
		List<Item> itemlist = Item.factory();
		for(Item i : itemlist) {
			warehouse[i.productPosY()][i.productPosX()] = new StorageAreaImpl(i);
		}
		
		// Alle vorgesehene Fields mit BoxingPlantImpl initialisieren
		// und RobotImpl zuweisen
		Robot tmpBot;
		int count = 1;

		for(int i=0; i<warehouse[temp_N-1].length; i++) {
			if(warehouse[temp_N-1][i] == null) {
				tmpBot = new RobotImpl(count, i, temp_N-1, warehouse);
				warehouse[temp_N-1][i] = new BoxingPlantImpl(count, i, temp_N-1, tmpBot);
				count++;
			}

		}
	}
	
	public static WarehouseImpl factory() {
		int temp_N = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
		int temp_NUMBOXINGPLANTS = (Simulation.TEST) ? JUnitTestframe.NUMBOXINGPLANTS : Simulation.NUMBOXINGPLANTS;
		
		return new WarehouseImpl(temp_N, temp_NUMBOXINGPLANTS);
	}
	
//	// nur zum debuggen
//	public String toStringTest() {
//		StringBuilder sb = new StringBuilder();
//		
//		for(int y=0; y<warehouse.length; y++) {
//			for(int x=0; x<warehouse[y].length; x++) {
//				if((warehouse[y][x] != null) && (!warehouse[y][x].isBoxingPlant())) {
//					sb.append(((StorageArea)warehouse[y][x]).item().id());
//					
//				}
//				
//				sb.append('\n');
//			}
//			
//			sb.append("|\n");
//		}
//		
//		return sb.toString();
//	}
	
	@Override
	public String toString() {
		int temp_N = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
		
		StringBuilder ret = new StringBuilder();
		
		for(int i=0; i<temp_N+2; i++) {
			ret.append('#');
		}
		ret.append('\n');
		
		for(int y=0; y<warehouse.length; y++) {
			ret.append('#');
			
			for(int x=0; x<warehouse.length; x++) {
				if(warehouse[y][x].hasRobots() > 1) {
					ret.append('X');
				} else if(warehouse[y][x].hasRobots() == 1) {
					ret.append(warehouse[y][x].robotID());
				} else {
					if(warehouse[y][x].isBoxingPlant()) {
						ret.append('B');
					} else {
						ret.append('.');
					}
				}
			}
			
			ret.append("#\n");
		}
		
		for(int i=0; i<temp_N+2; i++) {
			ret.append('#');
		}
		ret.append('\n');
		
		return ret.toString();
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
