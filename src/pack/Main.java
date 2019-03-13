package pack;

import java.util.Random;

public class Main {

    private static final int N = 8;
    public static int C = 2;

    static void newBoard(int[][] board, int a, int b) {
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                board[i][j] = -1;
            }
        }
    }

    static boolean isValid(int x, int y, int length, int[][] board) {
        if ((x < length && y < length) && (x >= 0 && y >= 0) && isEmpty(board, x, y))
            return true;
        else return false;
    }

    static boolean isEmpty(int[][] board, int x, int y) {
        if (board[y][x] > -1) return false;
        else return true;
    }

    static int getNumber(int x, int y, int[][] board, int length) {

        int num = 0;

        for (HopCoordinates h : HopCoordinates.values()) {
            if (isValid(x + h.x(), y + h.y(), length, board))
                num++;
        }

        return num;
    }

    static void printBoard(int[][] board, int i) {

        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                System.out.print(board[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void sortTable(int[][] tab, int iter) {
        for (int j = 0; j < iter; j++) {
            for (int k = 0; k < iter; k++) {
                if (tab[j][0] < tab[k][0] || tab[k][0] == -1) {
                    int temp = tab[k][0];
                    tab[k][0] = tab[j][0];
                    tab[j][0] = temp;
                    temp = tab[k][1];
                    tab[k][1] = tab[j][1];
                    tab[j][1] = temp;
                }
            }
        }
    }

    static boolean Warnsdorff(int[][] board, int length, int yy, int xx, int counter, int[][] table) {

        //int min = N + 1;
        int n;
        int iter = 0;
        int tab[][] = new int[8][2];

        //set up local table
        for (int j = 0; j < 8; j++) {
            tab[j][0] = -1;
            tab[j][1] = -1;
        }

        //for every possible and valid move of knight get coordinates and
        //number of possible hops from that square
        for (HopCoordinates h : HopCoordinates.values()) {
            if (isValid(xx + h.x(), yy + h.y(), length, board)) {
                n = getNumber(xx + h.x(), yy + h.y(), board, length);
                tab[iter][0] = n;
                tab[iter][1] = length * (yy + h.y()) + xx + h.x();
                iter++;
                // if (min > n) min = n;
            }
        }

        //sort table by no. of possible hops and insert to main table for every square of board.
        //insert only information about coordinates of next hops
        sortTable(tab, iter);
        for (int j = 0; j < 8; j++) {
            table[length * yy + xx][j] = tab[j][1];
        }

        //Using depth first search with recursive call of  Warnsdorff{} function,
        //which returns true when successfully reached last square on board, else returns false and
        //have to remove first hop coordinates from array and try another one.
        while (table[length * yy + xx][0] != -1) {
            int yTable = table[length * yy + xx][0] / length;
            int xTable = table[length * yy + xx][0] % length;

            if (counter == length * length) {
                board[yTable][xTable] = counter;
                return true;
            }

            //To stop looking for solution after more than 2 million attempts.
            C++;
            if (C > 2000000) return false;

            board[yTable][xTable] = counter;

            if (Warnsdorff(board, length, yTable, xTable, counter + 1, table))
                return true;
            else {
                removeHead(table, length, yy, xx);
                board[yTable][xTable] = -1;
            }
        }
        //printBoard(board, length);
        return false;
    }

    static void removeHead(int[][] table, int length, int yy, int xx) {
        for (int i = 0; i < 7; i++) {
            table[length * yy + xx][i] = table[length * yy + xx][i + 1];
        }
        table[length * yy + xx][7] = -1;
    }

    static boolean findRoute(int i) {

        int counter = 1;
        int next[] = new int[2];

        Random rand = new Random();
        int randomX = rand.nextInt(i);
        int randomY = rand.nextInt(i);

        int board[][] = new int[i][i];

        newBoard(board, i, i);

        board[randomY][randomX] = 1;
        next[0] = randomX;
        next[1] = randomY;

        int table[][] = new int[i * i][8];

        newBoard(table, i * i, 8);

        if (Warnsdorff(board, i, next[1], next[0], counter + 1, table)) {
            //printBoard(board, i);
            return true;
        } else {
            //printBoard(board, i);
            return false;
        }
    }

    static void testScenario(int in) {

        int notFound = 0, firstFound = 0, notFirstFound = 0;

        System.out.print("\n\nTesting Knights tour on " + in + "x" + in + " chess board with random starting position..\n");

        for (int i = 0; i < 20; i++) {
            if (!findRoute(in))
                notFound++;
            else {
                if (C == in * in)
                    firstFound++;
                if (C > in * in && C < 2000000)
                    notFirstFound++;
            }
            C = 2;
        }
        System.out.print("Solution was found on the first try " + firstFound + " times, \n");
        System.out.print("solution was found but not on the first try " + notFirstFound + " times and \n");
        System.out.print("solution was not found  " + notFound + " times out of 20.\n");
    }

    public static void main(String[] args) throws Exception {

       /* System.out.print("Zadaj dlzku hrany\n");
        Scanner s;
        s = new Scanner(System.in);
        int in = s.nextInt();*/

        testScenario(5);
        testScenario(6);
        testScenario(7);
        testScenario(8);
        testScenario(10);
        testScenario(14);
        testScenario(20);


//        for (int i = 0; i < 20; i++) {
//            if(findRoute(in) == false) System.out.print("Solution was not found.\n");
//            else {
//                if (C == in * in) System.out.print("Successfully found on the first try.\n");
//                if (C > in * in && C < 1999999) System.out.print("Found, but not on the first try.\n");
//            }
//            System.out.print(C + "\n");
//            C = 2;
//        }
    }
}