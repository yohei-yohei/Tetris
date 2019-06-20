/*
 * TCSS 305 – Fall 2017 Instructor Charles Bryan Assignment 6 – Tetris
 */

package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.TetrisPiece;

/**
 * This is the GUI class for Tetris.
 * 
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public class ScorePanel extends JPanel implements Observer {
    /**
     * the serial ID.
     */
    private static final long serialVersionUID = -8245417615560904464L;
    /**
     * the sound file name.
     */
    private static final String ZELDA_SOUND = "ZeldaClear.wav";
    /**
     * three lines integer.
     */
    private static final int THREE_NUMBER = 3;
    /**
     * the four line score.
     */
    private static final int FOUR_LINE_SCORE = 1200;
    /**
     * the three line score.
     */
    private static final int THREE_LINE_SCORE = 300;
    /**
     * the two line score.
     */
    private static final int TWO_LINE_SCORE = 100;
    /**
     * the one line score.
     */
    private static final int ONE_LINE_SCORE = 40;
    /**
     * "Serif" string.
     */
    private static final String SERIF = "Serif";
    /**
     * the font size.
     */
    private static final int FONT_SIZE = 25;
    /**
     * the font size for Next leve line.
     */
    private static final int FONT_NEXT_LEVEL = 15;
    /**
     * The line number for going to the next level.
     */
    private static final int NEXT_LEVEL_LINE = 5;
    /**
     * The row number for the GridLayout.
     */
    private static final int ROW = 4;
    /**
     * The column number for the GridLayout.
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
     * Level label.
     */
    private static final JLabel LEVEL_LABEL = new JLabel("Level");
    /**
     * the level.
     */
    private static final JLabel LEVEL = new JLabel();
    /**
     * The socre label.
     */
    private static final JLabel SCORE_LABEL = new JLabel("Score");
    /**
     * the score.
     */
    private static final JLabel SCORE = new JLabel();
    /**
     * The total line panel.
     */
    private static final JLabel TOTAL_LINE_LABEL = new JLabel("Total Lines");
    /**
     * the total line.
     */
    private static final JLabel TOTAL_LINE = new JLabel();
    /**
     * the next level line.
     */
    private static final JLabel NEXT_LEVEL = new JLabel();
    /**
     * The next level line panel.
     */
    private static final JLabel NEXT_LEVEL_LABEL = new JLabel("Next Level Lines      ");
    /**
     * The Level.
     */
    private int myLevel;
    /**
     * the total score.
     */
    private int myScore;
    /**
     * the total line number.
     */
    private int myTotalLine;
    /**
     * Necessary the number of line for going into next level.
     */
    private int myNextLevelLine;
    /**
     * the panel.
     */
    private final JPanel myPanel;
    /**
     * the Tetris Gui.
     */
    private TetrisGUI myGUI;
    
    
    /**
     * the constructor.
     */
    public ScorePanel() {
        super();
        myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(ROW, COL));
        myLevel = 1;
        myScore = 0;
        myTotalLine = 0;
        myNextLevelLine = (NEXT_LEVEL_LINE * myLevel) - myTotalLine;
        setup();
        add(myPanel);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    /**
     * getter for myLevel.
     * 
     * @return The level value.
     */
    public int getLevel() {
        return myLevel;
    }
    /**
     * Setter for the score.
     * 
     * @param theScore The score.
     */
    public void setScore(final int theScore) {
        myScore = theScore;
        SCORE.setText(String.valueOf(myScore));
    }
    /**
     * Setter for the level.
     * 
     * @param theLevel the level value.
     */
    public void setLevel(final int theLevel) {
        myLevel = theLevel;
        LEVEL.setText(String.valueOf(myLevel));
    }
    /**
     * setter for the total line.
     * 
     * @param theTotalLine the total line number.
     */
    public void setTotalLine(final int theTotalLine) {
        myTotalLine = theTotalLine;
        TOTAL_LINE.setText(String.valueOf(myTotalLine));
    }
    /**
     * setter for the next level line.
     * 
     * @param theNextLevelLine the necessary line number for next level.
     */
    public void setNextLevelLine(final int theNextLevelLine) {
        myNextLevelLine = theNextLevelLine;
        NEXT_LEVEL.setText(String.valueOf(myNextLevelLine));
    }
    /**
     * Setter for the GUI class.
     *  
     * @param theGUI the GUI class value.
     */
    public void setGUI(final TetrisGUI theGUI) {
        myGUI = theGUI;
    }
    /**
     * The setting up method.
     */
    private void setup() {
        SCORE_LABEL.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        myPanel.add(SCORE_LABEL);
        SCORE.setText(String.valueOf(myScore));
        SCORE.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        myPanel.add(SCORE);
        LEVEL_LABEL.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        myPanel.add(LEVEL_LABEL);
        LEVEL.setText(String.valueOf(myLevel));
        LEVEL.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        myPanel.add(LEVEL);
        myPanel.add(TOTAL_LINE_LABEL);
        TOTAL_LINE_LABEL.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        TOTAL_LINE.setText(String.valueOf(myTotalLine));
        TOTAL_LINE.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        myPanel.add(TOTAL_LINE);
        NEXT_LEVEL_LABEL.setFont(new Font(SERIF, Font.PLAIN, FONT_NEXT_LEVEL));
        myPanel.add(NEXT_LEVEL_LABEL);
        NEXT_LEVEL.setFont(new Font(SERIF, Font.PLAIN, FONT_SIZE));
        NEXT_LEVEL.setText(String.valueOf(myNextLevelLine));
        myPanel.add(NEXT_LEVEL);
    }

//    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable theObservable, final Object theArg) {
        
        if (theArg instanceof TetrisPiece) {
            myScore += ROW;
            SCORE.setText(String.valueOf(myScore));
        } else if (theArg.getClass().isArray()) {
            final Integer[] array = (Integer[]) theArg;
            if (array.length == ROW) {
                myScore += FOUR_LINE_SCORE * myLevel;
                SCORE.setText(String.valueOf(myScore));
                zeldaSound();
            } else if (array.length == THREE_NUMBER) {
                myScore += THREE_LINE_SCORE * myLevel;
                SCORE.setText(String.valueOf(myScore));
                zeldaSound();
            } else if (array.length == 2) {
                myScore += TWO_LINE_SCORE * myLevel;
                SCORE.setText(String.valueOf(myScore));
                zeldaSound();
            } else if (array.length == 1) {
                myScore += ONE_LINE_SCORE * myLevel;
                SCORE.setText(String.valueOf(myScore));
                zeldaSound();
            }
            myTotalLine += array.length;
            TOTAL_LINE.setText(String.valueOf(myTotalLine));
            myLevel = (myTotalLine / NEXT_LEVEL_LINE) + 1;
            myGUI.setTimer();
            LEVEL.setText(String.valueOf(myLevel));
            myNextLevelLine = (NEXT_LEVEL_LINE * myLevel) - myTotalLine;
            NEXT_LEVEL.setText(String.valueOf(myNextLevelLine));
        }
    }
    /**
     * making the zelda sound for clear lines.
     */
    public void zeldaSound() {
        final Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(ZELDA_SOUND)));
            clip.start();
        } catch (final LineUnavailableException | IOException 
                        | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
