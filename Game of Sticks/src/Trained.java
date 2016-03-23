/**
 * Creates a game against a trained AI
 * @author Brian
 *
 */
public class Trained extends Watch //uses the same methods and variables in Watch
{
	Memory memory; //memory of the trained AI
	/**
	 * Empty constructor
	 */
	public Trained()
	{
		
	}
	
	/**
	 * Starts the game
	 * (look at comments for AI class)
	 */
	public void play()
	{
		this.memory = Game.trained; //updates memory
		while (play)
		{
			turn(Game.name);
			if (isLost() == true)
			{
				Game.trainedWin++;
				play = false;
				compWin = true;
				System.out.println("You lost the game, " + Game.name + "!");
			}
			else
			{
				computer(Game.trained);
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
			buildList(memory);
		}
		else if(compWin == false)
		{
			removeList(memory);
		}
		Game.trained = memory;
		System.out.println("Score: " + Game.name + ": " + Game.playerWin + "\nScore: " + memory.name() + ": " + Game.trainedWin);
	}
	
	/**
	 * Trains a computer to play against
	 * @return The memory of the trained AI
	 */
	public Memory training()
	{
		for (int i = 0; i < 5000; i++)
		{
			new Watch().playing(); //runs AI vs AI 5000 times to train
		}
		//returns the more competent computer
		if(Game.comp1Win > Game.comp2Win)
		{
			System.out.println("The computer has been trained!");
			return Game.comp1;
		}
		else if (Game.comp1Win < Game.comp2Win)
		{
			System.out.println("The computer has been trained!");
			return Game.comp2;
		}
		else
		{
			System.out.println("The computer has been trained!");
			return Game.comp1;
		}
	}
}
