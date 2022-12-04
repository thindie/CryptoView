package com.example.thindie.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thindie.R
import com.example.thindie.databinding.FragmentCoinDetailBinding
import com.example.thindie.domain.Coin


class FragmentCoinDetail : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding Binding is null")
    private lateinit var coin: Coin


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCoinStats()
        setOnBackPress()
    }

    private fun showCoinStats() {
        with(binding) {
            tvSlash.text = " / "

            /*tvPrice.text = coin.price
            tvSlash.text = coin.slash
            tvLastUpdate.text = coin.lastUpdate
            tvLastMarket.text = coin.lastMarket
            tvFromSymbol.text = coin.fromSymbol*/
            tvLastMarketLabel.text = resources.getText(R.string.last_market_label)
            tvLastUpdateLabel.text = resources.getText(R.string.last_updated_label)
            //tvMaxPrice.text = coin.maxPrice
            tvMaxPriceLabel.text = resources.getText(R.string.max_price_label)
           // tvMinPrice.text = coin.minPrice
            tvMinPriceLabel.text = resources.getText(R.string.min_price_label)
          //  tvToSymbol.text = coin.toSymbol
            ivLogoCoin.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val args = navArgs<FragmentCoinPriceListArgs>()
        coin = args.value.coin
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun setOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(
                        FragmentCoinDetailDirections.actionFragmentCoinDetailToFragmentCoinPriceList2(

                        )
                    )
                }
            }
        )
    }
}