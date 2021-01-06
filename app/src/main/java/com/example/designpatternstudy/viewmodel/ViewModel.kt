package com.example.designpatternstudy.viewmodel

interface ViewModel {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onDestroy()
}