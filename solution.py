Here is a Python console application that solves the sliding puzzle problem using Breadth-First Search (BFS):

```python
from queue import Queue

def print_board(board):
    for row in board:
        print(' '.join(row))
    print()

def find_empty(board):
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] == '_':
                return i, j

def valid_moves(board, i, j):
    moves = [(i-1, j), (i+1, j), (i, j-1), (i, j+1)]
    return [(x, y) for x, y in moves if 0 <= x < len(board) and 0 <= y < len(board[0])]

def make_move(board, i, j, x, y):
    new_board = [row[:] for row in board]
    new_board[i][j], new_board[x][y] = new_board[x][y], new_board[i][j]
    return new_board

def bfs(board, goal):
    queue = Queue()
    queue.put((board, []))
    visited = set([str(board)])

    while not queue.empty():
        board, path = queue.get()
        if board == goal:
            return path

        i, j = find_empty(board)
        for x, y in valid_moves(board, i, j):
            new_board = make_move(board, i, j, x, y)
            if str(new_board) not in visited:
                queue.put((new_board, path + [(i, j, x, y)]))
                visited.add(str(new_board))

    return None

def solve_puzzle(board, goal):
    path = bfs(board, goal)
    if path is None:
        print('No solution found.')
        return

    for i, j, x, y in path:
        print('Move empty tile from ({}, {}) to ({}, {})'.format(i, j, x, y))
        board = make_move(board, i, j, x, y)
        print_board(board)

board = [['1', '2', '3'], ['4', '_', '6'], ['7', '5', '8']]
goal = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '_']]

solve_puzzle(board, goal)
```

This program first finds the empty tile on the board, then generates all valid moves from that position. It then uses BFS to explore the state space of the puzzle until it finds a solution. The solution is then printed out as a series of moves from the initial state to the goal state.