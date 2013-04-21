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
	
//  /**
//  * Bewegt sich auf die neue position und belegt sie
//  * @param position zukünftige position
//  * @return anzahl Roboter auf neuem Feld
//  */
// private int moveTo(int positionY, int positionX) {
//     field[this.positionY][this.positionX].unReg();
//     this.positionY = positionY;
//     this.positionX = positionX;
//     field[positionY][positionX].reg(this);
//     
//     return field[positionY][positionX].hasRobots();
// }
//
// /**
//  * Findet herraus in welche richtung er gehen muss;
//  * @param destination Wo der Robot hin will
//  * @return Richtung in die er gehen muss (N, S, W, E)
//  */
// private char findWay(int destinationY, int destinationX ) {
//     if(destinationY < positionY){
//         return 'N';
//     } else if (destinationY > positionY){
//         return 'S';
//     } else if (destinationX < positionX) {
//         return 'W';
//     } else if (destinationX > positionX) {
//         return 'E';
//     } else { 
//         return 'A';
//     }
// }
// 
// /**
//  * Prüft ob auf einem Robot ein Feld ist
//  * 
//  * @param positionY feld was geprüft werden soll
//  * @param positionX feld was geprüft werden soll
//  * @return es wird true zurück gegeben wenn das feld frei ist sonst false
//  */
// private boolean fieldFree(int positionY, int positionX) {
//     if (field[positionY][positionX].robotID() != 0) {
//         return false;
//     }
//     return true;
// }
// 
// private int crash() {
//     
//     return 0;
// }
// 
// public int id() {
//     return ID;
// }
// 
// public boolean giveOrder(Order order) {
//     if(this.order == null) {
//         this.order = order;
//         return true;
//     }
//    return false;
// }
// 
// public void action() {
//     
//     if(order != null) {
//         switch(findWay(order.getItem().productPosY(),order.getItem().productPosX())) {
//         case 'N':   if(fieldFree(positionY-1,positionX)){
//                         moveTo(positionY-1,positionX);
// //                        System.out.printf("N");
//                     }
//             break;
//         case 'S':   if(fieldFree(positionY+1,positionX)){
//                         moveTo(positionY+1,positionX);
// //                        System.out.printf("S");
//                         
//                     }
//             break;
//         case 'W':   if(fieldFree(positionY,positionX-1)) {
//                         moveTo(positionY,positionX-1);
// //                        System.out.printf("W");
//                     }
//             break;
//         case 'E':   if(fieldFree(positionY,positionX+1)) {
//                         moveTo(positionY,positionX+1);
// //                        System.out.printf("E");
//                     }
//             break;
//         case 'A':   //if( Schon da)
//                     // items laden
// //                    System.out.printf("Error");
//             break;
//         }
//     }
// }
}

