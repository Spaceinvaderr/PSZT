import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {
	
	/**
	 * Initialize the player with the symbol that is being used for playing
	 * @param symbol
	 */
	public HumanPlayer(char symbol) {
		super(symbol);
	}
	
	public Board makeB(Board board, ObjectCounter ctr, int col) {
		while (true) {
			try {
				int column = col;
				column--;
				boolean validity = board.checkDropValidity(column);
				if (!validity) {
					continue;
				}
				board.dropDisk(this.symbol, column);
				break;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	

	
	/**
	 * Asks user to enter a column to play the next move
	 * @return Board after the move has been played
	 */
	public Board makeMove(Board board, ObjectCounter ctr) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		while (true) {
			System.out.print("next move (1-9): ");
			try {
				int column = Integer.parseInt(br.readLine());
				column--;
				boolean validity = board.checkDropValidity(column);
				if (!validity) {
					System.out.println("Invalid column!");
					continue;
				}
				board.dropDisk(this.symbol, column);
				break;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return board;
	}
}
