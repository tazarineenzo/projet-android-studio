package fr.isen.tazarine.androiderestaurant

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.tazarine.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenClick()
        Log.d("life cycle", "HomeActivity onCreate")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun listenClick(){
        binding.buttonStarters.setOnClickListener{
            showCategory(LunchType.STARTER)
        }
        binding.buttonDishes.setOnClickListener{
            showCategory(LunchType.DISHES)
        }
        binding.buttonDesserts.setOnClickListener {
            showCategory(LunchType.DESSERTS)
        }
    }


    private fun showCategory(item: LunchType){
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(HomeActivity.CategoryType,item)
        startActivity(intent)
    }

    companion object{
        const val CategoryType ="CategoryType"
    }


    override fun onDestroy() {
        Log.d("life cycle", "HomeActivity on Destroy")
        super.onDestroy()
    }
    override fun onStart() {
        Log.d("life cycle", "HomeActivity on Start")
        super.onStart()
    }
    override fun onPause() {
        Log.d("life cycle", "HomeActivity on Pause")
        super.onPause()
    }
    override fun onResume() {
        Log.d("life cycle", "HomeActivity on Resume")
        super.onResume()
    }
    override fun onStop() {
        Log.d("life cycle", "HomeActivity on Stop")
        super.onStop()
    }
}
