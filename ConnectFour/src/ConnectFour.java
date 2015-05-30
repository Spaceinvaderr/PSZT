import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.*;
import java.util.Random;


public class ConnectFour {

	private Board board;
	private Player player1, player2;
	
	/**
	 * Initialize the board and two players
	 */
	public ConnectFour() {
		board = new Board(6);
		player1 = new HumanPlayer('X'); 
		player2 = new AlphaBetaPlayer('0');
	
		
	}
	
	/**
	 * Run the game between two players and collect statistics
	 */
	public void play() {
		ObjectCounter ctr1 = new ObjectCounter();
		ObjectCounter ctr2 = new ObjectCounter();
		
		Random generator = new Random(); 
		int i = generator.nextInt(10) + 1;
		int j = generator.nextInt(10) + 1;	
		while (j==i){
			j = generator.nextInt(10) + 1;		
		}
		for (int z=0; z<3; z++){
			board = player1.makeB(board, ctr1, j);
			board = player2.makeB(board, ctr1, j);
			board = player2.makeB(board, ctr1, i);
			board = player1.makeB(board, ctr1, i);
			
		}
	
		board.print();
		int p1_moves = 0;
		int p2_moves = 0;
		long p1_time = 0;
		long p2_time = 0;
		while(true) {			
			if (board.isWon('0')) {
				System.out.println("Player 2 wins!");
				break;
			}
			int prev_ctr = ctr1.get();
			long startTime = System.currentTimeMillis();
			board = player1.makeMove(board, ctr1);			
			long endTime = System.currentTimeMillis();
			p1_moves++;
			p1_time += (endTime - startTime);
			board.print();
			System.out.println("Player 1 created " + (ctr1.get()-prev_ctr) + " nodes");
			System.out.println("Player 1 took " + (endTime-startTime) + " milliseconds to move");
			if (board.isWon('X')) {
				System.out.println("Player 1 wins!");
				break;
			}
			prev_ctr = ctr2.get();
			startTime = System.currentTimeMillis();
			board = player2.makeMove(board,ctr2);			
			endTime = System.currentTimeMillis();
			p2_moves++;
			p2_time += (endTime - startTime);
			board.print();
			System.out.println("Player 2 created " + (ctr2.get()-prev_ctr) + " nodes");
			System.out.println("Player 2 took " + (endTime-startTime) + " milliseconds to move");

			
		}
		System.out.println("Player 2: Average nodes generated/move: " + (double)ctr2.get()/p2_moves + " Average time/move: " + (double)p2_time/p2_moves);
		 
	}
	
	public static void main(String[] args) {
		ConnectFour cf = new ConnectFour();
		cf.play();
	}

}
