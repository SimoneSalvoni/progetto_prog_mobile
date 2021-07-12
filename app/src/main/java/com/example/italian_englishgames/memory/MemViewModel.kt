package com.example.italian_englishgames.memory

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.italian_englishgames.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

private const val NUM_OF_WORDS = 286

class MemViewModel(application: Application):AndroidViewModel(application) {

    private val _words= MutableLiveData(mutableListOf<MemCard>())
    val words: LiveData<MutableList<MemCard>>
    get() = _words
    private var foundWords= BooleanArray(16){false}
    private val ctx= getApplication<Application>().applicationContext


    /**
     * Questa funzione legge dal file words_eng_it 16 coppie di parole l'una la traduzione dell'altra
     *
     * @param indices è la posizione di queste coppie nel file
     */
    private fun readFromFile(indices: MutableSet<Int>) {
        var dest = ""
        var destList: List<String>
        var returnList = mutableListOf<MemCard>()
        for (i in indices) {
            val isr = InputStreamReader(ctx.resources.openRawResource(R.raw.words_eng_it))
            val br = BufferedReader(isr)
            for (j in 1..i) dest = br.readLine()
            destList = dest.split(';')
            _words.value?.add(MemCard(destList[0], i))
            _words.value?.add(MemCard(destList[1], i))
        }
    }

    /**
     * Questa funzione è il setup del gioco, che sceglie le parole da inserire nel memory
     */
    fun createGame() {
        val indices = mutableSetOf<Int>()
        for (i in 1..8) while (!indices.add(Random.nextInt(1, NUM_OF_WORDS))) { }
        readFromFile(indices)
        _words.value?.shuffle()
    }

    /**
     * Questa funzione controlla se due carte scelte sono l'una la traduzione dell'altra
     *
     * @param card1 la prima carta, di tipo MemCard
     * @param card2 la seconda carta, anche questa di tipo MemCard
     */
    fun check(card1: MemCard, card2: MemCard): Boolean{
        return if (card1.id != card2.id) false
        else {
            foundWords[words.value!!.indexOf(card1)]=true
            foundWords[words.value!!.indexOf(card2)]=true
            true
        }
    }

    /**
     * Questa funzione controlla se il gioco è vinto
     */
    fun checkGameState(): Boolean{
        return (false !in foundWords)
    }

}