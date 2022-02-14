package fr.isen.tazarine.androiderestaurant.network

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.tazarine.androiderestaurant.R
import fr.isen.tazarine.androiderestaurant.basket.BasketItem
import fr.isen.tazarine.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.tazarine.androiderestaurant.databinding.CellBasketBinding
import java.text.FieldPosition
import kotlin.coroutines.coroutineContext

class BasketAdapter (private val items: List<BasketItem>,val deleteClicklistener: (BasketItem)->Unit): RecyclerView.Adapter <BasketAdapter.BasketViewHolder>(){
lateinit var context : Context
    class BasketViewHolder(binding: CellBasketBinding) :RecyclerView.ViewHolder(binding.root){
         val dishName: TextView = binding.textView7
         val price : TextView = binding.textView8
         val quantity : TextView = binding.textView3
         val delete : ImageButton = binding.imagepoubelle
         val imageview : ImageView = binding.imageView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): BasketViewHolder{
        context = parent.context
        return BasketViewHolder(CellBasketBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BasketViewHolder,position: Int){
        val basketItem = items[position]
        holder.dishName.text = basketItem.plats.name
        holder.quantity.text= basketItem.quantity.toString()
        holder.price.text = "${basketItem.plats.prices.first().prix}€"

        holder.delete.setOnClickListener{
            deleteClicklistener.invoke(basketItem)

        }
        Picasso.get()
            .load(basketItem.plats.getThumbnailURL())
            .placeholder(R.drawable.sans_titre)
            .into(holder.imageview)

    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
