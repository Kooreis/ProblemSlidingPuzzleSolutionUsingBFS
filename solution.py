```python
def valid_moves(board, i, j):
    moves = [(i-1, j), (i+1, j), (i, j-1), (i, j+1)]
    return [(x, y) for x, y in moves if 0 <= x < len(board) and 0 <= y < len(board[0])]

def make_move(board, i, j, x, y):
    new_board = [row[:] for row in board]
    new_board[i][j], new_board[x][y] = new_board[x][y], new_board[i][j]
    return new_board
```