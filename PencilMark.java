import java.util.ArrayList;

/**
 * A pencil mark is a notetaking technique that keeps track of all the possible values that an empty cell can take. 
 *The pencil mark object is essentially a 2D array of Array Lists. Each cell can have anywhere between 0 and 9 possible values.
 * @author Kobi Hall
 * @version 5/8
 */

public class PencilMark{
    // instance variables - replace the example below with your own
    private ArrayList<Integer>[][] pmBoard;

    /**
     * instantiates the array, and the Array List at each cell.
     */
    public PencilMark(){
        pmBoard = new ArrayList[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                pmBoard[i][j] = new ArrayList<>();
            }
        }
    }

    /**
     *gives the pencil marks that have been set at a certain cell
     *@return all the possible values that the user has identified
     */
    public ArrayList<Integer> value(int x, int y){
        return pmBoard[y][x];
    }

    /**
     *removes a certain value from the ArrayList at a given cell
     *@param val the value that is removed at the (x,y) coordinate
     */
    public int remove(int x, int y, int val){
        if(pmBoard[y][x].contains(val)){
            return pmBoard[y][x].remove(pmBoard[y][x].indexOf(val));
        }
        return 0;
    }

    /**
     *"toggles" a value at a certain cell. If the value isn't there, add it. If the value is there, remove it.
     */
    public void toggle(int x, int y, int val){
        if(!pmBoard[y][x].contains(val)){
            pmBoard[y][x].add(val);
        }
        else if(pmBoard[y][x].contains(val)){
            remove(x,y,val);
        }
    }

    /**
     *clears the entire board of pencil marks.
     */
    public void reset(){
        pmBoard = new ArrayList[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                pmBoard[i][j] = new ArrayList<>();
            }
        }
    }
}
