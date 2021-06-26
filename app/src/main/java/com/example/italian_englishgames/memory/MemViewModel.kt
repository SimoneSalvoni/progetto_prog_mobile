package com.example.italian_englishgames.memory

import android.app.Application
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
    /* private val _words = MutableLiveData(mutableListOf<String>())
    val words: LiveData<MutableList<String>>
    get()=_words */
    var found_words= BooleanArray(16){false}
    private val ctx= getApplication<Application>().applicationContext


    fun init (){
        val indices = mutableSetOf<Int>()
        for (i in 1..8) while(!indices.add(Random.nextInt(1, NUM_OF_WORDS))) 
            viewModelScope.launch{
                var dest=""
                var destList: List<String>
                withContext(Dispatchers.IO) {
                    for (i in indices){
                        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_eng_it))
                        val br = BufferedReader(isr)
                        for (j in 1..i)  dest=br.readLine()
                        destList = dest.split(';')
                        wordsAndIndices.value?.add(MemCard(destList[0], i))
                        wordsAndIndices.value?.add(MemCard(destList[1], i))
                    }
                }
                wordsAndIndices.value?.shuffle()
            }
    }

    fun check(card1: MemCard, card2: MemCard): Boolean{
        var index1 = 0
        var index2 = 0
        for (elem in wordsAndIndices){
            if (elem.word == card1.word) index1= elem.id!!
            if (elem.word == card2.word) index2= elem.id!!
        }
        if (index1!=index2) return false
        else {
            found_words[card1.id!!]=true
            found_words[card2.id!!]=true //card 1 e card 2 però hanno lo stesso id che è i, come le 2 word prima, è inutile
            return true
        }
    }

    //ret true se vittoria
    fun checkGameState(): Boolean{
        return (false !in found_words)
    }

}