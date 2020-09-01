package com.dijitlab.kopiketopos.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.dijitlab.kopiketopos.fragments.HistoryFragment
import com.dijitlab.kopiketopos.fragments.HomeFragment
import com.dijitlab.kopiketopos.fragments.MoreFragment
import com.dijitlab.kopiketopos.fragments.OngoingFragment
import com.dijitlab.kopiketopos.R
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val ongoingFragment = OngoingFragment()
        val historyFragment = HistoryFragment()
        val moreFragment = MoreFragment()

        val btn_product = findViewById<ImageButton>(R.id.btn_product)

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_ongoing -> makeCurrentFragment(ongoingFragment)
                R.id.ic_history -> makeCurrentFragment(historyFragment)
                R.id.ic_more -> makeCurrentFragment(moreFragment)
            }
            true
        }

        btn_product.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}
