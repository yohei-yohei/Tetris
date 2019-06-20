/*
 * TCSS 305 – Fall 2017 Instructor Charles Bryan Assignment 6 – Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Block;
import model.Point;
import model.TetrisPiece;

/**
 * This is the next block panel class for Tetris.
 * 
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public class NextBlockPanel extends JPanel implements Observer {
    /**
     * This is the serial ID.
     */
    private static final long serialVersionUID = -6265731666600723354L;
    /**
     * Purple Color.
     */
    private static final Color PURPLE = new Color(102, 0, 153);
    /**
     * The constant number 200.
     */
    private static final int WIDTH = 200;
    /**
     * The constant number 200.
     */
    private static final int HEIGHT = 200;
    /**
     * The constant number 30.
     */
    private static final int THIRTY = 30;
    /**
     * The constant number 57.
     */
    private static final int FIFTYSEVEN = 57;
    /**
     * The constant number 68.
     */
    private static final int SIXTYEIGHT = 68;
    /**
     * The constant number 47.
     */
    private static final int FORTYSEVEN = 47;
    /**
     * The constant number 72.
     */
    private static final int SEVENTYTWO = 72;
    /**
     * the brown color value.
     */
    private static final Color BROWN = new Color(153, 0, 0);
    /**
     * The next block variable.
     */
    private TetrisPiece myNextBlock;
    /**
     * the next block point list.
     */
    private Point[] myNextBlockPoint;

    /**
     * It's a constructor.
     */
    public NextBlockPanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(BROWN);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        if (myNextBlock != null && myNextBlockPoint != null) {
            if (myNextBlock.getBlock() == Block.I) {
                makeIblock(g2d);
            } else if (myNextBlock.getBlock() == Block.O) {
                makeOblock(g2d);
            } else {
                makeOtherBlock(g2d);
            }
        }
    }
    
    /**
     * The method for making I block.
     * 
     * @param theGraphics graphic variable.
     */
    public void makeIblock(final Graphics theGraphics) {
        for (final Point point : myNextBlockPoint) {
            theGraphics.setColor(Color.BLACK);
            theGraphics.fillRect(point.x() * THIRTY + ((getWidth() / 2) - FIFTYSEVEN),
                         -point.y() * THIRTY + (getHeight()) - FIFTYSEVEN, THIRTY,
                         THIRTY);
            theGraphics.setColor(Color.WHITE);
            theGraphics.drawRect(point.x() * THIRTY + ((getWidth() / 2) - FIFTYSEVEN),
                         -point.y() * THIRTY + (getHeight()) - FIFTYSEVEN, THIRTY,
                         THIRTY);
        }
    }
    /**
     * The method for making O Block.
     * 
     * @param theGraphics graphic variable.
     */
    public void makeOblock(final Graphics theGraphics) {
        for (final Point point : myNextBlockPoint) {
            theGraphics.setColor(PURPLE);
            theGraphics.fillRect(point.x() * THIRTY + ((getWidth() / 2) - FIFTYSEVEN),
                         -point.y() * THIRTY + (getHeight()) - SEVENTYTWO, THIRTY,
                         THIRTY);
            theGraphics.setColor(Color.WHITE);
            theGraphics.drawRect(point.x() * THIRTY + ((getWidth() / 2) - FIFTYSEVEN),
                         -point.y() * THIRTY + (getHeight()) - SEVENTYTWO, THIRTY,
                         THIRTY);
        }
    }
    /**
     * The method for making O Block.
     * 
     * @param theGraphics graphic variable.
     */
    public void makeOtherBlock(final Graphics theGraphics) {
        for (final Point point : myNextBlockPoint) {
            if (myNextBlock.getBlock() == Block.J) {
                theGraphics.setColor(Color.green);
            } else if (myNextBlock.getBlock() == Block.L) {
                theGraphics.setColor(Color.BLUE);
            } else if (myNextBlock.getBlock() == Block.S) {
                theGraphics.setColor(Color.YELLOW);
            } else if (myNextBlock.getBlock() == Block.Z) {
                theGraphics.setColor(Color.MAGENTA);
            } else {
                theGraphics.setColor(Color.RED);
            }
            theGraphics.fillRect(point.x() * THIRTY + ((getWidth() / 2) - FORTYSEVEN),
                         -point.y() * THIRTY + (getHeight()) - SIXTYEIGHT, THIRTY,
                         THIRTY);
            theGraphics.setColor(Color.WHITE);
            theGraphics.drawRect(point.x() * THIRTY + ((getWidth() / 2) - FORTYSEVEN),
                         -point.y() * THIRTY + (getHeight()) - SIXTYEIGHT, THIRTY,
                         THIRTY);
        }
    }
    
    

    @Override
    public void update(final Observable theObservable, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            myNextBlock = (TetrisPiece) theArg;
            myNextBlockPoint = myNextBlock.getPoints();
            repaint();
        }
    }
}
