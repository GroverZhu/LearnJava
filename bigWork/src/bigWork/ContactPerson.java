package bigWork;

public class ContactPerson extends Person {
	private String jobPosition;

	/**
	 * 
	 * @param initName
	 * @param initTelephoneNumber
	 * @param initEmail
	 * @param initJobPosition
	 */
	public ContactPerson(String initName, long initTelephoneNumber, String initEmail, String initJobPosition) {
		super(initName, initTelephoneNumber, initEmail);
		this.jobPosition = initJobPosition;
	}

	/**
	 * @return the jobPosition
	 */
	public String getJobPosition() {
		return jobPosition;
	}

	/**
	 * @param jobPosition
	 *            the jobPosition to set
	 */
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	/**
	 * @return a string contain the information of ContactPerson
	 */
	public String toString() {
		String str;
		str = super.toString();
		str = str + ", jobPosition:" + this.getJobPosition();
		return str;
	}
}
