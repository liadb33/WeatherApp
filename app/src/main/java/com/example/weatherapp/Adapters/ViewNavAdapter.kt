package com.example.weatherapp.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewNavAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: MutableList<Fragment> = mutableListOf()

    private val fragmentTitles : MutableList<String> = mutableListOf()



    fun addFragment(fragment: Fragment,title : String){
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
    }

    fun getPageTitle(position: Int) : String{
        return fragmentTitles.get(position)
    }

}