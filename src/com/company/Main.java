package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static int FRAME_WIDTH = 1200;
    private static int FRAME_HEIGHT = 800;
    private static int POS_X = 50;
    private static int POS_Y = 20;

    public static JTabbedPane tabbedPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MoneyPlanner moneyPlanner = new MoneyPlanner();
        frame.add(moneyPlanner);

        frame.setTitle("Money Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(POS_X,POS_X);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        frame.getLocationOnScreen();


        frame.pack();
    }
}
