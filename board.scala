class board(rows: Int, p: Array[Boolean]){
    val n = rows * (rows + 1) / 2           // Number of triangles
    
    var pieces = p

    def sourceToCoordinates(source: Int): (Int, Int) = {
        var v = 0                               // Vertical displacement
        while(v < rows && source >= ((v+1)*(v+2)/2)){
            v += 1
        }
        val h = source - (v*(v+1)/2)            // Horizontal displacement

        return(v, h)
    }

    def coordinatesToSource(coord: (Int, Int)): Int = {
        return (coord._1 * (coord._1 + 1) / 2) + coord._2
    }

    def valid(source: Int): List[(Int, Int)] = {
        var m = 0                               // m is the middle position
        var e = 0                               // e is the end position
        val (v,h) = sourceToCoordinates(source)

        var possMoves: List[(Int, Int)] = List()
        
        // Top left
        if(v >=2 && h >= 2){
            possMoves = (coordinatesToSource(v-2, h-2), coordinatesToSource(v-1, h-1))::possMoves
        }

        // Top right
        if(v >= 2 && h <= v-2){
            possMoves = (coordinatesToSource(v-2, h), coordinatesToSource(v-1, h))::possMoves
        }

        // Right
        if(h <= v-2){
            possMoves = (coordinatesToSource(v, h+2), coordinatesToSource(v, h+1))::possMoves
        }

        // Bottom right
        if(v <= rows - 3){
            possMoves = (coordinatesToSource(v+2, h+2), coordinatesToSource(v+1, h+1))::possMoves
        }

        // Bottom left
        if(v <= rows - 3){
            possMoves = (coordinatesToSource(v+2, h), coordinatesToSource(v+1, h))::possMoves
        }

        // Right
        if(h >= 2){
            possMoves = (coordinatesToSource(v, h-2), coordinatesToSource(v, h-1))::possMoves
        }

        return possMoves

    }
    
    val validMoves = for (x <- 0 until n) yield valid(x)

    // Remove first peice from the board
    def remove(i: Int): Unit = {
        pieces(i) = false
    }

    // Checks if a move is valid
    def canMove(start: Int, end: Int): Boolean = {
        var valid = true
        // Two triangles away
        if(!(validMoves(start).map(_._1) contains end)){
            valid = false
        }

        // Empty square and piece to move
        if(pieces(end) || !pieces(start)){
            valid = false
        }

        // Non-empty square inbetween
        val i = validMoves(start).map(_._1).indexOf(end)
        if(i != -1 && !pieces(validMoves(start)(i)._2)){
            valid = false
        }

        return valid
    }

    // Apply the move
    def makeMove(start: Int, end: Int): board = {
        require(canMove(start, end))
        val i = validMoves(start).map(_._1).indexOf(end)
        
        val p = new Array[Boolean](n)
        for(i <- 0 until pieces.length){
            p(i) = pieces(i)
        }

        val newBoard = new board(rows, p)
        newBoard.pieces(validMoves(start)(i)._2) = false
        newBoard.pieces(start) = false
        newBoard.pieces(end) = true
        return newBoard
    }

    // Check if there is one piece left on the board
    def finished: Boolean = {
        if(pieces.filter(_==true).length == 1){
            return true
        } else{
            return false
        }
    } 

    // Debugging function
    def printPieces: Unit = {
        for(p <- pieces){
            print(p.toString + " ")
        }
        println
    }
}
