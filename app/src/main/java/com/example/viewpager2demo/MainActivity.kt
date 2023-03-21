package com.example.viewpager2demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2demo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            myAdapter = Adapter()

            // add Fragments in your ViewPagerFragmentAdapter class
            myAdapter.addFragment(FirstFragment())
            myAdapter.addFragment(SecondFragment())

            viewPager2.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                offscreenPageLimit = 1

                val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
                val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()

                val recyclerView = getChildAt(0) as RecyclerView
                recyclerView.apply {
                    val padding = (pageMargin + pageOffset).toInt()
                    setPadding(padding, 0, padding, 0)
                    clipToPadding = false
                }
                adapter = myAdapter
            }
        }
    }

    inner class Adapter :
        FragmentStateAdapter(this) {

        private val fragmentList = mutableListOf<Fragment>()

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}