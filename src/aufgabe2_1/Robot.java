package aufgabe2_1;

import java.util.Map;

public interface Robot {
	public int id();
	public void action();
	public boolean isBusy();
	public void receiveOrder(Map<Item, Integer> order);
}
