package pack;

import java.util.Random;
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

    static boolean isValid(int x, int y, int length, int [][] board){

        if(( x < length && y < length) && (x >= 0 && y >= 0) && isEmpty(board, x, y))
            return true;
        else return false;
    }

    static boolean isEmpty(int [][] board, int x, int y){

        if(board[y][x] > -1) return false;
        else return true;
    }

    static int getNumber(int x, int y, int [][] board, int length){

        int num = 0;

        for(HopCoordinates h : HopCoordinates.values()) {
            if (isValid(x + h.x(), y + h.y(), length, board))
                num++;
        }

        return num;
    }

    static boolean Warnsdorff(int nextX[], int [][] board, int length, int yy, int xx, int counter, int [][] table){

        int min = N+1;
        int n;
        int i = 0;
        int help;

        int x = xx;
        int y = yy;

        for(HopCoordinates h : HopCoordinates.values()){
            if(isValid(x + h.x(),y + h.y(), length, board)) {
                n = getNumber(x + h.x(), y + h.y(), board, length);
                if (n < min) {
                    help = table[length*yy+xx][0];
                    table[length*yy+xx][0] = length*(y + h.y()) + x + h.x();
                    table[length*yy+xx][++i] = help;
                    min = n;
                    nextX[0] = x + h.x();
                    nextX[1] = y + h.y();
                }
                else {
                    table[length * yy + xx][i++] = length * (y + h.y()) + x + h.x();
                }
            }
        }

        if (min == N + 1) return false;

        while(table[length*yy+xx][0] != -1) {

            int yTable = table[length*yy+xx][0] / length;
            int xTable = table[length*yy+xx][0] % length;

            if (counter == length * length) {
                board[yTable][xTable] = counter;
                return true;
            }

            board[yTable][xTable] = counter;

            if (Warnsdorff(nextX, board, length, yTable, xTable, counter + 1, table))
                return true;
            else {
                removeHead(table, length, yy, xx);
                board[yTable][xTable] = -1;
            }
        }

        for(int j = 0; j < length; j++){
            for(int k = 0; k < length; k++){
                System.out.print(board[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return false;
    }

    static void removeHead(int [][] table, int length, int yy, int xx){
        for(int i = 0; i < 7; i++){
            table[length*yy+xx][i] = table[length*yy+xx][i+1];
        }
        table[length*yy+xx][7] = -1;
    }

    static void findRoute(int i) throws Exception{

        int number = 0;
        int counter = 1;
        int next[] = new int[2];

        Random rand = new Random();
        int randomX = rand.nextInt(i);
        int randomY = rand.nextInt(i);

        int board[][] = new int[i][i];

        newBoard(board,i);

        board[randomY][randomX] = 1;
        next[0] = randomX;
        next[1] = randomY;

        int table[][] = new int [i*i][8];
        for(int j = 0; j < i*i; j++){
            for(int k = 0; k < 8; k++)
                table[j][k] = -1;
        }

        Warnsdorff(next, board, i, next[1], next[0], counter+1, table);

        for(int j = 0; j < i; j++){
            for(int k = 0; k < i; k++){
                System.out.print(board[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();

      /*  if(counter == i*i) return true;
        else return false;*/
    }

    public static void main(String[] args) throws Exception{

       /* System.out.print("Zadaj dlzku hrany\n");
        Scanner s;
        s = new Scanner(System.in);
        int in = s.nextInt();
*/
        for(int i = 0; i < 1; i++) {
            findRoute(8);
        }
    }
}