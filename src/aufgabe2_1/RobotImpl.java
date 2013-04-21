package aufgabe2_1;

import java.util.*;

public class RobotImpl implements Robot {
	private int id;
	private Field[][] field;
	private int startPosX;
	private int startPosY;
	private int currentPosX;
	private int currentPosY;
	private boolean busy;
	private Map<Item, Integer> order; 
	
	
	public RobotImpl(int id, int startPosX, int startPosY, Field[][] field) {
		this.id = id;
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		this.field = field;
		busy = false;
	}
	
	public void action() {
		
	}

	public int id() {
		return id;
	}
	
	public boolean isBusy() {
		return busy;
	}
	
	public void receiveOrder(Map<Item, Integer> order) {
		this.order = order;
	}
}
