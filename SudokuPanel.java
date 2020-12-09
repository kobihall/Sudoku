import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class that extends the JPanel class. Manages the cursor, and draws to the screen.
 * 
 * @author Kobi Hall
 * @version 5/8
*/

public class SudokuPanel extends JPanel
{

    // The size of one cell one the screen, in pixels. You can make this
    // smaller if you make a big board.
    private final static int CELL_SIZE = 100;// controls the size of the window
    private final static int BOARD_WIDTH = 9;
    private final static int BOARD_HEIGHT = 9;
    private final static int BOARD_PARTITION = 3;

    public final static int FRAME_WIDTH = BOARD_WIDTH*CELL_SIZE;
    public final static int FRAME_HEIGHT = BOARD_HEIGHT*CELL_SIZE;

    private SudokuGame board;
    private int[] cursor;
    private boolean pencilMarkOn; //whether entering a value will make a guess or add a pencil mark
    
    /**
     * instantiates the cursor starting at (0,0)
     */
    public SudokuPanel(){
        board = new SudokuGame();

        cursor = new int[2];
        cursor[0] = 0;
        cursor[1] = 0;

        pencilMarkOn = false;
    }

    /**
     *toggles between adding pencil marks and making guesses
     */
    public void togglePMState(){
        pencilMarkOn = !pencilMarkOn;
    }


    /**
     *if pencilMarkOn is true, add a pencil mark where the cursor is. If it is false make a guess where the cursor is
     */
    public void makeGuess(int val){
        if(pencilMarkOn == false){
            board.makeGuess(cursor[0],cursor[1],val); 
            if(board.isSolved()){
                System.out.println("solved correctly!");
            }  
        }
        else if(pencilMarkOn == true){
            board.PMToggle(cursor[0],cursor[1],val);
        }
    }

    /**
     * Draws the board
     */
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

        double pmCoords[][] = {{-.25,-.25}, {0,-.25}, {.25,-.25}, {-.25,0}, {0,0}, {.25,0}, {-.25,.25}, {0,.25}, {.25,.25}};

        for (int i = 0; i < BOARD_WIDTH/BOARD_PARTITION; i++){
            for (int j = 0; j < BOARD_HEIGHT/BOARD_PARTITION; j++){
                for (int k = 0; k < BOARD_WIDTH/BOARD_PARTITION; k++){
                    for (int l = 0; l < BOARD_HEIGHT/BOARD_PARTITION; l++){
                        int x = j+i*BOARD_PARTITION;
                        int y = l+k*BOARD_PARTITION;
                        g.setFont(new Font("ROMAN_BASELINE", 1, 30));
                        if(board.givenValue(x,y) != 0){
                            g.setColor(Color.BLACK);
                            String str = new Integer(board.givenValue(x,y)).toString();
                            g.drawString(str, (int)((x+0.4)*CELL_SIZE), (int)((y+0.6)*CELL_SIZE));                            
                        }
                        if(board.guessValue(x,y) != 0){
                            g.setColor(Color.BLUE);
                            String str = new Integer(board.guessValue(x,y)).toString();
                            g.drawString(str, (int)((x+0.4)*CELL_SIZE), (int)((y+0.6)*CELL_SIZE));                            
                        }
                        if(board.PMValue(x,y).size() != 0){
                            g.setColor(Color.BLACK);
                            g.setFont(new Font("ROMAN_BASELINE", 1, 17));
                            for(int m = 0; m < board.PMValue(x,y).size(); m++){
                                String str = new Integer(board.PMValue(x,y).get(m)).toString();
                                g.drawString(str, (int)((x+0.5+pmCoords[m][0])*CELL_SIZE), (int)((y+0.6+pmCoords[m][1])*CELL_SIZE));
                            }
                        }
                    }
                }
            }
        }
        
        g.setColor(Color.BLACK);        
        for(int i = 0; i <= BOARD_WIDTH; i++){
            if(i%BOARD_PARTITION == 0){
                g.fillRect(i*CELL_SIZE, 0, 2, FRAME_HEIGHT+2);
                g.fillRect(0, i*CELL_SIZE, FRAME_WIDTH+2, 2);
            }            
            else{
                g.drawLine(0, i*CELL_SIZE, FRAME_WIDTH, i*CELL_SIZE);
                g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, FRAME_WIDTH); 
            }
        }
        g.setColor(Color.RED);
        g.drawRect(cursor[0]*CELL_SIZE+2,cursor[1]*CELL_SIZE+2,CELL_SIZE-3,CELL_SIZE-3);
        g.drawRect(cursor[0]*CELL_SIZE+3,cursor[1]*CELL_SIZE+3,CELL_SIZE-5,CELL_SIZE-5);
    }

    /**
     *moves the cursor left
     */
    public void cursorLeft(){
        if(cursor[0] > 0){
            cursor[0]--;
        }
    }

    /**
     *moves the cursor right
     */
    public void cursorRight(){
        if(cursor[0] < 8){
            cursor[0]++;
        }
    }

    /**
     *moves the cursor up
     */
    public void cursorUp(){
        if(cursor[1] > 0){
            cursor[1]--;
        }
    }

    /**
     *moves the cursor down
     */
    public void cursorDown(){
        if(cursor[1] < 8){
            cursor[1]++;
        }
    }


/*------------------Inherited Methods------------------*/

    public void generateBoard(){
        board.generateBoard();
    }

    public void fillPM(){
        board.fillPM();
    }

    public void shuffleBoard(){
        board.shuffleBoard();
    }
    
}
