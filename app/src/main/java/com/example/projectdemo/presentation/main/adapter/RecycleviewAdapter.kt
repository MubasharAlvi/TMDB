package com.example.projectdemo.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectdemo.R
import com.example.projectdemo.data.remote.model.MovieModelClass
import com.example.projectdemo.utils.Constant.URL_IMAGE
import com.example.projectdemo.utils.setSafeOnClickListener

class RecycleviewAdapter(ctx: Context, movieList: ArrayList<MovieModelClass>,
                         layout: Int, val interfaceClickListener:InterfaceClickListener
                         ) :
    RecyclerView.Adapter<RecycleviewAdapter.ViewHolder>() {
    private val context = ctx
    var list = movieList
    val layouta=layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layouta,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val url: String = "https://image.tmdb.org/t/p/w500"
        val urlImage: String = list[position].poster_path.toString()
        Glide.with(holder.itemView.context)
            .load(URL_IMAGE + urlImage)
            .into(holder.poster)
        holder.title.text = list[position].title.toString()
        val movieModelListPosition = list[position]

       holder.card.setSafeOnClickListener {
         interfaceClickListener.moviePlayer(movieModelListPosition,position)
       }

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val card = view.findViewById<CardView>(R.id.card_recycleview_id)
        val poster = view.findViewById<ImageView>(R.id.poster_Id)
        val title =  view.findViewById<TextView>(R.id.title_movie_id)

    }
    interface InterfaceClickListener {
        fun moviePlayer(movieModelListPosition: MovieModelClass, position: Int)

    }

}

