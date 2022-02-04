package fr.isen.tazarine.androiderestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.tazarine.androiderestaurant.databinding.FragmentPhotoBinding
import java.net.URL


class PhotoFragment : Fragment() {
      private lateinit var binding: FragmentPhotoBinding

      companion object{

          const val URL = "url"
          fun newInstance(url: String) : PhotoFragment{

              return PhotoFragment().apply {

                  arguments = Bundle().apply {

                      putString(URL,url)
                  }


              }

          }


      }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(URL)
        if (url?.isNotEmpty() == true){
            Picasso.get().load(url).placeholder(R.drawable.sans_titre).into(binding.photoFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater,container,false)
        return binding.root
    }


}