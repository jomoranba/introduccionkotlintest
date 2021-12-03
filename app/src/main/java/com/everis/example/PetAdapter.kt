package com.everis.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias PetCallback = (pet: Pet) -> Unit

class PetAdapter : ListAdapter<DataItem, PetAdapter.PetViewHolder>(PetDiffCallback()) {

    private lateinit var callback: PetCallback

    fun submitPets(list: List<Pet>) {
        //TODO : Transformar la lista de Pet a lista DataItem, y enviarlo como parametro a la función submitList
        submitList(list.map { DataItem.PetItem(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val item = getItem(position) as DataItem.PetItem
        holder.bind(item.pet)
    }

    fun setClickItemCallback(callback: PetCallback) {
        this.callback = callback
    }

    inner class PetViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: Pet) {
            view.findViewById<TextView>(R.id.tvBreed).text = item.breed
            view.findViewById<TextView>(R.id.tvGender).text = item.gender.toString()

            //TODO: Evaluar la el tipo de mascota para determinar que imagen mostrar
            view.findViewById<ImageView>(R.id.ivIcon).setImageResource(
                when(item){
                    is Dog -> R.drawable.ic_dog
                    else -> R.drawable.ic_cat
                }
            )
            view.setOnClickListener {
                //TODO: Evaluar si el callback esta inicializado, si  lo esta, utilizar el function
                if(::callback.isInitialized){
                    callback.invoke(item)
                }
            }
        }
    }

}

class PetDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem {
    data class PetItem(val pet: Pet) : DataItem() {
        override val id = pet.id
    }

    abstract val id: Int

}