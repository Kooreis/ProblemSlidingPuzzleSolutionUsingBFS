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
}