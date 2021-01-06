package com.example.designpatternstudy.presenter

import com.example.designpatternstudy.model.Board
import com.example.designpatternstudy.view_mvp.TicTacToeView

class TicTacToePresenter(private val view: TicTacToeView) : Presenter {
    private var model: Board = Board()

    override fun onCreate() {
        model = Board()
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }

    fun onButtonSelected(row: Int, col: Int) {
        val playerMoved = model.mark(row, col)

        if(playerMoved != null) {
            view.setButtonText(row, col, playerMoved.toString())

            if(model.winner != null) {
                view.showWinner(playerMoved.toString())
            }
        }
    }

    fun onResetSelected() {
        view.clearWinnerDisplay()
        view.clearButtons()
        model.restart()
    }

}