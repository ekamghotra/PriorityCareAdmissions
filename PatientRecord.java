/**
 * This class models PatientRecord objects to be managed in a priority queue at an urgent care
 * service.
 *
 */
public class PatientRecord implements Comparable<PatientRecord> {
  // data fields
  private static int patientCounter = 1; // counts the number of patients created,
                                         // Begins at 1, and advances to next value
                                         // after each caseID is generated.
  public final int CASE_NUMBER; // generated unique case number
  private TriageLevel triage; // This patient's triage level // RED < YELLOW < GREEN
  private char gender; // This patient's single-character gender marker. Standard values
                       // for gender are F=Female, M=male, X=nonbinary
  private int age; // this patient's age
  private int orderOfArrival; // The order in which this patient arrived; taken from the
                              // value of patientCounter when this record was created.
  private boolean hasBeenSeen; // tells whether this patient has been marked as "seen"

  /**
   * Creates a new patient record and assigns it a CASE_NUMBER, as the counter will advance when the
   * static helper method generateCaseNumber() is called.
   *
   * @param gender a single character representing this patient's reported gender
   * @param age    the age of this patient in years
   * @param triage the triage level of this patient
   */
  public PatientRecord(char gender, int age, TriageLevel triage) {
    this.gender = gender;
    this.age = age;
    this.triage = triage;
    this.orderOfArrival = patientCounter;
    this.CASE_NUMBER = PatientRecord.generateCaseNumber(gender, age);
  }

  /**
   * Generates a five-digit case number for this patient using their reported gender and age.
   *
   * The first digit of the case is based on gender marker: F=1, M=2, X=3. Any other gender marker
   * should be assigned the first digit of 4. The next two digits of the patient's age: 03 could
   * mean a three-year-old or a 103-year-old The last two digits increment according to the number
   * of patients admitted during this run of ExceptionalCare; the first patient should be 01,
   * counting up to 99, and then wrapping around to 00.
   *
   * Therefore, a 27-year-old nonbinary person who is the 20th patient of the day would be 32720.
   *
   * @param gender a single-character representation of this patient's reported gender
   * @param age    the age of this patient in years
   * @return a five-digit case number for the patient.
   */
  public static int generateCaseNumber(char gender, int age) {
    int caseNumber = 0;
    // add gender number
    switch (gender) {
      case 'F':
        caseNumber = 10000;
        break;
      case 'M':
        caseNumber = 20000;
        break;
      case 'X':
        caseNumber = 30000;
        break;
      default:
        caseNumber = 40000;
    }
    // add age
    caseNumber += (age % 100) * 100;
    // increment counter and add it to the case number
    caseNumber += (++patientCounter) % 100;
    return caseNumber;
  }

  /**
   * For tester class purposes only: resets PatientRecord.patientCounter to 1. This method should be
   * called at the beginning of EACH tester method to ensure that the methods are not dependent on
   * being called in a particular order.
   */
  public static void resetCounter() {
    patientCounter = 1;
  }

  /**
   * Accessor method for triage
   *
   * @return the triage of this patient record
   */
  public TriageLevel getTriage() {
    return triage;
  }

  /**
   * Accessor method for gender
   *
   * @return the gender of this patient record
   */
  public char getGender() {
    return gender;
  }

  /**
   * Accessor method for age
   *
   * @return the age of this patient record
   */
  public int getAge() {
    return age;
  }

  /**
   * Accessor method for the order of arrival of this patient record
   *
   * @return the order of arrival of this patient record
   */
  public int getArrivalOrder() {
    return orderOfArrival;
  }

  /**
   * Marks this patient as having been seen. There is no way to undo this action.
   */
  public void seePatient() {
    this.hasBeenSeen = true;
  }

  /**
   * Creates and returns a String representation of this PatientRecord formatted as follows:
   * CASE_NUMBER: <age><gender> (triage) - seen/not seen
   *
   * Below are three examples of string representations of three different Patient Records: 21701:
   * 17M (YELLOW) - not seen 11703: 17F (RED) - seen 32102: 21X (GREEN) - not seen
   *
   * @return a String representation of this PatientRecord.
   */
  @Override
  public String toString() {
    return CASE_NUMBER + ": " + age + "" + gender + " (" + triage.toString() + ") - "
        + (this.hasBeenSeen ? "seen" : "not seen");
  }

  /**
   * Checks whether this PatientRecord equals another specific object passed as input.
   *
   * @param other other object to compare
   * @return {@code true} if other is instanceof PatientRecord and this PatientRecord and other have
   *         the exact same CASE_Number
   */
  @Override
  public boolean equals(Object other) {
    return (other instanceof PatientRecord)
        && this.CASE_NUMBER == ((PatientRecord) other).CASE_NUMBER;
  }

  /**
   * Compares this PatientRecord to another patientRecord provided as input. PatientRecords are
   * first compared with respect to their triage levels. The smaller PatientRecord is the one having
   * a smaller triage. TriageLevels can be compared using the TriageLevel.compareTo() method. By
   * default, RED is less than YELLOW, and YELLOW is less than GREEN.
   *
   * If the PatientRecords have the same triage levels, they will be compared with respect to the
   * order of arrival. For example, if PatientRecord A arrived before PatientRecord B and both have
   * a YELLOW triage level, then A is less than B.
   *
   * If the PatientRecords have the same triage level and the same order of arrival, then they are
   * considered equal. (This is expected behavior of PatientRecord even though you will not have
   * equal PatientRecords in your priority queue.)
   *
   * @param other PatientRecord to compare with
   * @return zero if this PatientRecord and other have the same triage level and same order of
   *         arrival; a negative integer if this PatientRecord is less than other; and a positive
   *         integer if this PatientRecord is greater than other.
   */
  @Override
  public int compareTo(PatientRecord other) {
    if (this.triage.equals(other.triage)) {
      return this.orderOfArrival - other.orderOfArrival;
    }
    else {
      return this.triage.compareTo(other.triage);
    }
  }
}
