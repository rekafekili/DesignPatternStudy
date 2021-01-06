package com.example.designpatternstudy.view_mvp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.designpatternstudy.R
import com.example.designpatternstudy.presenter.TicTacToePresenter
import kotlinx.android.synthetic.main.tictactoe.*

class TicTacToeActivity : AppCompatActivity(), TicTacToeView{
    private val TAG = "TicTacToeActivity"

    private val presenter = TicTacToePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tictactoe)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_reset -> {
                presenter.onResetSelected()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    
    fun onCellClick(view: View) {
        val button = view as Button
        val tag = button.tag.toString()
        val row = tag.substring(0,1).toInt()
        val col = tag.substring(1,2).toInt()
        Log.d(TAG, "onCellClick: [$row, $col]")

        presenter.onButtonSelected(row, col)
    }

    override fun showWinner(winnerLabel: String) {
        winner_linear.visibility = View.VISIBLE
        winner_name_textview.text = winnerLabel
    }

    override fun clearWinnerDisplay() {
        winner_linear.visibility = View.GONE
        winner_name_textview.text = ""
    }

    override fun clearButtons() {
        for(i in 0 until board_grid.childCount) {
            val btn = board_grid.getChildAt(i) as Button
            btn.text = ""
        }
    }

    override fun setButtonText(row: Int, col: Int, text: String) {
        val btn = board_grid.findViewWithTag("$row$col") as Button
        btn.text = text
    }
}