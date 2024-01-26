
/**
 * This enumeration defines the set of the predefined constants to determine the triage level of a
 * patient in an urgent priority care. The level RED is less than, meaning it has a higher priority,
 * than YELLOW, and YELLOW is less than GREEN.<BR/>
 * 
 * TriageLevel constants are comparable. <BR/>
 * References of type TriageLevel can be compared using the Comparable.compareTo() method. For
 * instance:<BR/>
 * If we consider the following references. <BR/>
 * TriageLevel red = TriageLevel.RED; <BR/>
 * TriageLevel yellow = TriageLevel.YELLOW; <BR/>
 * TriageLevel green = TriageLevel.GREEN; <BR/>
 * The following statements are TRUE: <BR/>
 * red.compareTo(yellow) &lt; 0 <BR/>
 * red.compareTo(green) &lt; 0 <BR/>
 * green.compareTo(yellow) &gt; 0
 *
 */
public enum TriageLevel {
  /**
   * RED triage level
   */
  RED,
  /**
   * YELLOW triage level
   */
  YELLOW,
  /**
   * GREEN triage level
   */
  GREEN;
}
