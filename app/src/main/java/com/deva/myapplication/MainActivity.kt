package com.deva.myapplication

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.deva.myapplication.ui.PerfTrack
import com.deva.myapplication.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private lateinit var asyncLayoutInflater: AsyncLayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectFragment(HomeFragment())
    }

    private fun selectFragment(fragment: Fragment) {
        openFragment(fragment)
    }

    private fun openFragment(fragment: Fragment) {
        PerfTrack.startTrack("Inflate fragment")

        handler.post(Runnable {
            val backStateName = fragment.javaClass.name
            val manager = supportFragmentManager
            val ft = manager.beginTransaction()
            val currentFrag = manager.findFragmentByTag(backStateName)
            if (currentFrag != null && manager.fragments.size > 0) {
                showSelectedFragment(fragment, manager, ft)
            } else {
                ft.add(
                    R.id.fr_container,
                    fragment,
                    backStateName
                ) // add fragment if there re not registered on fragmentManager

                showSelectedFragment(fragment, manager, ft)

            }
            ft.commitNowAllowingStateLoss();
        })
    }

    private fun showSelectedFragment(
        fragment: Fragment,
        manager: FragmentManager,
        ft: FragmentTransaction
    ) {
        for (i in manager.fragments.indices) {
            val frag = manager.fragments[i]
            if (frag.javaClass.name.equals(fragment.javaClass.name, ignoreCase = true)) {
                ft.show(frag) // only show fragment what you want to show
                frag.userVisibleHint = true
            } else {
                ft.hide(frag) // hide all fragment
            }
        }
    }
}