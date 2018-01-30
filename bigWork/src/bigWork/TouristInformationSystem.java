/**
 * @author Grover Zhu
 * the tourist information system to save tourists information
 * tourists including individual tourist and tourist group
 */
package bigWork;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.zip.DataFormatException;

public class TouristInformationSystem {
	/** define a list to set Tourist */
	private List<Tourist> tourists = new ArrayList<Tourist>();
	private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter stdOut = new PrintWriter(System.out, true);
	private static PrintWriter stdErr = new PrintWriter(System.err, true);
	private final static String delim = "_";
	private final static String INDIVIDUAL = "IndividualTourist";
	private final static String GROUP = "TouristGroup";

	/**
	 * 
	 * @param tourist
	 *            add a new IndividualTourist to the system
	 */
	public void addTourist(IndividualTourist tourist) {
		tourists.add(tourist);
		stdOut.println("Add a new tourist successfully!");
	}

	/**
	 * 
	 * @param ID
	 * @return IndividualTourist 通过ID来寻找IndividualTourist
	 */
	public IndividualTourist lookUpTourist(String ID) {
		for (Tourist tourist : tourists) {
			if (tourist instanceof IndividualTourist) {
				if (tourist.getID().equals(ID)) {
					stdOut.println("Found the tourist!");
					stdOut.println(tourist.toString());
					return (IndividualTourist) tourist;
				}
			}
		}
		stdErr.println("The tourist didn't found!");
		return null;
	}

	/**
	 * 
	 * @param ID
	 *            通过ID删除指定IndividualTourist
	 */
	public void removeTourist(String ID) {
		Iterator<Tourist> touristIterator = tourists.iterator();
		while (touristIterator.hasNext()) {
			Tourist individualTourist = touristIterator.next();
			if (individualTourist instanceof IndividualTourist) {
				String str = individualTourist.getID();
				if (str.equals(ID)) {
					touristIterator.remove();
					stdOut.println("Remove the tourist successfully!");
					return;
				}
			}
		}
		stdOut.println("The tourist ID is wrong!");
		return;
	}

	/**
	 * 
	 * @param contact
	 * @param ID
	 */
	public void addContactForTouristGroups(ContactPerson contact, String ID) {
		for (Tourist tourist : tourists) {
			if (tourist instanceof TouristGroup) {
				if (tourist.getID().equals(ID)) {
					((TouristGroup) tourist).getContactPersons().add(contact);
					stdOut.println("Add the contact for a tourist group successfully!");
					return;
				}
			}
		}
		stdErr.println("Not found the Group!");
		return;
	}

	/**
	 * 
	 * @param ID
	 * @param name
	 * @return ContactPerson
	 */
	public ContactPerson lookUpContact(String ID, String name) {
		for (Tourist tourist : tourists) {
			if (tourist instanceof TouristGroup) {
				if (tourist.getID().equals(ID)) {
					for (ContactPerson person : ((TouristGroup) tourist).getContactPersons()) {
						if (person.getName().equals(name)) {
							stdOut.println("The contact person was found!");
							stdOut.println(person.toString());
							return person;
						}
					}
				}
			}
		}
		stdErr.println("the contact person was not found!");
		return null;
	}

	/**
	 * 
	 * @param ID
	 * @param name
	 *            通过ID删除TouristGroup中指定的ContactPerson
	 *            通过迭代器Iterator来遍历ArrayList，当找到指定的TouristGroup时，
	 *            再向其中的ContactPerson的List遍历直至找到指定的ContactPerson。
	 */
	public void removeContact(String ID, String name) {
		Iterator<Tourist> touristIterator = tourists.iterator();
		/** 先由ID找TouristGroup */
		while (touristIterator.hasNext()) {
			Tourist touristGroup = touristIterator.next();
			/** 找到指定的TouristGroup，遍历其中的ContactPerson */
			if (touristGroup instanceof TouristGroup && touristGroup.getID().equals(ID)) {
				Iterator<ContactPerson> personIterator = ((TouristGroup) touristGroup).getContactPersons().iterator();
				while (personIterator.hasNext()) {
					ContactPerson person = personIterator.next();
					/** 通过name判断寻找的person是否为所指定的ContactPerson */
					if (person.getName().equals(name)) {
						personIterator.remove();
						stdOut.println("The contact person removed successfully!");
						return;
					}
				}
			}
		}
		stdErr.println("The name or the ID is wrong!");
		return;
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws ParseException
	 *             initial the tourists
	 */
	private TouristInformationSystem() throws FileNotFoundException, IOException, DataFormatException, ParseException {
		this.tourists = loadTouristFromFile();
	}

	/**
	 * 
	 * @return Boolean judge the ArrayList includes the TouristGroup if exit
	 *         return true else return false
	 */
	private boolean exitTouristGroup() {
		for (Tourist tourist : tourists) {
			if (tourist instanceof TouristGroup) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return List
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws ParseException
	 */
	private List<Tourist> loadTouristFromFile()
			throws FileNotFoundException, IOException, DataFormatException, ParseException {
		// read a file which contain the information of all tourists
		List<Tourist> tourists = new ArrayList<Tourist>();
		BufferedReader fileOut = new BufferedReader(new FileReader("src/file.txt"));
		String line = fileOut.readLine();
		while (line != null) {
			StringTokenizer str = new StringTokenizer(line, delim);
			if (str.countTokens() != 5 && str.countTokens() != 6) {
				fileOut.close();
				throw new DataFormatException(line);
			} else {
				try {
					String prefix = str.nextToken();
					/**
					 * 判断是IndividualTourist还是TouristGroup
					 * 为IndividualTourist直接向tourists添加新增的IndividualTourist
					 */
					if (prefix.equals(INDIVIDUAL)) {
						IndividualTourist tourist = new IndividualTourist(str.nextToken(), str.nextToken(),
								Long.parseLong(str.nextToken()), str.nextToken());
						tourists.add(tourist);
					} /**
						 * 当判断为TouristGroup时，
						 * 先判断该TouristGroup在该ArrayList中是否存在，若不存在，则声明新的TouristGroup
						 * 若存在则向该TouristGroup中添加该ContactPerson
						 */
					else if (prefix.equals(GROUP)) {

						String ID = str.nextToken();
						String name = str.nextToken();
						long telephoneNumber = Long.parseLong(str.nextToken());
						String email = str.nextToken();
						String jobPosition = str.nextToken();

						ContactPerson person = new ContactPerson(name, telephoneNumber, email, jobPosition);
						List<ContactPerson> persons = new ArrayList<ContactPerson>();
						persons.add(person);
						TouristGroup group = new TouristGroup(ID, persons);

						if (!exitTouristGroup()) {
							tourists.add(group);
						} else {
							for (Tourist visiter : tourists) {
								if (visiter instanceof TouristGroup && visiter.getID().equals(ID)) {
									((TouristGroup) visiter).getContactPersons().add(person);
									break;
								} else if (visiter instanceof TouristGroup && !(visiter.getID().equals(ID))) {
									tourists.add(group);
									break;
								}
							}
						}
					}
					line = fileOut.readLine();
				} catch (NumberFormatException nfe) {
					stdErr.println(nfe);
				}
			}
		}
		fileOut.close();
		return tourists;
	}

	/**
	 * 
	 * @return IndividualTourist
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws NumberFormatException
	 *             从控制台添加新的IndividualTourist
	 */
	private IndividualTourist loadIndividualTouristFromConsole()
			throws IOException, DataFormatException, NumberFormatException {
		do {
			stdOut.print("Input the string of the tourist (format: ID_name_telephoneNumber_email: ): ");
			stdOut.flush();
			String str = stdIn.readLine();
			StringTokenizer s = new StringTokenizer(str, delim);
			if (s.countTokens() != 4) {
				stdOut.println(new DataFormatException(str));
				continue;
			} else {
				try {
					IndividualTourist tourist = new IndividualTourist(s.nextToken(), s.nextToken(),
							Long.parseLong(s.nextToken()), s.nextToken());
					return tourist;
				} catch (NumberFormatException nfe) {
					stdOut.println(nfe);
					continue;
				}
			}

		} while (true);
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws NumberFormatException
	 *             从控制台添加ContactPerson
	 */
	private ContactPerson loadContactPersonFromConsole()
			throws IOException, DataFormatException, NumberFormatException {
		do {
			stdErr.print("Input the string of the contact person (Format: name_telephoneNumber_email_jobPosition): ");
			stdErr.flush();
			String str = stdIn.readLine();
			StringTokenizer s = new StringTokenizer(str, delim);
			if (s.countTokens() != 4) {
				stdOut.println(new DataFormatException(str));
				continue;
			} else {
				try {
					ContactPerson person = new ContactPerson(s.nextToken(), Long.parseLong(s.nextToken()),
							s.nextToken(), s.nextToken());
					return person;
				} catch (NumberFormatException nfe) {
					stdErr.println(nfe);
				}
			}
		} while (true);
	}

	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws NumberFormatException
	 * @throws DataFormatException
	 *             运行程序函数
	 */
	private void run() throws IOException, InterruptedException, NumberFormatException, DataFormatException {

		int choice = getChoice();

		while (choice != 0) {
			if (choice == 1) {
				addTourist(loadIndividualTouristFromConsole());
			} else if (choice == 2) {
				lookUpTourist(readID());
			} else if (choice == 3) {
				removeTourist(readID());
			} else if (choice == 4) {
				addContactForTouristGroups(loadContactPersonFromConsole(), readID());
			} else if (choice == 5) {
				lookUpContact(readID(), readName());
			} else if (choice == 6) {
				removeContact(readID(), readName());
			}
			Thread.sleep(1);
			choice = getChoice();
		}
	}

	/**
	 * 
	 * @return String
	 * @throws IOException
	 *             从控制台读取ID
	 */
	private String readID() throws IOException {
		stdOut.print("Input ID: ");
		stdOut.flush();
		return stdIn.readLine();
	}

	/**
	 * 从控制台读取name
	 * 
	 * @return String
	 * @throws IOException
	 */
	private String readName() throws IOException {
		stdOut.print("Input name: ");
		stdOut.flush();
		return stdIn.readLine();
	}

	/**
	 * 从控制台读取要进行的函数
	 * 
	 * @return integer
	 * @throws IOException
	 */
	private int getChoice() throws IOException {

		int input;
		do {
			try {
				stdErr.println();
				stdErr.print("[0] Quit\n" + "[1] Add a tourist into the system\n"
						+ "[2] Look up a tourist given an ID\n" + "[3] Remove a tourist from the system given an ID\n"
						+ "[4] Add a new contact for a tourist group giving an ID\n"
						+ "[5] Look up a contact given the ID of the group and the name of the contact\n"
						+ "[6] Remove a contact given the ID of the group and the name of the contact\n" + "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && input <= 6) {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException nfe) {
				stdErr.println(nfe);
			}
		} while (true);

		return input;
	}

	public static void main(String[] args)
			throws IOException, InterruptedException, DataFormatException, ParseException {
		TouristInformationSystem application = new TouristInformationSystem();
		application.run();
	}

}
