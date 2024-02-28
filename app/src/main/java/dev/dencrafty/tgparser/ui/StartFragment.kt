package dev.dencrafty.tgparser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
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
            startPagingScreen(channelField)
        }

        channelField.setOnEditorActionListener { textView, action, event ->
            if (action == EditorInfo.IME_ACTION_DONE) {
                startPagingScreen(textView as EditText)
            }
            false
        }

        return binding.root
    }

    private fun startPagingScreen(channelField: EditText) {
        val action = StartFragmentDirections.actionStartFragmentToPagingFragment(
            channelField.text.toString().trim()
        )
        Navigation.findNavController(channelField).navigate(action)
    }
}