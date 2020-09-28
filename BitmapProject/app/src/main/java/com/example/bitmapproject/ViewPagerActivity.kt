package com.example.bitmapproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {
    private var position: Int =0
    private var listImg: ArrayList<String>? = null
    private lateinit var fragments: MutableList<Fragment>
    private lateinit var itemFragment: ItemFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        intent?.let{
            position = it.getIntExtra("position", 0)
            listImg = it.getStringArrayListExtra("images")
            listImg as ArrayList<String>
        }?.let{ listImg ->
            fragments = mutableListOf()
            listImg.forEach {url ->
                itemFragment = ItemFragment(url)
                fragments.add(itemFragment)
            }
            val myViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
            imgViewPager.adapter = myViewPagerAdapter
            imgViewPager.setCurrentItem(position,false)
        }

    }

}