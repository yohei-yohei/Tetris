/*
 * TCSS 305 – Fall 2017 
 * Instructor Charles Bryan 
 * Assignment 6 – Tetris
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import model.Board;

/**
 * This is the GUI class for Tetris.
 * 
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public class TetrisGUI extends JFrame  {
    /**
     * The constant number 32.
     */
    public static final int KEYSPACE_CODE = 32;
    /**
     * The constant number 37.
     */
    public static final int THIRTY_SEVEN = 37;
    /**
     * The constant number 38.
     */
    public static final int UP_ARROW = 38;
    /**
     * The constant number 39.
     */
    public static final int RIGHT_ARROW = 39;
    /**
     * The constant number 65.
     */
    public static final int KEYA_CODE = 65;
    /**
     * The constant number 68.
     */
    public static final int KEYD_CODE = 68;
    /**
     * The constant number 40.
     */
    public static final int DOWN_ARROW = 40;
    /**
     * The constant number 80.
     */
    public static final int KEYP_CODE = 80;
    /**
     * The constant number 83.
     */
    public static final int KEYS_CODE = 83;
    /**
     * The constant number 87.
     */
    public static final int KEYW_CODE = 87;
    /**
     * The constant number 50.
     */
    public static final int CHANGE_DELAY = 50;
    /**
     * The constant number 1000.
     */
    public static final int INITIAL_DELAY = 1000;
    /**
     * The line number for going to the next level.
     */
    private static final int NEXT_LEVEL_LINE = 5;
 
    /**
     * The big panel width size.
     */
    private static final int BIG_WIDTH = 300;
    /**
     * The big panel height size.
     */
    private static final int BIG_HEIGHT = 600;
    /**
     * The medium panel width size.
     */
    private static final int MEDIUM_WIDTH = 200;
    /**
     * The medium panel height size.
     */
    private static final int MEDIUM_HEIGHT = 400;
    /**
     * The small panel width size.
     */
    private static final int SMALL_WIDTH = 100;
    /**
     * The medium panel height size.
     */
    private static final int SMALL_HEIGHT = 200;
    /**
     * The big block size.
     */
    private static final int BIGBLOCK_SIZE = 30;
    /**
     * The medium block size.
     */
    private static final int MEDIUM_SIZE = 20;
    /**
     * The small block size.
     */
    private static final int SMALL_SIZE = 10;
    /**
     * The serial ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * the new button variable.
     */
    private final JButton myNewGameButton = new JButton("Start the new game");
    /**
     * the end button variable.
     */
    private final JButton myEndGameButton = new JButton("End the game");
    /**
     * this is the main frame.
     */
    private final JFrame myFrame;
    /**
     * the Tetris graphical board.
     */
    private Board myTetrisBoard;
    /**
     * this is the main Tetris panel variables.
     */
    private final TetrisPanel myTetrisPanel;
    /**
     * this is the next block panel variable.
     */
    private final NextBlockPanel myNextBlockPanel;
    /**
     * The panel for next block panel and the score.
     */
    private JPanel myEastPanel;
    /**
     * The timer variable.
     */
    private final Timer myMoveTimer;
    /**
     * The menu bar variable.
     */
    private JMenuBar myMenuBar;
    /**
     * The variable of control panel.
     */
    private final ControlPanel myControlPanel;
    /**
     * 
     */
    private ScorePanel myScorePanel;
    
    /**
     */
    private final KeySetting myKeySetting;
    /**
     * checking new game should work or not.
     */
    private boolean myCheck;
    /**
     * the delay variable.
     */
    private int myDelay;
    /**
     * the clip for the music.
     */
    private Clip myClip; 
    /**
     * The constructor for GUI.
     */
    public TetrisGUI() {
        super();
        myFrame = new JFrame();
        myTetrisPanel = new TetrisPanel();
        myNextBlockPanel = new NextBlockPanel();
        myControlPanel = new  ControlPanel();
        myKeySetting = new KeySetting();

        setup();
        myMoveTimer = new Timer(myDelay, new TimerListener());
    }
    /**
     * Setting up method.
     */
    private void setup() {
        myEastPanel = new JPanel(new BorderLayout());
        myTetrisBoard = new Board();
        myMenuBar = new JMenuBar();
        myScorePanel = new ScorePanel();
        myDelay = INITIAL_DELAY;
        myTetrisPanel.setGUI(this);
        myScorePanel.setGUI(this);
        myCheck = true;
    }
    
    /**
     * the method for setting up tetril main panel.
     */
    public void setUPTetrisGUI() {
        myTetrisPanel.setLayout(new GridBagLayout());
    }
    /**
     * Making the kety setting.
     */
    public void makeKeySetting() {
        final JMenu keysetting = new JMenu("Key Setting");
        keysetting.add(myKeySetting);
        myMenuBar.add(keysetting);
    }

    /**
     * Making the resize button functions.
     */
    public void makeResizeButton() {
        final JRadioButton bigButton = new JRadioButton("Resize Big");
        final JRadioButton mediumButton = new JRadioButton("Resize Medium");
        final JRadioButton smallButton = new JRadioButton("Resize Small");
        final Dimension bigDimension = new Dimension(BIG_WIDTH, BIG_HEIGHT);
        final Dimension mediumDimension = new Dimension(MEDIUM_WIDTH, MEDIUM_HEIGHT);
        final Dimension smallDimension = new Dimension(SMALL_WIDTH, SMALL_HEIGHT);
        final ButtonGroup group = new ButtonGroup();
        final JMenu resizeButton = new JMenu("Resize Board");
        bigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (!bigDimension.equals(myTetrisPanel.getSize())) {
                    myTetrisPanel.setPreferredSize(new Dimension(BIG_WIDTH, BIG_HEIGHT));
                    myTetrisPanel.setBlockSize(BIGBLOCK_SIZE);
                    myFrame.setResizable(false);
                    myFrame.pack();
                }
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (!mediumDimension.equals(myTetrisPanel.getSize())) {
                    myTetrisPanel.setPreferredSize(new Dimension(MEDIUM_WIDTH, MEDIUM_HEIGHT));
                    myTetrisPanel.setBlockSize(MEDIUM_SIZE);
                    myFrame.setResizable(false);
                    myFrame.pack();
                }
            }
        });
        smallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (!smallDimension.equals(myTetrisPanel.getSize())) {
                    myTetrisPanel.setPreferredSize(new Dimension(SMALL_WIDTH, SMALL_HEIGHT));
                    myTetrisPanel.setBlockSize(SMALL_SIZE);
                    myFrame.setResizable(false);
                    myFrame.pack();
                }
            }
        });
        group.add(bigButton);
        group.add(mediumButton);
        group.add(smallButton);
        resizeButton.add(bigButton);
        resizeButton.add(mediumButton);
        resizeButton.add(smallButton);
        bigButton.setSelected(true);
        resizeButton.setEnabled(true);
        resizeButton.setMnemonic(KeyEvent.VK_R);
        myMenuBar.add(resizeButton);
    }
    /**
     * making start and end game button.
     */
    public void makeNewENDGameButton() {
        final JMenu newGameMenu = new JMenu("New/End Game"); 

        final ImageIcon imageIcon = new ImageIcon("./images/cartman.gif");
        myNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                try {
                    myClip = AudioSystem.getClip();
                    myClip.open(AudioSystem.getAudioInputStream(new File("Zelda.wav")));
                    myClip.loop(Clip.LOOP_CONTINUOUSLY);
                    myClip.start();
                } catch (final LineUnavailableException | IOException 
                                | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                setupNewENDGameButton();
            }
        });
        myEndGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myClip.stop();
                myCheck = false;
                myTetrisPanel.setCheck(false);
                myMoveTimer.stop();
                myNewGameButton.setEnabled(true);
                myEndGameButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, 
                                              "The Game is End", 
                                   "                                       The GAME is End", 
                                               JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
        });
        myNewGameButton.setEnabled(true);
        myEndGameButton.setEnabled(false);
        newGameMenu.add(myNewGameButton);
        newGameMenu.add(myEndGameButton);
        myMenuBar.add(newGameMenu);
    }
    /**
     * setting up for New and END game.
     */
    public void setupNewENDGameButton() {
        myCheck = true;
        myTetrisPanel.setCheck(true);
        myMoveTimer.start();
        myTetrisBoard.newGame();   
        myTetrisBoard.step();
        myEndGameButton.setEnabled(true);
        myNewGameButton.setEnabled(false);
        myScorePanel.setScore(0);
        myScorePanel.setLevel(1);
        myScorePanel.setTotalLine(0);
        myScorePanel.setNextLevelLine(NEXT_LEVEL_LINE);
    }
    /**
     * setter for the start button and end button.
     * 
     * @param theStart the start button should be true or not.
     * @param theEnd the end button should be true or not.
     */
    public void setStartEndButton(final boolean theStart, final boolean theEnd) {
        myNewGameButton.setEnabled(theStart);
        myEndGameButton.setEnabled(theEnd);
    }
    /**
     * setter for the timer.
     */
    public void setTimer() {
        myDelay = INITIAL_DELAY - (CHANGE_DELAY * myScorePanel.getLevel());
        myMoveTimer.setDelay(myDelay);
    }
    /**
     * the method for stopping the music.
     */
    public void musicStop() {
        myClip.stop();
    }

    /**
     * The stating method.
     */
    public void start() {
        setUPTetrisGUI();
        makeNewENDGameButton();
        makeResizeButton();
        makeKeySetting();

        myFrame.setJMenuBar(myMenuBar);
        myTetrisBoard.addObserver(myTetrisPanel);
        myTetrisBoard.addObserver(myNextBlockPanel);
        myTetrisBoard.addObserver(myScorePanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        myFrame.addKeyListener(new KeyThing());
        myEastPanel.add(myNextBlockPanel, BorderLayout.NORTH);
        myEastPanel.add(myControlPanel, BorderLayout.CENTER);
        myEastPanel.add(myScorePanel, BorderLayout.SOUTH);
        myFrame.add(myTetrisPanel, BorderLayout.WEST);
        myFrame.add(myEastPanel, BorderLayout.EAST);
        myFrame.pack();
        myFrame.setVisible(true);

    }

    /**
     * It's a inner class for timer.
     * 
     * @author yohei Sato
     *
     */
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myTetrisBoard.step();
        }
    }
    
    
    /**
     * It's a inner class for key manipulation.
     * 
     * @author yohei Sato
     *
     */
    public class KeyThing extends KeyAdapter {
        /**
         * the key value.
         */
        private int myKey;
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            myKey = theEvent.getKeyCode();
            if (myTetrisPanel.isCheck()) {
                keyHleper();
            }
        }
        /**
         * The helper method for keyPressed.
         */
        public void keyHleper() {
            if (myCheck && (myKey == THIRTY_SEVEN || myKey == KEYA_CODE)) {
                myTetrisBoard.left();
            } else if (myCheck && (myKey == KEYD_CODE || myKey == RIGHT_ARROW)) {
                myTetrisBoard.right();
            } else if (myCheck && myKey == KEYSPACE_CODE) {
                myTetrisBoard.drop();
            } else  { 
                keyHelper2();
            }
        }
        /**
         * The helper method for keyHelper.
         */
        public void keyHelper2() {
            if (myCheck && (myKey == KEYS_CODE || myKey == DOWN_ARROW)) {
                myTetrisBoard.down();
            } else if (myCheck && (myKey == KEYW_CODE || myKey == UP_ARROW)) {
                myTetrisBoard.rotateCW();
            } else if (myKey == KEYP_CODE) {
                keyHelper3();
            }
        }
        /**
         * The helper method for keyHelper2.
         */
        public void keyHelper3() {
            if (myCheck) {
                myMoveTimer.stop();
                myCheck = false;
            } else if (!myCheck) {
                myMoveTimer.start();
                myCheck = true;
            }
        }

    }
}
    

