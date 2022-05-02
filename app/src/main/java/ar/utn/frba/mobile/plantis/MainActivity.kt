package ar.utn.frba.mobile.plantis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.utn.frba.mobile.plantis.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.navigationbar.itemIconTintList = null
    }
}