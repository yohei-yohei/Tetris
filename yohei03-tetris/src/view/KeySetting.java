/*
 * TCSS 305 – Fall 2017 
 * Instructor Charles Bryan 
 * Assignment 6 – Tetris
 */
package view;


import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is the GUI class for Tetris.
 * 
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public class KeySetting extends JPanel {
    /**
     * the serial number.
     */
    private static final long serialVersionUID = -7802863969372101812L;
    /**
     * GridLayout row number.
     */
    private static final int ROW = 5;
    /**
     * GridLayout column number.
     */
    private static final int COL = 2;
    /**
     * The constant number 250.
     */
    private static final int WIDTH = 250;
    /**
     * The constant number 200.
     */
    private static final int HEIGHT = 200;
    /**
     * Moving Left label.
     */
    private static final JLabel MOVE_LEFT = new JLabel("Move Left   ");
    /**
     * Moving Right label.
     */
    private static final JLabel MOVE_RIGHT = new JLabel("Move Right   ");
    /**
     * Rotating label.
     */
    private static final JLabel ROTATE = new JLabel("Rotate   ");
    /**
     * Moving down Label.
     */
    private static final JLabel MOVE_DOWN = new JLabel("Move Down   ");
    /**
     * Dropping label.
     */
    private static final JLabel DROP = new JLabel("Drop ");
    /**
     * the number 15.
     */
    private static final int FIFTEEN = 15;
    /**
     * the number 5.
     */
    private static final int FIVE = 5;
    /**
     * the left text variable.
     */
    private final JTextField myLeftText;
    /**
     * the right text variable.
     */
    private final JTextField myRightText;
    /**
     * the rotate text variable.
     */
    private final JTextField myRotate;
    /**
     * the move down text variable.
     */
    private final JTextField myMoveDown;
    /**
     * the drop text vriable.
     */
    private final JTextField myDrop;
    /**
     * the West panel variable.
     */
    private final JPanel myLeftPanel;

    /**
     * The constructor.
     */
    public KeySetting() {
        super();
        myLeftText = new JTextField();
        myLeftText.setPreferredSize(new Dimension(FIFTEEN, FIVE));
        myRightText = new JTextField();
        myRotate = new JTextField();
        myMoveDown = new JTextField();
        myDrop = new JTextField();
        myLeftPanel = new JPanel();
        myLeftPanel.setLayout(new GridLayout(ROW, COL));
        setup();
    }
    /**
     * the set up.
     */
    private void setup() {
        myLeftPanel.add(MOVE_LEFT);
        myLeftPanel.add(myLeftText);
        myLeftPanel.add(MOVE_RIGHT);
        myLeftPanel.add(myRightText);
        myLeftPanel.add(ROTATE);
        myLeftPanel.add(myRotate);
        myLeftPanel.add(MOVE_DOWN);
        myLeftPanel.add(myMoveDown);
        myLeftPanel.add(DROP);
        myLeftPanel.add(myDrop);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(myLeftPanel);
    }
    
    
}
