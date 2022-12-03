package com.example.thindie.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.thindie.R
import com.example.thindie.databinding.ItemCoinInfoBinding
import com.example.thindie.domain.Coin

class CoinListRVAdapter(private val viewModel: CoinPriceListViewModel) :
    ListAdapter<Coin, CoinInfoViewHolder>(CoinCallBack()) {
    private lateinit var binding: ItemCoinInfoBinding

    class CoinCallBack : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return false
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(binding){
            tvLastUpdate.text = coin.lastUpdateTime
            tvSymbols.text = coin.name
            tvPrice.text  = coin.rate
            ivLogoCoin.setImageResource(R.drawable.ic_launcher_foreground)
        }
        holder.itemView.setOnClickListener{
            viewModel.getCoin(coin.id)
        }

    }
}
