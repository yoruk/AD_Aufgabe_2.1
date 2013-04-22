package aufgabe2_1;

import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

public class BoxingPlantImpl implements BoxingPlant {
	private int amountOfRobots;
	private int coordinateX;
	private int coordinateY;
	private final int ID;
	private int robotId;
	private Robot robot;
	private Map<Item, Integer> order;
	private boolean busy;
	private int packingTime;
	private final int temp_PPTIME = (Simulation.TEST) ? JUnitTestframe.PPTIME : Simulation.PPTIME;
	private final int temp_CLTIME = (Simulation.TEST) ? JUnitTestframe.CLTIME : Simulation.CLTIME;
	private int temp_CLTIME_cnt = temp_CLTIME;
	private DecimalFormat df = new DecimalFormat("00");	

	public BoxingPlantImpl(int id, int x, int y, Robot bot) {
		robot = bot;
		robotId = bot.id();
		amountOfRobots = 1;
		busy = false;
		coordinateX = x;
		coordinateY = y;
		ID = id;
	}
	
	public void action() {
		// wenn eine bestellung vorliegt und der robot nicht unterwegs ist
		if(order != null && !robot.isBusy()) {
			// gib robot bestellung
			// und loesche bestellliste
		    System.out.println("BoxingPlant [" + df.format(this.id()) + "]: Bekomme Order " + order.toString());
			robot.receiveOrder(order);
			order = null;
		}
			
		// wenn der roboter unterwegs ist, wird nur eine action 
		// nach ablauf des counters ausgeloest
		if(order == null && robot.isBusy() && temp_CLTIME_cnt-1 != 0) {
			temp_CLTIME_cnt--;
		} else {
			robot.action();
			
			temp_CLTIME_cnt = temp_CLTIME;
		} 

		// wenn keine bestelliste vorliegt, robot nicht(mehr) unterwegs ist
		// aber packingTime > 0 --> es muss eine bestellung verpackt werden 
		if(order == null && !robot.isBusy() && packingTime != 0) {
			System.out.println("BoxingPlant " + ID + " packt");
			
			packingTime--;
		} 
		
		// nach dem verpacken ist die bplant fertig
		if(order == null && !robot.isBusy() && packingTime == 0){			
			busy = false;
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
		
		// reale packzeit ausrechnen
		packingTime *= temp_PPTIME;
		
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
		return ID;
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
