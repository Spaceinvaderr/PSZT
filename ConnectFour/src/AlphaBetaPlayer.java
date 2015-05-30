import java.util.ArrayList;

public class AlphaBetaPlayer extends Player {
	/**
	 * Initialize the Player with the symbol to play with
	 * @param symbol
	 */
	public AlphaBetaPlayer(char symbol) {
		super(symbol);
	}
	/**
	 * Makes a move using the minimax algorithm with alpha beta pruning
	 * @return Board after move has been played
	 */
	public Board makeMove(Board board, ObjectCounter ctr) {
		BoardNode bn = new BoardNode(board, 0, symbol, symbol, true,ctr);
		ArrayList<BoardNode> nodes = bn.getChildren();
		int maximum_eval = Integer.MIN_VALUE;
		int max_index = 0;
		for (int i = 0; i < nodes.size(); i++) {
			int evaluation = nodes.get(i).evaluate(Integer.MIN_VALUE);
			System.out.println("VAR " + evaluation);

			if (evaluation > maximum_eval) {
				maximum_eval = evaluation;
				max_index = i;
			}
		}
		board = nodes.get(max_index).getBoard();
		return board;
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
}
