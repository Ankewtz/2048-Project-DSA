package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Game2048Controller;
import model.Game2048Model;

public class Game2048View extends JPanel {
	private Game2048Model game2048Model = new Game2048Model();
	private Game2048Controller game2048Controller;
	private JButton[][] gameButtons = new JButton[4][4];
	private int[][] gameButtonIndexes = new int[4][4];
	private Font font = new Font("Time New Roman", Font.BOLD, 20);
	private JLabel scoreDisplay = new JLabel("Score: " + game2048Model.getPlayerScore());
	private boolean canMoveOrMerge = false;
	private JPanel mainGamePanel = new JPanel();

	public Game2048View(Game2048Controller controller) {
		this.game2048Controller = controller;
		initializeView();
	}

	private void initializeView() {
		scoreDisplay.setForeground(new Color(187, 173, 160));
		scoreDisplay.setFont(font);
		JLabel highScoreDisplay = new JLabel("High Score: " + game2048Model.getHighScore());
		highScoreDisplay.setForeground(new Color(187, 173, 160));
		highScoreDisplay.setFont(font);

		JPanel topRow = new JPanel(new GridLayout());
		topRow.add(scoreDisplay);
		topRow.add(highScoreDisplay);

		mainGamePanel.setPreferredSize(new Dimension(520, 540));
		mainGamePanel.setBackground(new Color(187, 173, 160));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				gameButtons[i][j] = new JButton();
				gameButtons[i][j].setPreferredSize(new Dimension(120, 120));
				gameButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(187, 173, 160)));
				gameButtonIndexes[i][j] = 0;
				gameButtons[i][j].setEnabled(false);
				gameButtons[i][j].setBackground(new Color(205, 193, 180));
				gameButtons[i][j].addMouseListener(game2048Controller);
				mainGamePanel.add(gameButtons[i][j]);
			}
		}
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(topRow);
		this.add(mainGamePanel);
		this.createRandomGameButton();
		this.createRandomGameButton();
		this.addMouseListener(game2048Controller);
		this.setVisible(true);
	}

	public JPanel lostWinView(String title, String buttonLabel) {
		this.setLayout(new GridLayout(2, 1));

		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 90));
		titleLabel.setForeground(new Color(102, 102, 102));

		JLabel scoreLabel = new JLabel("Score: " + game2048Model.getPlayerScore());
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
		scoreLabel.setForeground(new Color(102, 102, 102));

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
		textPanel.add(titleLabel);
		textPanel.add(scoreLabel);

		JButton actionButton = new JButton(buttonLabel);
		actionButton.setBounds(180, 0, 150, 60);
		actionButton.setBackground(new Color(187, 173, 160));
		actionButton.setFocusPainted(false);
		actionButton.setBorderPainted(false);
		actionButton.setFont(new Font("Arial", Font.BOLD, 20));
		actionButton.addActionListener(game2048Controller::actionPerformed);

		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(180, 100, 150, 60);
		quitButton.setBackground(new Color(187, 173, 160));
		quitButton.setFocusPainted(false);
		quitButton.setBorderPainted(false);
		quitButton.setFont(new Font("Arial", Font.BOLD, 20));
		quitButton.addActionListener(game2048Controller::actionPerformed);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.add(actionButton);
		buttonPanel.add(quitButton);

		this.add(textPanel);
		this.add(buttonPanel);
		this.setVisible(true);

		return this;
	}

	public Color getGameButtonColor(int index) {
		switch (index) {
			case 2:
				return new Color(255, 255, 255);
			case 4:
				return new Color(255, 255, 224);
			case 8:
				return new Color(255, 218, 185);
			case 16:
				return new Color(250, 210, 170);
			case 32:
				return new Color(176, 224, 230);
			case 64:
				return new Color(173, 216, 230);
			case 128:
				return new Color(135, 206, 250);
			case 256:
				return new Color(240, 128, 128);
			case 512:
				return new Color(205, 92, 92);
			case 1024:
				return new Color(220, 20, 60);
			case 2048:
				return new Color(255, 215, 0);
			default:
				return new Color(205, 193, 180);
		}
	}

	public JPanel getMainGamePanel() {
		return this.mainGamePanel;
	}

	public boolean getCanMoveOrMerge() {
		return this.canMoveOrMerge;
	}

	public void setCanMoveOrMerge(boolean canMoveOrMerge) {
		this.canMoveOrMerge = canMoveOrMerge;
	}

	public int getGameButtonIndex(int x, int y) {
		return this.gameButtonIndexes[x][y];
	}

	public void setGameButtonIndex(int x, int y, int value) {
		this.gameButtonIndexes[x][y] = value;
	}

	public void updateScoreDisplay(int x, int y) {
		game2048Model.setPlayerScore(game2048Model.getPlayerScore() + gameButtonIndexes[x][y]);
		this.scoreDisplay.setText("Score: " + game2048Model.getPlayerScore());
	}

	public void createGameButton(int x, int y) {
		gameButtons[x][y].setBackground(getGameButtonColor(gameButtonIndexes[x][y]));
		gameButtons[x][y].setText(gameButtonIndexes[x][y] == 0 ? "" : String.valueOf(gameButtonIndexes[x][y]));
		gameButtons[x][y].setFont(font);
	}

	public void createRandomGameButton() {
		Random random = new Random();
		while (true) {
			int x = random.nextInt(4);
			int y = random.nextInt(4);
			if (gameButtonIndexes[x][y] == 0) {
				gameButtonIndexes[x][y] = Math.random() < 0.9 ? 2 : 4;
				this.createGameButton(x, y);
				break;
			}
		}
	}

	public boolean checkWinOrEmpty(int value) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameButtonIndexes[i][j] == value) {
					return true;
				}
			}
		}
		return false;
	}

	public void endGame() {
		if (this.checkWinOrEmpty(0) && canMoveOrMerge) {
			this.createRandomGameButton();
		}
		canMoveOrMerge = false;
		boolean isGameOver = true;
		for (int i = 0; i < 3; i++) {
			if (gameButtonIndexes[i][3] == gameButtonIndexes[i + 1][3]) {
				isGameOver = false;
				break;
			}
			for (int j = 0; j < 3; j++) {
				if (gameButtonIndexes[i][j] == gameButtonIndexes[i + 1][j]
						|| gameButtonIndexes[i][j] == gameButtonIndexes[i][j + 1]
						|| gameButtonIndexes[3][j] == gameButtonIndexes[3][j + 1]) {
					isGameOver = false;
					break;
				}
			}
			if (!isGameOver) {
				break;
			}
		}
		if (isGameOver && !this.checkWinOrEmpty(0)) {
			this.removeAll();
			if (this.checkWinOrEmpty(2048)) {
				this.lostWinView("You Win", "New Game");
			} else {
				this.lostWinView("You Lost", "Try Again");
			}
			this.revalidate();
			if (this.game2048Model.getPlayerScore() > game2048Model.getHighScore()) {
				game2048Model.setHighScore();
			}
		}
	}
}