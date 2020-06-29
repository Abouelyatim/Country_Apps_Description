package com.example.geomob.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.geomob.ui.CountryImageFragment


class CountryPagerAdapter(fragmentManager: FragmentManager, private val images: List<String>?)
    : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return CountryImageFragment.newInstance(
            images?.get(position)!!,
            position,
            count
        )
    }


    override fun getCount(): Int {
        return images?.size!!
    }

}