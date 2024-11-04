import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] initialBoard = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}};
        int[][] goalBoard = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        Board initial = new Board(initialBoard);
        Board goal = new Board(goalBoard);

        BFS bfs = new BFS(initial, goal);
        bfs.solve();
    }
}