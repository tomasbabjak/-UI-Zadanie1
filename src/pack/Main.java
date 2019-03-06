package pack;

import java.util.Scanner;

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

        if(isValid(x-2,y+1, length) && isEmpty(board, x-2, y+1)) {
            n = getNumber(x - 2, y + 1, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x - 2;
                nextX[1] = y + 1;
            }
        }
        if(isValid(x-2,y-1, length) && isEmpty(board, x-2, y-1)) {
            n = getNumber(x - 2, y - 1, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x - 2;
                nextX[1] = y - 1;
            }
        }
        if(isValid(x-1,y+2, length) && isEmpty(board, x-1, y+2)) {
            n = getNumber(x - 1, y + 2, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x - 1;
                nextX[1] = y + 2;
            }
        }
        if(isValid(x-1,y-2, length) && isEmpty(board, x-1, y-2)) {
            n = getNumber(x - 1, y - 2, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x - 1;
                nextX[1] = y - 2;
            }
        }
        if(isValid(x+2,y+1, length) && isEmpty(board, x+2, y+1)) {
            n = getNumber(x + 2, y + 1, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x + 2;
                nextX[1] = y + 1;
            }
        }
        if(isValid(x+2,y-1, length) && isEmpty(board, x+2, y-1)) {
            n = getNumber(x + 2, y - 1, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x + 2;
                nextX[1] = y - 1;
            }
        }
        if(isValid(x+1,y+2, length) && isEmpty(board, x+1, y+2)) {
            n = getNumber(x + 1, y + 2, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x + 1;
                nextX[1] = y + 2;
            }
        }
        if(isValid(x+1,y-2, length) && isEmpty(board, x+1, y-2)) {
            n = getNumber(x + 1, y - 2, board, length);
            if (n < min) {
                min = n;
                nextX[0] = x + 1;
                nextX[1] = y - 2;
            }
        }

        if(min != N+1)
            return true;
        else
            return false;

    }

    static boolean findRoute() throws Exception{

        int number = 0;
        int next[] = new int[2];
        int counter = 1;

        System.out.print("Zadaj dlzku hrany\n");
        Scanner s;
        s = new Scanner(System.in);
        int i = s.nextInt();

        int board[][] = new int[i][i];

        newBoard(board,i);

        board[0][0] = 1;
        next[0] = 0;
        next[1] = 0;

        while(Warnsdorff(next, board, i, next[1], next[0])){
            board[next[1]][next[0]] = ++counter;
            for(int j = 0; j < i; j++){
                for(int k = 0; k < i; k++){
                    System.out.print(board[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        for(int j = 0; j < i; j++){
            for(int k = 0; k < i; k++){
                System.out.print(board[j][k] + " ");
            }
            System.out.println();
        }

        if(counter == i*i) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception{
        findRoute();
    }
}