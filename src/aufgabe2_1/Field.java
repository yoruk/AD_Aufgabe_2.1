package aufgabe2_1;

public interface Field {

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  Ausgabe: Ein Integer-Wert wird zurueckgegeben.
     * 
     * Funktion: Gibt die Anzahl von Robots auf einem Field 
     *           als Integer-Wert zurueck.
     */
    public int hasRobots();

    /*
     * Eingabe:  Ein Robot Objekt wird uebergeben.
     * 
     * Ausgabe:  keine
     * 
     * Funktion: Die uebergebene Robot ID wird in der Objektvariable robotId 
     *           von Field gespeichert. amountOfRobots wird inkrementiert.
     */
    public void reg(Robot bot);

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  keine
     *  
     * Funktion: amountOfRobots wird dekrementiert. 
     *           Die aktuelle Objektvariable robotId wird auf Integer-Wert 0 gesetzt.
     */
    public void unReg();

    /*
     * Eingabe:  keine
     *  
     * Ausgabe:  Ein Integer-Wert wird zurueckgegeben.
     * 
     * Funktion: Gibt die aktuelle robotId als Integer-Wert zurueck.
     */
    public int robotID();

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  Ein Integer-Wert wird zurueckgegeben.
     * 
     * Funktion: Gibt die aktuelle X-Koordinate als Integer-Wert zurueck.
     */
    public int coordinateX();

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  Ein Integer-Wert wird zurueckgegeben.
     * 
     * Funktion: Gibt die aktuelle Y-Koordinate als Integer-Wert zurueck.
     */
    public int coordinateY();

    /*
     * Eingabe:  keine
     * 
     * Ausgabe:  Ein Boolean wird zurueckgegeben.
     * 
     * Funktion: Gibt als Boolean zurueck, ob das aktuelle Field ein BoxingPlant ist oder nicht.
     */
    public boolean isBoxingPlant();
}
