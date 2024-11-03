Here is a Java console application that solves the sliding puzzle problem using Breadth-First Search (BFS):

```java
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

class BFS {
    private Board initial;
    private Board goal;

    public BFS(Board initial, Board goal) {
        this.initial = initial;
        this.goal = goal;
    }

    public void solve() {
        Queue<Board> queue = new LinkedList<>();
        Set<Board> visited = new HashSet<>();

        queue.add(initial);
        visited.add(initial);

        while (!queue.isEmpty()) {
            Board current = queue.poll();

            if (current.equals(goal)) {
                current.printPath();
                return;
            }

            for (Board next : current.neighbors()) {
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }

        System.out.println("No solution found");
    }
}

class Board {
    private int[][] tiles;
    private int zeroR;
    private int zeroC;
    private Board prev;

    public Board(int[][] tiles) {
        this.tiles = tiles;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (tiles[r][c] == 0) {
                    zeroR = r;
                    zeroC = c;
                }
            }
        }
    }

    public List<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int newZeroR = zeroR + direction[0];
            int newZeroC = zeroC + direction[1];

            if (newZeroR >= 0 && newZeroR < 3 && newZeroC >= 0 && newZeroC < 3) {
                int[][] newTiles = copyTiles();
                newTiles[zeroR][zeroC] = newTiles[newZeroR][newZeroC];
                newTiles[newZeroR][newZeroC] = 0;
                Board neighbor = new Board(newTiles);
                neighbor.prev = this;
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    public void printPath() {
        if (prev != null) {
            prev.printPath();
        }
        System.out.println(this);
    }

    private int[][] copyTiles() {
        int[][] copy = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                copy[r][c] = tiles[r][c];
            }
        }
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                sb.append(tiles[r][c]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
```

This program will print the steps to solve the sliding puzzle from the initial state to the goal state. If no solution is found, it will print "No solution found".