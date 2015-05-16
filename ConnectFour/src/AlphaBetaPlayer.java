import java.util.ArrayList;

public class AlphaBetaPlayer extends Player {
	ic AlphaBetaPlayer(char symbol) {
		super(symbol);
	}
	/**
	 * moves using the minimax algorithm with alpha beta pruning
	 */
	public Board makeMove(Board board, ObjectCounter ctr) {
		BoardNode bn = new BoardNode(board, 0, symbol, symbol, true,ctr);
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
