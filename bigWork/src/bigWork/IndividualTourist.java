package bigWork;

public class IndividualTourist implements Tourist {
	private String ID;
	private Person person;

	/**
	 * 
	 * @param initID
	 * @param initName
	 * @param initTelephoneNumber
	 * @param initEmail
	 */
	public IndividualTourist(String initID, String initName, long initTelephoneNumber, String initEmail) {
		this.ID = initID;
		person = new Person(initName, initTelephoneNumber, initEmail);
	}

	/**
	 * @return ID
	 */
	public String getID() {
		return this.ID;
	}

	/**
	 * @return a string of the IndividualTourist
	 */
	public String toString() {
		String str = new String();
		str = str + "ID: " + getID() + ", " + person.toString();
		return str;
	}

}
