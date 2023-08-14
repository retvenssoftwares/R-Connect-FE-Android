package rconnect.retvens.technologies.OnBoarding.ChainHotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import rconnect.retvens.technologies.R

class SecondOnboardingChainAdapter(private val itemList: List<Int>) : RecyclerView.Adapter<SecondOnboardingChainAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(add: Int)
        fun onRemoveItem(remove:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_secondonchainboarding_dynamic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        val count = position+1

        holder.text.text = count.toString()


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roomType = itemView.findViewById<TextInputEditText>(R.id.roomType_text)
        val maxAdult = itemView.findViewById<TextInputEditText>(R.id.maxAdult_text)
        val maxChild = itemView.findViewById<TextInputEditText>(R.id.maxChild_text)
        val maxOccupancy = itemView.findViewById<TextInputEditText>(R.id.maxOccupancy_text)
        val roomInventory = itemView.findViewById<TextInputEditText>(R.id.inventory_text)
        val addRoom = itemView.findViewById<ImageView>(R.id.addRoomType)
        val removeRoom = itemView.findViewById<ImageView>(R.id.removeRoomType)
        val text =itemView.findViewById<TextView>(R.id.position_card)


    }
}