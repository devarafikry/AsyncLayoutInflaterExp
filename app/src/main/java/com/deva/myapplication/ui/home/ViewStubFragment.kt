package com.deva.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.deva.myapplication.R
import com.deva.myapplication.ui.PerfTrack

class ViewStubFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        PerfTrack.startTrack("Inflate fragment")

        /**
         * Normal inflation - No view stub
         */
        val final_view: View? = LayoutInflater.from(context).inflate(R.layout.fragment_view_stub_normal, container, false)
        final_view?.findViewById<Button>(R.id.btnActViewStub)?.setOnClickListener {
            val value = final_view.findViewById<EditText>(R.id.etValue).text.toString().toInt()
            when(value) {
                0 -> {
                    final_view.findViewById<ImageView>(R.id.img1).visibility = View.VISIBLE
                    final_view.findViewById<ImageView>(R.id.img2).visibility = View.INVISIBLE
                    final_view.findViewById<ImageView>(R.id.img3).visibility = View.INVISIBLE
                }
                1 -> {
                    final_view.findViewById<ImageView>(R.id.img1).visibility = View.INVISIBLE
                    final_view.findViewById<ImageView>(R.id.img2).visibility = View.VISIBLE
                    final_view.findViewById<ImageView>(R.id.img3).visibility = View.INVISIBLE
                }
                2 -> {
                    final_view.findViewById<ImageView>(R.id.img1).visibility = View.INVISIBLE
                    final_view.findViewById<ImageView>(R.id.img2).visibility = View.INVISIBLE
                    final_view.findViewById<ImageView>(R.id.img3).visibility = View.VISIBLE
                }
            }
        }

        /**
         * View stub inflation - Lazy
         */
//        val final_view: View? = LayoutInflater.from(context).inflate(R.layout.fragment_view_stub, container, false)
//        final_view?.findViewById<Button>(R.id.btnActViewStub)?.setOnClickListener {
//            val value = final_view.findViewById<EditText>(R.id.etValue).text.toString().toInt()
//            when(value) {
//                0 -> {
//                    getInflatedImage(final_view, R.id.inflated_1, R.id.img1).visibility = View.VISIBLE
//                    getInflatedImage(final_view, R.id.inflated_2, R.id.img2).visibility = View.INVISIBLE
//                    getInflatedImage(final_view, R.id.inflated_3, R.id.img3).visibility = View.INVISIBLE
//                }
//                1 -> {
//                    getInflatedImage(final_view, R.id.inflated_1, R.id.img1).visibility = View.INVISIBLE
//                    getInflatedImage(final_view, R.id.inflated_2, R.id.img2).visibility = View.VISIBLE
//                    getInflatedImage(final_view, R.id.inflated_3, R.id.img3).visibility = View.INVISIBLE
//                }
//                2 -> {
//                    getInflatedImage(final_view, R.id.inflated_1, R.id.img1).visibility = View.INVISIBLE
//                    getInflatedImage(final_view, R.id.inflated_2, R.id.img2).visibility = View.INVISIBLE
//                    getInflatedImage(final_view, R.id.inflated_3, R.id.img3).visibility = View.VISIBLE
//                }
//            }
//        }
        PerfTrack.stopTrack()

        return final_view
    }

    fun getInflatedImage(final_view: View, targetId: Int, viewStubId: Int): ImageView {
        if (final_view.findViewById<ImageView>(targetId) == null) {
            final_view.findViewById<ViewStub>(viewStubId).inflate()
        }
        return final_view.findViewById<ImageView>(targetId)
    }

    companion object {
        fun newInstance(): ViewStubFragment {
            val fragmentFirst = ViewStubFragment()
            val args = Bundle()
            fragmentFirst.setArguments(args)
            return fragmentFirst
        }
    }
}