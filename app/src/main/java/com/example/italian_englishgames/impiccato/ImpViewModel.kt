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
    private val chosenLetters = mutableSetOf<Char>()
    private val ctx = getApplication<Application>().applicationContext

    /**
     * Questa funzione legge dal file una parola a caso e la mette in _chosenWord
     *
     * @param br l'istanza di BufferedReader che serve per la lettura
     * @param times Int che indica quante linee deve leggere, e quindi che parola prendere
     */
    private suspend fun readFromFile(br: BufferedReader, times: Int ) {
        var dest: String = ""
        withContext(Dispatchers.IO) { for (i in 1..times)  dest=br.readLine() }
        _chosenWord.value = dest
        for (i in 1..dest.length) _shownWord.value += "-"
    }

    /**
     * Questa funzione sceglie quale parola prendere dal file words_3000 casualmente
     */
    fun chooseWord() {
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_3000))
        val br = BufferedReader(isr)
        val word: Int = Random.nextInt(1, NUM_OF_WORDS)
        viewModelScope.launch{
            readFromFile(br,word)
        }
    }

    /**
     * Questa funziona modifica le proprietà del viewModel quando una lettera è stata indovinata
     *
     * @param letter è la lettera presente nella parola
     * @param pos è un IntArray che indica la posizione della lettera nella parola
     */
    private fun letterIsPresent(letter: Char, pos: IntArray ) {
        var arr = _shownWord.value!!.toCharArray()
        for (element in pos)  arr[element] = letter
        _shownWord.value = String(arr)
        chosenLetters.add(letter)
        if (_shownWord.value==_chosenWord.value) _gameState.value=State.WIN
    }

    /**
     * Questa funziona modifica lo stato del viewModel quando l'utente ha scelto una lettera sbagliata
     *
     * @param letter è la lettera non presente
     */
    private fun letterIsNotPresent(letter:Char) {
        chosenLetters.add(letter)
        _wrongLettersString.value+="$letter "
        _errors.value = _errors.value?.plus(1)
        if (errors.value==6) _gameState.value=State.LOSE
    }

    /**
     * Qusta funzione controlla se una lettera è presente nella parola da indovinare
     *
     * @param c è la lettera da controllare
     */
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

    /**
     * Questa funzione calcola il punteggio finale
     *
     * @param letCount è il numero di lettere della parola
     * @param time è il tempo impiegato
     */
    fun pointsCalc(letCount: Int, time: String): String {
        var numTime = time.replace(":", "").toInt()
        if (numTime==0) numTime = 1
        return (1000 * letCount / numTime).toString()
    }

    /**
     * Funzione che prepara ad un'altra partita
     */
    fun resetGame(){
        _gameState.value=State.PLAYING
        _errors.value=0
        chosenLetters.clear()
        chooseWord()
    }

    //PER IL TESTING
    //PER TESTING
    fun chooseWordForTesting(){
        _chosenWord.value="test"
        _shownWord.value="----"
    }
}