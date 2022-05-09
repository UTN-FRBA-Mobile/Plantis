package ar.utn.frba.mobile.plantis

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ar.utn.frba.mobile.plantis.databinding.MainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        System.out.println("Boton Result")
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            System.out.println("Boton ???????")
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }
}