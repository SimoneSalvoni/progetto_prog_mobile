package com.example.italian_englishgames.memory

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentMemGameBinding


class MemGameFragment : Fragment(), GridAdapter.OnItemClickListener {

    lateinit var binding: FragmentMemGameBinding
    private val viewModel: MemViewModel by viewModels()

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
            horizontal = !savedInstanceState.getBoolean("horizontal")
        }
        val toolbar: Toolbar = binding.mainToolbar
        toolbar.setNavigationOnClickListener {
            toolbar.findNavController().navigate(R.id.action_memGameFragment_to_memMenuFragment)
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.viewTimer.stop()
        outState.putLong("time", binding.viewTimer.base)
        outState.putBoolean("horizontal", horizontal)
        super.onSaveInstanceState(outState)
    }

    /**
     * Questa funzione scopre una carta, e se la carta è la seconda che viene scoperta di fila si controlla se
     * le due carte contengono l'una la traduzione dell'altra
     *
     * @param position è la posizione della carta nella lista cardList
     */
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
                    wrongChoice(cardview1,cardview2, card1, clickedItem)
                }
                card1 = MemCard()
                pos1 = 999
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Carta già scoperta!",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    /**
     * Modifica del colore delle carte trovare correttamente
     */
    private fun rightChoice(cardview1:View, cardview2:View){
        cardview1.findViewById<CardView>(R.id.cardView)
            .setBackgroundColor(Color.parseColor("#00c853"))
        cardview2.findViewById<CardView>(R.id.cardView)
            .setBackgroundColor(Color.parseColor("#00c853"))
        checkWin()
    }

    /**
     * Se si scoprono due carte che non formano una coppia, diventano rosse e poi si ricoprono
     */
    private fun wrongChoice(cardview1:View, cardview2:View, card1: MemCard, card2: MemCard){
        cardview1.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#ef1c19"))
        cardview2.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#ef1c19"))
        Handler(Looper.getMainLooper()).postDelayed({
            cardview1.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#fdd835"))
            cardview2.findViewById<CardView>(R.id.cardView).setBackgroundColor(Color.parseColor("#fdd835"))
            cardview1.findViewById<TextView>(R.id.card_front).visibility = View.INVISIBLE
            cardview2.findViewById<TextView>(R.id.card_front).visibility = View.INVISIBLE
            card1.isBack = true
            card2.isBack = true                                        },500)

    }

    /**
     * Questa funzione controlla se tutte le carte sono state scoperte, e in caso affermativo
     * l'utente viene mandato alla schermata di vittoria
     */
    private fun checkWin() {
        if (viewModel.checkGameState()) {
            val time = binding.viewTimer.text.toString()
            val action = MemGameFragmentDirections.actionMemGameFragmentToMemWinFragment(time)
            requireView().findNavController().navigate(action)
        }
    }



}

