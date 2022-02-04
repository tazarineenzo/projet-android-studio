package fr.isen.tazarine.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.tazarine.androiderestaurant.databinding.CellMainBinding
import fr.isen.tazarine.androiderestaurant.network.Plats

class ItemAdapter(val items:List<Plats>,val itemClickListener: (Plats)-> Unit): (RecyclerView.Adapter<ItemAdapter.ItemViewHolder>)() {

    class ItemViewHolder(binding:CellMainBinding ): RecyclerView.ViewHolder(binding.root){
        val title: TextView = binding.listMain
        val layout: CardView = binding.root
        val image:ImageView = binding.imageView
        val prix: TextView = binding.Prix
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CellMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(ViewHolder: ItemViewHolder, position: Int) {
        val item = items[position]
        ViewHolder.prix.text = "${item.prices.first().prix} €"
        ViewHolder.title.text = item.name
        Picasso
            .get()
            .load(item.getThumbnailURL())
            .placeholder(R.drawable.sans_titre)
            .into(ViewHolder.image)

        ViewHolder.layout.setOnClickListener {
        itemClickListener.invoke(item)}
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}