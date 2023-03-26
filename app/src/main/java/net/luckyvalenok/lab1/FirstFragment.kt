package net.luckyvalenok.lab1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.luckyvalenok.lab1.databinding.FragmentFirstBinding
import kotlin.random.Random

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var cards: MutableList<AbstractCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cards = MutableList(10) {
            val name = it.toString()
            if (Random.nextInt(2) == 1) BigCard(name, name, name, name, name) else SmallCard(name, name, name, name, name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardAdapter = CardAdapter {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(it.id, it.header, it.subhead, it.title, it.titleSubhead, it.description))
        }

        binding.items.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = cardAdapter
        }

        cardAdapter.submitList(cards)
    }
}