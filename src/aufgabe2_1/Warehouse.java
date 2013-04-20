package aufgabe2_1;

import java.util.Map;

public interface Warehouse {
	public void takeOrder(Map<Item, Integer> order);
	void action();
	public boolean done();
}
