package bakeapp.rako.challengeprogrammer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bakeapp.rako.challengeprogrammer.Solutions.Companion.biggerIsGreater1
import bakeapp.rako.challengeprogrammer.Solutions.Companion.biggerIsGreater2
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            getContainer()
        }

    }

    fun getContainer() {

        val inputStream: InputStream = baseContext.resources.openRawResource(R.raw.container)
        var reader : BufferedReader = BufferedReader(InputStreamReader(inputStream))

        var q = reader.readLine().toInt()

        for (qItr in 1..q) {
            var n = reader.readLine().toInt()
            val container = Array<Array<Int>>(n, { Array<Int>(n, { 0 }) })
            for (nRead in 0 until n) {
                container[nRead] = reader.readLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
            }
            organizingContainers(container)
        }
    }

    // Complete the organizingContainers function below.
    fun organizingContainers(container: Array<Array<Int>>): String {
        var tam = container[0].size -1
        var typeMaior = intArrayOf(tam)
        for (i in 0..tam) {
            typeMaior[i] = container[i].indexOf(container[i].max())
        }

        for(row in 0..tam) {//row
            for (col in 0..tam) {//cols
                if (typeMaior[row] == col) {
                    continue
                }

                container[row]
            }
        }

        return ""
    }

    fun getSlice(container: Array<Array<Int>>, row: Int, typeMaior: Int, tam : Int) {

        for (i in 0..tam) {
            if (row == i) continue
            if (container[i][typeMaior] > 0) { //mesmo tipo em outro container
                for (j in 0..tam) {
                    if (container[row][j] > 0) {//no container tipo para troca

                        // 7 - 3
                        // i == 1 typeMaior 0 row = 0 e j = 1
                        if (container[i][typeMaior] - container[row][j] >= 0) {

                            container[row][typeMaior] += container[row][j]
                            container[i][typeMaior] -= container[row][j]

                            container[i][j] += container[row][j]
                            container[row][j] = 0


                        }else if (container[row][j] - container[i][typeMaior] >= 0) {
                            var diff = container[row][j] - container[i][typeMaior]

                            container[row][typeMaior] += container[i][typeMaior]
                            container[row][j] -= container[i][typeMaior]

                        }
                    }
                }
            }
        }
    }

    fun verifcaOk(container: Array<Int>,  typeMaior: Int, tam: Int) : Boolean{
        for (i in 0..tam) {
            if(i == typeMaior) continue
            if(container[i] > 0) return false
        }
        return true
    }


    fun diference() {
        btn.visibility = View.GONE

        val inputStream: InputStream = baseContext.resources.openRawResource(R.raw.ttext)
        var reader : BufferedReader = BufferedReader(InputStreamReader(inputStream))
        var result: StringBuilder = StringBuilder()

        var line : String? = ""
        while (line != null) {
            line = reader.readLine()
            if (line != null && !biggerIsGreater1(line).equals(biggerIsGreater2(line))) {
                result.append(line+"\n")
            }
        }
        result1.text = result
    }



}
