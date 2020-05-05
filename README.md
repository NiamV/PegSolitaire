# Triangle Peg Solitaire

This is an algorithm to calculate the number of solutions to a Triangle Peg Solitaire Problem, as described in Matt Parker's Maths Problems Problem 5: https://www.youtube.com/watch?v=TEkJMFTyZwM.

## Description of the Files

The code is written in Scala. There are 4 different files:

- `board.scala`

This contains a class which represents the triangular board with a given number of rows, a set of positions which are filled and a list of valid moves that can be made. It contains functions: `remove` which takes a piece off the board, `canMove` which checks if a prospective move is valid and `makeMove` which actually performs the move.

- `BFS.scala`

This performs a breadth first search of the possible moves, including multi-jump moves. This will print out all of the possible paths in order of length, and finally print the total number of paths.

- `DFS.scala`

This performs a depth first search of the possible moves, including multi-jump moves. This will output the total number of ways of moving to each possible output position.

- `DFSSingleMoves.scala`

This performs a depth first search of the possible moves, where only one jump can be made per move. This will output the total number of ways of moving to each possible output position.

## How to Use

To run the code, first compile all of the files with the command:

```fsc board.scala BFS.scala DFS.scala DFSSingleMoves.scala```

Then if you want to run `file.scala`, run the command:

```scala file m n ```

where `m` is the number of rows in the triangle board, and `n` is the position of the piece you want to remove first.