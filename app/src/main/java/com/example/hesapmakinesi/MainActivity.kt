package com.example.hesapmakinesi  // Burayı kendi paket adına göre değiştir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.tvResult)

        val buttons = listOf(
            Pair(R.id.btn0, "0"), Pair(R.id.btn1, "1"), Pair(R.id.btn2, "2"),
            Pair(R.id.btn3, "3"), Pair(R.id.btn4, "4"), Pair(R.id.btn5, "5"),
            Pair(R.id.btn6, "6"), Pair(R.id.btn7, "7"), Pair(R.id.btn8, "8"),
            Pair(R.id.btn9, "9"), Pair(R.id.btnDot, "."), Pair(R.id.btnPlus, "+"),
            Pair(R.id.btnMinus, "-"), Pair(R.id.btnMultiply, "*"), Pair(R.id.btnDivide, "/")
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                expression += value
                resultText.text = expression
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression = ""
            resultText.text = "0"
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            try {
                val result = evaluateExpression(expression)
                resultText.text = result.toString()
                expression = result.toString()
            } catch (e: Exception) {
                resultText.text = "Hata"
            }
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
                resultText.text = if (expression.isEmpty()) "0" else expression
            }
        }
    }

    private fun evaluateExpression(expr: String): Double {
        return ExpressionBuilder(expr).build().evaluate()
    }
}
