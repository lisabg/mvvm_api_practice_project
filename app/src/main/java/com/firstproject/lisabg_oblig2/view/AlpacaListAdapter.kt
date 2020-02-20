package com.firstproject.lisabg_oblig2.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firstproject.lisabg_oblig2.R
import com.firstproject.lisabg_oblig2.model.Alpaca
import kotlinx.android.synthetic.main.item_alpaca.view.*

class AlpacaListAdapter(var alpacas: ArrayList<Alpaca>): RecyclerView.Adapter<AlpacaListAdapter.CountryViewHolder>() {

    fun updateAlpacas(newAlpacas: List<Alpaca>) {
        alpacas.clear()
        alpacas.addAll(newAlpacas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_alpaca, parent, false)
    )

    override fun getItemCount() = alpacas.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(alpacas[position])
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.item_profile_picture
        private val alpacaName = view.item_name
        private val alpacaAge = view.item_age
        private val alpacaLocation = view.item_location

        fun bind(alpaca: Alpaca) {
            alpacaName.text = alpaca.name
            alpacaAge.text = alpaca.age
            alpacaLocation.text = alpaca.location
            imageView.loadImage(alpaca.imgSrc)
        }

        private fun ImageView.loadImage(uri: String?) {
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            Glide.with(this.context)
                .setDefaultRequestOptions(options)
                .load(uri)
                .into(this)
        }
    }
}