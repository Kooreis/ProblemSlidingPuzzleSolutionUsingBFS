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