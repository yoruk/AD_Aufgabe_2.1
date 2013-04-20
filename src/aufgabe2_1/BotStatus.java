/**
 * @author Konstantin Dolberg
 * @date 19.04.2013
 */
package aufgabe2_1;

/**
 * Enum Klasse zur vereinfachung des Umgangs mit den Bots zwecks Uebersichtlichkeit/Vergleichbarkeit.
 * 
 * @version 1.0 -
 * Diese Klasse befindet in der Entwicklung und kann dementsprechend noch angepasst/veraendert werden!
 * 
 * @IDLE Bot wartet auf eine Aufgabe.
 * @BUSY Bot ist mit einer Aufgabe beschaeftigt.
 * @MOVING Bot bewegt sich.
 * @LOADING Bot belaedt sich.
 * @BOXING Bot entlaedt sich. 
 */
public enum BotStatus {

	IDLE,
	BUSY,
	MOVING,
	LOADING,
	BOXING
	
}