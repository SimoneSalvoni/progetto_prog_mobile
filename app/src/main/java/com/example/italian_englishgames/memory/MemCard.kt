package com.example.italian_englishgames.memory

class MemCard {
    var word:String = ""
    var isBack:Boolean = false
    var id:Int  = 0

    constructor(word: String,id: Int){
        this.word= word
        this.id = id
        this.isBack = false
    }
}