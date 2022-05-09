package ar.utn.frba.mobile.plantis

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ar.utn.frba.mobile.plantis.databinding.MainActivityBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val navView = binding.navigationbar
        navView.itemIconTintList = null
        navView.setupWithNavController(navController)

    }


    fun getUrlFromIntent(view: View) {
        val link = "https://simple.wikipedia.org/wiki/Chlorophytum_comosum"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }
}