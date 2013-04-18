package aufgabe2_1;

public class Item {
	final int[] productPos;
	final int productSize;
	final int item_id;

	public Item(int[] productPos, int productSize, int item_id) {
		this.productPos = productPos;
		this.productSize = productSize;
		this.item_id = item_id;
	}

	public int[] getPos() {
		return productPos;
	}
}
