package test;

import view.StartView;

public class main {
	private static StartView startView;

	public static void main(String[] args) {
		startView = new StartView();
	}

	public static StartView getStartView() {
		return startView;
	}
}
