package gini.apps.coin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gini.apps.coin.R
const val DOLLAR ="USD"

open class Adapter( private val coinsMap: Map<String, Double>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listCoin = coinsMap.toList()
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (getItemViewType(viewType)==1){
            viewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.above_the_dollar_rate_card, parent, false))
        } else {
            viewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.below_the_dollar_rate_card, parent, false
                )
            )

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is viewHolder) {
            val listOfCoinName = coinsMap.keys.toList()
            val listOfCoinValue = coinsMap.values.toList()
            holder.itemView.findViewById<TextView>(R.id.coin_view).text = listOfCoinName[position]

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(listCoin[position].second > coinsMap[DOLLAR]!!) {
            1
        } else{
            0
        }

    }

    override fun getItemCount(): Int {
        return coinsMap.size
    }


}

private class viewHolder(view: View) : RecyclerView.ViewHolder(view)
