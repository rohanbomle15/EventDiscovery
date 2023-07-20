package com.robo.eventdiscovery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robo.eventdiscovery.R
import com.robo.eventdiscovery.model.Product


class ProductAdapter(private val dataSet: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvPrice: TextView
        val tvDescription: TextView

        init {
            tvTitle = view.findViewById(R.id.title)
            tvPrice = view.findViewById(R.id.price)
            tvDescription = view.findViewById(R.id.description)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = dataSet[position]
        viewHolder.tvTitle.text = product.title
        viewHolder.tvPrice.text = product.price.toString()
        viewHolder.tvDescription.text = product.description
    }

    override fun getItemCount() = dataSet.size

}
