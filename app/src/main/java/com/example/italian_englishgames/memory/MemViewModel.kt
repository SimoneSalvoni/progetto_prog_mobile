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

//VALORE PLACEHOLDER
private const val NUM_OF_WORDS = 286

class MemViewModel(application: Application):AndroidViewModel(application) {

    private val wordsAndIndices= mutableListOf<Pair<Int,String>>()
    private val _words = MutableLiveData(mutableListOf<String>())
    val words: LiveData<MutableList<String>>
    get()=_words
    var found_words= BooleanArray(16){false}
    private val ctx= getApplication<Application>().applicationContext


    fun init (){
        val indices = mutableSetOf<Int>()
        for (i in 1..8) while(!indices.add(Random.nextInt(1, NUM_OF_WORDS))) //costanti da cambiare
            viewModelScope.launch{
                var dest=""
                var destList: List<String>
                withContext(Dispatchers.IO) {
                    for (i in indices){
                        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_eng_it))
                        val br = BufferedReader(isr)
                        for (j in 1..i)  dest=br.readLine()
                        destList = dest.split(';')
                        wordsAndIndices.add(Pair(i,destList[0]))
                        wordsAndIndices.add(Pair(i,destList[1]))
                        _words.value?.add(destList[0])
                        _words.value?.add(destList[1])
                    }
                }
                _words.value?.shuffle()
            }
    }

    fun check(word1: String, word2: String): Boolean{
        var index1 = 0
        var index2 = 0
        for (elem in wordsAndIndices){
            if (elem.second == word1) index1=elem.first
            if (elem.second == word2) index2=elem.first
        }
        if (index1!=index2) return false
        else {
            found_words[_words.value!!.indexOf(word1)]=true
            found_words[_words.value!!.indexOf(word2)]=true
            return true
        }
    }

    //ret true se vittoria
    fun checkGameState(): Boolean{
        return (false !in found_words)
    }

}