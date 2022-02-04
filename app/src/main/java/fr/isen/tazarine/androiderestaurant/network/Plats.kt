package fr.isen.tazarine.androiderestaurant.network

import android.media.Image
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Plats(
    @SerializedName("name_fr") val name: String,
    @SerializedName("images") val images : List<String>,
    @SerializedName("ingredients") val ingredient: List<Ingredient>,
    @SerializedName("prices") val prices: List<Prix>
): Serializable {
    fun getThumbnailURL(): String? {
        return if (images.isNotEmpty() && images.first().isNotEmpty()) {
            images.first()
        } else {
            null
        }

    }
}
