import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


class MinesweeperGUI {

	//the map size and mines
	int mapHeight;
	int mapWidth;
	int numberOfMines;

	int borderLayoutGap = 0;

	//a button for each field in the Minesweeper map
	JButton[][] mapButtons;
	
	
	JButton[] difficultyMenuButtons;
	JFrame gameFrame;
	JTextField gameStatus;
	JPanel mainPanel;

	//the object minesweepermap
	MinesweeperMap map;
	
	//start the gui with a standard 9x9 map containing 10 mines
	public static void main(String[] args) {
		new MinesweeperGUI();
	}

	//the gui creating a frame
	MinesweeperGUI() {
		mapHeight = 9;
		mapWidth = 9;
		numberOfMines = 10;

		gameFrame = new JFrame("Minesweeper");
		setup();
	}

	void setup() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 20, 20, 20));

		mainPanel = mkMainPanel();
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(mainPanel);

		//creates the section for menu buttons and information
		JPanel topPanel = mkTopBar();
		panel.add(topPanel, BorderLayout.NORTH);

		gameFrame.add(panel);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}

	void resetSetup() {
		mainPanel.removeAll();
		mainPanel = mkMainPanel();
		gameFrame.getContentPane().removeAll();
		gameFrame.getContentPane().add(mkTopBar(), BorderLayout.NORTH);
		gameFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		//updateMenuBox();
		updateGameFrame();
	}

	
	//main-center panel with the gamelayout, containing a button for each field, creating a minesweeper map for each game instance
	JPanel mkMainPanel() {
		//creates the game layout
		mapButtons = new JButton[mapHeight][mapWidth];
		map = new MinesweeperMap(mapHeight, mapWidth, numberOfMines);

		JPanel mkBL = new JPanel(new GridLayout(mapHeight, mapWidth, borderLayoutGap, borderLayoutGap));
		for(int i = 0; i < mapHeight; i++) {
			for(int j = 0; j < mapWidth; j++) {
				final int buttonEventHeight = i;
				final int buttonEventWidth = j;
				mapButtons[i][j] = new JButton();
				mapButtons[i][j].setMinimumSize(new Dimension(70, 70));
				mapButtons[i][j].setPreferredSize(new Dimension(70, 70	));
				mapButtons[i][j].addActionListener(e -> {
					System.out.println("Event at: " + buttonEventHeight + " and: " + buttonEventWidth);
					map.leftClickOnField(buttonEventHeight, buttonEventWidth);
					for(int z = 0; z < mapHeight; z++) {
						for(int k = 0; k < mapWidth; k++) {
							updateButtonVisuals(z, k);
						}
					}
					map.checkIfGameIsWon();
					gameWonUpdate();
				});
				mkBL.add(mapButtons[i][j]);
			}
		}
		updateGameFrame();
		return mkBL;
	}

	//topbar mit 2 Panel: Menu und Infobar
	JPanel mkTopBar() {
		JPanel mkTB = new JPanel();
		mkTB.setLayout(new BoxLayout(mkTB, BoxLayout.Y_AXIS));
		JPanel menuPanel = mkMenuBar();
		mkTB.add(menuPanel);
		JPanel infoPanel = mkInformationBar();
		mkTB.add(infoPanel);
		return mkTB;
	}

	//menubar
	JPanel mkMenuBar() {
		difficultyMenuButtons = new JButton[3];
		JPanel mkMB = new JPanel(new FlowLayout());
		JButton reset = new JButton();
		reset.setText("Reset");
		reset.addActionListener(e -> {	
				gameStatus.setText("game status");
				map = new MinesweeperMap(mapHeight, mapWidth, numberOfMines);
				for(int i = 0; i < mapHeight; i++) {
					for(int j = 0; j < mapWidth; j++) {
						updateButtonVisuals(i, j);
					}
				}

				for(int z = 0; z < mapHeight; z++) {
					for(int k = 0; k < mapWidth; k++) {
						mapButtons[z][k].getModel().setPressed(false);
						mapButtons[z][k].setBackground(null);
					}
				}
			}
			);
		mkMB.add(reset);
		JButton help = new JButton();
		help.setText("Help");
		help.addActionListener( e -> gameStatus.setText("Looser lol"));
		mkMB.add(help);
		JComboBox menu = mkMenuBox();
		mkMB.add(menu);
		return mkMB;
	}

	//is created automatically by the menubar, adding difficulty settings
	JComboBox mkMenuBox() {
		JComboBox<String> mkMenuBox = new JComboBox<String>();
		mkMenuBox.addItem("easy");
		mkMenuBox.addItem("medium");
		mkMenuBox.addItem("hard");
		
		//event listener for changed mode
		mkMenuBox.addActionListener(e -> {
			String selectedItem = (String) mkMenuBox.getSelectedItem();
			if ("easy".equals(selectedItem)) {
				System.out.println("Easy mode selected");
				mapHeight = 9;
				mapWidth = 9;
				numberOfMines = 10;
				resetSetup();
			}
			if ("medium".equals(selectedItem)) {
				System.out.println("Medium mode selected");
				mapHeight = 16;
				mapWidth = 16;
				numberOfMines = 40;
				resetSetup();
			}
			if ("hard".equals(selectedItem)) {
				System.out.println("Hard mode selected");
				mapHeight = 30;
				mapWidth = 16;
				numberOfMines = 99;
				resetSetup();
			}
		});
		return mkMenuBox;
	}
	JPanel mkInformationBar() {
		JPanel mkIB = new JPanel(new FlowLayout());
		gameStatus = new JTextField();
		gameStatus.setText("game status");
		mkIB.add(gameStatus);
		return mkIB;
	}

	//??
	void updateGameFrame() {
		gameFrame.revalidate();
		gameFrame.repaint();
	}

	//updates a button-fields visuals after it has been opened
	void updateButtonVisuals(int h, int w) {
		if(map.getFeldIsOpen(h, w)) {
			mapButtons[h][w].getModel().setPressed(true);
			if(map.checkIsFeldMine(h, w)) {
				mapButtons[h][w].setBackground(Color.RED);
				playBombSound();
				gameOverUpdate();
			}
			if(!map.checkIsFeldMine(h, w)) {
				if(map.manuallyCheckNearbyMines(h, w) == 0) {
				}
				if(map.manuallyCheckNearbyMines(h, w) != 0) {
					mapButtons[h][w].setText(String.valueOf(map.manuallyCheckNearbyMines(h, w)));
				}
			}
		}
		if(!map.getFeldIsOpen(h, w) && !map.getGameIsLost()) {
			if(map.getFieldIsMarked(h, w)) {
				mapButtons[h][w].setText("M");
			}
			if(!map.getFieldIsMarked(h, w)) {
				mapButtons[h][w].setText("");
			}
		}
		updateGameFrame();
	}

	void gameOverUpdate() {
		gameStatus.setText("game over");
		for(int p = 0; p < mapHeight; p++) {
			for(int u = 0; u < mapWidth; u++) {
				if(map.checkIsFeldMine(p, u)) {
					mapButtons[p][u].setText("X");
					System.out.println("" + p + "-" + u);
				}
			}
		}
	}
	
	void gameWonUpdate() {
		if(map.getGameIsWon()) {
			gameStatus.setText("game won");
			playGameWonSound();
			for(int p = 0; p < mapHeight; p++) {
				for(int u = 0; u < mapWidth; u++) {
					if(map.checkIsFeldMine(p, u)) {
						mapButtons[p][u].setText("X");
						System.out.println("" + p + "-" + u);
					}
				}
			}
		}
	}

	void playGameWonSound() {
  		String soundName = "gameWon.wav";    
   		AudioInputStream audioInputStream = null;
    		Clip clip = null;
    
    		try {
    	    		audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        		clip = AudioSystem.getClip();
        		clip.open(audioInputStream);
        		clip.start();
    		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        		e.printStackTrace(); // Log the exception
        		System.out.println("Default sound or alternative action here."); // Use default sound or alternative action if an error occurs
    		}
	}

	void playBombSound() {
  		String soundName = "bombSound.wav";    
   		AudioInputStream audioInputStream = null;
    		Clip clip = null;
    
    		try {
    	    		audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        		clip = AudioSystem.getClip();
        		clip.open(audioInputStream);
        		clip.start();
    		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        		e.printStackTrace(); // Log the exception
        		System.out.println("Default sound or alternative action here."); // Use default sound or alternative action if an error occurs
    		}
	}
}