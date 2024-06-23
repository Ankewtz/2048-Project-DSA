package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import model.Game2048AI;
import test.main;
import view.Game2048View;
import view.StartView;

public class Game2048Controller implements ActionListener, MouseListener {
	private StartView startView;
	private Game2048View game2048View;
	private Game2048AI game2048AI;
	private Thread aiThread;
	private int x, y;

	// Constructor for StartView
	public Game2048Controller(StartView startView) {
		this.startView = startView;
	}

	// Constructor for Game2048View
	public Game2048Controller(Game2048View game2048View) {
		this.game2048View = game2048View;
		setupKeyBindings();
	}

	public Game2048View getGame2048View() {
		return this.game2048View;
	}

	public void setGame2048View(Game2048View view) {
		this.game2048View = view;
	}

	private void setupKeyBindings() {
		JComponent mainGamePanel = game2048View.getMainGamePanel();

		// Ensure the panel is focusable
		mainGamePanel.setFocusable(true);
		mainGamePanel.requestFocusInWindow(); // Ensure the panel requests focus

		mainGamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
				"Left");
		mainGamePanel.getActionMap().put("Left", moveLeft);

		mainGamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				"Right");
		mainGamePanel.getActionMap().put("Right", moveRight);

		mainGamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				"Up");
		mainGamePanel.getActionMap().put("Up", moveUp);

		mainGamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				"Down");
		mainGamePanel.getActionMap().put("Down", moveDown);

		// Add debug statements
		System.out.println("Key bindings set up.");
	}

	// Abstract actions for game movements
	AbstractAction moveUp = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Move Up");
			moveUp();
			game2048View.setVisible(true);
		}
	};

	AbstractAction moveDown = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Move Down");
			moveDown();
			game2048View.setVisible(true);
		}
	};

	AbstractAction moveLeft = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Move Left");
			moveLeft();
			game2048View.setVisible(true);
		}
	};

	AbstractAction moveRight = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Move Right");
			moveRight();
			game2048View.setVisible(true);
		}
	};

	// Implementing movement methods (Move Up, Down, Left, Right)
	public void moveUp() {
		for (int j = 0; j < 4; j++) {
			boolean hasMerged = false;
			for (int i = 1; i < 4; i++) {
				for (int k = i; k > 0; k--) {
					if (game2048View.getGameButtonIndex(k, j) != 0) {
						if (game2048View.getGameButtonIndex(k - 1, j) == 0) {
							game2048View.setGameButtonIndex(k - 1, j, game2048View.getGameButtonIndex(k, j));
							game2048View.setGameButtonIndex(k, j, 0);
							game2048View.createGameButton(k - 1, j);
							game2048View.createGameButton(k, j);
							game2048View.setCanMoveOrMerge(true);
						} else if (game2048View.getGameButtonIndex(k, j) == game2048View.getGameButtonIndex(k - 1, j)
								&& !hasMerged) {
							game2048View.updateScoreDisplay(k - 1, j);
							game2048View.setGameButtonIndex(k - 1, j, game2048View.getGameButtonIndex(k - 1, j) * 2);
							game2048View.setGameButtonIndex(k, j, 0);
							game2048View.createGameButton(k - 1, j);
							game2048View.createGameButton(k, j);
							game2048View.setCanMoveOrMerge(true);
							hasMerged = true;
							break;
						}
					}
				}
			}
		}
		game2048View.endGame();
	}

	public void moveDown() {
		for (int j = 0; j < 4; j++) {
			boolean hasMerged = false;
			for (int i = 2; i > -1; i--) {
				for (int k = i; k < 3; k++) {
					if (game2048View.getGameButtonIndex(k, j) != 0) {
						if (game2048View.getGameButtonIndex(k + 1, j) == 0) {
							game2048View.setGameButtonIndex(k + 1, j, game2048View.getGameButtonIndex(k, j));
							game2048View.setGameButtonIndex(k, j, 0);
							game2048View.createGameButton(k + 1, j);
							game2048View.createGameButton(k, j);
							game2048View.setCanMoveOrMerge(true);
						} else if (game2048View.getGameButtonIndex(k, j) == game2048View.getGameButtonIndex(k + 1, j)
								&& !hasMerged) {
							game2048View.updateScoreDisplay(k + 1, j);
							game2048View.setGameButtonIndex(k + 1, j, game2048View.getGameButtonIndex(k + 1, j) * 2);
							game2048View.setGameButtonIndex(k, j, 0);
							game2048View.createGameButton(k + 1, j);
							game2048View.createGameButton(k, j);
							game2048View.setCanMoveOrMerge(true);
							hasMerged = true;
							break;
						}
					}
				}
			}
		}
		game2048View.endGame();
	}

	public void moveLeft() {
		for (int i = 0; i < 4; i++) {
			boolean hasMerged = false;
			for (int j = 1; j < 4; j++) {
				for (int k = j; k > 0; k--) {
					if (game2048View.getGameButtonIndex(i, k) != 0) {
						if (game2048View.getGameButtonIndex(i, k - 1) == 0) {
							game2048View.setGameButtonIndex(i, k - 1, game2048View.getGameButtonIndex(i, k));
							game2048View.setGameButtonIndex(i, k, 0);
							game2048View.createGameButton(i, k - 1);
							game2048View.createGameButton(i, k);
							game2048View.setCanMoveOrMerge(true);
						} else if (game2048View.getGameButtonIndex(i, k) == game2048View.getGameButtonIndex(i, k - 1)
								&& !hasMerged) {
							game2048View.updateScoreDisplay(i, k - 1);
							game2048View.setGameButtonIndex(i, k - 1, game2048View.getGameButtonIndex(i, k - 1) * 2);
							game2048View.setGameButtonIndex(i, k, 0);
							game2048View.createGameButton(i, k - 1);
							game2048View.createGameButton(i, k);
							game2048View.setCanMoveOrMerge(true);
							hasMerged = true;
							break;
						}
					}
				}
			}
		}
		game2048View.endGame();
	}

	public void moveRight() {
		for (int i = 0; i < 4; i++) {
			boolean hasMerged = false;
			for (int j = 2; j > -1; j--) {
				for (int k = j; k < 3; k++) {
					if (game2048View.getGameButtonIndex(i, k) != 0) {
						if (game2048View.getGameButtonIndex(i, k + 1) == 0) {
							game2048View.setGameButtonIndex(i, k + 1, game2048View.getGameButtonIndex(i, k));
							game2048View.setGameButtonIndex(i, k, 0);
							game2048View.createGameButton(i, k + 1);
							game2048View.createGameButton(i, k);
							game2048View.setCanMoveOrMerge(true);
						} else if (game2048View.getGameButtonIndex(i, k) == game2048View.getGameButtonIndex(i, k + 1)
								&& !hasMerged) {
							game2048View.updateScoreDisplay(i, k + 1);
							game2048View.setGameButtonIndex(i, k + 1, game2048View.getGameButtonIndex(i, k + 1) * 2);
							game2048View.setGameButtonIndex(i, k, 0);
							game2048View.createGameButton(i, k + 1);
							game2048View.createGameButton(i, k);
							game2048View.setCanMoveOrMerge(true);
							hasMerged = true;
							break;
						}
					}
				}
			}
		}
		game2048View.endGame();
	}

	// Method to start the AI
	public void startAI() {
		game2048AI = new Game2048AI(this);
		aiThread = new Thread(game2048AI);
		aiThread.start();
	}

	// Method to stop the AI
	public void stopAI() {
		if (game2048AI != null) {
			game2048AI.stop();
		}
		if (aiThread != null) {
			aiThread.interrupt();
		}
	}

	// Mouse listener methods for swipe actions
	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int deltaX = e.getX() - x;
		int deltaY = e.getY() - y;

		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			if (deltaX > 100) {
				moveRight();
			} else if (deltaX < -100) {
				moveLeft();
			}
		} else {
			if (deltaY > 100) {
				moveDown();
			} else if (deltaY < -100) {
				moveUp();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not used
	}

	// Handle button actions from StartView
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals("Quit")) {
			System.exit(0);
		} else if (actionCommand.equals("Play")) {
			game2048View = new Game2048View(this); // Initialize new game view
			startView.setContentPane(game2048View);
			startView.revalidate();
		} else if (actionCommand.equals("Autoplay")) {
			game2048View = new Game2048View(this); // Initialize new game view
			startView.setContentPane(game2048View);
			startView.revalidate();
			startAI();
		}
	}
}
