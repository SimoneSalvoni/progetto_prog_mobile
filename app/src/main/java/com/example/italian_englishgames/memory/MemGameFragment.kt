package com.example.italian_englishgames.memory

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentMemGameBinding
import com.google.android.material.snackbar.Snackbar


class MemGameFragment : Fragment(), GridAdapter.OnItemClickListener {

    lateinit var binding: FragmentMemGameBinding
    //lateinit var front_anim: AnimatorSet
    //lateinit var back_anim: AnimatorSet
    val viewModel: MemViewModel by viewModels()
    var adapter = GridAdapter

    lateinit var card1: MemCard
    lateinit var card2: MemCard



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_mem_game,container,false)
        viewModel.init()
        var cardList = viewModel.words//dobbiamo prendere questo e non words
        var adapter = GridAdapter(this, cardList)
        binding.gameTable.adapter = adapter
        binding.gameTable.LayoutManager = GridLayoutManager(this)
        binding.gameTable.setHasFixedSize(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.memViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /* animazioni, serve prendere il context
        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator)
                as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator)
                as AnimatorSet */

        /*card.setOnClickListener{
            if(isBack){
                if(card1=="") {
                    backToFront()
                    isBack = false
                    card1 = binding..text.toString() //o valore o posizione
                }
                else{
                    card2 = binding..text.toString()
                    if(viewModel.check(card1, card2)) {
                        viewModel.checkGameState() //feedback positivo per aver preso la parola
                    }
                    else{
                        frontToBack()//di entrambe le carte

                    }
                    card1=""
                }
            }else{
                Snackbar.make(
                    binding.ConstraintLayout,
                    "Carta già scoperta!",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }

        }*/
    }

    override fun onItemClick(position: Int) {
        var clickedItem: MemCard = cardList[position]
        if(clickedItem.isBack){
            if(card1.word=="") {
                clickedItem.isBack = false
                card1 = clickedItem
                //show ma bisogna mettere mano all'xml
            }
            else{
                card2 = clickedItem
                if(viewModel.check(card1, card2)) {
                    viewModel.checkGameState()
                    //feedback positivo per aver preso la parola
                }
                else{
                    //hide di card1 e card2
                    //feedback negativo
                }
                card1.word=""
            }
        }else{
            Snackbar.make(
                binding.ConstraintLayout,
                "Carta già scoperta!",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
        }

    }

    /*fun backToFront(){
        front_anim.setTarget(card_front)
        back_anim.setTarget(card_back)
        front_anim.start()
        back_anim.start()
    }

    fun frontToBack(){
        front_anim.setTarget(card_back)
        back_anim.setTarget(card_front)
        front_anim.start()
        back_anim.start()
    } */


}