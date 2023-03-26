package net.luckyvalenok.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val onClick: (AbstractCard) -> Unit) :
    ListAdapter<AbstractCard, CardAdapter.CardViewHolder>(CardDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            LayoutInflater.from(parent.context).inflate(if (viewType == 1) R.layout.item_big else R.layout.item_small, parent, false)
        )

    override fun getItemViewType(position: Int): Int {
        val card: AbstractCard = getItem(position)
        return card.viewType
    }

    override fun submitList(list: MutableList<AbstractCard>?) {
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val header by lazy { itemView.findViewById<TextView>(R.id.header) }
        private val subhead by lazy { itemView.findViewById<TextView>(R.id.subhead) }
        private val title by lazy { itemView.findViewById<TextView?>(R.id.title) }
        private val titleSubhead by lazy { itemView.findViewById<TextView?>(R.id.titleSubhead) }
        private val description by lazy { itemView.findViewById<TextView?>(R.id.description) }

        init {
            itemView.setOnClickListener {
                onClick(getItem(adapterPosition))
            }
        }

        fun bind(abstractCard: AbstractCard) {
            header.text = abstractCard.header
            subhead.text = abstractCard.subhead
            title?.text = abstractCard.title
            titleSubhead?.text = abstractCard.titleSubhead
            description?.text = abstractCard.description
        }
    }

    class CardDiffUtil : DiffUtil.ItemCallback<AbstractCard>() {
        override fun areItemsTheSame(oldItem: AbstractCard, newItem: AbstractCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AbstractCard, newItem: AbstractCard): Boolean {
            return oldItem == newItem
        }
    }
}