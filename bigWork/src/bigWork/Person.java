package bigWork;

public class Person {
	private String name;
	private long telephoneNumber;
	private String email;

	/**
	 * 
	 * @param initName
	 * @param initTelephoneNumber
	 * @param initEmail
	 */
	public Person(String initName, long initTelephoneNumber, String initEmail) {
		this.name = initName;
		this.telephoneNumber = initTelephoneNumber;
		this.email = initEmail;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the telephoneNumber
	 */
	public long getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber
	 *            the telephoneNumber to set
	 */
	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return a string contain all information of the person
	 */
	public String toString() {
		String str = new String();
		str = str + "name: " + this.getName() + ", telephoneNumber: " + this.getTelephoneNumber() + ", email: "
				+ this.getEmail();
		return str;
	}
}
