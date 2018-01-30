
public class Coffee {
	private int id;
	private String name;
	private String taste;
	private double cost;
    /**
     * 
     * @param initialId
     * @param initialName
     * @param initialTaste
     * @param initialcost
     */
	public Coffee(int initialId, String initialName, String initialTaste, double initialcost) {
		this.id = initialId;
		this.name = initialName;
		this.taste = initialTaste;
		this.cost = initialcost;
	}
    /**
     * 
     * @return name
     */
	public String getName() {
		return this.name;
	}
    /**
     * 
     * @return taste
     */
	public String getTaste() {
		return this.taste;
	}
    /**
     * 
     * @return id
     */
	public int getId() {
		return this.id;
	}
    /**
     * 
     * @return cost
     */
	public double getCost() {
		return this.cost;
	}

	public String toString() {
		return "Coffee id: " + this.id + "\ttaste: " + this.taste + "\tname: " + this.name + "\tcost: " + this.cost
				+ "\r\n";
	}
}
