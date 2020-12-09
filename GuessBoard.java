
/**
 * A class that stores all the guesses the user has made.
 *
 * @author Kobi Hall
 * @version 5/8
 */
public class GuessBoard
{
    private int[][] guessBoard;
    private SudokuBoard board;
    private PencilMark pm;

    /**
     *instantiates with a pre existing board and pencil mark board, because methods from these classes are used here.
     */
    public GuessBoard(SudokuBoard board, PencilMark pm){
        this.board = board;
        this.pm = pm;
        guessBoard = new int[9][9];
    }

    /**
     *@return gives the value of a user's guess at a cell (x,y)
     */
    public int guessValue(int x, int y){
        return guessBoard[y][x];
    }

    /**
     *makes a guess at a point. A guess can't be made if there is already a given. If the user enters the same number that has already been guessed, it will remove it
     *(essentially a toggle). Making a guess will automatically remove all pencil marks at the same cell. It will also remove pencil marks of the same value as the guess
     *in the same column, row, and sub board
     */
    public void makeGuess(int x, int y, int val){
        if(board.givenValue(x,y) == 0 && guessValue(x,y) != val){
            guessBoard[y][x] = val;
            for(int i = 1; i <= 9; i++){
            	pm.remove(x,y,i);
            }
        	for(int i = 0; i < 9; i++){
	        	if(pm.value(i,y).contains(val)){
	        	    pm.remove(i,y,val);                    
	        	}
	        	if(pm.value(x,i).contains(val)){
	        	    pm.remove(x,i,val);                    
	        	}
        	}
        	for(int m = 0; m < 3; m++){
        		for(int n = 0; n < 3; n++){
                	pm.remove(m + 3*(x/3),n + 3*(y/3),val);
        		}
        	}            
        }
        else if(guessValue(x,y) == val){
            guessBoard[y][x] = 0;
        }
    }    
}
