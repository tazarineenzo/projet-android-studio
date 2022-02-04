package fr.isen.tazarine.androiderestaurant.Detail
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import fr.isen.tazarine.androiderestaurant.BaseActivity
import fr.isen.tazarine.androiderestaurant.CategoryActivity
import fr.isen.tazarine.androiderestaurant.R
import fr.isen.tazarine.androiderestaurant.basket.Basket
import fr.isen.tazarine.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.tazarine.androiderestaurant.network.Plats
import kotlin.math.max


class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var currentDish:   Plats? = null
    //private var itemCount=1
    private var cpt = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(CategoryActivity.ItemSelected) as? Plats
        //peut etre nul à cause du cast
        setupContent()

        refreshShopButton()


        binding.boutonPlus.setOnClickListener {
            cpt++
            binding.textView4.setText("${cpt.toInt()}")
            refreshShopButton()

        }
        binding.boutonMoins.setOnClickListener {
            cpt = max(1f, cpt-1)
            binding.textView4.setText("${cpt.toInt()}")
            refreshShopButton()

        }
        binding.button.setOnClickListener {
            currentDish?.let { plats ->
            val basket = Basket.getBasket( this)
            basket.addItem(plats,cpt.toInt())
                basket.save(this)
                Snackbar.make(binding.root,R.string.itemAdded, Snackbar.LENGTH_LONG).show()
                //Toast.makeText(this,R.string.itemAdded,Toast.LENGTH_LONG).show()
                invalidateOptionsMenu()
            }
        }
    }



    private fun refreshShopButton() {
        currentDish?.let { dish ->
            val price: Float = dish.prices.first().prix.toFloat()
            val total = price * cpt
            binding.button.text = "${getString(R.string.total)} : $total euros"
        }
    }

    private fun setupContent(){
        binding.titre.text = currentDish?.name

        //var string = ""
        //currentDish?.ingredients.forEach {
        //    string = "$(it.name), "
        // }
        binding.textView.text = currentDish?.ingredient?.map { it.name }?.joinToString (", ")
        //map permet de convertir une liste

        currentDish?.let {

            binding.viewPager.adapter = PhotoAdapter(this, it.images)

        }

    }

    /*

    private fun refreshShopButton() {
        currentDish?.let { dish ->
            val price: Float = dish.prices.first().price.toFloat()
            val total = price * itemCount
            binding.buttonShop.text = "${getString(R.string.total)} $total euros"
            binding.quantity.text = itemCount.toInt().toString()


        }
    }


    private fun observeClick() {
        binding.imageButton1.setOnClickListener {
            itemCount = max(1f, itemCount-1)
            //1f : on précise que c'est un float
            refreshShopButton()
        }

        binding.imageButton2.setOnClickListener {
            itemCount++
            refreshShopButton()

        }

        binding.buttonShop.setOnClickListener {

        }
    }

     */
}


