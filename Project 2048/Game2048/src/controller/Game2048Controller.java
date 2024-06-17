package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import test.main;
import view.Game2048View;
import view.StartView;

public class Game2048Controller implements ActionListener, MouseListener {
	private StartView startView;
	private Game2048View game2048View;
	private int x, y;

	public Game2048Controller(StartView startView) {
		this.startView = startView;
	}

	public Game2048Controller(Game2048View game2048View) {
		this.game2048View = game2048View;
		game2048View.getMainGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("LEFT"), "Left");
		game2048View.getMainGamePanel().getActionMap().put("Left", moveLeft);

		game2048View.getMainGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("RIGHT"), "Right");
		game2048View.getMainGamePanel().getActionMap().put("Right", moveRight);

		game2048View.getMainGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),
				"Up");
		game2048View.getMainGamePanel().getActionMap().put("Up", moveUp);

		game2048View.getMainGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("DOWN"), "Down");
		game2048View.getMainGamePanel().getActionMap().put("Down", moveDown);
	}

	AbstractAction moveUp = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveUp();
		}
	};

	AbstractAction moveDown = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveDown();
		}
	};

	AbstractAction moveLeft = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveLeft();
		}
	};

	AbstractAction moveRight = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveRight();
		}
	};

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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (y - e.getY() > 100 && Math.abs(e.getX() - x) < 100) {
			this.moveUp();
		}
		if (e.getY() - y > 100 && Math.abs(e.getX() - x) < 100) {
			this.moveDown();
		}
		if (x - e.getX() > 100 && Math.abs(e.getY() - y) < 100) {
			this.moveLeft();
		}
		if (e.getX() - x > 100 && Math.abs(e.getY() - y) < 100) {
			this.moveRight();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		startView = main.getStartView();
		if (actionCommand.equals("Quit")) {
			System.exit(0);
		} else {
			startView.setContentPane(new Game2048View());
			startView.revalidate();
		}
	}
}
