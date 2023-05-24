package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var txtTitle:TextView
    lateinit var txtInput:EditText
    lateinit var btnC:Button
    lateinit var btnDivide:Button
    lateinit var btnDistance:Button
    lateinit var btnAdd:Button
    lateinit var btnResult:Button
    lateinit var btnDot:Button
    lateinit var btnMultiply:Button
    lateinit var btn0:Button
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button
    lateinit var btn5:Button
    lateinit var btn6:Button
    lateinit var btn7:Button
    lateinit var btn8:Button
    lateinit var btn9:Button
    lateinit var txtResult:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTitle = findViewById(R.id.txtTitle)
        txtInput = findViewById(R.id.txtInput)
        btnC = findViewById(R.id.btnc)
        btnDivide = findViewById(R.id.btnDivide)
        btnDistance = findViewById(R.id.btndistance)
        btnAdd = findViewById(R.id.btnAdd)
        btnResult = findViewById(R.id.btnResult)
        btnDot = findViewById(R.id.btnDot)
        btnMultiply = findViewById(R.id.btnMultiply)
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        txtResult = findViewById(R.id.txtResult)

        btnC.setOnClickListener(){
            txtInput.setText("0")
        }

        btn1.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("1")
            } else {
                txtInput.setText("${txtInput.text.toString()}1")
            }
        }

        btn2.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("2")
            } else {
                txtInput.setText("${txtInput.text.toString()}2")
            }
        }

        btn3.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("3")
            } else {
                txtInput.setText("${txtInput.text.toString()}3")
            }
        }
        btn4.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("4")
            } else {
                txtInput.setText("${txtInput.text.toString()}4")
            }
        }
        btn5.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("5")
            } else {
                txtInput.setText("${txtInput.text.toString()}5")
            }
        }
        btn6.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("6")
            } else {
                txtInput.setText("${txtInput.text.toString()}6")
            }
        }
        btn7.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("7")
            } else {
                txtInput.setText("${txtInput.text.toString()}7")
            }
        }
        btn8.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("8")
            } else {
                txtInput.setText("${txtInput.text.toString()}8")
            }
        }
        btn9.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("9")
            } else {
                txtInput.setText("${txtInput.text.toString()}9")
            }
        }
        btn0.setOnClickListener(){
            if(txtInput.text.toString() == "0"){
                txtInput.setText("0")
            } else {
                txtInput.setText("${txtInput.text.toString()}0")
            }
        }

        val inputText:String = txtInput.text.toString()
        val lastInputItemIndex:Int = txtInput.text.toString().length -1
        val lastInputItem:Char = inputText[lastInputItemIndex]

        val isNotZero:Boolean =  inputText != "0"
        val isNotDot:Boolean = lastInputItem != '.'
        val isNotDivide:Boolean = lastInputItem != '/'



        btnDot.setOnClickListener(){
            if (txtInput.text.toString() != "0" && txtInput.text.toString()[txtInput.text.toString().length -1] != '.'){
                txtInput.setText("${txtInput.text.toString()}.")
            }
        }

        btnDivide.setOnClickListener(){
            if (txtInput.text.toString() != "0" && txtInput.text.toString()[txtInput.text.toString().length -1] != '÷'){
                txtInput.setText("${txtInput.text.toString()}÷")
            }
        }

        btnAdd.setOnClickListener(){
            if (txtInput.text.toString() != "0" && txtInput.text.toString()[txtInput.text.toString().length -1] != '+'){
                txtInput.setText("${txtInput.text.toString()}+")
            }
        }

        btnMultiply.setOnClickListener(){
            if (txtInput.text.toString() != "0" && txtInput.text.toString()[txtInput.text.toString().length -1] != 'x'){
                txtInput.setText("${txtInput.text.toString()}x")
            }
        }

        btnDistance.setOnClickListener(){
            if (txtInput.text.toString() != "0" && txtInput.text.toString()[txtInput.text.toString().length -1] != '-'){
                txtInput.setText("${txtInput.text.toString()}-")
            }
        }

        btnResult.setOnClickListener(){
            txtResult.setText(txtInput.text.toString())
            var input = txtInput.text.toString()
            var z = input.replace("x", "*")
            var c = z.replace("÷", "/")
                    var result = eval(c)
            txtInput.setText(result.toString())
        }





    }

    fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.code)) x *= parseFactor() // multiplication
                    else if (eat('/'.code)) x /= parseFactor() // division
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return +parseFactor() // unary plus
                if (eat('-'.code)) return -parseFactor() // unary minus
                var x: Double
                val startPos = pos
                if (eat('('.code)) { // parentheses
                    x = parseExpression()
                    if (!eat(')'.code)) throw RuntimeException("Missing ')'")
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) { // numbers
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.code && ch <= 'z'.code) { // functions
                    while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    if (eat('('.code)) {
                        x = parseExpression()
                        if (!eat(')'.code)) throw RuntimeException("Missing ')' after argument to $func")
                    } else {
                        x = parseFactor()
                    }
                    x =
                        if (func == "sqrt") Math.sqrt(x) else if (func == "sin") Math.sin(Math.toRadians(x)) else if (func == "cos") Math.cos(
                            Math.toRadians(x)
                        ) else if (func == "tan") Math.tan(Math.toRadians(x)) else throw RuntimeException(
                            "Unknown function: $func"
                        )
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.code)) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
        }.parse()
    }
}