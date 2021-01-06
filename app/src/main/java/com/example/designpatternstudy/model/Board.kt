package com.example.designpatternstudy.model

class Board {
    private enum class GameState { IN_PROGRESS, FINISHED }

    private var cells = Array<ArrayList<Cell>>(3) { arrayListOf() }
    var winner: Player? = null
    private var state: GameState? = null
    private var currentTurn: Player? = null

    init {
        restart()
    }

    /**
     * 새로운 게임을 시작하거나, 재시작하는 메소드
     * 보드와 승리 상태를 리셋한다.
     */
    fun restart() {
        clearCells()
        currentTurn = Player.X
        state = GameState.IN_PROGRESS
    }

    private fun clearCells() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                cells[i].add(Cell())
            }
        }
    }

    fun mark(row: Int, col: Int) : Player {
        var playerMoved: Player? = null

        if(isValid(row, col)) {
            cells[row][col].player = currentTurn
            playerMoved = currentTurn

            if(isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED
                winner = currentTurn
            } else {
                flipCurrentTurn()
            }
        }

        return playerMoved!!
    }

    private fun isValid(row: Int, col: Int): Boolean {
        return if (state == GameState.FINISHED) {
            false
        } else if(isOutOfBounds(row) || isOutOfBounds(col)) {
            false
        } else !isCellValueAlreadySet(row, col)
    }

    private fun isCellValueAlreadySet(row: Int, col: Int): Boolean {
        return cells[row][col].player != null
    }

    private fun isOutOfBounds(idx: Int): Boolean {
        return idx < 0 || idx > 2
    }

    private fun isWinningMoveByPlayer(player: Player?, currentRow: Int, currentCol: Int): Boolean {
        return (cells[currentRow][0].player == player       // 3 in the row
                && cells[currentRow][1].player == player
                && cells[currentRow][2].player == player
                || cells[0][currentCol].player == player    // 3 in the column
                && cells[1][currentCol].player == player
                && cells[2][currentCol].player == player
                || currentRow == currentCol                 // 3 in the diagonal
                && cells[0][0].player == player
                && cells[1][1].player == player
                && cells[2][2].player == player
                || currentRow + currentCol == 2
                && cells[0][2].player == player
                && cells[1][1].player == player
                && cells[2][0].player == player)
    }

    private fun flipCurrentTurn() {
        currentTurn = if(currentTurn == Player.X) Player.O else Player.X
    }
}