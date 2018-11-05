package bakeapp.rako.challengeprogrammer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            forest()
        }

    }

    fun forest() {

        var text = ""
        val inputStream: InputStream = baseContext.resources.openRawResource(R.raw.container)
        var reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))


        var firstLine = reader.readLine().split(" ")
        var arvTam = firstLine[0].trim().toInt() - 1
        var edges = firstLine[1].trim().toInt()
        var t_from = MutableList<Int>(arvTam, {0})
        var t_to = MutableList<Int>(arvTam, {0})

        for (i in 0 until arvTam) {
            var line = reader.readLine().split(" ")
            t_from[i] = line[0].trim().toInt()
            t_to[i] = line[1].trim().toInt()
        }

        Solution.evenForest(arvTam, edges, t_from, t_to)
        var l = 0
    }

}
