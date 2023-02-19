package com.example.converter_demo_app

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.converter_demo_app.Utils.postTextOnClick
import com.example.converter_demo_app.databinding.MainActivityBinding
import com.example.converter_demo_app.ui.AppViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActivityMain : AppCompatActivity() {
    private var binding: MainActivityBinding? = null
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    private var tabPositionModel: AppViewModel? = null
    private var keyboardModel: AppViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initViewModels()
        mFragmentTitleList.addAll(listOf(*resources.getStringArray(R.array.units)))
        initViewPager()
        initTabLayoutMediator()
        initOnClickListeners()
        setTabPositionListener()
        enableButtonOnPositionChange(binding!!.tabLayout.selectedTabPosition)
    }

    private fun initViewModels() {
        tabPositionModel = Utils.generateViewModel(this)
        keyboardModel = Utils.generateViewModel(this)
    }

    private fun setTabPositionListener() {
        binding!!.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabPositionModel!!.select(position)
                enableButtonOnPositionChange(position)
            }
        })
    }

    private fun enableButtonOnPositionChange(pos: Int) {
        if (pos == 5) {
            binding!!.buttonPlusMinus.isEnabled = true
            binding!!.buttonPlusMinus.alpha = 1f
        } else {
            binding!!.buttonPlusMinus.isEnabled = false
            binding!!.buttonPlusMinus.alpha = 0.5f
        }
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle
        )
        for (i in resources.getIntArray(R.array.units).indices) {
            adapter.addFragment(ActivityConverter())
        }
        binding!!.viewPager.adapter = adapter
    }

    private fun initTabLayoutMediator() {
        val tabLayoutMediator = TabLayoutMediator(
            binding!!.tabLayout,
            binding!!.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = mFragmentTitleList[position]
        }
        tabLayoutMediator.attach()
    }

    private fun initOnClickListeners() {
        postTextOnClick(model = keyboardModel,button = binding!!.butOne)
        postTextOnClick(model = keyboardModel,button = binding!!.butTwo)
        postTextOnClick(model = keyboardModel,button = binding!!.butThree)
        postTextOnClick(model = keyboardModel,button = binding!!.butFour)
        postTextOnClick(model = keyboardModel,button = binding!!.butFive)
        postTextOnClick(model = keyboardModel,button = binding!!.butSix)
        postTextOnClick(model = keyboardModel,button = binding!!.butSeven)
        postTextOnClick(model = keyboardModel,button = binding!!.buttonEight)
        postTextOnClick(model = keyboardModel,button = binding!!.butNine)
        postTextOnClick(model = keyboardModel,button = binding!!.buttonPlusMinus)
        postTextOnClick(model = keyboardModel,button = binding!!.butClear)
        postTextOnClick(model = keyboardModel,button = binding!!.buttonDecimal)
        postTextOnClick(model = keyboardModel,button = binding!!.butCorrectValue)

        binding!!.calculatorButton.setOnClickListener {
            val intent = Intent(
                this@ActivityMain,
                ActivityCalculator::class.java
            )
            goToActivity(intent = intent)
        }
    }

    private fun goToActivity(intent: Intent) {
        startActivity(
            intent,
            ActivityOptions.makeSceneTransitionAnimation(this@ActivityMain).toBundle()
        )
    }

    internal class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }

        override fun createFragment(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getItemCount(): Int {
            return mFragmentList.size
        }
    }
}