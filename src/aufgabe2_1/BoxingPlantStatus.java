/**
 * @author Konstantin Dolberg
 * @date 19.04.2013
 */
package aufgabe2_1;

/**
 * Enum Klasse zur vereinfachung des Umgangs mit den Packstationen zwecks Uebersichtlichkeit/Vergleichbarkeit.
 * 
 * @version 1.0 -
 * Diese Klasse befindet in der Entwicklung und kann dementsprechend noch angepasst/veraendert werden!
 * 
 * @IDLE Packstationen wartet auf einen Auftrag.
 * @BUSY Packstationen ist mit einem Auftrag belegt (wartet auf rueckkehr des entspr. Bots).
 * @BOXING Packstationen verpackt einen Auftrag. 
 */
public enum BoxingPlantStatus {

	IDLE,
	BUSY,
	BOXING
	
}