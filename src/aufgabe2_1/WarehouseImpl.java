package aufgabe2_1;

import java.text.DecimalFormat;
import java.util.*;

public class WarehouseImpl implements Warehouse {
	private Field[][] warehouse;
	private Queue<Map<Item, Integer>> orderQueue;
	private BoxingPlant[] bplants;
	private boolean done;
	private DecimalFormat df = new DecimalFormat("00");

	public WarehouseImpl() {
		new WarehouseImpl(Item.factory());
	}
	
	public WarehouseImpl(List<Item> itemList) {
		int temp_N = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
		int temp_NUMBOXINGPLANTS = (Simulation.TEST) ? JUnitTestframe.NUMBOXINGPLANTS : Simulation.NUMBOXINGPLANTS;
		
		warehouse = new Field[temp_N][temp_N];
		orderQueue = new LinkedList<Map<Item, Integer>>();
		bplants = new BoxingPlant[temp_NUMBOXINGPLANTS];
		done = false;
		
		// Alle vorgesehene Fields mit StorageAreaImpl initialisieren
		// und Items zuweisen
		for(Item i : itemList) {
			warehouse[i.productPosY()][i.productPosX()] = new StorageAreaImpl(i);
		}
		
		// Alle vorgesehene Fields mit BoxingPlantImpl initialisieren
		// und RobotImpl zuweisen
		Robot tmpBot;
		int count = 1;
		
		// temp_N-1 = letzte zeile
		for(int i=0; i<warehouse[temp_N-1].length; i++) {
			
			// nur zuweisungen bei null vornehmen
			if(warehouse[temp_N-1][i] == null) {
			    
				if(i < temp_NUMBOXINGPLANTS) {
				
					// robot erstellen
					tmpBot = new RobotImpl(count, i, temp_N-1, warehouse);
					
					// boxingplant erstellen
					warehouse[temp_N-1][i] = new BoxingPlantImpl(count, i, temp_N-1, tmpBot);
					
					// extra referenz fuer schnelleren zugriff auf boxingplant erstellen
					bplants[count-1] = (BoxingPlant)warehouse[temp_N-1][i];
			    
				} else {
					
					// fake boxingplant erstellen
			        warehouse[temp_N-1][i] = new FieldImpl();
			    }
				count++;
			}
		}
	}
	
	public void action() {
		// index fuer eine bPlant die idle ist
		int idle;
		
		// wenn alle bPlants fertig sind 
		// und keine weiteren bestelungen vorliegen
		if(bPlantsDone() && orderQueue.isEmpty()) {
            done = true;
        }

		// wenn bestellungen vorliegen
		for(int i=0; i<bplants.length; i++) {
		       if(!orderQueue.isEmpty()) {
		            // freie bPlant suchen
		            idle = findIdleBPlant();
		            
		            // dieser die bestellung zuweisen
		            if(idle != 0) {
		                bplants[idle-1].receiveOrder(orderQueue.remove());
		            }
		       }
		      
		       // allen bplants nacheinander action-signal geben
		       bplants[i].action();
		}
	}
	
	public void takeOrder(Map<Item, Integer> order) {
		orderQueue.add(order);
		
		done = false;
	}
	
	/*
	 * der return wert ist der index+1
	 * der return wert ist 0, falls es keine idle bplant gibt.
	 */
	private int findIdleBPlant() {
		int ret = 0;
		
		for(int i=0; i<bplants.length; i++) {
			if(!bplants[i].isBusy()) {
				return i+1;
			}
		}
		
		return ret;
	}
	
	/*
	 * kontrolliert ob alle bplants fertig sind
	 */
	private boolean bPlantsDone() {
		for(int i=0; i<bplants.length; i++) {
			if(bplants[i].isBusy()) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean done() {
		return done;
	}
	
	public String toStringMini() {
		int temp_N = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
		
		StringBuilder ret = new StringBuilder();
		
		for(int i=0; i<temp_N+2; i++) {
			ret.append('#');
		}
		ret.append('\n');
		
		for(int y=0; y<warehouse.length; y++) {
			ret.append('#');
			
			for(int x=0; x<warehouse.length; x++) {
				if(warehouse[y][x].hasRobots() > 1) {
					ret.append('X');
				} else if(warehouse[y][x].hasRobots() == 1) {
					ret.append(warehouse[y][x].robotID());
				} else {
					if(warehouse[y][x].isBoxingPlant()) {
						ret.append('B');
					} else {
						ret.append('.');
					}
				}
			}
			
			ret.append("#\n");
		}
		
		for(int i=0; i<temp_N+2; i++) {
			ret.append('#');
		}
		ret.append('\n');
		
		return ret.toString();
	}

    public String toStringMaxi() {

        int border = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
        StringBuilder output = new StringBuilder();
        StringBuilder xFrame = new StringBuilder("####");
        StringBuilder frame;

        for (int y = 0; y < warehouse.length; y++) {

            // Y-Koordinaten anzeigen
            output.append('#').append(df.format(y)).append("#");

            for (int x = 0; x < warehouse.length; x++) {

                // Einmalig das X-Koodinaten Frame erstellen
                if(y == 0) {
                    xFrame.append('#').append(df.format(x)).append("#"); // X-Koordinate erzeugen
                }

                if (warehouse[y][x].hasRobots() > 1) {
                    output.append("[XX]"); // Robot Crash
                } else if (warehouse[y][x].hasRobots() == 1) {
                    output.append("[").append(df.format(warehouse[y][x].robotID())).append("]");
                } else {
                    if (warehouse[y][x].isBoxingPlant()) {
                        output.append("[BX]"); // BoxingPlant
                    } else {
                        output.append("[  ]"); // Field
                    } // else
                } // else
            } // for
            output.append("#\n"); // Zeilenumbruch an Y-Zeile anhaengen            
        } // for

        border = output.indexOf("\n"); // Zeilenbreite in border speichern
        frame = new StringBuilder(""); // Leeren StringBuilder initialisieren

        // String mit der Laenge einer Zeilenbreite mit Rauten fuellen
        for (int i = 0; i < border; i++) {
            frame.append("#");
        }

        frame.append("\n"); // Zeilenumbruch in Rauten-String anhaengen
        xFrame.append("#\n"); // Zeilenumbruch mit Raute xFrame anhaengen
        output.insert(0, frame); // Rauten-Abgrenzung fuer X-Koordinaten einfuegen
        output.insert(0, xFrame); // X-Koordinaten einfuegen
        output.insert(0, frame); // Erste Zeile mit Rauten befuellen
        output.append(frame); // Letzte Zeile mit Rauten befuellen

        return output.toString();
    }
    
    @Override
    public String toString() {
    	int tmp = (Simulation.TEST) ? JUnitTestframe.N : Simulation.N;
    	
    	if(tmp >= 10) {
    		return toStringMaxi();
    	} else {
    		return toStringMini();
    	}
    }
    
    @Override

    public Field[][] getWarehouseArr() {
    	return this.warehouse;
    }
    
    @Override
    public Queue<Map<Item, Integer>> getOrderQueue() {
    	return this.orderQueue;
    }
    
    @Override
    public BoxingPlant[] getBplants() {
    	return this.bplants;
    }
}
