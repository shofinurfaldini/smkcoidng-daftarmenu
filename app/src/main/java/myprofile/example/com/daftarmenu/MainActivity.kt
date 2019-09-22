package myprofile.example.com.daftarmenu

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.tabs_main
import kotlinx.android.synthetic.main.activity_main.viewpager_main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager_main.adapter=ViewPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }

    inner class ViewPagerAdapter(fm: FragmentManager)
        : FragmentPagerAdapter(fm){
        private val pages = listOf(
            MakananFragmen.getInstance(),
            MinumanFragmen.getInstance()
        )

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0 -> "Makanan"
                else -> "Minuman"
            }
        }
    }
}