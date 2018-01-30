
public class Rectangle implements XMLElement, Shape {
	private double height;
	private double width;

	/**
	 * @param initHeight
	 * @param initWidth
	 */
	public Rectangle(double initHeight, double initWidth) throws IllegalArgumentException {
		if (initHeight <= 0 || initWidth <= 0) {
			throw new IllegalArgumentException("the parameter is negative!");
		}
		height = initHeight;
		width = initWidth;
	}

	/**
	 * the construction function is used for no formal parameter
	 */
	public Rectangle() {
		height = 0;
		width = 0;

	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(double height) throws IllegalArgumentException {
		if (height <= 0) {
			throw new IllegalArgumentException("the parameter is negative!");
		}
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(double width) {
		if (width <= 0) {
			throw new IllegalArgumentException("the parameter is negative!");
		}
		this.width = width;
	}

	/**
	 * judge the two object by their area, if two areas are equal then return
	 * true, if not return false
	 * 
	 * @param otherObject
	 * @return boolean
	 */
	public boolean equal(Object otherObject) {
		if (otherObject instanceof Rectangle) {
			Rectangle otherRectangle = (Rectangle) otherObject;
			if (this.computeArea() == otherRectangle.computeArea()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return area
	 */
	public double computeArea() {
		return this.height * this.width;
	}

	/**
	 * using the XML format to print the information of the object
	 * 
	 * @return String
	 */
	public String getXML() {
		String XMLFormat = new String();
		XMLFormat = "<rectangle>\n";
		XMLFormat = XMLFormat + "<height>" + this.getHeight() + "</heigth>\n";
		XMLFormat = XMLFormat + "<width>" + this.getWidth() + "</width>\n";
		XMLFormat = XMLFormat + "</rectangle>\n";
		return XMLFormat;
	}

	public static void main(String[] args) {
		Rectangle rectangle1 = new Rectangle(2.1, 2.0);
		Rectangle rectangle2 = new Rectangle(2.1, 2.0);
		System.out.println(rectangle1.getXML());
		System.out.println(rectangle1.equal(rectangle2));
		// Rectangle rectangle3 = new Rectangle();
		// Rectangle rectangle4 = new Rectangle();
		// rectangle3.setHeight(-0.1);
		// rectangle3.setWidth(0.1);
		// rectangle4.setHeight(0.1);
		// rectangle4.setWidth(-0.1);
		// Rectangle rectangle5 = new Rectangle(-2.1, 2.1);

	}

}
