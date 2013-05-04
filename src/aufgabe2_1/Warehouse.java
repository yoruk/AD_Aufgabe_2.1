package aufgabe2_1;

import java.util.*;

public interface Warehouse {
    /*
     * Eingabe:  Eine Map die eine Bestellung darstellt.
     *              Als key wird das bestellte item und als Value
     *              die bestellte Anzahl des Items benutzt
     * 
     * Ausgabe:  keine
     * 
     * Funktion: Die Bestellung wird an das Objekt Warehouse
     *           uebergeben.
     */
    public void takeOrder(Map<Item, Integer> order);

    /*
     * Eingabe:  keine Eingabe
     * 
     * Ausgabe:  keine Ausgabe
     * 
     * Funktion: Mit dieser Methode wird dem Warehouse 
     *           ein Taktsignal gegeben. Bei einem Taktsignal
     *           wird ein Uebergang in den naechsten Zustand herbeigefuehrt. 
     */
    public void action();

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  True, wenn das Warehouse fertig ist, d.h. wenn alle
     *           Bestellungen abgearbeitet sind. False, 
     *           wenn das Gegenteil der falls ist.
     * 
     * Funktion: siehe Ausgabe.
     */
    public boolean done();

    //JUnit
    public Field[][] getWarehouseArr();
    public Queue<Map<Item, Integer>> getOrderQueue();
    public BoxingPlant[] getBplants();
}
