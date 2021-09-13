package Main;

public class Score {

	private String player;
	private int score;
	
	public Score(String level, int score) {
		this.player = level;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Player:"+player+" - "+"Score:"+score;
	}
	
}
