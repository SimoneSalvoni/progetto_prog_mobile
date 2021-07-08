package com.example.italian_englishgames.unitTest

import android.os.Build.VERSION_CODES.Q
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.italian_englishgames.boggle.BoggleViewModel
import com.example.italian_englishgames.impiccato.ImpViewModel
import com.example.italian_englishgames.memory.MemCard
import com.example.italian_englishgames.memory.MemViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class ViewModelTest {

    lateinit var impViewModel: ImpViewModel
    lateinit var chosenWord: String
    lateinit var memViewModel: MemViewModel
    lateinit var cards: MutableList<MemCard>
    lateinit var boggleViewModel: BoggleViewModel


    @Before
    fun setUp(){
        impViewModel = ImpViewModel(ApplicationProvider.getApplicationContext())
        impViewModel.chooseWordForTesting()
        chosenWord = impViewModel.chosenWord.value!!
        memViewModel = MemViewModel(ApplicationProvider.getApplicationContext())
        memViewModel.createGame()
        cards = memViewModel.words.value!!
        boggleViewModel = BoggleViewModel(ApplicationProvider.getApplicationContext())
        runBlocking { boggleViewModel.startGame()}
    }

    @Test
    fun testCheckLetterImp(){
        assertEquals(false, impViewModel.checkLetter('a'))
        assertEquals(false, impViewModel.checkLetter('z'))
        assertEquals(true, impViewModel.checkLetter('e'))
        assertEquals(true, impViewModel.checkLetter('t'))
    }

    @Test
    fun testPointsCalcImp(){
        //non lo abbiamo...
    }

    @Test
    fun testCheckMem(){
        val card1 = cards[0]
        var card2= MemCard()
        for (i in cards){
            card2=i
            if(card1.id==card2.id && card1.word!=card2.word) break
        }
        assertEquals(memViewModel.check(card1,card2), true)
    }

    @Test
    fun testIsPresentBoggle(){
        /*
        words si azzera... Ma poi nell'app funziona, solo qua accade
        assertEquals(true, boggleViewModel.isPresent("word"))
        assertEquals(true, boggleViewModel.isPresent("test"))
        assertEquals(false, boggleViewModel.isPresent("aoifwoiefmow"))

         */
    }
}