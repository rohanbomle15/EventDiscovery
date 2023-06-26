package com.robo.eventdiscovery.adapter

import android.content.Context
import com.robo.eventdiscovery.model.Event
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.robo.eventdiscovery.databinding.EventItemBinding


class EventAdapter(
    private val context: Context,
    private val eventList: List<Event>
) : RecyclerView.Adapter<EventViewHolder>() {

    private lateinit var binding: EventItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
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

    override fun getItemCount(): Int = eventList.size
}
