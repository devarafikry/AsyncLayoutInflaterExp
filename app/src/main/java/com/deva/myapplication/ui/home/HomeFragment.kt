package com.deva.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ScrollView
import android.widget.TextView
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deva.myapplication.R
import com.deva.myapplication.databinding.FragmentHomeBinding
import com.deva.myapplication.ui.PerfTrack

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAsyncInflater()
    }

    fun initAsyncInflater() {
        asyncLayoutInflater= AsyncLayoutInflater(context!!)
    }
    private lateinit var asyncLayoutInflater: AsyncLayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        PerfTrack.startTrack("Inflate fragment")
        val final_view: View? = LayoutInflater.from(context).inflate(R.layout.fragment_loading, container, false)
        asyncLayoutInflater.inflate(R.layout.fragment_home, container) {
            view, _, _ ->
            (final_view as? ViewGroup)?.addView(view) // add view to already inflated view
        }
//        val final_view: View? = LayoutInflater.from(context).inflate(R.layout.fragment_home, container, false)
        someHeavyProcess()
        final_view?.viewTreeObserver?.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (final_view.findViewById<ScrollView>(R.id.content_scroll_view)?.isShown == true) {
                    PerfTrack.stopTrack()
                    final_view.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                }
            }
        })
        return final_view
    }

    fun someHeavyProcess() {
        Thread.sleep(500)
    }

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentFirst = HomeFragment()
            val args = Bundle()
            fragmentFirst.setArguments(args)
            return fragmentFirst
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}