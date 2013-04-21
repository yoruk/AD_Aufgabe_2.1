package aufgabe2_1;

import java.util.*;
import java.util.Map.Entry;

public class BoxingPlantImpl implements BoxingPlant {
	private int amountOfRobots;
	private int coordinateX;
	private int coordinateY;
	private int id;
	private int robotId;
	private Robot robot;
	private Map<Item, Integer> order;
	private boolean busy;
	private int amountOfItems;
	
	public BoxingPlantImpl(int id, int x, int y, Robot bot) {
		robot = bot;
		robotId = bot.id();
		amountOfRobots = 1;
		busy = false;
	}
	
	public void action() {
		// wenn eine bestellung vorliegt und der robot nicht unterwegs ist
		if(order != null && !robot.isBusy()) {
			// gib robot bestellung
			// und loesche bestellliste
			robot.receiveOrder(order);
			order = null;
			
		// wenn keine bestelliste vorliegt, robot nicht(mehr) unterwegs ist
		// aber amountOfItems > 0 --> es muss eine bestellung verpackt werden 
		} else if(order == null && !robot.isBusy() && amountOfItems != 0) {
			packOrder();
			amountOfItems = 0;
			busy = false;
		}
 		
		robot.action();
	}
	
	/*
	 * verpackt die einzelnen items
	 * verpackungszeit = PPTIME * amountOfItems
	 */
	private void packOrder() {
		int temp_PPTIME = (Simulation.TEST) ? JUnitTestframe.PPTIME * 1000 : Simulation.PPTIME * 1000;
		
		for(int i=0; i<amountOfItems; i++) {
			 try {
				 Thread.sleep(temp_PPTIME);
			 } catch(Exception e) {
				 System.out.println("Es ist eine Exception in packOrder() aufgetreten!");
			 }
		}
		
		amountOfItems = 0;
		busy = false;
	}
	/*
	 * nimmt bestellen des warehouses entgegen 
	 */
	public void receiveOrder(Map<Item, Integer> order) {
		// Bestellung entgegennehmen
		this.order = order;
		
		// das gesamtgewicht merken
		for (Entry<Item, Integer> element : order.entrySet()) {
            amountOfItems += element.getValue();
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
