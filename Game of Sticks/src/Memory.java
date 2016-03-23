import java.util.ArrayList;

/**
 * A memory storage system for each element for each turn for the game
 * @author Brian
 *
 */
public class Memory 
{
	Integer[] besides; //Array of numbers chosen
	ArrayList<Integer[]> content; //ArrayList of Arrays of Integers the computer can chose from
	String name; //name of the memory for the computer
	Memory(String name)
	{
		this.name = name; //gives the name
		besides = new Integer[20];
		content = new ArrayList<Integer[]>();
		for (int i = 0; i < besides.length; i++)
		{
			//adds in default values for each Array
			Integer[] list = new Integer[100];
			list[0] = 1;
			list[1] = 2;
			list[2] = 3;
			content.add(list); //adds each Array into the ArrayList
		}
	}
	
	/**
	 * Finds the name of the computer
	 * @return The name of the computer
	 */
	public String name()
	{
		return name;
	}
	
	/**
	 * Finds the ArrayList of Arrays
	 * @return the ArrayList of Arrays
	 */
	public ArrayList<Integer[]> content()
	{
		return content;
	}
	
	/**
	 * The Array of chosen Integers
	 * @return the Array
	 */
	public Integer[] besides()
	{
		return besides;
	}
	
	/**
	 * Sets the name of the computer
	 * @param name Name the computer
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the Array of chosen Integers
	 * @param besides An array of select Integers
	 */
	public void newBesides(Integer[] besides)
	{
		this.besides = besides;
	}
	
	/**
	 * Sets a new ArrayList of Arrays of Integers
	 * @param content The possibilities in which the computer can chose from
	 */
	public void newContent(ArrayList<Integer[]> content)
	{
		this.content = content;
	}
}
