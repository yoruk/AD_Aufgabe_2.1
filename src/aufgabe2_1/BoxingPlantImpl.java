package aufgabe2_1;

import java.util.*;

public class BoxingPlantImpl implements BoxingPlant {
	private int amountOfRobots;
	private int coordinateX;
	private int coordinateY;
	private int id;
	private int robotId;
	private Robot robot;
	private List<Item> orderList;
	private boolean busy;
	
	public BoxingPlantImpl(int id, int x, int y, Robot bot) {
		robot = bot;
		robotId = bot.id();
		amountOfRobots = 1;
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
