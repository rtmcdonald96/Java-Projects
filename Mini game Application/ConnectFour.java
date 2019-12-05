import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.color.*;

public class ConnectFour extends JFrame implements ActionListener {
//Finals for constants
	private final int HEIGHT = 500;
	private final int WIDTH = 659;
	private final int COL = 7;
	private final int ROW = 6;
	private final int GAP = 0;

//Variables to keep track of wins, turns, and player name
	private int p1wins;
	private int p2wins;
	int turnCounter;
	private String player1Name;
	private String player2Name;
	
//Boolean value for if there is a winner
	Boolean isWinner;

//Images for populating JFrame
	ImageIcon white;
	Image whiteTransformed;

	ImageIcon blue;
	Image blueTransformed;

	ImageIcon blue2;
	Image blue2Transformed;
	
	ImageIcon red;
	Image redTransformed;
	
	ImageIcon red2;
	Image red2Transformed;
	
	ImageIcon arrow;
	Image arrowTransformed;
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
	JLabel p1Color;
	JLabel p2Color;
	JLabel scoreboard;
	JLabel p1Name;
	JLabel p2Name;
	
//Text field to display player score
	JLabel p1Score;
	JLabel p2Score;
	Color TurnJustTaken;
//Buttons
	JButton mainMenu;
	JButton quit;
	JButton ticTacToe;
	JButton newGame;
	
//Arrays so game can function
	Boolean[] colMax;
	Color[][] colorArray;
	JPanel[][] gameBoard;
	JLabel[][] labelArray;
	JButton[] buttonArray;
	int[] colHeight;

	ConnectFour(String n1, String n2)
	{
		//Settings for JFrame
		super("Connect Four");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		//setResizable(false);
		setLayout(new BorderLayout());
		
		//Initialize gameplay values
		turnCounter = 0;
		player1Name = n1;
		player2Name = n2;
		isWinner = false;
//****************************
//Initialize Images
//****************************
		white = new ImageIcon("WhiteCircle.png");
		whiteTransformed = white.getImage().getScaledInstance
				(50, 50, java.awt.Image.SCALE_SMOOTH);
			
		red = new ImageIcon("RedCircle.png");
		redTransformed = red.getImage().getScaledInstance
				(50, 50, java.awt.Image.SCALE_SMOOTH);
		
		red2 = new ImageIcon("newRed.png");
		red2Transformed = red2.getImage().getScaledInstance
			(50, 50, java.awt.Image.SCALE_SMOOTH);
	
		blue = new ImageIcon("BlueCircle.png");
		blueTransformed = blue.getImage().getScaledInstance
				(50, 50, java.awt.Image.SCALE_SMOOTH);
		blue2 = new ImageIcon("newBlue.png");
		blue2Transformed = blue2.getImage().getScaledInstance
				(50, 50, java.awt.Image.SCALE_SMOOTH);
		
		arrow = new ImageIcon("Arrow.png");
		arrowTransformed = arrow.getImage().getScaledInstance
				(50, 50, java.awt.Image.SCALE_SMOOTH);
		
//*****************
//Center Panel
//*****************
		gameBoard = new JPanel[ROW][COL];
		labelArray = new JLabel[ROW][COL];
		buttonArray = new JButton[COL];
		colHeight = new int[COL];
		colorArray = new Color[ROW][COL];
		colMax = new Boolean[COL];
		
		centerPanel = new JPanel(new GridLayout(COL,ROW+1, 0, 0));
		for(int i=0; i<COL; ++i) {
			buttonArray[i] = new JButton();
			buttonArray[i].setIcon(new ImageIcon(arrowTransformed));
			buttonArray[i].setBackground(Color.BLACK);
			centerPanel.add(buttonArray[i]);
			colHeight[i]=5;
			colMax[i] = false;
			buttonArray[i].addActionListener(this);
		}

		for (int i=0; i<ROW; ++i)
		{
			for(int j=0; j<COL; ++j)
			{
				colorArray[i][j] = Color.WHITE;
				labelArray[i][j] = new JLabel();
				labelArray[i][j].setIcon(new ImageIcon(whiteTransformed));
				gameBoard[i][j] = new JPanel();
				gameBoard[i][j].setBackground(Color.BLACK);
				gameBoard[i][j].add(labelArray[i][j]);
				centerPanel.add(labelArray[i][j]);
			}
			
		}
//*****************
//Left Panel
//*****************	
		p1wins = 0;
		p2wins = 0;
		scoreboard = new JLabel("Score",SwingConstants.RIGHT);
		p1Name = new JLabel(n1 + "'s Wins: ");
		p2Name = new JLabel(n2 + "'s Wins: ");
		p1Score = new JLabel(Integer.toString(p1wins));
		p2Score = new JLabel(Integer.toString(p2wins));

		
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
		mainMenu = new JButton("Main Menu");
		newGame = new JButton("New Game");
		ticTacToe = new JButton("Tic Tac Toe");
		quit = new JButton("Quit");
		
		rightPanel = new JPanel(new GridLayout(4,1,0,0));
		rightPanel.add(newGame);
		rightPanel.add(mainMenu);
		rightPanel.add(ticTacToe);
		rightPanel.add(quit);
		//ActionListeners
		mainMenu.addActionListener(this);
		quit.addActionListener(this);
		newGame.addActionListener(this);
		ticTacToe.addActionListener(this);
	
//*****************
//Top Panel
//*****************	
		topPanel = new JPanel(new GridLayout(2,3,5,5));
		playerTurn = new JLabel(n1 + "'s Turn", SwingConstants.CENTER);
		p1Turn = new JLabel(n1, SwingConstants.CENTER);
		p2Turn = new JLabel(n2, SwingConstants.CENTER);
		p1Color = new JLabel("", SwingConstants.CENTER);
		p2Color = new JLabel("", SwingConstants.CENTER);
		currentTurn = new JLabel(" ",SwingConstants.CENTER);
		currentTurn.setIcon(new ImageIcon(red2Transformed));
		p1Color.setIcon(new ImageIcon(red2Transformed));
		p2Color.setIcon(new ImageIcon(blue2Transformed));
		
		topPanel.add(p1Turn);
		topPanel.add(playerTurn);
		topPanel.add(p2Turn);
		topPanel.add(p1Color);
		topPanel.add(currentTurn);
		topPanel.add(p2Color);
		
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
		//If there is no winner turns can still be made.
		if (isWinner == false) {
		//Takes the turn in the column where the button was pressed
		if (source == buttonArray[0]) {
			takeTurn(0);
		}
		else if (source == buttonArray[1]) {
			takeTurn(1);
		}
		else if (source == buttonArray[2]) {
			takeTurn(2);
		}
		else if (source == buttonArray[3]) {
			takeTurn(3);
		}
		else if (source == buttonArray[4]) {
			takeTurn(4);
		}
		else if (source == buttonArray[5]) {
			takeTurn(5);
		}
		else if (source == buttonArray[6]) {
			takeTurn(6);
		}
		if(checkWinner())
			{
			setWinner();
			}
		}
		if (source == quit)
		{
			super.dispose();
		}
		else if (source == newGame)
		{
			newGame();
		}
		else if (source == mainMenu)
		{
			MenuOptions frame = new MenuOptions();
			frame.setVisible(true);
			super.dispose();
		}
		else if (source == ticTacToe)
		{
			TicTacToe frame = new TicTacToe(player1Name, player2Name);
			frame.setVisible(true);
			super.dispose();
		}
	}
private void takeTurn(int col)
{	
	//If colHeight for this row is = -1 then no more spots can be placed
	//Set colMax to true for this row.
	if(colHeight[col] == -1)
	{
		colMax[col] = true;
	}
	{
		//If colMax is false then a move is made and the colHeight value is decreased
		//by one so the next piece will go in the row above. The setColor function is
		//called at the end so that the turn counter is incremented and the color
		//at the top is changed to reflect the next person's turn
		if (colMax[col] == false) {
		if (turnCounter % 2 ==0)
			{
			labelArray[colHeight[col]][col].setIcon(new ImageIcon(redTransformed));
			colorArray[colHeight[col]][col] = Color.RED;
			}
		else
			{
			labelArray[colHeight[col]][col].setIcon(new ImageIcon(blueTransformed));
			colorArray[colHeight[col]][col] = Color.BLUE;
			}
		colHeight[col] -=1;
		setColor();
		}
	}
}
private void setColor()
//Increments turn counter and sets the icon's and text depending on the turn
{	turnCounter +=1;
if (turnCounter % 2 == 0)
{
	playerTurn.setText(player1Name + "'s Turn");
	currentTurn.setIcon(new ImageIcon(red2Transformed));
}
else {
	playerTurn.setText(player2Name + "'s Turn");
	currentTurn.setIcon(new ImageIcon(blue2Transformed));
}
}
private Boolean checkWinner() {
	//Checks for four in a row in a row

	for (int i=0; i<ROW; ++i) {
		for(int j=0; j<(COL-3); ++j)
			if(((colorArray[i][j] == Color.RED) || (colorArray[i][j] == Color.BLUE))
					&& (colorArray[i][j] == colorArray[i][j+1]) 
					&& (colorArray[i][j+1] == colorArray[i][j+2]) 
					&& (colorArray[i][j+2] == colorArray[i][j+3]))
				{
					return true;	
				}
			
		}
	
	//Checks for four in a row in a column
	for (int i=0; i<ROW-3; ++i)
	{
		for (int j=0; j<COL; ++j)
		{
		if(((colorArray[i][j] == Color.RED) || (colorArray[i][j] == Color.BLUE))
				&& (colorArray[i][j] == colorArray[i+1][j]) 
				&& (colorArray[i+1][j] == colorArray[i+2][j]) 
				&& (colorArray[i+2][j] == colorArray[i+3][j]))
			{
			return true;
			}
		}
	}
	//Checks for four in a row in the diagonals going down to the right.
	for (int i=0; i <ROW -3;++i)
	{
		for (int j=0; j<COL -3; ++j)
		{
			if(((colorArray[i][j] == Color.RED) || (colorArray[i][j] == Color.BLUE)) 
					&& (colorArray[i][j] == colorArray[i+1][j+1]) 
					&& (colorArray[i+1][j+1] == colorArray[i+2][j+2])
					&& (colorArray[i+2][j+2] == colorArray[i+3][j+3]))
			{
			return true;
			}
		}
	}
	//Checks for four in a row in the diagonals going up to the right.
	for(int i=5; i>2; --i) 
	{
		for(int j=0; j<COL-3;++j)
		{
			if(((colorArray[i][j] == Color.RED) || (colorArray[i][j] == Color.BLUE)) 
					&& (colorArray[i][j] == colorArray[i-1][j+1]) 
					&& (colorArray[i-1][j+1] == colorArray[i-2][j+2]) 
					&& (colorArray[i-2][j+2] == colorArray[i-3][j+3]))
			{
			return true;
			}
		}
	}
	return false;
	}
private void setWinner() {
	//Sets the text for the winner of the game and increments their
	//wins by one while changing the text to reflect that. Sets the
	//isWinner to true so that input from the buttons to place a piece
	//do not work and the game cannot be continued to be played. If there
	//are multiple four in a rows when one player wins
	//this function would be called multiple times. 
	//The if isWinner == false statement prevents it from incrementing
	//the win count multiple times
	if(isWinner == false) {
if (turnCounter % 2 == 0)
	{
		p2wins +=1;
		playerTurn.setText(player2Name + " has won the game!!");
		p2Score.setText(Integer.toString(p2wins));
		currentTurn.setIcon(null);
		isWinner = true;
	}
	if(turnCounter % 2 ==1)
	{
		p1wins +=1;
		playerTurn.setText(player1Name + " has won the game!!");
		p1Score.setText(Integer.toString(p1wins));
		currentTurn.setIcon(null);
		isWinner = true;
	}
	}
}
private void newGame() {
	//Resets all of the variables and arrays that allow the game to function
	//so that it is a fresh and clean game board ready to play again.
	for (int i=0; i<COL; ++i)
	{
		colHeight[i]=5;	
		colMax[i]=false;
		currentTurn.setIcon(new ImageIcon(red2Transformed));
		for(int j=0; j<ROW; ++j)
		{	
			
			turnCounter = 0;
			labelArray[j][i].setIcon(new ImageIcon(whiteTransformed));
			colorArray[j][i] = Color.WHITE;
			playerTurn.setText(player1Name + "'s Turn");
			isWinner = false;	
		}
	}
}

}