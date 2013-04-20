package aufgabe2_1;

import java.util.*;

public class Item {
	private int productPosX;
	private int productPosY;
	private int productSize;
	private int item_id;
	private static int idCounter;

	private Item(int productPosX, int productPosY, int productSize, int item_id) {
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
    
    public static List<Item> factory() {
    	List<Item> itemList = new ArrayList<Item>();
    	int maxSize;
    	
    	idCounter = 1;
    	
    	for(int y=0; y<Simulation.N; y++) {
    		for(int x=0; x<Simulation.N; x++) {
       			if((x >= Simulation.N - Simulation.NUMBOXINGPLANTS) && y == Simulation.N-1) {
    				break;
    			}

       			maxSize = (int)((Math.random()) * Simulation.ORDERMAXSIZE + 1);
       			
       			itemList.add(new Item(x, y, maxSize, idCounter));
    			
       			idCounter++;
    		}
    	}
    	
    	return itemList;
    }

    @Override
    public String toString() {
    	return "Item - ID: " + item_id + " Size: " + productSize 
    			+ " Pos x/y: " + productPosX + "/" + productPosY;
    }
}
