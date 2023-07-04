package com.robo.eventdiscovery.adapter

import android.content.Context
import com.robo.eventdiscovery.model.Event
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.robo.eventdiscovery.databinding.EventItemBinding


class EventAdapter(
    private val context: Context
) : ListAdapter<Event, EventViewHolder>(EventsDiffUtil()), Filterable {

    private lateinit var binding: EventItemBinding

    var eventList: ArrayList<Event> = ArrayList()
    var eventListFiltered: ArrayList<Event> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        if (event.images.isNotEmpty()) {
            val imageURL = event.images[0].url
            var requestOptions = RequestOptions()
            Glide.with(context)
                .load(imageURL)
                .apply(requestOptions)
                .skipMemoryCache(true)
                .into(binding.ivEvent)
        }
        holder.bind(event)
    }

    override fun submitList(list: MutableList<Event>?) {
        list?.let {
            eventList.addAll(it)
            eventListFiltered.addAll(eventList)
        }
        super.submitList(list)
    }

    override fun getItemCount(): Int = eventListFiltered.size

    class EventsDiffUtil: DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString()?.lowercase() ?: ""
                eventListFiltered = if (charString.isEmpty()) eventList else {
                    val filteredList = ArrayList<Event>()
                    eventList
                        .filter {
                            (it.name.lowercase().contains(constraint!!)) or
                                    (it.name.lowercase().contains(constraint))

                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = eventListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                this@EventAdapter.eventListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Event>
                notifyDataSetChanged()
            }
        }
    }
}
