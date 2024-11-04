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