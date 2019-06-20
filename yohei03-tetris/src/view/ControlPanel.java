/*
 * TCSS 305 – Fall 2017 
 * Instructor Charles Bryan 
 * Assignment 6 – Tetris
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This is the GUI class for Tetris.
 * 
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public class ControlPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -3245339411323307948L;
    /**
     * The constant number 200.
     */
    private static final int WIDTH = 250;
    /**
     * The constant number 200.
     */
    private static final int HEIGHT = 200;
    /**
     * GridLayout row number.
     */
    private static final int ROW = 6;
    /**
     * GridLayout column number.
     */
    private static final int COL = 1;
    /**
     * Moving Left label.
     */
    private static final JLabel MOVE_LEFT = new JLabel("Move Left");
    /**
     * Moving Right label.
     */
    private static final JLabel MOVE_RIGHT = new JLabel("Move Right");
    /**
     * Rotating label.
     */
    private static final JLabel ROTATE = new JLabel("Rotate");
    /**
     * Moving down Label.
     */
    private static final JLabel MOVE_DOWN = new JLabel("Move Down");
    /**
     * Dropping label.
     */
    private static final JLabel DROP = new JLabel("Drop");
    /**
     * Pause label.
     */
    private static final JLabel PAUSE = new JLabel("Pause");
    /**
     * Left key description.
     */
    private static final JLabel LEFT_KEY = new JLabel("left arrow and 'a' and 'A'");
    /**
     * Right key description.
     */
    private static final JLabel RIGHT_KEY = new JLabel("right arrow and 'd' and 'D' ");
    /**
     * Rotate key description.
     */
    private static final JLabel ROTATE_KEY = new JLabel("up arrow and 'w' and 'W' ");
    /**
     * Moved Down key description.
     */
    private static final JLabel MOVEDOWN_KEY = new JLabel("down arrow and 's' and 'S'");
    /**
     * Drop Key description.
     */
    private static final JLabel DROP_KEY = new JLabel("space");
    /**
     * Pause Key description.
     */
    private static final JLabel PAUSE_KEY = new JLabel("'p' and 'P'");
    /**
     * the West panel variable.
     */
    private final JPanel myLeftPanel;
    /**
     * the East panel variable.
     */
    private final JPanel myRightPanel;
    
    /**
     * the constructor method.
     */
    public ControlPanel() {
        super();
        myLeftPanel = new JPanel();
        myLeftPanel.setLayout(new GridLayout(ROW, COL));
        myRightPanel = new JPanel();
        myRightPanel.setLayout(new GridLayout(ROW, COL));
        setup();
        add(myLeftPanel, BorderLayout.WEST);
        add(myRightPanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setBackground(Color.GRAY);
    }
    /**
     * The helper method for setting label.
     */
    private void setup() {
        myLeftPanel.add(MOVE_LEFT);
        myLeftPanel.add(MOVE_RIGHT);
        myLeftPanel.add(ROTATE);
        myLeftPanel.add(MOVE_DOWN);
        myLeftPanel.add(DROP);
        myLeftPanel.add(PAUSE);
        myRightPanel.add(LEFT_KEY);
        myRightPanel.add(RIGHT_KEY);
        myRightPanel.add(ROTATE_KEY);
        myRightPanel.add(MOVEDOWN_KEY);
        myRightPanel.add(DROP_KEY);
        myRightPanel.add(PAUSE_KEY);
    }
    // public void MakingControlPanel() {
    // myLeftPanel.add(MOVE_LEFT);
    // myLeftPanel.add(MOVE_RIGHT);
    // myLeftPanel.add(ROTATE);
    // myLeftPanel.add(MOVE_DOWN);
    // myLeftPanel.add(DROP);
    // myRightPanel.add(LEFT_KEY);
    // myRightPanel.add(RIGHT_KEY);
    // myRightPanel.add(ROTATE_KEY);
    // myRightPanel.add(MOVEDOWN_KEY);
    // myRightPanel.add(DROP_KEY);
    // add(myLeftPanel, BorderLayout.WEST);
    // add(myRightPanel, BorderLayout.EAST);
    // setPreferredSize(new Dimension(WIDTH, HEIGHT));
    // setBackground(Color.GRAY);
    // }

}
