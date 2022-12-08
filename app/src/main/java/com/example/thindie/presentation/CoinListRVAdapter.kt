package com.example.thindie.presentation

import android.content.res.Resources
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.thindie.R
import com.example.thindie.databinding.ItemCoinInfoBinding
import com.example.thindie.domain.Coin
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CoinListRVAdapter(
    private val viewModel: CoinPriceListViewModel,
    private val resources: Resources
) :
    ListAdapter<Coin, CoinInfoViewHolder>(CoinCallBack()) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var _binding: ItemCoinInfoBinding? = null
    private val binding: ItemCoinInfoBinding
        get() = _binding ?: throw RuntimeException("CoinListAdapter binding is null")

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
        _binding = binding
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(binding){
            tvLastUpdate.text = String.format(resources.getText(R.string.last_update_template).toString()
                ,coin.lastUpdate)
            tvSymbols.text = String.format(resources.getText(R.string.symbols_template).toString(),
                coin.fromSymbol,coin.toSymbol)
            tvPrice.text  = String.format(resources.getText(R.string.price_label).toString(),
                coin.price)

            Picasso.get()
                .load(coin.imageUrl)
                .into(ivLogoCoin)
        }
        holder.itemView.setOnClickListener{
            viewModel.getCoin(position)
        }
    }

}
