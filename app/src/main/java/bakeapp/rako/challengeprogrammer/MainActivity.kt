package bakeapp.rako.challengeprogrammer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            result1.setText(biggerIsGreater1("fikh"))//fihk //eye // fb
            result2.setText(biggerIsGreater2("fikh"))//fkhi
        }
        diference()
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

    fun biggerIsGreater1(w: String): String {
        var change: Boolean = false
        var stringR = w

        var ja = stringR.length - 2
        while (ja >= 0) {
            var ix = 0
            var charss = stringR.substring(ja+1).toCharArray()
            Arrays.sort(charss)
            var stringS = String(charss)
            while (ix < stringS.length) {
                if (stringR[ja].compareTo(stringS[ix]) < 0) {
                    change = true
                    var temp = stringS[ix]
                    stringS = stringS.replaceRange(ix , ix+1, "")
                    stringS += stringR[ja]

                    charss = stringS.toCharArray()
                    Arrays.sort(charss)
                    var stringS = String(charss)

                    stringR = stringR.replaceRange(ja, ja+1, "")
                    stringR = stringR.substring(0, ja) + temp + stringS
                    break
                }
                ix++
            }
            if (change) break
            ja--
        }

        if (!change) return "no answer"
        /*var stringSorted = stringR.substring(index + 1).toSortedSet()
        stringR = stringR.substring(0, index + 1)
        stringSorted.forEach {
            stringR += it.toString()
        }*/

        return stringR
    }

    fun biggerIsGreater2(s: String = ""): String {
        var a = ""
        var numberCharsTaken: Int
        var flag = 0
        var x = 0
        numberCharsTaken = 1
        while (numberCharsTaken <= s.length) {
            a = s.substring(s.length - numberCharsTaken)
            x = a.length - 1
            while (x > 0) {
                if (a[0] < a[x]) {
                    flag = 1
                    break
                }
                if (flag == 1)
                    break
                x--
            }
            if (flag == 1)
                break
            numberCharsTaken++
        }
        if (flag == 1) {
            var temp = a.substring(0, x) + a.substring(x + 1)
            val c = temp.toCharArray()
            Arrays.sort(c)
            temp = a[x] + String(c)
            val ans = s.substring(0, s.length - numberCharsTaken) + temp
            return (ans)
        } else
            return ("no answer")
    }

    fun bigggerIsGreater3(s: String): String {
        var change: Boolean = false
        var index: Int = 0

        var stringR = s

        var j = stringR.length - 1
        while (j > 0) {
            var i = j-1
            while (i >= 0){
                if (stringR.get(j).compareTo(stringR.get(i)) > 0) {
                    change = true
                    stringR = stringR.substring(0, i) + stringR.get(j) + stringR.substring(i)
                    stringR = stringR.replaceRange(j + 1, j + 2, "")
                    index = i
                    break
                }
                i--
            }
            if (change) break
            j--
        }

        if (!change) return "no answer"
        var stringSorted = stringR.substring(index + 1).toSortedSet()
        stringR = stringR.substring(0, index + 1)
        stringSorted.forEach {
            stringR += it.toString()
        }

        return stringR
    }

}
