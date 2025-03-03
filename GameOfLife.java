import java.util.Arrays;

public class GameOfLife implements Board {

    private int[][] board;

    public GameOfLife(int x, int y) {
        board = new int[x][y];
    }

    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }
//I added a new method
    public void step() {
        int[][] newBoard = new int[board.length][board[0].length];
        
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int neighbors = countNeighbors(x, y);
                if (board[x][y] == 1) {
                    newBoard[x][y] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    newBoard[x][y] = (neighbors == 3) ? 1 : 0;
                }
            }
        }
        board = newBoard;
        print();
    }

    public int countNeighbors(int x, int y) { 
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    count += get(x + i, y + j);
                }
            }
        }
        return count;
    }
    
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    public int get(int x, int y) { 
        int xLimit = board.length; 
        int yLimit = board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }

    public int[][] get() {
        return board;
    }

    public void print() {
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == 1) {
                    System.out.print("⬛");
                } else {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();

    }
}
