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

    static int getNumber(int x, int y, int [][] board, int length){

        int num = 0;

        if(isValid(x-2,y+1, length) && isEmpty(board, x-2, y+1))
            num++;
        if(isValid(x-2,y-1, length) && isEmpty(board, x-2, y-1))
            num++;
        if(isValid(x-1,y+2, length) && isEmpty(board, x-1, y+2))
            num++;
        if(isValid(x-1,y-2, length) && isEmpty(board, x-1, y-2))
            num++;
        if(isValid(x+2,y+1, length) && isEmpty(board, x+2, y+1))
            num++;
        if(isValid(x+2,y-1, length) && isEmpty(board, x+2, y-1))
            num++;
        if(isValid(x+1,y+2, length) && isEmpty(board, x+1, y+2))
            num++;
        if(isValid(x+1,y-2, length) && isEmpty(board, x+1, y-2))
            num++;

        return num;
    }

    static boolean Warnsdorff(int nextX[], int [][] board, int length, int yy, int xx){

        int min = N+1;
        int n;

        int x = xx;
        int y = yy;

        n = getNumber(x - 2, y + 1, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x - 2;
            nextX[1] = y + 1;
        }

        n = getNumber(x - 2, y - 1, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x - 2;
            nextX[1] = y - 1;
        }

        n = getNumber(x - 1, y + 2, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x - 1;
            nextX[1] = y + 2;
        }

        n = getNumber(x - 1, y - 2, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x - 1;
            nextX[1] = y - 2;
        }

        n = getNumber(x + 2, y + 1, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x + 2;
            nextX[1] = y + 1;
        }

        n = getNumber(x + 2, y - 1, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x + 2;
            nextX[1] = y - 1;
        }

        n = getNumber(x + 1, y + 2, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x + 1;
            nextX[1] = y + 2;
        }

        n = getNumber(x + 1, y - 2, board, length);
        if (n < min) {
            min = n;
            nextX[0] = x + 1;
            nextX[1] = y - 2;
        }

        if(min != N+1)
            return true;
        else
            return false;

    }

    public static void main(String[] args) {
	// write your code here
    }
}