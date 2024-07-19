package com.example.viewmodel

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        val view = findViewById<TextView>(R.id.textView)
        var editText = findViewById<TextInputLayout>(R.id.textInputLayout).findViewById<TextInputEditText>(R.id.textInputEditText)
        viewModel.getSecondsLeft().observe(this, Observer {
            view.text = it.toString()
        })
        viewModel.isFinished.observe(this, Observer {
            if(it){
                Toast.makeText(this,"Finished",Toast.LENGTH_SHORT).show()
            }
        })
        findViewById<MaterialButton>(R.id.button).setOnClickListener {
            if(viewModel.secondsLeft.value!=0L){
                Toast.makeText(this,"The Timer is Currently Working stop and then start the timer again",Toast.LENGTH_SHORT).show()
            }
            else if((editText.text?.length != null) && (editText.text.toString().length>=2)){
                viewModel.secondsLeft.value= editText.text.toString().toLong()*1000
                viewModel.startTimer()
            }
            else{
                Toast.makeText(this,"Number should be more than 10 seconds",Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<MaterialButton>(R.id.stop).setOnClickListener {
            viewModel.stopTimer()
        }
    }
}