import java.util.ArrayList;

public class MinimaxPlayer extends Player {

	public MinimaxPlayer(char symbol) {
		super(symbol);
	}
	/**
	 * Makes a move using the minimax algorithm
	 */
	public Board makeMove(Board board, ObjectCounter ctr) {
		BoardNode bn = new BoardNode(board, 0, symbol, symbol, false, ctr);
		ArrayList<BoardNode> nodes = bn.getChildren();
		int maximum_eval = Integer.MIN_VALUE;
		int max_index = 0;
		for (int i = 0; i < nodes.size(); i++) {
			int evaluation = nodes.get(i).evaluate(Integer.MIN_VALUE);	
			if (evaluation > maximum_eval) {
				maximum_eval = evaluation;
				max_index = i;
			}
		}
		board = nodes.get(max_index).getBoard();
		return board;
	}
	
}
