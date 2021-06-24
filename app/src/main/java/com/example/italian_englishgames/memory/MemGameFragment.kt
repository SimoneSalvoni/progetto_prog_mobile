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
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentMemGameBinding



class MemGameFragment : Fragment() {

    lateinit var binding: FragmentMemGameBinding
    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    val viewModel: MemViewModel by viewModels()
    lateinit var card1: String
    lateinit var card2: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_mem_game,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.memViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //animazioni, serve prendere il context
        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator)
                as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator)
                as AnimatorSet

        card.setOnClickListener{
            if(isBack){
                if(card1=="") {
                    backToFront()
                    isBack = false
                    card1 = binding..text.toString()
                }
                else{
                    card2 = binding..text.toString()
                    viewModel.check(card1, card2)
                    viewModel.checkGameState()
                }
            }else{
                frontToBack()
                isBack = true
            }

        }
    }
    fun backToFront(){
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
    }
}