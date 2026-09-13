package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val resultButton: Button = findViewById(R.id.resultButton)

        resultButton.setOnClickListener {
            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()) {
                return@setOnClickListener
            }
                val height: Int = heightEditText.text.toString().toInt()
                val weight: Int = weightEditText.text.toString().toInt()
        }
    }
}
