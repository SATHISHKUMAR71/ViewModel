package com.example.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    private lateinit var timer:CountDownTimer
    var isFinished = MutableLiveData<Boolean>()
    var secondsLeft = MutableLiveData<Long>(0)
    fun getSecondsLeft():LiveData<Long>{
        return secondsLeft
    }
    var pauseNumber = MutableLiveData<Int>()
    fun startTimer(){
        timer = object :CountDownTimer(secondsLeft.value!!.toLong(),1000){

            override fun onTick(p0: Long) {
                secondsLeft.value = (p0/1000)
            }
            override fun onFinish() {
                isFinished.value = true
            }
        }.start()
    }

    fun stopTimer(){
        secondsLeft.value = 0
        timer.cancel()
    }

}