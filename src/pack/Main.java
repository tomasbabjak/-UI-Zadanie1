package pack;

public class Main {

    private static final int N = 8;

    static void newBoard(int [][] board, int length){

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                board[i][j] = -1;
            }
        }
    }

    static boolean isValid(int x, int y, int length){

        if(( x <= length && y <= length) && (x > 0 && y > 0))
            return true;
        else return false;
    }

    static boolean isEmpty(int [][] board, int x, int y){

        if(board[y][x] > -1) return false;
        else return true;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
