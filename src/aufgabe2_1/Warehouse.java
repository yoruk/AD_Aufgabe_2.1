package aufgabe2_1;

import java.util.*;

public interface Warehouse {
	public void takeOrder(Map<Item, Integer> order);
	public void action();
	public boolean done();
	
	//JUnit
	public Field[][] getWarehouseArr();
	public Queue<Map<Item, Integer>> getOrderQueue();
	public BoxingPlant[] getBplants();
}
