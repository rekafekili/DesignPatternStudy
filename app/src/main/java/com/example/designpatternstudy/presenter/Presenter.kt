package com.example.designpatternstudy.presenter

interface Presenter {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onDestroy()
}