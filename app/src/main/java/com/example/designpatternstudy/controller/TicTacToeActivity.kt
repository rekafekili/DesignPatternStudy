package com.example.designpatternstudy.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import com.example.designpatternstudy.R
import com.example.designpatternstudy.model.Board
import kotlinx.android.synthetic.main.tictactoe.*

class TicTacToeActivity : AppCompatActivity() {
    private val model = Board()
    private val TAG = "TicTacToeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tictactoe)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_reset) {
            reset()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun reset() {
        winner_linear.visibility = View.GONE
        winner_name_textview.text = ""

        model.restart()

        for(i in 0 until board_grid.childCount) {
            val button = board_grid.getChildAt(i) as Button
            button.text = ""
        }
    }

    fun onCellClick(v: View) {
        val button = v as Button

        val tag = button.tag.toString()
        val row = Integer.valueOf(tag.substring(0, 1))
        val col = Integer.valueOf(tag.substring(1, 2))
        Log.d(TAG, "Clicked Position : [$row, $col]")

        val playerMoved = model.mark(row, col)

        if(playerMoved != null) {
            button.text = playerMoved.toString()
            if(model.winner != null) {
                winner_linear.visibility = VISIBLE
                winner_name_textview.text = playerMoved.name
            }
        }
    }
}