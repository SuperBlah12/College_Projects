//454 LOC
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ZGUI extends JPanel {

	//Images of dice for GUI
	static ImageIcon rBrain = new ImageIcon(ZGUI.class.getResource("/diceImages/RB.png"));
	static ImageIcon yBrain = new ImageIcon(ZGUI.class.getResource("/diceImages/YB.png"));
	static ImageIcon gBrain = new ImageIcon(ZGUI.class.getResource("/diceImages/GB.png"));
	static ImageIcon rRun = new ImageIcon(ZGUI.class.getResource("/diceImages/RR.png"));
	static ImageIcon yRun = new ImageIcon(ZGUI.class.getResource("/diceImages/YR.png"));
	static ImageIcon gRun = new ImageIcon(ZGUI.class.getResource("/diceImages/GR.png"));
	static ImageIcon rShot = new ImageIcon(ZGUI.class.getResource("/diceImages/RS.png"));
	static ImageIcon yShot = new ImageIcon(ZGUI.class.getResource("/diceImages/YS.png"));
	static ImageIcon gShot = new ImageIcon(ZGUI.class.getResource("/diceImages/GS.png"));
	
	//Dice
	static JLabel zDie1 = new JLabel("");
	static JLabel zDie2 = new JLabel("");
	static JLabel zDie3 = new JLabel("");
	
	//Scores
	static JLabel playerScore = new JLabel("0");
	static JLabel com0Score = new JLabel("0");
	static JLabel com1Score = new JLabel("0");
	static JLabel com2Score = new JLabel("0");
	static JLabel com3Score = new JLabel("0");
	
	//Shots, pool, and infoBox
	static JLabel lShots = new JLabel("X X X");
	static JLabel lPool = new JLabel("0");
	static JTextPane infoLabel = new JTextPane();
	
	//buttons
	static JButton rollBtn = new JButton("Roll");
	static JButton passBtn = new JButton("Pass");
	static JButton nextBtn = new JButton("Next");
	
	//Panels for players
	static JPanel Player = new JPanel();
	static JPanel COM0 = new JPanel();
	static JPanel COM1 = new JPanel();
	static JPanel COM2 = new JPanel();
	static JPanel COM3 = new JPanel();
	
	static ButtonListener listener;
	
	//various game variables
	static int shots = 0;				//Shots
	static int pool = 0;				//Pool
	static boolean dead = false; 		//Does COM[X] have 3 shots?
	static boolean winner = false;  	//Is there a winner?
	static boolean done = false; 		//Is the final round active?
	static int X = 4;					//COM control variable
	static DiceCup cup = new DiceCup(); //Cup of Dice
	
	static Player play;					//The player
	static Computer[] COM = new Computer[4];//The computers

	public ZGUI() 
	{
		boolean risk = Startup(); //Get player name and risk value
		for(int i = 0; i < 4; i++)
			COM[i] = new Computer(i,risk); //make computer players with appropriate risk value
		
		//Set Zombie names.
		COM[0].setName("Zombo");
		COM[1].setName("Walker");
		COM[2].setName("Zed");
		COM[3].setName("Steve");
		setBackground(Color.BLACK);
		setLayout(null);

		listener = new ButtonListener();
		
		//Die 1
		zDie1.setBounds(10, 101, 100, 100);
		zDie1.setIcon(gBrain);
		add(zDie1);
		
		//Die 2
		zDie2.setIcon(gBrain);
		zDie2.setBounds(120, 101, 100, 100);
		add(zDie2);
		
		//Die 3
		zDie3.setIcon(gBrain);
		zDie3.setBounds(230, 101, 100, 100);
		add(zDie3);
		
		//Player Score Panel START
			Player.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			Player.setBounds(10, 11, 86, 79);
			Player.setLayout(null);
			add(Player);
			
			JLabel PlayerName = new JLabel(play.getName());
			PlayerName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			PlayerName.setBounds(0, 0, 83, 25);
			PlayerName.setHorizontalAlignment(SwingConstants.CENTER);
			Player.add(PlayerName);
			
			playerScore.setBounds(10, 36, 66, 32);
			playerScore.setHorizontalAlignment(SwingConstants.CENTER);
			playerScore.setFont(new Font("Tahoma", Font.PLAIN, 25));
			Player.add(playerScore);
		//END Player Score Panel
			
		//COM0 Score Panel START
			COM0.setBounds(106, 11, 86, 79);
			COM0.setLayout(null);
			COM0.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			add(COM0);
			
			com0Score.setHorizontalAlignment(SwingConstants.CENTER);
			com0Score.setFont(new Font("Tahoma", Font.PLAIN, 25));
			com0Score.setBounds(10, 36, 66, 32);
			COM0.add(com0Score);
			
			JLabel com0Name = new JLabel("Zombo");
			com0Name.setBounds(0, 0, 83, 25);
			com0Name.setHorizontalAlignment(SwingConstants.CENTER);
			com0Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
			COM0.add(com0Name);
		//END COM0 Score Panel 
		
		//COM1 Score Panel START
			COM1.setLayout(null);
			COM1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			COM1.setBounds(202, 11, 86, 79);
			add(COM1);
			
			com1Score.setHorizontalAlignment(SwingConstants.CENTER);
			com1Score.setFont(new Font("Tahoma", Font.PLAIN, 25));
			com1Score.setBounds(10, 36, 66, 32);
			COM1.add(com1Score);
			
			JLabel com1Name = new JLabel("Walker");
			com1Name.setHorizontalAlignment(SwingConstants.CENTER);
			com1Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
			com1Name.setBounds(0, 0, 83, 25);
			COM1.add(com1Name);
		//END COM1 Score Panel 
		
		//COM2 Score Panel START
			COM2.setLayout(null);
			COM2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			COM2.setBounds(295, 11, 86, 79);
			add(COM2);
			
			com2Score.setHorizontalAlignment(SwingConstants.CENTER);
			com2Score.setFont(new Font("Tahoma", Font.PLAIN, 25));
			com2Score.setBounds(10, 36, 66, 32);
			COM2.add(com2Score);
			
			JLabel com2Name = new JLabel("Zed");
			com2Name.setHorizontalAlignment(SwingConstants.CENTER);
			com2Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
			com2Name.setBounds(0, 0, 83, 25);
			COM2.add(com2Name);
		//END COM2 Score Panel
		
		//COM3 Score Panel START
			COM3.setLayout(null);
			COM3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			COM3.setBounds(388, 11, 86, 79);
			add(COM3);
			
			com3Score.setHorizontalAlignment(SwingConstants.CENTER);
			com3Score.setFont(new Font("Tahoma", Font.PLAIN, 25));
			com3Score.setBounds(10, 36, 66, 32);
			COM3.add(com3Score);
			
			JLabel com3Name = new JLabel("Steve");
			com3Name.setHorizontalAlignment(SwingConstants.CENTER);
			com3Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
			com3Name.setBounds(0, 0, 83, 25);
			COM3.add(com3Name);
		
		//END COM3 Score Panel
		
		//Shots
		lShots.setBounds(355, 117, 94, 32);
		lShots.setFont(new Font("Tahoma", Font.BOLD, 25));
		lShots.setForeground(Color.RED);
		lShots.setHorizontalAlignment(SwingConstants.CENTER);
		add(lShots);
		
		//Pool
		lPool.setBounds(355, 160, 94, 28);
		lPool.setForeground(Color.PINK);
		lPool.setHorizontalAlignment(SwingConstants.CENTER);
		lPool.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lPool);
		
		//Roll Button
		rollBtn.setBackground(Color.RED);
		rollBtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		rollBtn.setBounds(10, 289, 135, 58);
		rollBtn.addActionListener(listener);
		add(rollBtn);
		
		//Pass Button
		passBtn.setBackground(Color.WHITE);
		passBtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		passBtn.setBounds(155, 289, 135, 58);
		passBtn.addActionListener(listener);
		passBtn.setEnabled(false);
		add(passBtn);
		
		//Next Button
		nextBtn.setBackground(Color.LIGHT_GRAY);
		nextBtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		nextBtn.setBounds(339, 289, 135, 58);
		nextBtn.setVisible(false);
		nextBtn.addActionListener(listener);
		add(nextBtn);
		
		//Label Brain Pool
		JLabel lblBrainPool = new JLabel("Brain Pool");
		lblBrainPool.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrainPool.setForeground(Color.WHITE);
		lblBrainPool.setBounds(355, 143, 94, 20);
		add(lblBrainPool);
		
		//Label Shots Taken
		JLabel lblShotsTaken = new JLabel("Shots Taken");
		lblShotsTaken.setHorizontalAlignment(SwingConstants.CENTER);
		lblShotsTaken.setForeground(Color.WHITE);
		lblShotsTaken.setBounds(355, 101, 94, 20);
		add(lblShotsTaken);
		
		//Details Panel
		JPanel DetailsPanel = new JPanel();
		DetailsPanel.setForeground(Color.RED);
		DetailsPanel.setBackground(Color.DARK_GRAY);
		DetailsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		DetailsPanel.setBounds(10, 212, 465, 66);
		DetailsPanel.setLayout(null);
		add(DetailsPanel);
		
		//info Label
		infoLabel.setText("Press \"Roll\" to continue.");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		infoLabel.setForeground(Color.RED);
		infoLabel.setBackground(Color.DARK_GRAY);
		infoLabel.setBounds(10, 11, 445, 44);
		DetailsPanel.add(infoLabel);
	}
	
	void printPlayerScore(int score)//prints player's score
	{
		playerScore.setText("" + score + "");
	}
	void printComScore(int com, int score)//uses com to determine where to print score
	{
		switch(com)
		{
			case 0: com0Score.setText(""+score+""); break;
			case 1: com1Score.setText(""+score+""); break;
			case 2: com2Score.setText(""+score+""); break;
			case 3: com3Score.setText(""+score+""); break;
		}
	}
	void playPanelColor(boolean turn)
	{
		//Sets background to pink to indicate whose turn it is.
		//resets background when turn is done.
		if(turn)
			Player.setBackground(Color.PINK);
		else
			Player.setBackground(UIManager.getColor("Panel.background")); //found online, resets color to default.
	}
	void comPanelColor(int com, boolean turn)
	{
		//Sets background to pink to indicate whose turn it is.
		//resets background when turn is done.
		if(turn)//It is COM[X]'s turn
		{
			switch(com)
			{
				case 0: COM0.setBackground(Color.PINK); break;
				case 1: COM1.setBackground(Color.PINK); break;
				case 2: COM2.setBackground(Color.PINK); break;
				case 3: COM3.setBackground(Color.PINK); break;
			}
		}
		else//It is not COM[X]'s turn
		{
			switch(com)
			{
				case 0: COM0.setBackground(UIManager.getColor ( "Panel.background" )); break;
				case 1: COM1.setBackground(UIManager.getColor ( "Panel.background" )); break;
				case 2: COM2.setBackground(UIManager.getColor ( "Panel.background" )); break;
				case 3: COM3.setBackground(UIManager.getColor ( "Panel.background" )); break;
				default: break;
			}
		}
	
	}
	void printPool()//prints value to pool
	{
		lPool.setText(""+ pool +"");
	}
	
	void printShots()//Prints shots taken
	{
		switch(shots)
		{
			case 0: lShots.setText(""); break;
			case 1: lShots.setText("X"); break;
			case 2: lShots.setText("X X"); break;
			case 3: lShots.setText("X X X"); break;
		}
	}
	void printInfo(String info)//Prints information into the infoLabel.
	{
		infoLabel.setText(info);
	}
	
	void playerMode()//Player turn buttons ROLL & PASS, hides NEXT
	{
		rollBtn.setVisible(true);
		rollBtn.setEnabled(true);
		passBtn.setVisible(true);
		passBtn.setEnabled(false);//force roll, will be enabled after first roll.
		nextBtn.setVisible(false);
	}
	void computerMode()//Computer Proceed button NEXT, hides ROLL & PASS
	{
		rollBtn.setVisible(false);
		passBtn.setVisible(false);
		nextBtn.setVisible(true);
		nextBtn.setEnabled(true);
	}
	void printDice(Dice[] die) //Prints images of dice to GUI.
	{
		switch(die[0].getColor())							//First die
		{
			case 0: 										//Green
				switch(die[0].getFace())
				{
					case 0: zDie1.setIcon(gBrain); break;	//Brain
					case 1: zDie1.setIcon(gRun); break;		//Run
					case 2: zDie1.setIcon(gShot); break;	//Shot
				} 
			break;
			case 1: 										//Yellow
				switch(die[0].getFace())
				{
					case 0: zDie1.setIcon(yBrain); break;	//Brain
					case 1: zDie1.setIcon(yRun); break;		//Run
					case 2: zDie1.setIcon(yShot); break;	//Shot
				} 
			break;
			case 2: 										//Red
				switch(die[0].getFace())
				{
					case 0: zDie1.setIcon(rBrain); break;	//Brain
					case 1: zDie1.setIcon(rRun); break;		//Run
					case 2: zDie1.setIcon(rShot); break;	//Shot
				} 
			break;
		}
		switch(die[1].getColor())							//Second die
		{
			case 0: 										//Green
				switch(die[1].getFace())
				{
					case 0: zDie2.setIcon(gBrain); break;	//Brain
					case 1: zDie2.setIcon(gRun); break;		//Run
					case 2: zDie2.setIcon(gShot); break;	//Shot
				} 
			break;
			case 1: 										//Yellow
				switch(die[1].getFace())
				{
					case 0: zDie2.setIcon(yBrain); break;	//Brain
					case 1: zDie2.setIcon(yRun); break;		//Run
					case 2: zDie2.setIcon(yShot); break;	//Shot
				} 
			break;
			case 2: 										//Red
				switch(die[1].getFace())
				{
					case 0: zDie2.setIcon(rBrain); break;	//Brain
					case 1: zDie2.setIcon(rRun); break;		//Run
					case 2: zDie2.setIcon(rShot); break;	//Shot
				} 
			break;
		}
		switch(die[2].getColor())							//Third die
		{
			case 0: 										//Green
				switch(die[2].getFace())
				{
					case 0: zDie3.setIcon(gBrain); break;	//Brain
					case 1: zDie3.setIcon(gRun); break;		//Run
					case 2: zDie3.setIcon(gShot); break;	//Shot
				} 
			break;
			case 1: 										//Yellow
				switch(die[2].getFace())
				{
					case 0: zDie3.setIcon(yBrain); break;	//Brain
					case 1: zDie3.setIcon(yRun); break;		//Run
					case 2: zDie3.setIcon(yShot); break;	//Shot
				} 
			break;
			case 2: 										//Red
				switch(die[2].getFace())
				{
					case 0: zDie3.setIcon(rBrain); break;	//Brain
					case 1: zDie3.setIcon(rRun); break;		//Run
					case 2: zDie3.setIcon(rShot); break;	//Shot
				} 
			break;
		}
	}
	boolean doneCheck()//Checks if conditions for Final round are met. 
	{
		if((play.getScore() >=13 || COM[0].getScore() >=13 || COM[1].getScore() >=13 || COM[2].getScore() >=13)&&!done)
		{
			//If conditions are met, it is announced to the player.
			printInfo("Someone has 13 or more brains! Everyone gets one \nmore turn before a winner is announced!");
			return true;
		}
		else // not done yet.
			return false;
	}
	void getWinner()
	{
		//Checks to see who the winner is, announces them, and allows player to reset and play again.
		int win = 0;
		playerMode();									//ROLL & PASS
		passBtn.setEnabled(false);						//No passing, force roll
		for(int i = 0;i<4;i++)							//For every COM
			if(COM[win].getScore() <= COM[i].getScore())//check their scores and find the highest
				win = i;
		if(COM[win].getScore() < play.getScore())		//see if player beat best scoring computer
			win = 4;
		if(win == 4)									//4 = player won
			printInfo("You are the Winner, "+play.getName()+"!\nClick \"Roll\" to play again with these settings.");
		else 											//Computer @ COM[win] is winner.
			printInfo(COM[win].getName() + " is the Winner!\nClick \"Roll\" to play again with these settings.");
		winner= true;
	}
	int getHighScore()									//returns highest score
	{
		int high = 0;
		int score = 0;
		for(int i = 0;i<4;i++)
		{
			if(COM[high].getScore() <= COM[i].getScore())//checks for highest COM score
			{
				high = i;
				score = COM[high].getScore();
			}
		}
		if(COM[high].getScore() < play.getScore())	//Checks if Player score is better than highest COM score.
			score = play.getScore();				//if so, return Player score.
		return score;
	}
	void printStatus(Player p)
	{
		if(!done && doneCheck())					//If we are done, announce final round.
			done = true;
		else if(p.getShots() == 3) 					// Player rolled 3 shots, instantly losing the round.
			printInfo(p.getName() + " was DECIMATED! \n" + p.getName() + " gets no points and play passes.");
		else if(shots >=3)							//Player was shot 3+ times and lost the round.
			printInfo(p.getName() + " died this round.\n" + p.getName() + " gets no points and play passes.");
		else if(p.getBrains() == 3) 				// Player rolled 3 brains.
			printInfo(p.getName() + " FEASTS! \nPool increases by three.");
		else if(p.getRunners() == 3)				// Player rolled 3 runners.
			printInfo(p.getName() + " is getting their cardio up!\nRUN " + p.getName() + ", RUN!");
		else if(shots == 2)							// Player has 2 shots.
			printInfo(p.getName() + " is in danger!\nOne more shot and they are DEAD!");
		else if(p.getShots() == 1)					// Player has 2 shots.
			printInfo("Gosh, " + p.getName() + ", that looks like it hurt!");
		else										//All other situations
			printInfo("Pool increases by " + p.getBrains());
	}
	void comPass()
	{
		if(shots>=3)									//If COM[X] has died,
		{
			//When the computer dies, it needs to perform 
			//the operations of the pass button and reset the playing field.
			if(done)									//If we're done,
				COM[X].setLastTurn(true);				//this was their last turn.
			comPanelColor(X,false);						//De-color panel
			dead = true;								//Mark as dead. (Skips Strategy)
			pool = 0;									//Reset Pool
			printPool();								//Display Pool
			printStatus(COM[X]);						//Output status
			COM[X].resetHand();							//Reset Hand
			printShots();								//Display Shots
			shots = 0;									//Reset Shots
			X++;										//Increment COM control variable
		}
		else 											//COM[X] Passes
		{
			if(done)									//If we're done,
				COM[X].setLastTurn(true);				//this was their last turn.
			comPanelColor(X,false);						//De-color panel
			COM[X].setScore(COM[X].getScore()+pool);	//Increment Score
			printComScore(X,COM[X].getScore());			//Print Score
			dead = false;								//Un-Dead for next turn
			printInfo(COM[X].getName() + " banks " + pool + " brains and passes play.");
			pool = 0;									//Reset Pool
			shots = 0;									//Reset Shots
			COM[X].resetHand();							//Reset Hand
			X++;										//Increment COM control variable
		}									
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			//START
			if (event.getSource() == rollBtn) 
			{
				//Only the player uses the Roll button. It's function is to facilitate one roll of the dice and give the player
				//appropriate feedback and options for moving forward.
				
				if(!passBtn.isEnabled())	
					passBtn.setEnabled(true);					//enable Pass button after first roll.
				if(!winner && !play.lastTurn())//There is no winner and the player has not finished their last turn.
				{
					playPanelColor(true);						//Set Panel Color to indicate turn.
					play.setHand(cup.grabThree(play.getHand()));//get three dice, keeping runners as per rules.
					play.roll();								//roll dice
					pool = pool + play.getBrains();				//Increment pool
					shots = shots + play.getShots();			//Increment shots
					if(shots>=3)								//If player has died,
					{
						pool = 0;								//empty the pool.
						rollBtn.setEnabled(false);				//Disable rolling
					}
					printShots();								//Display Shots
					printPool();								//Display Pool
					printDice(play.getHand());					//Display Dice
					printStatus(play);							//Output status
				}
				else if(!winner && play.lastTurn())//There is no winner and the player already had their last turn.
				{
					//This implies that COM[3] was the one who reached 13+ points and initiated the final round.
					//Since COM[3] has passed play, the roll button must execute the ending.
					getWinner();		
				}
				else if(winner)
				{
					//If a winner has been declared, the roll button resets the game.
					shots = 0;						//Reset Shots
					pool = 0;						//Reset Pool
					winner = false;					//Reset Winner
					done = false;					//Reset Done
					X = 4;							//COM control set to Player
					play.reset();					//Reset Player
					comPanelColor(0,false);			//De-color COM[0]
					comPanelColor(1,false);			//De-color COM[1]
					comPanelColor(2,false);			//De-color COM[2]
					comPanelColor(3,false);			//De-color COM[3]
					for(Computer com : COM)
						com.reset();				//Reset COMs
					printShots();					//Print Empty Shots
					printPool();					//Print Empty Pool
					printPlayerScore(0);			//Print Player score 0
					for(int i = 0; i < 4; i++)
						printComScore(i,0);			//Print COM scores 0
					printInfo("Press \"Roll\" to continue.");
				}
			}
			if (event.getSource() == passBtn)
			{
				//The purpose of this button is to transfer play to the computer players.
				//It is only pressed when the player wants to end their turn and bank their points, OR
				//when the player has "died" and is forced to pass play.
				//In most cases, this code will merely reset the playing field for the computer player.
				//However, there is one scenario where the pass button runs the win state code.

				if(done)								//If we're done,
					play.setLastTurn(true);				//This was their last turn
				play.resetHand();						//Set hand back to Default (3 Green Brains)
				play.setScore(play.getScore() + pool);	//Increment score
				printPlayerScore(play.getScore());		//print score
				computerMode();							//NEXT
				if(shots < 3)							//if player didn't die Display banking points message.
					printInfo(play.getName() + " banks " + pool + " brains and passes play.\nPress \"Next\" to continue.");
				else									//else, display instructions.
					printInfo("Press \"Next\" to continue.");
				pool = 0;								//empty pool
				shots = 0;								//clear shots
				playPanelColor(false);					//De-color panel
				printShots();							//Print shots
				printPool();							//Print pool
				X=0;									//COM control variable set to COM[0]
				if(!winner && COM[X].lastTurn())		//There is no winner and COM[0] already had their last turn.
				{	
					//This implies that the Player was the one who reached 13+ points and initiated the final round.
					//Since the player has passed play, the roll button must execute the ending.
					getWinner();
				}
			}
			if (event.getSource() == nextBtn)
			{	
				if(dead)//Should not be dead at beginning of round.
					dead = false;
				//The next button is used to progress through the computer players turns.
				//It needs to run a computer turn, much in the same way
				//the roll button runs the players turns.
				//It needs to have the code that comprises the AI strategies.
				//It must also have code for passing from one computer to the next.
				//Lastly, it must be able to pass back to the player when the last computer
				//player finishes their turn.
				if(!winner && !COM[X].lastTurn())//There is no winner and COM[X] has not finished their last turn.
				{
					comPanelColor(X,true);							//Set Panel Color to indicate turn.
					COM[X].setHand(cup.grabThree(COM[X].getHand()));//get three dice, keeping runners as per rules.
					COM[X].roll();									//roll dice
					pool = pool + COM[X].getBrains();				//Increment pool
					shots = shots + COM[X].getShots();				//Increment shots
					printShots();									//Display Shots
					printPool();									//Display Pool
					printDice(COM[X].getHand());					//Display Dice
					printStatus(COM[X]);							//Output status
					if(shots>=3)									//If COM[X] has died,
					{
						//When the computer dies, it needs to perform 
						//passing operations. While doing this, the COM
						//control variable is incremented, and, if COM[X] has
						//3+ shots, COM[X] is marked as dead.
						comPass();
					}
				
					//COM[X] is not dead. They must now execute strategy.
					if(!dead)
					{
						if(COM[X].getRisk())//Risk Taking Strategy
						{
							//Will pass if following conditions are true:
							//+Comp has 2 shots and at least 4 brains.
							//+Comp has 1 shot and at least 6 brains.
							//+Comp has not been shot and has at least 8 brains.
							//+Comp has been shot and enough points to activate final round.
							//+During final round, pool + score surpasses the highest score.
							if((shots==2&&pool>=4)||(shots==1&&pool>=6)||(pool>=8)||(!done&&shots>0&&(COM[X].getScore()+pool)>=13)||(done&&(COM[X].getScore()+pool)>getHighScore()))
								comPass();
						}
						else if(!COM[X].getRisk())//Safe playing Computer
						{
							//Will pass if following conditions are true:
							//+Comp has 2 shots and at least 2 brains.
							//+Comp has 1 shot and at least 3 brains.
							//+Comp has not been shot and has at least 4 brains.
							//+Comp has been shot and enough points to activate final round.
							//+During final round, pool + score surpasses the highest score.
							if((shots==2&&pool>=2)||(shots==1&&pool>=3)||(pool>=4)||(!done&&shots>0&&(COM[X].getScore()+pool)>=13)||(done&&(COM[X].getScore()+pool)>getHighScore()))
								comPass();
						}
					}
					if(X==4) //If COM control variable is four, play passes to the Player.
						playerMode();
					
				}
				else if(!winner && COM[X].lastTurn())	//There is no winner and COM[X] already had their last turn.
				{
					//This covers the other three scenarios not covered with the other buttons.
					//In this scenario, Computer player 0, 1, or 2 reached 13+ points and initiated the final round.
					//Since COM[X-1] has passed play, the next button must execute the ending.
					getWinner();
				}
			}
			//END
		}
	}
	static boolean Startup()
	{//Asks player for name and what type of Computers they'd like to challenge.
		play = new Player(JOptionPane.showInputDialog("Enter your name."));
		int r = 27;
		do
		{
			String sr = JOptionPane.showInputDialog("Set Computer Difficulty[0 or 1] (1 = Risk Taker, 0 = Plays it Safe");
			//Risk takers want t have more brains in the pool before they pass
			//Plays it safe will bank more frequently.
			r = Integer.parseInt(sr);
		}while(r>1||r<0);
		if(r==1)
			return true;
		else
			return false;
	}
}