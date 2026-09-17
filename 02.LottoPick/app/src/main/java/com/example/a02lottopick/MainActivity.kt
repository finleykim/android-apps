package com.example.a02lottopick

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.Cleaner

class MainActivity : AppCompatActivity() {
    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6)
        )
    }

    private var didRun = false
    private var numbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPicker.minValue = 1
        numberPicker.maxValue = 45
        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun refreshNumbers()  {
        if (didRun) {
            numberTextViewList.forEachIndexed { index, textView ->
                textView.visibility = View.GONE
            }
        } else {
            for (i in 0..<numbers.size) {
                var background = when (numbers[i]) {
                    in 1..10 -> R.drawable.circle_yellow
                    in 11..20 -> R.drawable.circle_blue
                    in 21..30 -> R.drawable.circle_red
                    in 31..40 -> R.drawable.circle_gray
                    else -> R.drawable.circle_green
                }
                numberTextViewList[i].setBackgroundResource(background)
                numberTextViewList[i].text = numbers[i].toString()
                numberTextViewList[i].visibility = View.VISIBLE
            }
        }
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val maxNum = 5
            var randomNum = 0
            while (numbers.size < 6) {
                randomNum = (1..45).random()
                if (numbers.contains(randomNum)) {
                    continue
                } else {
                    numbers.add(randomNum)
                }
            }
            Log.d("TAG", "numbers: $numbers")
            refreshNumbers()
            didRun = true
        }
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            if (didRun) {
                Toast.makeText(this, "초기화 후에 시도해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (numbers.size >= 6) {
                Toast.makeText(this, "번호는 5개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (numbers.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택된 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            numbers.add(numberPicker.value)
            Log.d("TAG","numbers: $numbers")
            refreshNumbers()
        }
    }

    private fun initClearButton() {
        clearButton.setOnClickListener {
            if (didRun) {
                numbers.clear()
                refreshNumbers()
                didRun = false
            } else {
                Toast.makeText(this, "초기화할 번호가 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


