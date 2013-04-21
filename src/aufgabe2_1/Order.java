package aufgabe2_1;

import java.util.*;
import java.util.Map.Entry;

public class Order {
	private Map<Item, Integer> order;

    // Public Konstruktor zum Anlegen von Testfaellen
    public Order() {
        order = new HashMap<Item, Integer>();;
    }
    
    // zum testen
    public void addItem(Item item, int amount) {
    	order.put(item, amount);
    }
    
    // zum testen
    public Map<Item, Integer> list() {
    	return order;
    }

    public static Map<Item, Integer> factory(List<Item> items) {
    	int temp_ORDERMAXSIZE = (Simulation.TEST) ? JUnitTestframe.ORDERMAXSIZE : Simulation.ORDERMAXSIZE;
    	
    	Map<Item, Integer> orderMap = new HashMap<Item, Integer>();
    	boolean orderComplete = false;
        int randomItem;
        int currentOrderSize = temp_ORDERMAXSIZE;
        Item tempItem = null;
        Integer tempQuantity = 0;

        while (!orderComplete) {
        	
        	// Zufaelliges Item aus uebergebener List<Item> suchen
            randomItem = (int) (Math.random() * items.size() + 1);

            for (Item element : items) {
                if (element.id() == randomItem) {
                    tempItem = element;
                }
            }

            // Wenn das neue Random Item zu gross ist, wird es nicht mehr hinzugefuegt
            if ((currentOrderSize - tempItem.size()) >= 0) {

                // Wenn das Item in der Map vorhanden ist, wird die Quantity vom Item erhoeht,
                // ansonten wird es neu angelegt
                if (orderMap.containsKey(tempItem)) {

                    tempQuantity = orderMap.get(tempItem);
                    tempQuantity++;
                } else {
                    tempQuantity = 1;
                }

                orderMap.put(tempItem, tempQuantity);
                currentOrderSize -= tempItem.size();
            } else {
                orderComplete = true;
            }
        }

        return orderMap;
    }

    @Override
    public String toString() {
    	StringBuilder output = new StringBuilder();

        for (Entry<Item, Integer> element : order.entrySet()) {
            output.append(element.getKey() + " = " + element.getValue());
        }

        return output.toString();
    }
}
