import java.util.Random;
import java.util.ArrayList;

/**
 * the class that stores all of the info in the givens of the sudoku board
 * @author Kobi Hall
 * @version 5/8
 */

public class SudokuBoard
{
    private int[][] givenBoard;
    private Random rng;

    /**
     *instantiates the 2D array that represents the board
     */
    public SudokuBoard()
    {
        rng = new Random();
        givenBoard = new int[9][9];
        //a default sudoku board, with ordered permutations of all the numbers
        //shuffleBoard();
    }

    /**
     *begins with a preselected board. Proceeds to randomly flip, transpose, or rotate the board. Then it will randomly shuffle the rows and columns
     *of the board, yeilding a unique filled board.
     */
    public void shuffleBoard(){
        int[][] newBoard = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            {4, 5, 6, 7, 8, 9, 1, 2, 3},
                            {7, 8, 9, 1, 2, 3, 4, 5, 6},
                            {2, 3, 1, 5, 6, 4, 8, 9, 7},
                            {5, 6, 4, 8, 9, 7, 2, 3, 1},
                            {8, 9, 7, 2, 3, 1, 5, 6, 4},
                            {3, 1, 2, 6, 4, 5, 9, 7, 8},
                            {6, 4, 5, 9, 7, 8, 3, 1, 2},
                            {9, 7, 8, 3, 1, 2, 6, 4, 5}};
        givenBoard = newBoard;
    	newBoard = new int[9][9];


        int perm = rng.nextInt(8);//randomly chooses one of the symmetries of the square
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(perm == 1){
                    newBoard[j][8-i] = givenBoard[i][j];
                }
                else if(perm == 2){
                    newBoard[8-i][8-j] = givenBoard[i][j];
                }
                else if(perm == 3){
                    newBoard[8-j][i] = givenBoard[i][j];
                }
                else if(perm == 4){
                    newBoard[i][8-j] = givenBoard[i][j];
                }
                else if(perm == 5){
                    newBoard[8-i][j] = givenBoard[i][j];
                }
                else if(perm == 6){
                    newBoard[j][i] = givenBoard[i][j];
                }
                else if(perm == 7){
                    newBoard[8-j][8-i] = givenBoard[i][j];
                }
            }
        }
        givenBoard = newBoard;
        newBoard = new int[9][9];



    	for(int i = 0; i < 3; i++){
    		ArrayList<Integer> index = new ArrayList<>();
    		index.add(0);
    		index.add(1);
    		index.add(2);
    		for(int j = 0; j < 3; j++){
    			int row = index.remove(rng.nextInt(index.size()));
    			for(int k = 0; k < 9; k++){
    				newBoard[j+i*3][k] = givenBoard[row+i*3][k];
    			}
    		}
    	}
    	givenBoard = newBoard;
    	newBoard = new int[9][9];
    	for(int i = 0; i < 3; i++){
    		ArrayList<Integer> index = new ArrayList<>();
    		index.add(0);
    		index.add(1);
    		index.add(2);
    		for(int j = 0; j < 3; j++){
    			int col = index.remove(rng.nextInt(index.size()));
    			for(int k = 0; k < 9; k++){
    				newBoard[k][j+i*3] = givenBoard[k][col+i*3];
    			}
    		}
    	}
    	givenBoard = newBoard;
    }

    /**
     *@return the value at a given cell
     */
    public int givenValue(int x, int y){
    	return givenBoard[y][x];
    }

    /**
     *sets the value at a given cell
     */
    public void setGiven(int x, int y, int val){
        givenBoard[y][x] = val;
    }
}
