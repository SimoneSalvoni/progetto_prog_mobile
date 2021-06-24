package com.example.italian_englishgames.memory

class MemCard {
    var word:String ? = null
    var isBack:Boolean = false

    constructor(word: String){
        this.word= word
        this.isBack = false
    }
}