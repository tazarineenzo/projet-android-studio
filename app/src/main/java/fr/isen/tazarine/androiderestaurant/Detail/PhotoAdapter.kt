package fr.isen.tazarine.androiderestaurant.Detail


import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class PhotoAdapter(activity: AppCompatActivity, val list: List<String>): FragmentStateAdapter(activity){

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(list[position])
    }
}