/*
 * TCSS 305 – Fall 2017 
 * Instructor Charles Bryan 
 * Assignment 6 – Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Block;
/**
 * This is the main tetris panele class.
 * 
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public class TetrisPanel extends JPanel implements Observer {
    /**
     * The serial ID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Purple Color.
     */
    private static final Color PURPLE = new Color(102, 0, 153);
    /**
     * The constant number 300.
     */
    private static final int WIDTH = 300;
    /**
     * The constant number 600.
     */
    private static final int HEIGHT = 600;
    /**
     * The constant number 19.
     */
    private static final int NINETEEN = 19;
    /**
     * The big block size.
     */
    private static final int BIGBLOCK_SIZE = 30;
    /**
     * The list variable for saving Block data.
     */
    private List<Block[]> myBlocksList;
    /**
     * The block size variable.
     */
    private int myBlockSize;
    /**
     * The boolean value for checking the game is over or not.
     */
    private boolean myCheck;
    /**
     * The GUI variable.
     */
    private TetrisGUI myGUI;

    
    /**
     * It;s a constructor.
     */
    public TetrisPanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        myBlocksList = new ArrayList<Block[]>();
        myBlockSize = BIGBLOCK_SIZE;
        myCheck = true;
    }
    /**
     * Setting the block size.
     * 
     * @param theSize the block size
     */
    public void setBlockSize(final int theSize) {
        myBlockSize = theSize;
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        for (int row = myBlocksList.size() - 1; row >= 0; row--) {
            checkBlock(g2d, row);
           
           
        }
    }
    /**
     * 
     * The helper method for checking block.
     * 
     * 
     * @param theG2d the graphic variable.
     * @param theRow the row number.
     */
    public void checkBlock(final Graphics2D theG2d, final int theRow) {
        final Block[] block = myBlocksList.get(theRow);
        for (int col = 0; col < block.length; col++) {
            if (block[col] != null) {
                if (block[col] == Block.O) {
                    theG2d.setColor(PURPLE);
                } else if (block[col] == Block.I) {
                    theG2d.setColor(Color.BLACK);
                } else if (block[col] == Block.J) {
                    theG2d.setColor(Color.green);
                } else if (block[col] == Block.L) {
                    theG2d.setColor(Color.BLUE);
                } else if (block[col] == Block.S) {
                    theG2d.setColor(Color.YELLOW);
                } else if (block[col] == Block.Z) {
                    theG2d.setColor(Color.MAGENTA);
                } else {
                    theG2d.setColor(Color.RED);
                }
                theG2d.fill(new Rectangle2D.Double(col * myBlockSize, (NINETEEN - theRow) 
                                                * myBlockSize,
                                                myBlockSize, myBlockSize));
                theG2d.setColor(Color.WHITE);
                theG2d.draw(new Rectangle2D.Double(col * myBlockSize, (NINETEEN - theRow) 
                                                * myBlockSize,
                                                myBlockSize, myBlockSize));           
            } 
        }
    }
    /**
     * If the game is over or not.
     * 
     * @return check value.
     */
    public boolean isCheck() {
        return myCheck;
    }
    /**
     * the setter for check.
     * 
     * @param theCheck true or false.
     */
    public void setCheck(final boolean theCheck) {
        myCheck = theCheck;
    }
    /**
     * setter for the GUI class.
     * 
     * @param theGUI the GUI class value.
     */
    public void setGUI(final TetrisGUI theGUI) {
        myGUI = theGUI;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable theObservable, final Object theArg) {        
        if (theArg instanceof Boolean) {
            final ImageIcon imageIcon = new ImageIcon("./images/gameover.gif");

            JOptionPane.showMessageDialog(null, 
                                          "GAME OVER", 
                               "                                       GAME OVER", 
                                           JOptionPane.INFORMATION_MESSAGE, imageIcon);
            myCheck = false;
            System.out.println("Hello");
            myGUI.setStartEndButton(true, false);
            myGUI.musicStop();
        }
        if (theArg instanceof List) {
            myBlocksList = (List<Block[]>) theArg;
            repaint(); 
        }
    }
}
