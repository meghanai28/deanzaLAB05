/**
 * Lab 02
 * @author Lab Group 7: Meghana Indukuri & Joseph Khamisy
 * This program creates the class Dollar which extends Currency with overriden methods and useful constructors.
 * July 11th 2022
 */
public class Dollar extends Currency {
	private String name; // name of currency
	
	/**
	 * Constructor that intializes fields by calling super constructor and giving name a value.
	 * @precondition 
	 * @postcondition intializes private fields
	 */
	public Dollar ()
	{
		super();
		name = "Dollar";
	}
	/**
	 * Constructor that intializes fields by calling super constructor.
	 * @precondition val - a non-negative double that is used to represent a dollar value
	 * @postcondition intializes private fields
	 * @throws Exception when value is a negative number
	 */
	public Dollar (double val) throws Exception
	{
		super(val);
		name = "Dollar";
	}
	
	/**
	 * Constructor that intializes private fields copying another object currency.
	 * @precondition copy - the dollar object to be copied. must be of type dollar that is being initalized.
	 * @postcondition private fields initalized
	 * @throws Exception is thrown when inputed currency is not same type as object invoked on.
	 */
	public Dollar (Dollar copy) throws Exception
	{
		super(copy);
		this.name = copy.name;
	}
	
	/**
	 * A getter method to get the value of the private field name
	 * @precondition
	 * @postcondition
	 * @return returns String name
	 */
	public String getName()
	{
		return name;
	}
	
	
	/**
	 * An overidden method, that formats dollar value into a string with common currency notation.
	 * @precondition
	 * @postcondition
	 * @return a string formatted using the value of the dollar object
	 */
	public String toString() {
		// TODO Auto-generated method stub
		String value = getCurrNoteVal() + "." + String.format("%02d", getCurrCoinVal()) + " " + getName();
		return value;
	}
	
	
}
