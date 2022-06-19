package algorithms

class TicTacToe(private val n: Int) {
    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    fun move(row: Int, col: Int, player: Int): Int {
        val toAdd = if (player == 1) 1 else -1
        val target = if (player == 1) n else -n
        if (row == col) {
            diag += toAdd
            if (diag == target) return player
        }
        if (row + col == n - 1) {
            antiDiag += toAdd
            if (antiDiag == target) return player
        }
        rows[row] += toAdd
        if (rows[row] == target) return player
        cols[col] += toAdd
        return if (cols[col] == target) player else 0
    }

    // record count('X') - count('O')
    private val rows: IntArray = IntArray(n)
    private val cols: IntArray = IntArray(n)
    private var diag = 0
    private var antiDiag = 0

}