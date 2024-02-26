package dev.dencrafty.tgparser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.dencrafty.tgparser.databinding.ActivityHostBinding

@AndroidEntryPoint
class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}