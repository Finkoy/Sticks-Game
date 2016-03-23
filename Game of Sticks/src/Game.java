import java.util.*;

/**
 * Class that creates all of the game objects
 * and asks user for input information to prepare the game
 * @author Brian
 *
 */
public class Game extends NetworkingAI
{
	Scanner scan;
	static Memory AIComp; //hat memory for player vs AI
	static Memory comp1;  //memory for AI vs AI
	static Memory comp2;  //memory for AI vs AI
	static Memory trained; //memory for the trained computer
	static int playerWin; //counter for player win in player vs AI
	static int player1Win; //counter for player 1 win in player vs player
	static int player2Win; //counter for player 2 win in player vs player
	static int AIWin; //counter for AI win in player vs AI
	static int comp1Win; //counter for AI1 win in AI vs AI
	static int comp2Win; //counter for AI2 win in AI vs AI
	static int trainedWin; //counter for wins of the trained computer
	static String name; //name of the main player
	
	/*
	 * Constructor
	 * Gets user information
	 */
	public Game(String host)
	{
		super(host); //for networking purposes
		scan = new Scanner(System.in); 
		System.out.println("Hello, what is your name?");
		name = scan.next();
		//rules
		System.out.println("Welcome, " + name + ", to the game of sticks! \nTo play the game, read the following rules: \nOut of a pile of sticks, you can chose \nto pick up 1,2 or 3 sticks. \nTwo players take turns picking them up. \nThe person to pick up the last stick out of the 20, loses! \nDo you want to play the game? \nType in yes or no.");
		String response = scan.next(); //run the game or to not
		boolean wrong = true; //response has not yet been correct to continue
		while(wrong) //loops if response is not yes or no
		{
			if (response.equalsIgnoreCase("yes"))
			{//prepares all of the AI's
				Trained train = new Trained();
				AIComp = new Memory("Computer");
				comp1 = new Memory("Computer 1");
				comp2 = new Memory("Computer 2");
				trained = train.training();
				trained.setName("Trained Computer");
				Start(); //starts the game
				wrong = false;
			}
			else if (response.equalsIgnoreCase("no"))
			{
				System.out.println("That's a shame. See you next time!");
				wrong = false;
				System.exit(0);//closes the game
			}
			else //reprompts for a proper response
			{
				System.out.println("Please enter in yes or no.");
				response = scan.next();
			}
		}
	}
	
	/*
	 * Method to start the game
	 */
	public void Start()
	{
		boolean playing = true; //ends the while loop once an appropriate response is reached
		System.out.println("Do you wish to play versus another player, an AI,\nwatch AI vs AI, play against a \ntrained computer or end the game? \nType 'player' for another player. \nType 'AI' to play against an AI. \nType 'watch' to watch two AI play.\nType 'trained' to play against a trained computer.\nType 'bye' to end the game.");
		String response = scan.next();
		while (playing)//loop that ends after getting proper response
		{
			if (response.equalsIgnoreCase("player"))
			{
				new Human().playing();
				playing = false;
			}
			else if(response.equalsIgnoreCase("AI"))
			{
				new AI().playing();
				playing = false;
			}
			else if(response.equalsIgnoreCase("watch"))
			{
				new Watch().playing();
				playing = false;
			}
			else if(response.equalsIgnoreCase("trained"))
			{
				Trained train = new Trained();
				train.play();
				train.printHat(trained);
				playing = false;
			}
			else if(response.equalsIgnoreCase("bye"))
			{
				System.exit(0);//closes the game
			}
			else //loops if improper response
			{
				System.out.println("Please type in 'player', 'AI', 'watch', 'trained' or 'bye'.");
				response = scan.next();
			}
			Start();
		}
		
	}

	/*
	 * for network purposes only
	 */
	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		new Trained().play();
	}
	
}
