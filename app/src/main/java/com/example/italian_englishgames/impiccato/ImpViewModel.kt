package com.example.italian_englishgames.impiccato

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.italian_englishgames.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class ImpViewModel(application: Application): AndroidViewModel(application) {


    enum class State {
        PLAYING, WIN, LOSE
    }
    private val _chosenWord = MutableLiveData<String>("")
    val chosenWord: LiveData<String>
        get() = _chosenWord
    private val _shownWord = MutableLiveData<String>("prova")
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
        withContext(Dispatchers.IO) { for (i in 1..times) dest = br.readLine() }
        _chosenWord.value = dest
        for (i in 1..dest.length) _shownWord.value += "-"
    }

    fun chooseWord() {
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_3000))
        val br = BufferedReader(isr)
        val word: Int = Random.nextInt(1, 3000)
        viewModelScope.launch{
            readFromFile(br,word)
        }
    }

    private fun letterIsPresent(letter: Char, pos: IntArray ) {
        var arr = _shownWord.value!!.toCharArray()
        for (element in pos)  arr[element] = letter
        _shownWord.value = String(arr)
        chosenLetters.add(letter)
        if (_shownWord==_chosenWord) _gameState.value=State.WIN
    }

    private fun letterIsNotPresent(letter:Char) {
        chosenLetters.add(letter)
        _wrongLettersString.value+="${letter.toString()} "
        _errors.value = _errors.value?.plus(1)
        if (errors.value==6) _gameState.value=State.LOSE
    }

    fun checkLetter(c: Char) {
        val ind = mutableListOf<Int>()
        for (i in _chosenWord.value!!.indices)
            if (c== _chosenWord.value!![i]) ind.add(i)
        if (ind.size==0) letterIsNotPresent(c)
        else letterIsPresent(c, ind.toIntArray() )
    }

    //per vedere se una lettere inserita è già stata usata
    fun ChosenLetterUsable(c: Char): Boolean {
        return !(c in chosenLetters)
    }

    //non sono sicuro se è questo esattamente che dobbiamo fare nel reset
    fun resetGame(){
        _gameState.value=State.PLAYING
        _errors.value=0
        chosenLetters.clear()
        chooseWord()
    }
}