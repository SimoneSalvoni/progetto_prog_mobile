package com.example.italian_englishgames.memory


//Ha bisogno di implementare Parcelable se vogliamo passarla onSaveInstanceState
class MemCard {
    var word:String = ""
    var isBack:Boolean = true
    var id:Int  = 0

    constructor(word: String ="", id: Int=999){
        this.word= word
        this.id = id
        this.isBack = true
    }
}