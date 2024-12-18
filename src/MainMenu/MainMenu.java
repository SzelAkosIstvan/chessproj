package MainMenu;

import Game.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private JButton start, settings, exit, about;
    private JFrame parentFrame;
    public MainMenu(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);
        setOpaque(false);
        start = new JButton("Start Game");
        settings = new JButton("Settings");
        exit = new JButton();
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/img/exit.png"));
        Image scaledImage = exitIcon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        exit.setIcon(new ImageIcon(scaledImage));
        exit.setBounds(650, 500, 100, 70);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        start.setBounds(50, 100, 200, 40);
        settings.setBounds(50, 150, 200, 40);
        //exit.setBounds(50, 200, 200, 40);
        add(start);
        add(settings);
        add(exit);

        HandlerClass handler = new HandlerClass();
        start.addActionListener(handler);
        settings.addActionListener(handler);
        exit.addActionListener(handler);
    }



    public class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == start) {
                StartGame startGame = new StartGame();
                startGame.startGame(parentFrame);
                //switchToGamePanel();
            }
            if (event.getSource() == settings) {
                switchToSettings();
            }
            if (event.getSource() == exit) {
                System.exit(0);
            }

        }

    }

    private void switchToGamePanel() {
        Table game = new Table();
        parentFrame.getContentPane().removeAll();
        parentFrame.add(game);
        parentFrame.setSize(800,830);
        parentFrame.revalidate();
        parentFrame.setLocationRelativeTo(null);
        parentFrame.repaint();
    }

    private void switchToSettings() {
        Settings settings = new Settings(parentFrame);
        parentFrame.getContentPane().removeAll();
        parentFrame.add(settings);
        //parentFrame.setSize(800,830);
        parentFrame.revalidate();
        parentFrame.setLocationRelativeTo(null);
        parentFrame.repaint();
    }
}