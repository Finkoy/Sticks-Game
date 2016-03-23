
public class Watch extends AI //uses methods and variables from the AI class
{
	boolean comp1Win; //determines the winning computer
	boolean comp2Win;
	public Watch()
	{
		super(); //initializes variables
		comp1Win = false;
		comp2Win = false;
	}
	
	public void playing()
	{
		while (play)
		{
			computer(Game.comp1); //computer 1's turn
			if (isLost() == true)
			{
				play = false;
				comp1Win = false;
				comp2Win = true;
				Game.comp2Win++;
				System.out.println("Computer 2 has won the game!");
			}
			else
			{
				computer(Game.comp2); //computer 2's turn
				if (isLost() == true)
				{
					play = false;
					comp1Win = true;
					comp2Win = false;
					Game.comp1Win++;
					System.out.println("Computer 1 has won the game!");
				}
			}
		}
		//modifies the memories of both computers depending on results
		if (comp1Win == true)
		{
			buildList(Game.comp1);
			removeList(Game.comp2);
			
		}
		else if(comp1Win == false)
		{
			buildList(Game.comp2);
			removeList(Game.comp1);
		}
		System.out.println("Score: " + Game.comp1.name() + ": " + Game.comp1Win + "\nScore: " + Game.comp2.name() + ": " + Game.comp2Win);
	}
}
