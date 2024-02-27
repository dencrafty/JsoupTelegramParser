package dev.dencrafty.tgparser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import dev.dencrafty.tgparser.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(layoutInflater)
        val startBtn = binding.start
        val channelField = binding.channel

        startBtn.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToPagingFragment(channelField.text.toString().trim())
            Navigation.findNavController(startBtn).navigate(action)
        }

        return binding.root
    }
}