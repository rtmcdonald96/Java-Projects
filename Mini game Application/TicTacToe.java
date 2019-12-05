import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.color.*;

public class TicTacToe extends JFrame implements ActionListener{
	private final int HEIGHT = 175;
	private final int WIDTH = 455;
	private final int SIZE = 3;
	
	//Variables to keep track of wins, turns, and player name
		private int p1wins;
		private int p2wins;
		int turnCounter;
		private String player1Name;
		private String player2Name;
		Boolean isWinner;
		Boolean isCatsGame;
		
	
	//Panels for BorderLayout
		JPanel topPanel;
		JPanel leftPanel;
		JPanel rightPanel;
		JPanel centerPanel;

	//Labels
		JLabel playerTurn;
		JLabel currentTurn;
		JLabel p1Turn;
		JLabel p2Turn;
		JLabel p1Move;
		JLabel p2Move;
		JLabel scoreboard;
		JLabel p1Name;
		JLabel p2Name;
		
	//Text field to display player score
		JLabel p1Score;
		JLabel p2Score;
		
	//Buttons
		JButton mainMenu;
		JButton quit;
		JButton connectFour;
		JButton newGame;
		
		//Arrays
		JPanel[][]gameBoard;
		JButton[][]playerMoves;
		char[][] btsGameBoard;
	TicTacToe(String n1, String n2)
	{
		super("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setLayout(new BorderLayout());
		player1Name = n1;
		player2Name = n2;
		isWinner = false;
		isCatsGame = false;
		turnCounter = 0;
		gameBoard = new JPanel[SIZE][SIZE];
		playerMoves = new JButton[SIZE][SIZE];
		btsGameBoard = new char[SIZE][SIZE];
//*****************
//Left Panel
//*****************	
		p1wins = 0;
		p2wins = 0;
		scoreboard = new JLabel("Score",SwingConstants.RIGHT);
		p1Name = new JLabel(n1 + "'s Wins:");
		p2Name = new JLabel(n2 + "'s Wins:");
		p1Score = new JLabel(" " + Integer.toString(p1wins));
		p2Score = new JLabel(" " + Integer.toString(p2wins));

				
		leftPanel = new JPanel(new GridLayout(3,2,0,0));
		leftPanel.add(scoreboard);
		leftPanel.add(new JLabel("board"));
		leftPanel.add(p1Name);
		leftPanel.add(p1Score);
		leftPanel.add(p2Name);
		leftPanel.add(p2Score);		
//*****************
//Right Panel
//*****************
		rightPanel = new JPanel();
		mainMenu = new JButton("Main Menu");
		newGame = new JButton("New Game");
		connectFour = new JButton("Connect Four");
		quit = new JButton("Quit");
		
		rightPanel = new JPanel(new GridLayout(4,1,0,0));
		rightPanel.add(newGame);
		rightPanel.add(mainMenu);
		rightPanel.add(connectFour);
		rightPanel.add(quit);
		//ActionListeners
		mainMenu.addActionListener(this);
		quit.addActionListener(this);
		newGame.addActionListener(this);
		connectFour.addActionListener(this);
//*****************
//Top Panel
//*****************	
		topPanel = new JPanel();
		topPanel = new JPanel(new GridLayout(2,3,5,5));
		playerTurn = new JLabel(n1 + "'s Turn", SwingConstants.CENTER);
		p1Turn = new JLabel(n1, SwingConstants.CENTER);
		p2Turn = new JLabel(n2, SwingConstants.CENTER);
		p1Move = new JLabel("X", SwingConstants.CENTER);
		p2Move = new JLabel("O", SwingConstants.CENTER);
		currentTurn = new JLabel("X", SwingConstants.CENTER);

		
		topPanel.add(p1Turn);
		topPanel.add(playerTurn);
		topPanel.add(p2Turn);
		topPanel.add(p1Move);
		topPanel.add(currentTurn);
		topPanel.add(p2Move);
//*****************
//Center Panel
//*****************	
		centerPanel = new JPanel(new GridLayout(SIZE,SIZE, 0, 0));
		for (int i=0; i<SIZE; ++i) 
		{
			for(int j=0; j<SIZE; ++j)
			{
				gameBoard[i][j] = new JPanel();
				playerMoves[i][j] = new JButton();
				btsGameBoard[i][j] = ' ';
				gameBoard[i][j].add(playerMoves[i][j]);
				playerMoves[i][j].setBackground(Color.WHITE);
				playerMoves[i][j].setText(Character.toString(btsGameBoard[i][j]));
				centerPanel.add(gameBoard[i][j]);
				playerMoves[i][j].addActionListener(this);
			}
		}
					
//**************************
//Add panels to BorderLayout
//**************************
		add(topPanel, BorderLayout.NORTH);
		add(rightPanel, BorderLayout.EAST);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		//If quit button the game quits
		if (source == quit)
		{
			super.dispose();
		}		
		//Calls the mainMenu constructor
		else if (source == mainMenu)
		{
			MenuOptions frame = new MenuOptions();
			frame.setVisible(true);
			super.dispose();
		}
		//Calls the connect four Constructor
		else if (source == connectFour)
		{
			ConnectFour frame = new ConnectFour(player1Name, player2Name);
			frame.setVisible(true);
			super.dispose();
		}
		//Resets the gameboard
		else if (source == newGame)
		{
			newGame();
		}
		//Makes the move on the correct spot if it does not already
		//have a character in the spot
		if(isWinner == false && isCatsGame == false){
			if (source == playerMoves[0][0])
			{
				makeMove(0,0);
			}
			if (source == playerMoves[0][1])
			{
				makeMove(0,1);
			}
			if (source == playerMoves[0][2])
			{
				makeMove(0,2);
			}
			if (source == playerMoves[1][0])
			{
				makeMove(1,0);
			}
			if (source == playerMoves[1][1])
			{
				makeMove(1,1);
			}
			if (source == playerMoves[1][2])
			{
				makeMove(1,2);
			}
			if (source == playerMoves[2][0])
			{
				makeMove(2,0);
			}
			if (source == playerMoves[2][1])
			{
				makeMove(2,1);
			}
			if (source == playerMoves[2][2])
			{
				makeMove(2,2);
			}
			//Checks if there is either a cats game or a winner after the moves 
			//have been made
			checkCatsGame();
			checkWinner();
			
		}
	}
private void newGame() {
	for (int i=0; i<SIZE; ++i) 
	{
		for(int j=0; j<SIZE; ++j)
		{
			btsGameBoard[i][j] = ' ';
			playerMoves[i][j].setText(Character.toString(btsGameBoard[i][j]));
			isWinner = false;
			isCatsGame = false;
			turnCounter = 0;
			playerTurn.setText(player1Name + "'s Turn");
			currentTurn.setText("X");
		}
	}
}
private void makeMove(int row, int col)
{	
	if(btsGameBoard[row][col] == ' ')
	{
		if (turnCounter % 2 == 0)
		{
			btsGameBoard[row][col] = 'X';
			playerMoves[row][col].setText("X");
			turnCounter +=1;
			playerTurn.setText(player2Name + "'s Turn");
			currentTurn.setText("O");
		}
	else
		{
		btsGameBoard[row][col] = 'O';
		playerMoves[row][col].setText("O");
		turnCounter +=1;
		playerTurn.setText(player1Name + "'s Turn");
		currentTurn.setText("X");
		}
	}
}
private void checkWinner() {

	    if(
	        //Checks Diagonals for winner
	    	
	        (btsGameBoard[0][0] != ' ' &&
	        btsGameBoard[0][0] == btsGameBoard[1][1]) && 
	        (btsGameBoard[0][0] == btsGameBoard[2][2])
	            ||
	        ((btsGameBoard[2][0] != ' ' &&
	        btsGameBoard[2][0] == btsGameBoard[1][1]) && 
	        (btsGameBoard[2][0] == btsGameBoard[0][2]))
	            ||
	        //Checks Rows for winner
	        (btsGameBoard[0][0] != ' ') && 
	        (btsGameBoard[0][0] == btsGameBoard[0][1]) && 
	        (btsGameBoard[0][0] == btsGameBoard[0][2])
	            ||
	        (btsGameBoard[1][0] != ' ') && 
	        (btsGameBoard[1][0] == btsGameBoard[1][1]) && 
	        (btsGameBoard[1][0] == btsGameBoard[1][2])
	            ||
	        (btsGameBoard[2][0] != ' ') && 
	        (btsGameBoard[2][0] == btsGameBoard[2][1]) && 
	        (btsGameBoard[2][0] == btsGameBoard[2][2])
	            ||
	        //Checks Column for winners
	        (btsGameBoard[0][0] != ' ' &&
	        btsGameBoard[0][0] == btsGameBoard[1][0]) && 
	        (btsGameBoard[0][0] == btsGameBoard[2][0])
	            ||
	        (btsGameBoard[0][1] != ' ' &&
	        btsGameBoard[0][1] == btsGameBoard[1][1]) && 
	        (btsGameBoard[0][1] == btsGameBoard[2][1])
	            ||
	        (btsGameBoard[0][2] != ' ' &&
	        btsGameBoard[0][2] == btsGameBoard[1][2]) && 
	        (btsGameBoard[0][2] == btsGameBoard[2][2]))
	    {
	        setWinner();
	    }
	    
}
private void checkCatsGame() 
{
	for (int i=0; i<SIZE; i++)
	{
		for (int j=0; j<SIZE; ++j)
		{
		if(btsGameBoard[i][j] == ' ')
		{
			isCatsGame = false;
			return;
		}
		}
	}
	setCatsGame();
}
private void setCatsGame()
{
	isCatsGame = true;
	playerTurn.setText("Cat's Game!");
	currentTurn.setText("Start a new game");
}

private void setWinner()
{
	if (turnCounter % 2 == 1)
	{

		p1wins +=1;	
		playerTurn.setText(player1Name + " wins!!");
		p1Score.setText(Integer.toString(p1wins));
		currentTurn.setText("");
		
	}
	else if (turnCounter % 2 == 0)
	{		
		p2wins +=1;
		playerTurn.setText(player2Name + " wins!!");
		p2Score.setText(Integer.toString(p2wins));
		currentTurn.setText("");
	}
	isWinner = true;
	}
}
