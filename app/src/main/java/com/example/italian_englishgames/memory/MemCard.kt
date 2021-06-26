package com.example.italian_englishgames.memory

class MemCard {
    var word:String = ""
    var isBack:Boolean = true
    var id:Int  = 0

    constructor(word: String ="", id: Int=0){
        this.word= word
        this.id = id
        this.isBack = true
    }
}