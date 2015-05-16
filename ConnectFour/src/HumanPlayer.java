import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

	public HumanPlayer(char symbol) {
		super(symbol);
	}
	
	/**
	 * Asks user to enter a column to play the next move
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
