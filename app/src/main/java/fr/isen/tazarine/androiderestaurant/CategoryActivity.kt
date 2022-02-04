package fr.isen.tazarine.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.tazarine.androiderestaurant.Detail.DetailActivity
import fr.isen.tazarine.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.tazarine.androiderestaurant.network.MenuResult
import fr.isen.tazarine.androiderestaurant.network.NetworkConstants
import fr.isen.tazarine.androiderestaurant.network.Plats
import org.json.JSONObject


enum class LunchType {
    STARTER,DISHES,DESSERTS;

    companion object{
        fun getResString(type: LunchType):Int {
            return when(type) {
                STARTER -> R.string.starters
                DISHES -> R.string.dishes
                DESSERTS -> R.string.desserts
            }
        }

        fun getCategoryTiltle(type: LunchType): String{
            return when(type){
                STARTER ->"Entrées"
                DISHES -> "Plats"
                DESSERTS -> "Desserts"

            }

        }
    }
}



class CategoryActivity : BaseActivity() {
    lateinit var binding: ActivityCategoryBinding
    lateinit var currentCategory: LunchType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val item: LunchType = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        makeRequest()
        setupTitle()
        Log.d("life cycle", "CategoryActivity onCreate")

        //binding.textView.setText("extra " +item)
    }
    private fun setupTitle() {

        binding.titre.text = getString(LunchType.getResString(currentCategory)) //getString : convertir une ressource ID (le name de la string) en string
    }

    private fun setupList(items : List<Plats>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(items) {
            val intent = Intent (this@CategoryActivity, DetailActivity::class.java)
            intent.putExtra(ItemSelected,it)
            startActivity(intent)

        }
        binding.recyclerView.adapter = adapter
    }


    private fun makeRequest(){
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL+NetworkConstants.MENU
        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP,NetworkConstants.SHOP)
        val request = JsonObjectRequest(Request.Method.POST,
            url,
            parameters,
            {

                Log.d("tag","${it.toString(2)}")
                parseResult(it.toString())

            },
            {

                Log.d("tag","$it")


            })
        queue.add(request)
    }



    private fun parseResult(response: String) {
        val result = GsonBuilder().create().fromJson(response, MenuResult::class.java)
        val items = result.data.firstOrNull {
            it.name == LunchType.getCategoryTiltle(currentCategory)
        }?.items

      //  if (items != null) {
        //    setupList(items)
        //}
        items?.let {
            setupList(it)
        }
    }
    companion object {
        const val ItemSelected = "ItemSelected"
    }
    override fun onDestroy() {
        Log.d("life cycle", "CategoryActivity on Destroy")
        super.onDestroy()
    }
    override fun onStart() {
        Log.d("life cycle", "CategoryActivity on Stop")
        super.onStart()
    }
    override fun onPause() {
        Log.d("life cycle", "CategoryActivity on Pause")
        super.onPause()
    }
    override fun onResume() {
        Log.d("life cycle", "CategoryActivity on Resume")
        super.onResume()
    }
    override fun onStop() {
        Log.d("life cycle", "CategoryActivity on Stop")
        super.onStop()
    }
}
