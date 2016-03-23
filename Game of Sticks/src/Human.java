import java.util.*;
/**
 * The game class for human versus humans
 * @author Brian
 *
 */
public class Human
{
	int pile; //number of total sticks in the game
	int pick; //the amount the user chooses to pick
	boolean play; //determines if the game is ongoing
	String name2; //name of player 2
	Scanner scan;
	public Human()
	{
		pile = 20;//number of sticks per game
		scan = new Scanner(System.in);
		play = true; //true if the game is still running.
	}
	
	/**
	 * method that runs the player vs player game and determines wins and loses
	 */
	public void playing()
	{
		System.out.println("What is the second player's name?");
		name2 = scan.next();
		while (play) //play is true as long as the game is running
		{
			turn(Game.name);
			if (isLost() == true) //game over
			{
				Game.player2Win++; //counter
				play = false;
			}
			else //game over
			{
				turn(name2);
				if (isLost() == true)
				{
					Game.player1Win++; //counter
					play = false;
				}
			}
		}
		//scoreboard
		System.out.println("Score: " + Game.name + ": " + Game.player1Win + "\nScore: " + name2 + ": " + Game.player2Win);
	}
	
	/**
	 * Method that determines the turn for each player
	 * @param s The name of the player
	 */
	public void turn(String s)
	{
		System.out.println("It is your turn, " + s + ". How many sticks are you picking up?");
		pick = scan.nextInt(); //only of type int works
		if (pick > 0 && pick < 4) //within the range from 1-3
		{
			if (pile - pick >= 1) //changes dialogue based on the number of sticks left
			{
				System.out.println("You have taken " + pick + " from the pile of " + pile + ".");
			}
			else
			{
				System.out.println("You have taken the last stick.");
			}
			pile -= pick; //removes from pile
			if (pile > 1)
			{
				System.out.println("There are " + pile + " sticks left."); //tells user the pile
			}
			else if (pile == 1)
			{
				System.out.println("There is 1 stick left."); //tells the user that 1 stick remains
			}
			else
			{
				System.out.println("There are no sticks left.");
			}
			if (isLost() == true) //game over
			{
				System.out.println(s + " has lost the game.");//displays who lost
			}
		}
		else //the pick was higher than 3 or less than 1
		{
				System.out.println("That's an invalid response. Pick from 1 to 3 sticks.");
				turn(s);
		}
	}
	
	/**
	 * ends the game
	 * @return true if a player picks up the last stick
	 */
	public boolean isLost()
	{
		if (pile  < 1) //no more sticks are left
		{
			return true;
		}
		return false;
	}
}
