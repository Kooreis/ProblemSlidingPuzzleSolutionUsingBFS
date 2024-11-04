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
```