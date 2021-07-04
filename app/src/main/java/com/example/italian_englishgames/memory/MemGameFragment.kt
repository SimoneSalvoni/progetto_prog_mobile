package com.example.italian_englishgames.memory

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentMemGameBinding
import com.google.android.material.snackbar.Snackbar


class MemGameFragment : Fragment(), GridAdapter.OnItemClickListener {

    lateinit var binding: FragmentMemGameBinding

    //lateinit var front_anim: AnimatorSet
    //lateinit var back_anim: AnimatorSet
    val viewModel: MemViewModel by viewModels()

    private var card1 = MemCard()
    private lateinit var cardList: MutableList<MemCard>
    private var pos1 = 999
    private var horizontal = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mem_game, container, false)
        if (savedInstanceState != null){
            binding.viewTimer.base = savedInstanceState.getLong("time")
            horizontal = !horizontal
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.memViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        if (savedInstanceState==null) viewModel.createGame()
        cardList = viewModel.words.value!!
        val adapter = GridAdapter(requireContext(), cardList, this)
        binding.gameTable.adapter = adapter
        if (horizontal) binding.gameTable.layoutManager = GridLayoutManager(requireContext(), 8)
        else binding.gameTable.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.gameTable.setHasFixedSize(true)
        binding.viewTimer.start()
        /* animazioni, serve prendere il context
        front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator)
                as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.back_animator)
                as AnimatorSet */
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.viewTimer.stop()
        outState.putLong("time", binding.viewTimer.base)
        super.onSaveInstanceState(outState)
    }

    override fun onItemClick(position: Int) {
        val clickedItem: MemCard = cardList[position]
        if (clickedItem.isBack) {
            if (card1.word == "") {
                clickedItem.isBack = false
                card1 = clickedItem
                pos1 = position
                val cardview1 = binding.gameTable.getChildAt(position)
                cardview1.findViewById<TextView>(R.id.card_front).visibility = View.VISIBLE
            } else {
                clickedItem.isBack = false
                val cardview1 = binding.gameTable.getChildAt(pos1)
                val cardview2 = binding.gameTable.getChildAt(position)
                cardview2.findViewById<TextView>(R.id.card_front).visibility = View.VISIBLE
                if (viewModel.check(card1, clickedItem)) {
                    rightChoice(cardview1, cardview2)
                } else {
                    wrongChoice(cardview1,cardview2)
                    card1.isBack = true
                    clickedItem.isBack = true
                }
                card1 = MemCard()
                pos1 = 999
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

    private fun rightChoice(cardview1:View, cardview2:View){
        cardview1.findViewById<CardView>(R.id.cardView)
            .setBackgroundColor(Color.parseColor("#00c853"))
        cardview2.findViewById<CardView>(R.id.cardView)
            .setBackgroundColor(Color.parseColor("#00c853"))
        checkWin()
    }

    private fun wrongChoice(cardview1:View, cardview2:View){
        cardview1.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#ef1c19"))
        cardview2.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#ef1c19"))
        Handler(Looper.getMainLooper()).postDelayed({
            cardview1.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#fdd835"))
            cardview2.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#fdd835"))
            cardview1.findViewById<TextView>(R.id.card_front).visibility = View.INVISIBLE
            cardview2.findViewById<TextView>(R.id.card_front).visibility = View.INVISIBLE },500)

    }

    private fun checkWin() {
        if (viewModel.checkGameState()) {
            val time = binding.viewTimer.text.toString()
            val action = MemGameFragmentDirections.actionMemGameFragmentToMemWinFragment(time)
            requireView().findNavController().navigate(action)
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
