import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuOptions extends JFrame implements ActionListener{
	final int FRAME_WIDTH = 400;
	final int FRAME_HEIGHT = 150;


	//Panels
	JPanel MainMenu;
	JPanel MainMenuEast;
	JPanel MainMenuWest;
		
	//Labels
	JLabel WelcomeMessage;
	JLabel EnterYourName;
	JLabel Player1Name;
	JLabel Player2Name;
	JLabel SelectAGame;
	JLabel Spacing;
	//Buttons
	JButton ticTacToe;
	JButton connectFour;
	JButton Quit;
	
	//Text Fields
	JTextField Player1NamePrompt;
	JTextField Player2NamePrompt;

	//Strings
	String p1Name;
	String p2Name;
	
	MenuOptions(){
		super("Mini games");
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
//********************************
//Main Menu
//********************************	
		MainMenu = new JPanel(new BorderLayout());
		add(MainMenu);
		
//********************************
//Main Menu North Panel
//********************************	
		WelcomeMessage = new JLabel("Welcome to mini games", SwingConstants.CENTER);
		WelcomeMessage.setFont(new Font ("Arial", Font.BOLD, 24));
		MainMenu.add(WelcomeMessage, BorderLayout.NORTH);
		
//********************************
//Main Menu East Panel
//********************************
		MainMenuEast = new JPanel(new GridLayout(4,1,5,5));
		
		SelectAGame = new JLabel("Select a game");
		SelectAGame.setFont(new Font("Arial", Font.BOLD, 16));
		
		ticTacToe = new JButton("Tic Tac Toe");
		connectFour = new JButton("Connect Four");
		Quit = new JButton("Quit");
		
		connectFour.addActionListener(this);
		Quit.addActionListener(this);
		ticTacToe.addActionListener(this);
		
		MainMenuEast.add(SelectAGame);
		MainMenuEast.add(ticTacToe);
		MainMenuEast.add(connectFour);
		MainMenuEast.add(Quit);
		
		MainMenu.add(MainMenuEast, BorderLayout.EAST);	
		
//********************************
//Main Menu West Panel
//********************************	
		MainMenuWest = new JPanel(new GridLayout(3,2,5,5));

		EnterYourName = new JLabel("Enter your name", SwingConstants.CENTER);
		EnterYourName.setFont(new Font("Arial", Font.BOLD, 16));
		Spacing = new JLabel(" ");
		
		Player1Name = new JLabel("Player1: ");
		Player1NamePrompt = new JTextField(8);
		
		Player2Name = new JLabel("Player2: ");
		Player2NamePrompt = new JTextField(8);
		
		MainMenuWest.add(EnterYourName);
		MainMenuWest.add(Spacing);
		MainMenuWest.add(Player1Name);
		MainMenuWest.add(Player1NamePrompt);
		MainMenuWest.add(Player2Name);
		MainMenuWest.add(Player2NamePrompt);

		MainMenu.add(MainMenuWest, BorderLayout.WEST);	
		
	}
	@Override public void actionPerformed(ActionEvent e)
{
		Object source = e.getSource();
		//Exits the JFrame
		if(source == Quit)
		{
			super.dispose();
		}
		//Loads the Connect Four Game with Player Names
		else if (source == connectFour)
		{
			defaultNames();
			ConnectFour myFrame = new ConnectFour
					(Player1NamePrompt.getText(),Player2NamePrompt.getText());
			myFrame.setVisible(true);
			super.dispose();
		}
		//Loads ticTacToe with player names
		else if(source == ticTacToe) 
		{
			defaultNames();
			TicTacToe myFrame = new TicTacToe
					(Player1NamePrompt.getText(),Player2NamePrompt.getText());
			myFrame.setVisible(true);
			super.dispose();
		}
}
	
private void defaultNames() {
	//If no names have been entered default names are set to
	//player one and player two
	if(Player1NamePrompt.getText().equals("")) {
		Player1NamePrompt.setText("Player 1");
	}
	if(Player2NamePrompt.getText().equals("")) {
		Player2NamePrompt.setText("Player 2");
	}	
}
}
