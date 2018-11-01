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
             text += organizingContainers(container)+"\n"
        }
        result1.text = text
    }

    var quantidadeTiposdeBola: Int = 0
    var verIndicesTiposdeBoladeContainers: Array<Int>? = null
    var varQuantidadeTotalOutrosTiposContainer : Array<Int>? = null
    var containersOk : Array<Boolean>? = null

    fun getIndiceTipoMaiordoContainer(container: Array<Int>): Int {
        return container.indexOf(container.max()!!)
    }

    fun getQuantidadedosTotaldosOutrosTiposdeBoladoContainer(container: Array<Int>, indexTipoPredominante : Int) : Int{
        var total = 0
        for (i in 0 until (quantidadeTiposdeBola - 1)!!) {
            if (i == indexTipoPredominante) {
                continue
            }
            total += container[i]
        }
        return total
    }

    fun pegarQuantidadedeumTipoDeBoladedoUmContainer(container: Array<Int>, tipoDeBola : Int) : Int {
        return container[tipoDeBola]
    }

    //envio todas as bolas diferentes do container1 para o container2, só as bolas do tipo principal que não
    fun troca1(container: Array<Array<Int>>, indContainer1 : Int, indContainer2: Int, indNao : Int) {
        for (i in 0 until (quantidadeTiposdeBola - 1)) {
            if (i == indNao) {
                continue
            }
            container[indContainer2][i] += container[indContainer1][i]
            container[indContainer1][i] = 0
        }
    }

    //envio as bolas diferentes para o container2, a quantidade é a mesma que o container um recebeu
    fun troca2(container: Array<Array<Int>>, indContainer1 : Int, indContainer2: Int, indNao : Int, quantidade : Int) {
        var quant = quantidade
        for (i in 0 until (quantidadeTiposdeBola - 1)) {
            if (i == indNao) {
                continue
            }
            if (quantidade == container[indContainer1][i]) {
                container[indContainer2][i] += container[indContainer1][i]
                container[indContainer1][i] = 0
                quant = 0

            }else if(quantidade > container[indContainer1][i]) {
                container[indContainer2][i] += container[indContainer1][i]
                quant -=  container[indContainer1][i]
                container[indContainer1][i] = 0

            }else if (quantidade < container[indContainer1][i]) {
                container[indContainer2][i] += quant
                container[indContainer1][i] -= quant
                quant = 0
            }

            if (quant == 0) {
                break
            }
        }
    }

    fun organizingContainers(container: Array<Array<Int>>): String {
        quantidadeTiposdeBola = container[0].size
        verIndicesTiposdeBoladeContainers = Array<Int>(quantidadeTiposdeBola!!, {0})
        varQuantidadeTotalOutrosTiposContainer = Array<Int>(quantidadeTiposdeBola!!, {0})

        containersOk = Array<Boolean>(quantidadeTiposdeBola!!, {false})

        //Get o tipo de bola q tem quantidade maior de cada container

        var i1 = 0
        while (i1 < quantidadeTiposdeBola){
            verIndicesTiposdeBoladeContainers!![i1] = getIndiceTipoMaiordoContainer(container[i1])
            i1++
        }

        var i2 = 0
        while (i2 < quantidadeTiposdeBola){
            varQuantidadeTotalOutrosTiposContainer!![i2] =   getQuantidadedosTotaldosOutrosTiposdeBoladoContainer(
                    container[i2],
                    verIndicesTiposdeBoladeContainers!![i2])
            i2++
        }

        //passo2
        //trocar os outros tipos de bola pelo tipo de bola com maior quantidade com os outros container
        var i = 0
        while (i < quantidadeTiposdeBola){
            var j = i+1
            while (j < quantidadeTiposdeBola){
                var quantidadeDisponivelParaTroca = pegarQuantidadedeumTipoDeBoladedoUmContainer(container[j], verIndicesTiposdeBoladeContainers!![i])
                var quantidadeTiposDeBolaTroca = varQuantidadeTotalOutrosTiposContainer!![i]

                if (quantidadeTiposDeBolaTroca == 0) {
                    containersOk!![i] = verficaContainerOk(container[i], verIndicesTiposdeBoladeContainers!![i])
                    break
                }

                if (quantidadeDisponivelParaTroca == 0) {
                    j++
                    continue
                }

                if (quantidadeDisponivelParaTroca == quantidadeTiposDeBolaTroca) {
                    container[i][varQuantidadeTotalOutrosTiposContainer!![i]] += quantidadeDisponivelParaTroca
                    troca1(container, i, j, verIndicesTiposdeBoladeContainers!![i])
                    verIndicesTiposdeBoladeContainers!![i] = 0

                }else if (quantidadeTiposDeBolaTroca > quantidadeDisponivelParaTroca) {
                    container[i][varQuantidadeTotalOutrosTiposContainer!![i]] += quantidadeDisponivelParaTroca
                    troca2(container, i, j, verIndicesTiposdeBoladeContainers!![i], quantidadeDisponivelParaTroca)
                    verIndicesTiposdeBoladeContainers!![i] -= quantidadeDisponivelParaTroca

                }else if(quantidadeTiposDeBolaTroca < quantidadeDisponivelParaTroca) {
                    container[i][varQuantidadeTotalOutrosTiposContainer!![i]] += quantidadeTiposDeBolaTroca
                    troca2(container, i, j, verIndicesTiposdeBoladeContainers!![i], quantidadeTiposDeBolaTroca)
                    verIndicesTiposdeBoladeContainers!![i] -= quantidadeTiposDeBolaTroca
                }

                containersOk!![i] = verficaContainerOk(container[i], verIndicesTiposdeBoladeContainers!![i])
                if (containersOk!![i]) {
                    break
                }
                j++
            }
            if (containersOk!![i]) {
                i++
                break
            }
            i++
        }

        return  verficaTodos(containersOk!!)
    }

    fun verficaContainerOk(container: Array<Int>, indTipoBola : Int) : Boolean {
        for (i in 0 until (quantidadeTiposdeBola - 1)) {
            if(indTipoBola == i) continue
            if(container[i] != 0) return false
        }
        return true
    }

    fun verficaTodos(container: Array<Boolean>): String {
        for (i in 0 until (quantidadeTiposdeBola - 1)) {
            if(!container[i]) return  "Impossible"
        }
        return "Possible"
    }

}
