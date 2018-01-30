package bigWork;

import java.util.List;

public class TouristGroup implements Tourist {
	private String ID;
	private List<ContactPerson> contactPersons;

	/**
	 * @param iD
	 * @param contactPersons
	 */
	public TouristGroup(String iD, List<ContactPerson> contactPersons) {
		ID = iD;
		this.contactPersons = contactPersons;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the contactPersons
	 */
	public List<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	/**
	 * @param contactPersons
	 *            the contactPersons to set
	 */
	public void setContactPersons(List<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}

	/**
	 * @return the string that contain all information of the TouristGroup the
	 *         ID and the contanctPersons
	 */
	public String toString() {
		String str = new String();
		str = str + "ID: " + getID();
		int number = 1;
		for (ContactPerson contact : contactPersons) {
			str = "contactPerson" + number + ": " + contact.toString() + "\n";
			number++;
		}
		return str;
	}

}
