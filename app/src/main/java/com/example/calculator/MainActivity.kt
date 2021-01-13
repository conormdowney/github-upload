package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var sum: String = ""
    private var symbolNext: Boolean = false
    private lateinit var resultView: TextView
    private lateinit var sumView: TextView
    private var currentNum: String = "";

    /**
     * I added sin, cos, tan and lg buttons but i dont handle them.
     * I just added them to hide and show different buttons in different layouts
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.resultView)
        sumView = findViewById(R.id.sumView)

        val equalsBtn = findViewById<TextView>(R.id.equalsBtn)
        equalsBtn.setOnClickListener {
            setResult()
        }

        val clearBtn = findViewById<TextView>(R.id.clearBtn)
        clearBtn.setOnClickListener {
            clear()
        }

        val plusBtn = findViewById<TextView>(R.id.plusBtn)
        plusBtn.setOnClickListener {
            val view = it as TextView
            symbolPressed(view)
        }

        val subtractBtn = findViewById<TextView>(R.id.subtractBtn)
        subtractBtn.setOnClickListener {
            val view = it as TextView
            symbolPressed(view)
        }

        val multiplyBtn = findViewById<TextView>(R.id.multiplyBtn)
        multiplyBtn.setOnClickListener {
            val view = it as TextView
            symbolPressed(view)
        }

        val divideBtn = findViewById<TextView>(R.id.divideBtn)
        divideBtn.setOnClickListener {
            val view = it as TextView
            symbolPressed(view)
        }

        val modBtn = findViewById<TextView>(R.id.modulusBtn)
        modBtn.setOnClickListener {
            val view = it as TextView
            symbolPressed(view)
        }

        val oneBtn = findViewById<TextView>(R.id.oneBtn)
        oneBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val twoBtn = findViewById<TextView>(R.id.twoBtn)
        twoBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val threeBtn = findViewById<TextView>(R.id.threeBtn)
        threeBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val fourBtn = findViewById<TextView>(R.id.fourBtn)
        fourBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val fiveBtn = findViewById<TextView>(R.id.fiveBtn)
        fiveBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val sixBtn = findViewById<TextView>(R.id.sixBtn)
        sixBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val sevenBtn = findViewById<TextView>(R.id.sevenBtn)
        sevenBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val eightBtn = findViewById<TextView>(R.id.eightBtn)
        eightBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val nineBtn = findViewById<TextView>(R.id.nineBtn)
        nineBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val zeroBtn = findViewById<TextView>(R.id.zeroBtn)
        zeroBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val decimalBtn = findViewById<TextView>(R.id.decimalPointBtn)
        decimalBtn.setOnClickListener {
            val view = it as TextView
            numberPressed(view)
        }

        val delBtn = findViewById<TextView>(R.id.deleteBtn)
        delBtn.setOnClickListener {
            delete()
        }
    }

    private fun numberPressed(view: TextView)
    {
        if(view.tag == "." && currentNum == "")
        {
            currentNum += "0"
            sum += 0
        }
        currentNum += view.tag
        sum += view.tag

        sumView.text = sum
        symbolNext = true
    }

    private fun symbolPressed(view: TextView)
    {
        if(symbolNext) {
            sum += " " + view.tag.toString() + " "
            sumView.text = sum
            symbolNext = false
            currentNum = ""
        }
    }

    private fun setResult()
    {
        sum = getValuesPerOrderOfPrecedence(sum)

        val splitSum = sum.split(" ")
        val firstVal = splitSum[0].toDouble()
        var total: Double = firstVal

        for(i in 1..splitSum.size - 1)
        {
            if(splitSum[i] == "+")
            {
                val secondNum = splitSum[i + 1].toDouble()
                total += secondNum
            }
            else if(splitSum[i] == "-")
            {
                val secondNum = splitSum[i + 1].toDouble()
                total -= secondNum
            }
        }

        resultView.text = total.toString()
    }

    private fun getValuesPerOrderOfPrecedence(sumString: String): String
    {
        val splitString = sumString.split(" ")
        var resultString: String = sumString

        for(i in 0..splitString.size - 1)
        {
            println("in loop")
            //Use a when for the different symbols
            if(splitString[i] == "*")
            {
                val firstNum = splitString[i - 1].toDouble()
                val secondNum = splitString[i + 1].toDouble()
                resultString = resultString.replace("${splitString[i - 1]} * ${splitString[i + 1]}",
                        (firstNum * secondNum).toString())
            }
            else if(splitString[i] == "/")
            {
                val firstNum = splitString[i - 1].toDouble()
                val secondNum = splitString[i + 1].toDouble()
                resultString = resultString.replace("${splitString[i - 1]} / ${splitString[i + 1]}",
                        (firstNum / secondNum).toString())
            }
            else if(splitString[i] == "%")
            {
                val firstNum = splitString[i - 1].toDouble()
                val secondNum = splitString[i + 1].toDouble()
                resultString = resultString.replace("${splitString[i - 1]} % ${splitString[i + 1]}",
                        (firstNum % secondNum).toString())
            }
        }

        return resultString
    }

    private fun clear()
    {
        resultView.text = "0"
        sumView.text = ""
        sum = ""
        currentNum = ""
    }

    private fun delete()
    {
        if(sum.isNotEmpty()) {
            val numDropChars: Int
            if (sum.last() == ' ') {
                symbolNext = true
                numDropChars = 3
            } else {
                numDropChars = 1
            }

            sum = sum.dropLast(numDropChars)

            sumView.text = sum
        }
    }
}