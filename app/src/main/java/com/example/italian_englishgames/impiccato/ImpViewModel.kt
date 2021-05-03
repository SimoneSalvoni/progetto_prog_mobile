package com.example.italian_englishgames.impiccato

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italian_englishgames.R
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class ImpViewModel: ViewModel() {

    private val _chosenWord = MutableLiveData<String>("")
    val chosenWord: LiveData<String>
        get() = _chosenWord
    private val _shownWord = MutableLiveData<String>()
    val shownWord: LiveData<String>
        get() = _shownWord
    private val _errors = MutableLiveData<Int>(0)
    val errors: LiveData<Int>
        get() = _errors
    private val chosenLetters = mutableSetOf<Char>()

    enum class State {
        PLAYING, WIN, LOSE
    }

    private val _gameState = MutableLiveData<State>(State.PLAYING)
    val gameState: LiveData<State>
    get() = _gameState

    fun chooseWord(ctx: Context) {
        /*
        val bufferedReader: BufferedReader = File("R.raw.words_3000").bufferedReader()
        */
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_3000))
        val br = BufferedReader(isr)
        val w: Int = Random.nextInt(1, 3000)
        var s: String = ""
        for (i in 1..w) s = br.readLine()
        _chosenWord.value = s
        for (i in 1..s.length) _shownWord.value += "-"
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

    fun ChosenLetterOk(c: Char): Boolean {
        return !(c in chosenLetters)
    }
}