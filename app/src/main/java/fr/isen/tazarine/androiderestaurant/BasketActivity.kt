package fr.isen.tazarine.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.tazarine.androiderestaurant.basket.Basket
import fr.isen.tazarine.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.tazarine.androiderestaurant.network.BasketAdapter

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadList()
    }


    private fun loadList(){
        val basket= Basket.getBasket(this)
        val items = basket.items
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = BasketAdapter(items){
            basket.removeItem(it)
            basket.save(this)
            loadList()
        }
    }
}