package com.example.designpatternstudy.view_mvp

interface TicTacToeView {
    fun showWinner(winnerLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(row: Int, col: Int, text: String)
}