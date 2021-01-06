package com.example.designpatternstudy.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.designpatternstudy.R
import com.example.designpatternstudy.databinding.TictactoeBinding
import com.example.designpatternstudy.viewmodel.TicTacToeViewModel

class TicTacToeActivity : AppCompatActivity() {
    private val ticTacToeViewModel = TicTacToeViewModel()
    private lateinit var binding: TictactoeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@TicTacToeActivity, R.layout.tictactoe)
        binding.run {
            viewModel = ticTacToeViewModel
        }
        ticTacToeViewModel.onCreate()
    }

    override fun onPause() {
        super.onPause()
        ticTacToeViewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        ticTacToeViewModel.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        ticTacToeViewModel.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.action_reset) {
            ticTacToeViewModel.onResetSelected()
            true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}