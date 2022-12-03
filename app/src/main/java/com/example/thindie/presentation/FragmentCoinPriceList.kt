package com.example.thindie.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thindie.R



class FragmentCoinPriceList : Fragment() {
    // TODO: Rename and change types of parameter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_coin_price_list, container, false)
    }

    companion object {
        private const val ARG_PARAM1 =""
        private const val ARG_PARAM2 =""
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCoinPriceList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}