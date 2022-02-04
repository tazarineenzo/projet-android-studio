package fr.isen.tazarine.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Prix(@SerializedName("price") val prix: String) : Serializable {
}
