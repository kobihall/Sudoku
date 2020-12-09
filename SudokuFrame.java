import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A frame to hold and display the sudoku board and all of the buttons
 * 
 * @author Kobi Hall
 * @version 5/8
 */

public class SudokuFrame extends JFrame implements ActionListener, KeyListener{
    private JFrame frame; //the actual frame we'll be showing
    private SudokuPanel board; //the life board to draw
    private Timer animator; //a timer to control animation
    private JButton quitButton;
    private JButton generateButton;
    private JButton pmButton;
    private JButton fillPM;

    /**
     * Creates a new Sudoku object.
     */
    public SudokuFrame(){
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new SudokuPanel();
        board.setPreferredSize(new Dimension(SudokuPanel.FRAME_WIDTH, SudokuPanel.FRAME_HEIGHT));

        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate a New Board");
        generateButton.addActionListener(this);
        buttonPanel.add(generateButton);
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        buttonPanel.add(quitButton);
        pmButton = new JButton("Toggle Pencil Marks");
        pmButton.addActionListener(this);
        buttonPanel.add(pmButton);
        fillPM = new JButton("Fill all Pencil Marks (Cheating)");
        fillPM.addActionListener(this);
        buttonPanel.add(fillPM);

        fillPM.setFocusable(false);
        generateButton.setFocusable(false);
        pmButton.setFocusable(false);

        frame.addKeyListener(this);

        Container myContainer = frame.getContentPane();
        frame.setLayout(new BorderLayout());
        frame.add(board, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack(); //make everything the preferred size
        frame.setVisible(true); //show the frame
        frame.setFocusable(true);
        

    }

    /**
     * Responds to the button
     */
    //@Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == quitButton){
            System.exit(0); //hard quit
        }
        else if(e.getSource() == fillPM){
            board.fillPM();
            board.repaint();
        }
        else if(e.getSource() == pmButton){
            board.togglePMState();
        }
        else if(e.getSource() == generateButton){
            board.generateBoard();
            board.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            board.cursorLeft();
            board.repaint();
        }

        if(key == KeyEvent.VK_RIGHT){
            board.cursorRight();
            board.repaint();
        }

        if(key == KeyEvent.VK_UP){
            board.cursorUp();
            board.repaint();
        }

        if(key == KeyEvent.VK_DOWN){
            board.cursorDown();
            board.repaint();
        }

        if(key == KeyEvent.VK_1){
            board.makeGuess(1);
            board.repaint();
        }
        else if(key == KeyEvent.VK_2){
            board.makeGuess(2);
            board.repaint();
        }
        else if(key == KeyEvent.VK_3){
            board.makeGuess(3);
            board.repaint();
        }
        else if(key == KeyEvent.VK_4){
            board.makeGuess(4);
            board.repaint();
        }
        else if(key == KeyEvent.VK_5){
            board.makeGuess(5);
            board.repaint();
        }
        else if(key == KeyEvent.VK_6){
            board.makeGuess(6);
            board.repaint();
        }
        else if(key == KeyEvent.VK_7){
            board.makeGuess(7);
            board.repaint();
        }
        else if(key == KeyEvent.VK_8){
            board.makeGuess(8);
            board.repaint();
        }
        else if(key == KeyEvent.VK_9){
            board.makeGuess(9);
            board.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
    }

    @Override
    public void keyTyped(KeyEvent e){
    }
}
