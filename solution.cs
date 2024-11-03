```C#
using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    static void Main(string[] args)
    {
        int[,] initial = { { 1, 2, 3 }, { 5, 0, 6 }, { 7, 8, 4 } };
        int[,] goal = { { 1, 2, 3 }, { 5, 8, 6 }, { 0, 7, 4 } };
        Puzzle puzzle = new Puzzle(initial, goal);
        puzzle.Solve();
    }
}

public class Puzzle
{
    private int[,] initial;
    private int[,] goal;
    private int size;

    public Puzzle(int[,] initial, int[,] goal)
    {
        this.initial = initial;
        this.goal = goal;
        this.size = initial.GetLength(0);
    }

    public void Solve()
    {
        Node root = new Node(initial, 0, null);
        Queue<Node> queue = new Queue<Node>();
        queue.Enqueue(root);

        while (queue.Count > 0)
        {
            Node node = queue.Dequeue();
            if (IsGoal(node.State))
            {
                PrintPath(node);
                return;
            }

            foreach (var child in ExpandNode(node))
            {
                queue.Enqueue(child);
            }
        }
    }

    private bool IsGoal(int[,] state)
    {
        return state.Cast<int>().SequenceEqual(goal.Cast<int>());
    }

    private void PrintPath(Node node)
    {
        if (node.Parent != null)
        {
            PrintPath(node.Parent);
        }

        PrintState(node.State);
        Console.WriteLine();
    }

    private void PrintState(int[,] state)
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                Console.Write(state[i, j] + " ");
            }
            Console.WriteLine();
        }
    }

    private IEnumerable<Node> ExpandNode(Node node)
    {
        List<Node> successors = new List<Node>();

        int zeroRow = 0, zeroCol = 0;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (node.State[i, j] == 0)
                {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }

        int[,] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < 4; i++)
        {
            int newRow = zeroRow + directions[i, 0];
            int newCol = zeroCol + directions[i, 1];
            if (IsValid(newRow, newCol))
            {
                int[,] newState = CopyState(node.State);
                newState[zeroRow, zeroCol] = newState[newRow, newCol];
                newState[newRow, newCol] = 0;
                successors.Add(new Node(newState, node.Depth + 1, node));
            }
        }

        return successors;
    }

    private bool IsValid(int row, int col)
    {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private int[,] CopyState(int[,] state)
    {
        return (int[,])state.Clone();
    }
}

public class Node
{
    public int[,] State { get; set; }
    public int Depth { get; set; }
    public Node Parent { get; set; }

    public Node(int[,] state, int depth, Node parent)
    {
        this.State = state;
        this.Depth = depth;
        this.Parent = parent;
    }
}
```