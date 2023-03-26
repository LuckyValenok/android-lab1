package net.luckyvalenok.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.luckyvalenok.lab1.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.materialButton.text = args.id.toString()
        binding.materialSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.text.setBackgroundColor(resources.getColor(if (isChecked) R.color.black else R.color.primary_90, context?.theme))
        }
        return binding.root
    }
}