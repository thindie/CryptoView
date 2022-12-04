package com.example.thindie.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thindie.databinding.FragmentCoinPriceListBinding
import com.example.thindie.domain.Coin


class FragmentCoinPriceList : Fragment() {
    private val viewModel: CoinPriceListViewModel by lazy {
        ViewModelProvider(this)[CoinPriceListViewModel::class.java]
            .apply {
                getCoinList()
            }
    }

    private lateinit var coin: Coin
    private var _binding: FragmentCoinPriceListBinding? = null
    private val binding: FragmentCoinPriceListBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinPriceList Binding is null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        waitingForCoinToShow()

    }

    private fun waitingForCoinToShow() {
        viewModel.coin.observe(viewLifecycleOwner) {
            coin = it
            findNavController().navigate(
                FragmentCoinPriceListDirections.actionFragmentCoinPriceList2ToFragmentCoinDetail(
                    coin
                )
            )
        }
    }

    private fun setupRecyclerView() {
        val resources = resources
        val adapter = CoinListRVAdapter(viewModel, resources)
        val recyclerView = binding.rvCoinPriceList
        recyclerView.adapter = adapter
        viewModel.coinList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinPriceListBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}