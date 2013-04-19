package aufgabe2_1;

import java.util.*;

public class Item {
	final int productPosx;
	final int productPosy;
	final int productSize;
	final int item_id;
	private static int idCounter = 1;

	private Item(int productPosx, int productPosy, int productSize, int item_id) {
		this.productPosx = productPosx;
		this.productPosy = productPosy;
		this.productSize = productSize;
		this.item_id = item_id;
	}

	public int getProductPosx() {
        return productPosx;
    }

    public int getProductPosy() {
        return productPosy;
    }
    
    public static List<Item> factory() {
    	List<Item> itemList = new ArrayList<Item>();
    	int maxSize;
    	
    	for(int y=0; y<Simulation.N; y++) {
    		for(int x=0; x<Simulation.N; x++) {
       			if(x >= Simulation.N - Simulation.NUMBOXINGPLANTS) {
    				break;
    			}

       			maxSize = (int)((Math.random()) * Simulation.ORDERMAXSIZE + 1);
       			
       			itemList.add(new Item(x, y, maxSize, idCounter));
    			
       			idCounter++;
    		}
    	}
    	
    	return itemList;
    }
}
