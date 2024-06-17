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
    public StartView(){
        this.setTitle("2048");
        this.setSize(new Dimension(520,580));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new GridLayout(2,1));

        JLabel jLabel = new JLabel("2048");
        jLabel .setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(new Font("Arial",Font.BOLD,180));
        jLabel.setForeground(new Color(102, 102, 102));

        JButton jButton_start = new JButton("Start");
        jButton_start.setHorizontalAlignment(SwingConstants.CENTER);
        jButton_start.setBounds(180,0,150,60);
        jButton_start.setBackground(new Color(187, 173, 160));
        jButton_start.setFocusPainted(false);  
        jButton_start.setBorderPainted(false);
        jButton_start.setFont(new Font("Arial",Font.BOLD,20));
        jButton_start.addActionListener(new Game2048Controller(this));
        JButton jButton_quit = new JButton("Quit");
        jButton_quit.setHorizontalAlignment(SwingConstants.CENTER);
        jButton_quit.setBounds(180,100,150,60);
        jButton_quit.setBackground(new Color(187, 173, 160));
        jButton_quit.setFocusPainted(false);  
        jButton_quit.setBorderPainted(false);
        jButton_quit.setFont(new Font("Arial",Font.BOLD,20));
        jButton_quit.addActionListener(new Game2048Controller(this));

        JPanel jPanel_buttom = new JPanel();
        jPanel_buttom.setLayout(null);
        jPanel_buttom.add(jButton_start);
        jPanel_buttom.add(jButton_quit);

        this.add(jLabel);
        this.add(jPanel_buttom);
		URL urlMain = StartView.class.getResource("Logo.png");
		Image imageMain = Toolkit.getDefaultToolkit().createImage(urlMain);
		this.setIconImage(imageMain);
        this.setVisible(true);    
    }
}

