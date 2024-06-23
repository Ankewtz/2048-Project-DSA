package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Game2048Controller;

public class StartView extends JFrame {
    private Game2048Controller game2048Controller;

    public StartView() {
        // Window setup
        this.setTitle("2048");
        this.setSize(new Dimension(520, 580));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new GridLayout(2, 1));
        this.game2048Controller = new Game2048Controller(this);
        // Title Label
        JLabel jLabel = new JLabel("2048");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(new Font("Arial", Font.BOLD, 180));
        jLabel.setForeground(new Color(102, 102, 102));

        // Start Button
        JButton jButton_start = new JButton("Start");
        jButton_start.setBackground(new Color(187, 173, 160));
        jButton_start.setFocusPainted(false);
        jButton_start.setBorderPainted(false);
        jButton_start.setFont(new Font("Arial", Font.BOLD, 20));
        jButton_start.setActionCommand("Play");
        jButton_start.addActionListener(game2048Controller::actionPerformed);

        // Quit Button
        JButton jButton_quit = new JButton("Quit");
        jButton_quit.setBackground(new Color(187, 173, 160));
        jButton_quit.setFocusPainted(false);
        jButton_quit.setBorderPainted(false);
        jButton_quit.setFont(new Font("Arial", Font.BOLD, 20));
        jButton_quit.setActionCommand("Quit");
        jButton_quit.addActionListener(game2048Controller::actionPerformed);

        // Autoplay Button
        JButton autoPlayButton = new JButton("Autoplay");
        autoPlayButton.setBackground(new Color(187, 173, 160));
        autoPlayButton.setFocusPainted(false);
        autoPlayButton.setBorderPainted(false);
        autoPlayButton.setFont(new Font("Arial", Font.BOLD, 20));
        autoPlayButton.setActionCommand("Autoplay");
        autoPlayButton.addActionListener(game2048Controller::actionPerformed);

        // Button Panel
        JPanel jPanel_buttons = new JPanel();
        jPanel_buttons.setLayout(new GridLayout(3, 1, 10, 10)); // Using GridLayout to arrange buttons
        jPanel_buttons.add(jButton_start);
        jPanel_buttons.add(autoPlayButton);
        jPanel_buttons.add(jButton_quit);

        // Add components to the frame
        this.add(jLabel);
        this.add(jPanel_buttons);

        // Set window icon
        URL urlMain = StartView.class.getResource("Logo.png");
        Image imageMain = Toolkit.getDefaultToolkit().createImage(urlMain);
        this.setIconImage(imageMain);

        this.setVisible(true);
    }

    public void setGame2048Controller(Game2048Controller game2048Controller) {
        this.game2048Controller = game2048Controller;
    }
}
