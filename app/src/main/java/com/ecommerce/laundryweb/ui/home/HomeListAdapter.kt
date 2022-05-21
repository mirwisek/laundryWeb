package com.snorlux.app.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.laundryweb.R
import com.ecommerce.laundryweb.changeDrawableTint
import com.ecommerce.laundryweb.databinding.ItemListHomeBinding
import com.ecommerce.laundryweb.ui.home.HomeListItem


class HomeListAdapter(private val onHomeItemClicked: ((selectedItem: HomeListItem) -> Unit)? = null): RecyclerView.Adapter<HomeListAdapter.HomeListItemViewHolder>() {

    private var list: List<HomeListItem>? = null
    private lateinit var context: Context

    companion object {
        const val DARK_BLUE = R.color.dark_blue
        const val SKY_BLUE =  R.color.sky_blue_light
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListItemViewHolder {

        context = parent.context
        val binding = ItemListHomeBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeListItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: HomeListItemViewHolder, position: Int) {

        val darkBlueColor = ContextCompat.getColor(context, DARK_BLUE)
        val skyBlueColor = ContextCompat.getColor(context, SKY_BLUE)

        list?.let { list ->
            val item = list[position]
            // Change color for alternative views
            if(position % 2 == 0) {
                holder.viewBinding.let { binding ->
                    binding.text.setTextColor(darkBlueColor)
                    binding.iconNextBg.drawable.changeDrawableTint(darkBlueColor)
                    binding.iconNext.drawable.changeDrawableTint(skyBlueColor)
                    binding.card.setCardBackgroundColor(skyBlueColor)
                }
            } else {
                holder.viewBinding.let { binding ->
                    binding.text.setTextColor(skyBlueColor)
                    binding.iconNextBg.drawable.changeDrawableTint(skyBlueColor)
                    binding.iconNext.drawable.changeDrawableTint(darkBlueColor)
                    binding.card.setCardBackgroundColor(darkBlueColor)
                }
            }

            holder.viewBinding.apply {
                text.text = item.title
                icon.setImageDrawable(ContextCompat.getDrawable(context, item.icon))
            }

            holder.itemView.setOnClickListener {
                onHomeItemClicked?.invoke(item)
            }
        }
    }


    inner class HomeListItemViewHolder(val viewBinding: ItemListHomeBinding):
        RecyclerView.ViewHolder(viewBinding.root) {
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<HomeListItem>){
        list = newList
        notifyDataSetChanged()
    }

}