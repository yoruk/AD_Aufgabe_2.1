package aufgabe2_1;

import java.util.*;
import java.util.Map.Entry;

public class BoxingPlantImpl implements BoxingPlant {
	private int amountOfRobots;
	private int coordinateX;
	private int coordinateY;
	private final int id;
	private int robotId;
	private Robot robot;
	private Map<Item, Integer> order;
	private boolean busy;
	private int packingTime;
	
	public BoxingPlantImpl(int id, int x, int y, Robot bot) {
		robot = bot;
		robotId = bot.id();
		amountOfRobots = 1;
		busy = false;
		coordinateX = x;
		coordinateY = y;
		this.id = id;
	}
	
	public void action() {
//	    System.out.println("Action Boxinplant");
//	    System.out.println("Boxingplant1:" + order.toString());
		// wenn eine bestellung vorliegt und der robot nicht unterwegs ist
		if(order != null && !robot.isBusy()) {
			// gib robot bestellung
			// und loesche bestellliste
		    System.out.println("Boxingplant:" + order.toString());
			robot.receiveOrder(order);
			order = null;
			
		// wenn keine bestelliste vorliegt, robot nicht(mehr) unterwegs ist
		// aber amountOfItems > 0 --> es muss eine bestellung verpackt werden 
		} else if(order == null && !robot.isBusy() && packingTime != 0) {
		    System.out.println("Packen BosingStation:"+id);
			packingTime--;
			busy = false;
		} 
		if(packingTime > 0) {
		    robot.action();
		}
	}

	/*
	 * nimmt bestellen des warehouses entgegen 
	 */
	public void receiveOrder(Map<Item, Integer> order) {
		// Bestellung entgegennehmen
		this.order = order;
		
		// das gesamtgewicht merken
		for (Entry<Item, Integer> element : order.entrySet()) {
            packingTime += element.getValue();
        }
		
		// zustand of busy setzen
		busy = true;
	}
	
	public int hasRobots() {
		return amountOfRobots;
	}
	
	public int coordinateX() {
		return coordinateX;
	}
	
	public int coordinateY() {
		return coordinateY;
	}

	public boolean isBoxingPlant() {
		return true;
	}

	public int id() {
		return id;
	}

	public boolean isBusy() {
		return busy;
	}

	public void reg(Robot bot) {
		 amountOfRobots++;
		 this.robotId = bot.id();
	}

	public void unReg() {
		amountOfRobots--;
        this.robotId = 0;
	}

	public int robotID() {
		return robotId;
	}
}
