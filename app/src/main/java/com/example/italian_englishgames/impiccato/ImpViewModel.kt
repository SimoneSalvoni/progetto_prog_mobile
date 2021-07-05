package com.example.italian_englishgames.impiccato

import android.app.Application
import androidx.lifecycle.*
import com.example.italian_englishgames.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

private const val NUM_OF_WORDS = 3000

class ImpViewModel(application: Application): AndroidViewModel(application) {

    enum class State {
        PLAYING, WIN, LOSE
    }
    private val _chosenWord = MutableLiveData<String>("")
    val chosenWord: LiveData<String>
        get() = _chosenWord
    private val _shownWord = MutableLiveData<String>("")
    val shownWord: LiveData<String>
        get() = _shownWord
    private val _errors = MutableLiveData<Int>(0)
    val errors: LiveData<Int>
        get() = _errors
    private val _gameState = MutableLiveData<State>(State.PLAYING)
    val gameState: LiveData<State>
        get() = _gameState
    private val _wrongLettersString = MutableLiveData<String>("Lettere non presenti: ")
    val wrongLettersString: LiveData<String>
    get() = _wrongLettersString
    private val chosenLetters = mutableSetOf<Char>()
    private val ctx = getApplication<Application>().applicationContext


    private suspend fun readFromFile(br: BufferedReader, times: Int ) {
        var dest: String = ""
        withContext(Dispatchers.IO) { for (i in 1..times)  dest=br.readLine() }
        _chosenWord.value = dest
        for (i in 1..dest.length) _shownWord.value += "-"
    }

    fun chooseWord() {
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_3000))
        val br = BufferedReader(isr)
        val word: Int = Random.nextInt(1, NUM_OF_WORDS)
        viewModelScope.launch{
            readFromFile(br,word)
        }
    }

    private fun letterIsPresent(letter: Char, pos: IntArray ) {
        var arr = _shownWord.value!!.toCharArray()
        for (element in pos)  arr[element] = letter
        _shownWord.value = String(arr)
        chosenLetters.add(letter)
        if (_shownWord.value==_chosenWord.value) _gameState.value=State.WIN
    }

    private fun letterIsNotPresent(letter:Char) {
        chosenLetters.add(letter)
        _wrongLettersString.value+="${letter.toString()} "
        _errors.value = _errors.value?.plus(1)
        if (errors.value==6) _gameState.value=State.LOSE
    }

    fun checkLetter(c: Char):Boolean {
        val ind = mutableListOf<Int>()
        for (i in _chosenWord.value!!.indices)
            if (c== _chosenWord.value!![i]) ind.add(i)
        return if (ind.size==0) {
            letterIsNotPresent(c)
            false
        } else{
            letterIsPresent(c, ind.toIntArray())
            true
        }
    }

    fun pointsCalc(letCount: Int, time: String): String {
        val numTime = time.replace(":", "").toInt()
        return (1000 * letCount / numTime).toString()
    }

    fun resetGame(){
        _gameState.value=State.PLAYING
        _errors.value=0
        chosenLetters.clear()
        chooseWord()
    }
}