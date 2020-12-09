import java.util.ArrayList;
import java.util.Random;

/**
 * All the rules and generation for sudoku.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SudokuGame
{
    private Random rng;
    private GuessBoard guess;//the guesses the player makes
    private SudokuBoard board;//the board given to the player
    private SudokuBoard finalBoard;//the solved puzzle
    private PencilMark pm;//the possible values an empty cell may take on

    /**
     *instantiates all the objects
     */
    public SudokuGame()
    {
        rng = new Random();
        board = new SudokuBoard();
        finalBoard = new SudokuBoard();
        pm = new PencilMark();
        guess = new GuessBoard(board,pm);
    }
    
    /**
     *shuffles the board to get a unique solved board. creates a temporary empty board. Progressively adds givens to the empty board until it's solveable.
     */
    public void generateBoard(){
        board.shuffleBoard();
        finalBoard = board;
        SudokuBoard newBoard = new SudokuBoard();
        boolean isSolveable = false;
        while(!isSolveable){
            Solver solver = new Solver(newBoard);
            int x;
            int y;
            do{
                x = rng.nextInt(9);
                y = rng.nextInt(9);
            }
            while(newBoard.givenValue(x,y) != 0);
            newBoard.setGiven(x,y,board.givenValue(x,y));
            isSolveable = solver.isSolveable();
        }
        board = newBoard;
        pm.reset();
        guess = new GuessBoard(board,pm);
    }

    /**
     *fills the entire pencil mark board with possible values
     */
    public void fillPM(){
        pm.reset();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board.givenValue(j,i) == 0 && guess.guessValue(j,i) == 0){
                    for(int k = 1; k < 10; k++){
                        if(!inColsRows(j,i,k) && !inSubBoard(j,i,k)){
                            pm.toggle(j,i,k);
                        }
                    }
                }
            }
        }
    }

    /**
     *Checks to see if every guess or given value matches with the solved puzzle
     */
    public boolean isSolved(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board.givenValue(i,j) != finalBoard.givenValue(i,j) && guess.guessValue(i,j) != finalBoard.givenValue(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
    *checks if a value is already in a given column or row. 
    *@return returns true if val is already given or guessed in the column or row of (x,y).
    */
    public boolean inColsRows(int x, int y, int val){
        for(int i = 0; i < 9; i++){
            if(i != y && (givenValue(x,i) == val || guessValue(x,i) == val)){
                return true;
            }
        }
        for(int i = 0; i < 9; i++){
            if(i != x && (givenValue(i,y) == val || guessValue(i,y) == val)){
                return true;
            }
        }
        return false;
    }

    /**
     *check if a value is already in a given sub board. (a sub board is the 3x3 smaller area within the larger board)
     *@return returns true if val is already given or guessed in the sub board of (x,y).
     */
    public boolean inSubBoard(int x, int y, int val){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!(i + 3*(x/3) == x && j + 3*(y/3) == y) && (board.givenValue(i + 3*(x/3),j + 3*(y/3)) == val || guess.guessValue(i + 3*(x/3),j + 3*(y/3)) == val)){
                    return true;
                }
            }
        }
        return false;
    }

/*------------------Inherited Methods------------------*/

    public int givenValue(int x, int y){
        return board.givenValue(x,y);
    }

    public void shuffleBoard(){
        board.shuffleBoard();
        pm = new PencilMark();
        guess = new GuessBoard(board,pm);
    }

    public void PMToggle(int x, int y, int val){
        if(guessValue(x,y) == 0){
            pm.toggle(x,y,val);
        }
    }

    public ArrayList<Integer> PMValue(int x, int y){
    	return pm.value(x,y);
    }

    public int guessValue(int x, int y){
        return guess.guessValue(x,y);
    }

    public void makeGuess(int x, int y, int val){
        guess.makeGuess(x,y,val);
    }
}
