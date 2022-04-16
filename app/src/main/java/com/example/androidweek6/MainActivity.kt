package com.example.androidweek6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidweek6.databinding.ActivityMainBinding
import com.example.androidweek6.fragment.NowPlayFragment
import com.example.androidweek6.fragment.TopRateFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        replaceFragment(NowPlayFragment())
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.now_play -> replaceFragment(NowPlayFragment())
                R.id.top_rate ->replaceFragment(TopRateFragment())
            }
            true
        }
    }
     fun replaceFragment(fragment: Fragment){
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}