import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ConnectFour extends JPanel
{
	private static final long serialVersionUID = 1L;
	
    /** current game dummy */
	private Board currentBoard;
    private Player player1, player2;
    
	/** references to the images */
	private Map<Board, Image> fieldImage;
	private Map<HumanPlayer, Image> playerImage;
	private Map<MinimaxPlayer, Image> enemyImage;
	
	/**
	 * Initialize the currentBoard and two players
	 */
	public ConnectFour() {
		currentBoard = new Board(6);
		player1 = new Map<HumanPlayer, playerImage>; // can be AlphaBetaPlayer, MinimaxPlayer or HumanPlayer
		player2 = new AlphaBetaPlayer('0');
	}
	
	/**
	 * Run the game between two players and collect statistics
	 */
	public void play() {
		ObjectCounter ctr1 = new ObjectCounter();
		ObjectCounter ctr2 = new ObjectCounter();
		currentBoard.print();
		int p1_moves = 0;
		int p2_moves = 0;
		long p1_time = 0;
		long p2_time = 0;
		while(true) {			
			if (currentBoard.isWon('0')) {
				System.out.println("Player 2 wins!");
				break;
			}
			int prev_ctr = ctr1.get();
			long startTime = System.currentTimeMillis();
			currentBoard = player1.makeMove(currentBoard, ctr1);			
			long endTime = System.currentTimeMillis();
			p1_moves++;
			p1_time += (endTime - startTime);
			currentBoard.print();
			System.out.println("Player 1 created " + (ctr1.get()-prev_ctr) + " nodes");
			System.out.println("Player 1 took " + (endTime-startTime) + " milliseconds to move");
			if (currentBoard.isWon('X')) {
				System.out.println("Player 1 wins!");
				break;
			}
			prev_ctr = ctr2.get();
			startTime = System.currentTimeMillis();
			currentBoard = player2.makeMove(currentBoard,ctr2);			
			endTime = System.currentTimeMillis();
			p2_moves++;
			p2_time += (endTime - startTime);
			currentBoard.print();
			System.out.println("Player 2 created " + (ctr2.get()-prev_ctr) + " nodes");
			System.out.println("Player 2 took " + (endTime-startTime) + " milliseconds to move");
		}
		System.out.println("Player 1: Average nodes generated/move: " + (double)ctr1.get()/p1_moves + " Average time/move: " + (double)p1_time/p1_moves);
		System.out.println("Player 2: Average nodes generated/move: " + (double)ctr2.get()/p2_moves + " Average time/move: " + (double)p2_time/p2_moves);
	}
	
	public static void main(String[] args) {
		ConnectFour cf = new ConnectFour();
		cf.play();
	}

}
