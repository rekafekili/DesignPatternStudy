package com.example.designpatternstudy.viewmodel

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import com.example.designpatternstudy.model.Board

class TicTacToeViewModel : ViewModel {
    private val model = Board()
    val cells = ObservableArrayMap<String, String>()
    val winner = ObservableField<String>()

    override fun onCreate() { }

    override fun onPause() { }

    override fun onResume() { }

    override fun onDestroy() { }

    fun onResetSelected() {
        model.restart()
        winner.set(null)
        cells.clear()
    }

    fun onClickedCellAt(row: Int, col: Int) {
        val playerMoved = model.mark(row, col)
        cells["$row$col"] = playerMoved?.toString()
        winner.set(model.winner?.toString())
    }
}