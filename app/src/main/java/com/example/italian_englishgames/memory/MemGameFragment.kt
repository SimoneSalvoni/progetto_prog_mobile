package com.example.italian_englishgames.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    private var card1=MemCard()
    private lateinit var cardList: MutableList<MemCard>
    private var pos1=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mem_game, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.memViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.createGame()
        cardList = viewModel.words.value!!
        val adapter = GridAdapter(requireContext(), cardList, this)
        binding.gameTable.adapter = adapter
        binding.gameTable.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.gameTable.setHasFixedSize(true)
        /* animazioni, serve prendere il context
        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator)
                as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator)
                as AnimatorSet */
    }

    override fun onItemClick(position: Int) {
        var clickedItem: MemCard = cardList[position]
        if (clickedItem.isBack) {
            if (card1.word == "") {
                clickedItem.isBack = false
                card1 = clickedItem
                pos1= position
                var cardview1 = binding.gameTable.getChildAt(position)
                cardview1.findViewById<TextView>(R.id.card_front).visibility = View.VISIBLE
                //show ma bisogna mettere mano all'xml
            } else {
                if (viewModel.check(card1, clickedItem)) {
                    viewModel.checkGameState()
                    //feedback positivo per aver preso la parola
                } else {
                    var cardview1 = binding.gameTable.getChildAt(pos1)
                    cardview1.findViewById<TextView>(R.id.card_front).visibility = View.INVISIBLE
                    var cardview2 = binding.gameTable.getChildAt(position)
                    cardview2.findViewById<TextView>(R.id.card_front).visibility = View.INVISIBLE
                    card1.isBack=true
                    clickedItem.isBack=true
                    //feedback negativo
                }
                card1.word = ""
            }
        } else {
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
    }

     */
