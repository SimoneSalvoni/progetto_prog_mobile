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

class MemViewModel(application: Application):AndroidViewModel(application) {
    lateinit var words:  Array <Array<String>>
    private val _errors = MutableLiveData<Int>(0)
    val errors:LiveData<Int>
        get() = _errors
    private val ctx= getApplication<Application>().applicationContext
/*
    fun init (){
        val isr = InputStreamReader (ctx.resources.openRawResource(R.raw.words_eng_it))
        val br = BufferedReader(isr)
        var indices = mutableSetOf<Int>()
        for (i in 1..n) while(!indices.add(Random.nextInt(1, n2)))
            viewModelScope.launch{
                var destEng=""
                var destIt=""
                withContext(Dispatchers.IO) {
                    for (i in 1..18){
                        for (i in 1..n){}
                    }
                }
            }
    }

 */
}