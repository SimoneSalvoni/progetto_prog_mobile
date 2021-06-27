package com.example.italian_englishgames.boggle

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

private const val NUM_OF_WORDS = 466451

class BoggleViewModel(application: Application): AndroidViewModel(application) {

    private val ctx = getApplication<Application>().applicationContext
    private val options = arrayOf<Array<String>>()
    private val words = mapOf<Char,MutableList<String>>()
    val letters= arrayOf(String())
    private val _foundWords = MutableLiveData<MutableList<String>>()
    val foundWords: LiveData<MutableList<String>>
    get()=_foundWords
    private val _points = MutableLiveData<Int>(0)
    val points: LiveData<Int>
    get()=_points

    private fun initOptions(){
        options[0]= arrayOf("R", "I", "F", "O", "B", "X")
        options[1]= arrayOf("I", "F", "E", "H", "E", "I")
        options[2]= arrayOf("D", "E", "N", "O", "W", "S")
        options[3]= arrayOf("U", "T", "O", "K", "N", "D")
        options[4]= arrayOf("H", "M", "S", "R", "A", "O")
        options[5]= arrayOf("L", "U", "P", "E", "T", "S")
        options[6]= arrayOf("A", "C", "I", "T", "O", "A")
        options[7]= arrayOf("Y", "L", "G", "K", "U", "E")
        options[8]= arrayOf("Qu", "B", "M", "J", "O", "A")
        options[9]= arrayOf("E", "H", "I", "S", "P", "N")
        options[10]= arrayOf("V", "E", "T", "I", "G", "N")
        options[11]= arrayOf("B", "A", "L", "I", "Y", "T")
        options[12]= arrayOf("E", "Z", "A", "V", "N", "D")
        options[13]= arrayOf("R", "A", "L", "E", "S", "C")
        options[14]= arrayOf("U", "W", "I", "L", "R", "G")
        options[15]= arrayOf("P", "A", "C", "E", "M", "D")
    }

    private fun chooseLetters(){
        for (i in 0..15){
            letters[i] = options[i][Random.nextInt(0,5)]
        }
    }

    private suspend fun copyWordsFromFile(){
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words))
        val br = BufferedReader(isr)
        var currLetter: Char = 'a'
        var currWord=""
        withContext(Dispatchers.IO) {
            for (i in 1..NUM_OF_WORDS) {
                currWord = br.readLine()
                words[currLetter]?.add(currWord)
                if (!currWord.startsWith(currLetter, ignoreCase = true)) currLetter++
            }
        }
    }

    fun startGame(){
        chooseLetters()
        viewModelScope.launch{
            copyWordsFromFile()
        }

    }

    private fun calcPoints(word:String): Int{
        val wordLength = word.length
        return if (wordLength<=4) 1
        else {
            when(wordLength){
                5 -> 2
                6 -> 3
                7 -> 5
                else -> 11
            }
        }
    }

    fun isPresent(word:String){
        val firstChar = word.subSequence(0,0)
        val present = (words[firstChar]!!.contains(word))
        if (present){
            _points.value = _points.value?.plus(calcPoints(word))
            _foundWords.value?.add(word)
        }
    }
}