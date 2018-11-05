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

    /*
    * Descobrir o tipo de bola com maior quantidade
    * trocar os outros tipos de bola pelo tipo de bola com maior quantidade com os outros container
      * se o primeiro container não conseguir trocar todas as bolas até o ultimo container, retornar impossible
      * senão fazer o mesmo com o próximo container
      * caso algum container fique com dois tipos de bolas, esse deve procurar por outro container q tenha uma quantidade
        * igual do tipo de bola não predominnte do mesmo, com um container que já está com apenas um tipo
    * */

    fun getContainer() {

        var text = ""
        val inputStream: InputStream = baseContext.resources.openRawResource(R.raw.container)
        var reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))

        var q = reader.readLine().toInt()

        for (qItr in 1..q) {
            var n = reader.readLine().toInt()
            val container = Array<Array<Int>>(n, { Array<Int>(n, { 0 }) })
            for (nRead in 0 until n) {
                container[nRead] = reader.readLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            }
            text += organizingContainers(container) + "\n"
        }
        result1.text = text
    }

    fun organizingContainers(container: Array<Array<Int>>): String {
        var tam = container.size
        var a = IntArray(tam) //number of balls of container
        var b = IntArray(tam) //number of balls of type

        for (i in 0 until tam) {
            for (j in 0 until tam) {
                a[i] += container[i][j]
                b[j] += container[i][j]
            }
        }
        a.sort()
        b.sort()


        return if (Arrays.equals(a, b))
            "Possible"
        else
            "Impossible"

    }

}
