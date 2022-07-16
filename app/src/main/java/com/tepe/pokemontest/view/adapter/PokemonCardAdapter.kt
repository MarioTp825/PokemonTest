package com.tepe.pokemontest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tepe.pokemontest.databinding.RvItemPokemonCardBinding
import com.tepe.pokemontest.databinding.RvItemPokemonLoadingBinding
import com.tepe.pokemontest.model.dto.Pokemon

class PokemonCardAdapter(
    private val pokemonCollection: MutableList<Pokemon>,
    private val loadData: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LOADING = 0
    private val TYPE_CARD = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == TYPE_CARD) ViewHolder(
            RvItemPokemonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) else LoadingViewHolder(
            RvItemPokemonLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is LoadingViewHolder) {
            holder.bind()
            loadData()
        }else
            (holder as ViewHolder).bind(pokemonCollection[position])
    }

    fun addItems(items: List<Pokemon>) = add(items)

    fun update() {
        val su = pokemonCollection.size
        println(su)
        notifyDataSetChanged()
    }

    fun replaceItems(items: List<Pokemon>) {
        pokemonCollection.clear()
        add(items)
    }

    override fun getItemViewType(position: Int) = if(pokemonCollection.lastIndex == position) TYPE_LOADING else TYPE_CARD

    private fun add(items: List<Pokemon>) {
        pokemonCollection.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = pokemonCollection.size

    class ViewHolder(private val view: RvItemPokemonCardBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(pokemon: Pokemon) {
            view.tvPokemonId.text = pokemon.number
            view.tvPokemonName.text = pokemon.name

            Picasso.get()
                .load(pokemon.image)
                .fit()
                .centerInside()
                .into(view.ivPokemonImage)

        }
    }

    class LoadingViewHolder(private val view: RvItemPokemonLoadingBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind() {
            view.llLoadingPikachu
        }
    }
}