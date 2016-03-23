import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Game class for player versus AI
 * @author Brian
 *
 */
public class AI extends Human //takes methods and variables from the human class
{
	Memory memory; //memory of the computer
	boolean compWin; //computer wins
	public AI()
	{
		super(); //initializes variables
		compWin = false; //ensure the computer hasn't won yet
	}
	
	/**
	 * Method that starts the player vs AI game
	 */
	public void playing()
	{
		this.memory = Game.AIComp; //makes sure the saved memory is being used
		
		while (play)
		{
			turn(Game.name); //player's turn
			if (isLost() == true) //game over
			{
				Game.AIWin++;
				play = false;
				compWin = true;
				System.out.println("You lost the game, " + Game.name + "!");
			}
			else
			{
				computer(Game.AIComp); //game's turn
				if (isLost() == true)
				{
					Game.playerWin++;
					play = false;
					System.out.println("You won the game, " + Game.name + "!");
				}
			}
		}
		if (compWin == true)
		{
			buildList(memory); //builds to the memory if the AI wins
		}
		else if(compWin == false)
		{
			removeList(memory); //removes faulty numbers from the AI memory
		}
		Game.AIComp = memory; //updates the memory
		//scoreboard
		System.out.println("Score: " + Game.name + ": " + Game.playerWin + "\nScore: " + memory.name() + ": " + Game.AIWin);
	}

	/**
	 * Code for the AI of the computer
	 * @param memory The hat memory for each given turn
	 */
	public void computer(Memory memory)
	{
		Integer[] options = memory.content().get(pile-1); //access the specific hat of numbers for the given pile size
		int length = 0;
		for (int i = 0; i < options.length; i++) //counts how many valid numbers are in the range in the hat to avoid NullPointer
		{
			if (options[i] != null)
			{
				length++;
			}
		}
		int position = (int)(Math.random()*length); //random position number of where to pick from the hat
		int nextMove = options[position]; //number at the hat position
		memory.besides()[pile-1] = nextMove; //keeps the number in mind to see whether to add it or remove it
		if (pile - nextMove >= 1) //changes the dialogue depending on the pile and sticks taken
		{
			System.out.println(memory.name() + " has taken " + nextMove + " from the pile of " + pile);
		}
		else
		{
			System.out.println(memory.name() + " has taken the last stick.");
		}
		pile -= nextMove;
		if (pile > 1)
		{
			System.out.println("There are " + pile + " sticks left.");
		}
		else if (pile == 1)
		{
			System.out.println("There is 1 stick left.");
		}
		else
		{
			System.out.println("There are no sticks left.");
		}
		if (isLost() == true)
		{
			System.out.println(memory.name() + " has lost the game.");
		}
	}
	
	/**
	 * Builds each ArrayList from results of the previous game
	 * @param memory Builds onto the given memory for the computer
	 */
	public void buildList(Memory memory)
	{
		for(int i = 0; i < 20; i++) //goes into each hat
		{
			if (memory.besides()[i] != null) //looks for non-null spaces
			{
				for(int j = 0; j < memory.content().get(i).length; j++) //increments for each Array in the ArrayList
				{
					if (memory.content().get(i)[j] == null && memory.besides()[i] != null) //checks if the slot in the Array is empty and that the modifying array has an Integer
					{
						memory.content().get(i)[j] = memory.besides()[i]; //puts in the Integer
						memory.besides()[i] = null; //removes from the position where the Integer came from
						ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(memory.content().get(i))); //creates an ArrayList of the Array being modified
						for (int k = 0; k < temp.size(); k++)
						{
							memory.content().get(i)[k] = temp.get(k);//recreates the initial Array to avoid nulls
						}
					}
				}
			}	
			/*for(Integer number : memory.content().get(i)) for testing purposes
			{
				System.out.print(number + " ");
			}
			System.out.println();*/
		}
		Integer[] fresh = new Integer[20]; //new Array to store Integers
		memory.newBesides(fresh);
		this.memory = memory; //updates the memory
	}
	
	/**
	 * Removes the elements chosen during that round if the computer loses
	 * @param memory The memory that the computer is using
	 */
	public void removeList(Memory memory)
	{
		ArrayList<Integer[]> storage = new ArrayList<Integer[]>(); //stores reassorted lists
		for (int i = 0; i < memory.content().size() ; i++) //checks for all Arrays in the ArrayList
		{
			for (int j = 0; j < memory.content().get(i).length; j++) //checks for each Array in the ArrayList
			{
				if (memory.content().get(i)[j] == memory.besides()[i]) //removes if the number at the position is the same as the chosen
				{
					memory.content().get(i)[j] = null;
					memory.besides()[i] = null;
					break;
				}
			}
		}
		for (Integer[] list : memory.content()) //looks at each Array in the ArrayList
		{
			ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(list)); //changes each Array to an ArrayList for sorting purposes
			//adds 1,2 or 3 is they have been removed so that all are still possibilities
			if (!temp.contains(1))
			{
				temp.add(1);
			}
			else if (!temp.contains(2))
			{
				temp.add(2);
			}
			else if (!temp.contains(3))
			{
				temp.add(3);
			}
			list = new Integer[100]; //makes a clean Array so that all Integers are consecutive
			ArrayList<Integer> clear = new ArrayList<Integer>(); //ArrayList with no nulls that can be sorted
			for (int i = 0; i < temp.size(); i++)
			{
				if(temp.get(i) != null) //adds each non-null element into the clear ArrayList
				{
					clear.add(temp.get(i));
				}
			}
			Collections.sort(clear); //sorts
			
			for (int i = 0; i < clear.size(); i++)
			{
				list[i] = clear.get(i); //converts each list
			}
			
			/*for (Integer number : list) for testing purposes
			{
				System.out.print(number + " ");
			}
			System.out.println();*/
			storage.add(list); //add Adds each Array into a storage ArrayList
		}
		memory.newContent(storage); //sets the memory to be what was in the storage
		Integer[] fresh = new Integer[20]; //new Array to store Integers
		memory.newBesides(fresh); //sets the besides Array to an empty Array
		this.memory = memory; //updates the memory
	}
	
	/**
	 * Prints out the elements of each Array in the memory ArrayList
	 * @param memory Input the memory for where the Arrays you want to see are
	 */
	public void printHat(Memory memory)
	{
		for (int i = 0; i < 20; i++)
		{
			System.out.print(i + " :");
			for(Integer number : memory.content().get(i))		
			{
				System.out.print(number + " ");
			}
				System.out.println();
		}
	}
}
