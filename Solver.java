/**
 * A class that can solve a sudoku puzzle given a premade board.
 * 
 * @author Kobi Hall
 * @version 5/8
 */

public class Solver
{
    private SudokuBoard board;
    private GuessBoard guess;
    private PencilMark pm;
    private boolean madeGuess;
    
    /**
     * instatiates using a sudoku board
     */
    public Solver(SudokuBoard b){
        board = b;
        pm = new PencilMark(); //creates a pencil mark board unique to the solver
        guess = new GuessBoard(b,pm); //creates a guess board unique to the solver
        madeGuess = true;
    }

    /**
     *fills the entire pencil mark board with possible values
     */
    public void fillPM(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board.givenValue(j,i) == 0){
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
     *the solver will fill all possible values in the pencil mark board. It will proceed to loop through guessing techniques until it can no longer make a move.
     *if the board is full at this point, it is deemed solveable and will return true. If the board still has empty cells after running out of valid moves
     *the board is deemed unsolveable and will return false.
     */
    public boolean isSolveable(){
    	fillPM();
    	while(madeGuess == true){
    		madeGuess = false;
    		loneSingles();
    		hiddenSingles();
    	}
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			if(board.givenValue(i,j) == 0 && guess.guessValue(i,j) == 0){
    				return false;
    			}
    		}
    	}
    	return true;
    }

    /**
     *The method of lone singles checks to see if a cell only has a single value that it could possibly be.
     *The solver will loop through every cell, if there is only one pencil mark at that cell, the solver will make a guess.
     */
    public void loneSingles(){
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			if(pm.value(j,i).size() == 1){
    				guess.makeGuess(j,i,pm.value(j,i).get(0));
    				madeGuess = true;
    			}
    		}
    	}
    }

    /**
     *The method of hidden singles checks to see if there is only one possible place for a value in the same row, column, or sub board.
     *The solver loops through every cell, and loops through each pencil mark at each cell. Within that loop the solver checks to see if there
     *are any other pencil marks with the same value in the same row, column, or sub board. If there are the solver will make a guess.
     */
    public void hiddenSingles(){
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){ 

    			if(board.givenValue(i,j) == 0 && guess.guessValue(i,j) == 0){ //loops through each empty cell
    				for(int v = 0; v < pm.value(i,j).size(); v++){ //loops through each possible value
    					boolean condition1 = false;
    					boolean condition2 = false;
    					boolean condition3 = false;
    					for(int l = 0; l < 9; l++){
   				    	    if(l != j && (pm.value(i,l).contains(pm.value(i,j).get(v)))){
   				    	        condition1 = true;
   				    	    }
   				    	}
   				    	for(int l = 0; l < 9; l++){
   				    	    if(l != i && (pm.value(l,j).contains(pm.value(i,j).get(v)))){
   				    	        condition2 = true;
   				    	    }
   				    	}
    					for(int m = 0; m < 3; m++){
    						for(int n = 0; n < 3; n++){
    							if(!(m + 3*(i/3) == i && n + 3*(j/3) == j) && (pm.value(m + 3*(i/3),n + 3*(j/3)).contains(pm.value(i,j).get(v)) )) {
                				    condition3 = true;
                				}
    						}
    					}
    					if(!condition1 || !condition2 || !condition3){
    						guess.makeGuess(i,j,pm.value(i,j).get(v));
    						madeGuess = true;
    					}
    				}
    			}
    		}
    	}
    }
   
   /**
    *checks if a value is already in a given column or row. 
    *@return returns true if val is already given or guessed in the column or row of (x,y).
    */
   public boolean inColsRows(int x, int y, int val){
        for(int i = 0; i < 9; i++){
            if(i != y && (board.givenValue(x,i) == val || guess.guessValue(x,i) == val)){
                return true;
            }
        }
        for(int i = 0; i < 9; i++){
            if(i != x && (board.givenValue(i,y) == val || guess.guessValue(i,y) == val)){
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
}
