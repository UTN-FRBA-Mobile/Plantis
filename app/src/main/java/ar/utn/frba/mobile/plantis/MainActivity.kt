package ar.utn.frba.mobile.plantis

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ar.utn.frba.mobile.plantis.databinding.MainActivityBinding
import ar.utn.frba.mobile.plantis.fragments.MyPlantisFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    private lateinit var myplantisFragment: Fragment
    private lateinit var link: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        if (savedInstanceState == null) {
            myplantisFragment = MyPlantisFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, myplantisFragment)
                .commitNow()
        }

        val navView = binding.navigationbar
        navView.itemIconTintList = null
        navView.setupWithNavController(navController)
    }

    fun getUrlFromIntent(view: View) {
        val url = "https://simple.wikipedia.org/wiki/Chlorophytum_comosum"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}