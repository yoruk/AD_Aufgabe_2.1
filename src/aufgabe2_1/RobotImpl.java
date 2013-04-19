package aufgabe2_1;

public class RobotImpl implements Robot {
	private int id;
	private Field[][] field;
	private int startPosX;
	private int startPosY;
	private int currentPosX;
	private int currentPosY;
	
	public RobotImpl(int id, int startPosX, int startPosY, Field[][] field) {
		this.id = id;
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		this.field = field;
	}
	
	public int id() {
		return id;
	}
}
