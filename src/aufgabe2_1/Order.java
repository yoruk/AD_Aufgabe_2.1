package aufgabe2_1;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int amount;
	private Item item;
	
	public Order(int amount, Item item) {
		this.amount = amount;
		this.item = item;
	}

	public int amount() {
		return amount;
	}

	public Item item() {
		return item;
	}
	
	/**
	 * erzeugt eine liste über Order
	 * generiert zwei random-werte
	 * der eine random-wert ist die artikel id mit der dann der entsprechende artikel gesucht wird
	 * der andere ist die anzahl wie oft der artikel "bestellt" wird
	 *
	 * @param items erwartet eine liste aller Items
	 * @param maxItems ist die anzahl pro artikel pro bestellungsposten
	 *
	 * @return List<Order>
	 */
	public static List<Order> factory(List<Item> items, int maxItems) { 
//		int rndItemCnt;
//		int item;
//		Item temp = null;
//		int currentOrderSize = Simulation.ORDERMAXSIZE;
//		boolean orderComplete = false;
		List<Order> Orderlist = new ArrayList<Order>();
//		
//		while(!orderComplete){
//			
//			item=(int) (Math.random()*items.size()+1);
//			rndItemCnt = (int) (Math.random()*maxItems+1);
//			
//			for(Item e:items){
//				if(e.id()==item){
//					temp=e;
//				}
//			}
//			//debug System.out.println(item);
//			if((rndItemCnt*temp.size())<currentOrderSize){
//				Orderlist.add(new Order(rndItemCnt,temp));
//				currentOrderSize-=rndItemCnt*temp.size();
//			}
//			
//			if(real){
//				if(Math.random()<0.5&&(Orderlist.size()!=0)){
//					orderComplete=true;
//				}
//			}
//			else {
//				if(currentOrderSize<(Simulation.ORDERMAXSIZE*0.16)){
//				orderComplete=true;
//				}
//			}
//		}
//		// debug totalsize(Orderlist);
		return Orderlist;
	}
	
	//debug
	private static int totalSize(List<Order> orderlist) {
		int sum = 0;
		
		for(Order e : orderlist){
			sum += e.amount() * e.item().size();
		}
		
		return sum;
	}

	@Override
	public String toString() {
		return "Order [cnt=" + amount + ", item=" + item + "]";
	}
}
