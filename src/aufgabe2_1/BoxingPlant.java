package aufgabe2_1;

import java.util.Map;

public interface BoxingPlant extends Field {
	public int id();
	public boolean isBusy();
	public void receiveOrder(Map<Item, Integer> order);
	public void action();
}
