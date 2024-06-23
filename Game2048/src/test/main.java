package test;

import controller.Game2048Controller;
import view.StartView;

public class main {
	private static StartView startView;

	public static void main(String[] args) {
		startView = new StartView();
		Game2048Controller game2048Controller = new Game2048Controller(startView);
		startView.setGame2048Controller(game2048Controller);
		startView.setVisible(true);
	}

	public static StartView getStartView() {
		return startView;
	}
}