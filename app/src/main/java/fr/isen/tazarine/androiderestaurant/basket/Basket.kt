package fr.isen.tazarine.androiderestaurant.basket

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import fr.isen.tazarine.androiderestaurant.network.Plats
import java.io.File
import java.io.Serializable
import java.security.AccessControlContext


class Basket(val items: MutableList<BasketItem>): Serializable{

    var itemsCount: Int = 0
        get(){

           /* var count = 0
            items.forEach{
                count += it.quantity
            }
            return count*/
            val count = items.map {
                it.quantity
            }.reduceOrNull { acc, i -> acc + i  }
            return count ?:0
        }

    fun removeItem(basketItem: BasketItem){

        items.remove(basketItem)
    }


    fun addItem(item: Plats, quantity: Int){
        val existingItem = items.firstOrNull {it.plats.name == item.name}
        existingItem?.let {
            existingItem.quantity += quantity
        } ?: run {
            val basketItem = BasketItem(item,quantity)
            items.add(basketItem)
        }

    }

    fun save(context: Context){
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        Log.d("basket", json)
        updateCounter(context)

    }

    private fun updateCounter(context: Context){
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT,itemsCount)
        editor.apply()

    }

    companion object {

        fun getBasket(context: Context): Basket{
            val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
            if(jsonFile.exists()){
                val json = jsonFile.readText()
                return GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                return Basket(mutableListOf())
            }
        }
         const val BASKET_FILE ="basket.json"
         const val ITEMS_COUNT = "ITEMS_COUNT"
         const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"

    }
}

class BasketItem(val plats: Plats,var quantity: Int):Serializable{}