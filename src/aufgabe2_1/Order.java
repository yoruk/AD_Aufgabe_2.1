package aufgabe2_1;

public class Order {
	private int cnt;
	private Item item;
	
	public Order(int cnt, Item item) {
		this.cnt = cnt;
		this.item = item;
	}

	public int getCnt() {
		return cnt;
	}

	public Item getItem() {
		return item;
	}
}
