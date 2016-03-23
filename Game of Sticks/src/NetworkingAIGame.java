
public class NetworkingAIGame extends NetworkingAI
{
	Memory trained;
	Trained train;

	public NetworkingAIGame(String host) {
		super(host);
		trained = train.training();
		trained.setName("Trained Computer");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		Trained train = new Trained();
		train.play();
	}

}
