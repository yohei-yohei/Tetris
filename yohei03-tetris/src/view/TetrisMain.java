/*
* TCSS 305 – Fall 2017
* Instructor Charles Bryan
* Assignment 6 – Tetris
*/
package view;

import java.awt.EventQueue;

/** This is the main class.
 * @author Yohei Sato yohei03@uw.edu
 * 
 * @version 8 December 2017.
 */
public final class TetrisMain {
    /**
     * The constructor for GUI.
     */
    private TetrisMain() {
    }
    /**
     * the main method.
     * 
     * @param theArgs the argument.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI().start();
            }
        });
    }
}
