package com.example.calcu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var currentNumber = ""
    private var firstNumber = 0.0
    private var currentOperation: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        setupNumberButtons()
        setupOperationButtons()
    }

    private fun setupNumberButtons() {
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9, R.id.buttonPoint
        )

        numberButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                val buttonText = (it as Button).text.toString()
                if (buttonText == "." && currentNumber.contains(".")) return@setOnClickListener
                currentNumber += buttonText
                resultTextView.text = currentNumber
            }
        }
    }

    private fun setupOperationButtons() {
        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            currentNumber = ""
            firstNumber = 0.0
            currentOperation = null
            resultTextView.text = "0"
        }

        val operationButtons = listOf(
            R.id.buttonPlus to '+',
            R.id.buttonMinus to '-',
            R.id.buttonMultiply to '*',
            R.id.buttonDivide to '/'
        )

        operationButtons.forEach { (buttonId, operation) ->
            findViewById<Button>(buttonId).setOnClickListener {
                if (currentNumber.isNotEmpty()) {
                    firstNumber = currentNumber.toDouble()
                    currentOperation = operation
                    currentNumber = ""
                }
            }
        }

        findViewById<Button>(R.id.buttonEquals).setOnClickListener {
            if (currentNumber.isNotEmpty() && currentOperation != null) {
                val secondNumber = currentNumber.toDouble()
                val result = when (currentOperation) {
                    '+' -> firstNumber + secondNumber
                    '-' -> firstNumber - secondNumber
                    '*' -> firstNumber * secondNumber
                    '/' -> firstNumber / secondNumber
                    else -> 0.0
                }
                resultTextView.text = result.toString()
                currentNumber = result.toString()
                currentOperation = null
            }
        }
    }
}