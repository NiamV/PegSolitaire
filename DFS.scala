object DFS{
    // Function that finds all of the possible moves from a source piece
    // Uses a DFS search through the grid
    def moves(b: board, start: Int): List[List[Int]] = {
        // Create stack for DFS
        // Triple of current board, source and path so far
        val stack2 = new scala.collection.mutable.Stack[(board, Int, List[Int])]
        stack2.push((b, start, List()))

        // List that moves will be added to
        var possMoves: List[List[Int]] = List()

        while(!stack2.isEmpty){
            // Dequeue the top of the queue
            val p = stack2.pop
            val s = p._2
            val path = p._3

            // Iterate through potential moves
            for(j <- 0 until b.n){
                if(p._1.canMove(s, j)){
                    // Make the move and add move to the path so far
                    val newb = p._1.makeMove(s, j)
                    val l = p._3.concat(List(j))

                    // Put this new triple back on the queue to be explored again
                    stack2.push((newb, j, l))

                }
            }

            // Add the path so far onto the possible moves
            var possMove = List(path)
            possMoves = possMoves:::possMove
             
        }
        return possMoves.tail
    }

    def solve(b: board): Unit = {
        // Create a stack for the DFS
        // Double containing the board and path
        val stack = new scala.collection.mutable.Stack[(board, String)]
        stack.push((b, ""))

        var found = false
        var count = new Array[Int](b.n)

        // Repeat until the queue is empty
        while(!stack.isEmpty){
            val p = stack.pop

            // If we have one piece left on the board, return this
            if((p._1).finished){
                found = true
                //println(p._2)

                var x = p._1.pieces.indexOf(true)
                count(x) += 1
            }
            else{
                var currentBoard = p._1
                var currentPath = p._2

                //Iterate through the pieces to be moved
                for(i <- 0 until b.n){
                    // Find the possible moves this piece can do
                    var m = moves(currentBoard, i)

                    // Tracker of where the piece is
                    var k = i

                    for(elem <- m){
                        // Reset values
                        var newb = currentBoard
                        var newpath = currentPath
                        k = i

                        // Apply each move
                        for(move <- elem){
                            newb = newb.makeMove(k, move)
                            if(newpath.length == 0 || newpath.last == ' '){
                                newpath = newpath + k.toString + "-" + move.toString
                            } else {
                                newpath = newpath + "-" + move.toString
                            }
                            
                            k = move
                        }
                        newpath = newpath + " "

                        // Add the new path onto the queue for every possible move
                        stack.push((newb, newpath))
                    }
                }
            }
        }
        
        if(!found){
            println("No path found")
        }
        
        for(i <- 0 until b.n){
            println(i, count(i))
        }
    }
    
    def main(args: Array[String]): Unit = {
        val r = args(0).toInt
        
        var fullBoard = new Array[Boolean]( (r * (r+1) / 2))
        fullBoard = fullBoard.map( _ => true )
             
        val b = new board(r, fullBoard)

        val first = args(1).toInt
        b.remove(first)

        solve(b)

    }
}