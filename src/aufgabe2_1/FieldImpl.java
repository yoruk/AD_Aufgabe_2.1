package aufgabe2_1;

public class FieldImpl implements Field {
	private int amountOfRobots;
	private int robotId;

	public FieldImpl() {
		amountOfRobots = 0;
	}
	
	public int hasRobots() {
		return amountOfRobots;
	}
	

	public boolean isBoxingPlant() {
		return true;
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

    @Override
    public int coordinateX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int coordinateY() {
        // TODO Auto-generated method stub
        return 0;
    }
}
