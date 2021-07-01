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
import java.lang.Exception
import kotlin.random.Random

private const val NUM_OF_WORDS = 466405

class BoggleViewModel(application: Application): AndroidViewModel(application) {

    private val ctx = getApplication<Application>().applicationContext
    private var options: Array<Array<String>> = emptyArray()
    private var words: Map<String, MutableList<String>> = emptyMap()
    lateinit var letters: Array<String>
    val foundWords = mutableListOf<String>()
    private val _foundWordsText = MutableLiveData<String>("Parole trovate: ")
    val foundWordsText: LiveData<String>
    get()=_foundWordsText
    private val _points = MutableLiveData<Int>(0)
    val points: LiveData<Int>
    get()=_points

    private fun initOptionsAndWords() {
        options = arrayOf(
            arrayOf("R", "I", "F", "O", "B", "X"),
            arrayOf("I", "F", "E", "H", "E", "I"),
            arrayOf("D", "E", "N", "O", "W", "S"),
            arrayOf("U", "T", "O", "K", "N", "D"),
            arrayOf("H", "M", "S", "R", "A", "O"),
            arrayOf("L", "U", "P", "E", "T", "S"),
            arrayOf("A", "C", "I", "T", "O", "A"),
            arrayOf("Y", "L", "G", "K", "U", "E"),
            arrayOf("Qu", "B", "M", "J", "O", "A"),
            arrayOf("E", "H", "I", "S", "P", "N"),
            arrayOf("V", "E", "T", "I", "G", "N"),
            arrayOf("B", "A", "L", "I", "Y", "T"),
            arrayOf("E", "Z", "A", "V", "N", "D"),
            arrayOf("R", "A", "L", "E", "S", "C"),
            arrayOf("U", "W", "I", "L", "R", "G"),
            arrayOf("P", "A", "C", "E", "M", "D")
        )
        words = mapOf(
            "a" to mutableListOf(),
            "b" to mutableListOf(),
            "c" to mutableListOf(),
            "d" to mutableListOf(),
            "e" to mutableListOf(),
            "f" to mutableListOf(),
            "g" to mutableListOf(),
            "h" to mutableListOf(),
            "i" to mutableListOf(),
            "j" to mutableListOf(),
            "k" to mutableListOf(),
            "l" to mutableListOf(),
            "m" to mutableListOf(),
            "n" to mutableListOf(),
            "o" to mutableListOf(),
            "p" to mutableListOf(),
            "q" to mutableListOf(),
            "r" to mutableListOf(),
            "s" to mutableListOf(),
            "t" to mutableListOf(),
            "u" to mutableListOf(),
            "v" to mutableListOf(),
            "w" to mutableListOf(),
            "x" to mutableListOf(),
            "y" to mutableListOf(),
            "z" to mutableListOf(),
        )
    }

    private fun chooseLetters(){
        letters = Array<String>(16) {
            options[it][Random.nextInt(0, 5)]
        }
    }

    private suspend fun copyWordsFromFile(){
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words))
        val br = BufferedReader(isr)
        var currLetter: Char = 'a'
        var currWord=""
        withContext(Dispatchers.IO) {
            for (i in 1..NUM_OF_WORDS) {
                try {
                    currWord = br.readLine()
                    if (i==94059) {
                        print("prova")
                    }
                    if (!currWord.startsWith(currLetter, ignoreCase = true))  currLetter++
                    words[currLetter.toString()]!!.add(currWord)
                }
                catch(e:Exception){
                    print(e.toString())
                }
            }
        }
    }

    fun startGame(){
        initOptionsAndWords()
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

    suspend fun isPresent(word:String): Boolean{
        val firstChar = word.subSequence(0,1).toString()
        var present = false
        withContext(Dispatchers.Default) { present = words[firstChar]!!.contains(word)}
        if (present){
            _points.value = _points.value?.plus(calcPoints(word))
            foundWords.add(word)
            if (_foundWordsText.value == "Parole trovate: ") _foundWordsText.value=_foundWordsText.value.plus(word)
            else _foundWordsText.value=_foundWordsText.value.plus(", $word")
        }
        return present
    }
}