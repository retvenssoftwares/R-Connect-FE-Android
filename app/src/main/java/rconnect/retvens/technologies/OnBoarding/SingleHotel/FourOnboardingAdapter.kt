package rconnect.retvens.technologies.OnBoarding.SingleHotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import rconnect.retvens.technologies.R

class FourOnboardingAdapter(private val itemList: List<Int>) : RecyclerView.Adapter<FourOnboardingAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(add: Int)
        fun onRemoveItem(remove:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fouronboarding_dynamic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        val count = position+1

        holder.text.text = count.toString()

        if (position == 0 && itemList.size >= 2){
            holder.addRoom.visibility = View.GONE
            holder.removeRoom.visibility = View.GONE
        }else {
            holder.addRoom.visibility = if (position == itemList.size - 1) View.VISIBLE else View.GONE
            holder.removeRoom.visibility = View.VISIBLE
        }



        holder.addRoom.setOnClickListener {
            itemClickListener?.onItemClick(item)
        }
        holder.removeRoom.setOnClickListener {
            itemClickListener?.onRemoveItem(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roomType = itemView.findViewById<TextInputEditText>(R.id.roomType_text)
        val rateType = itemView.findViewById<TextInputEditText>(R.id.rateType_text)
        val ratePlan = itemView.findViewById<TextInputEditText>(R.id.ratePlan_text)
        val baseRate = itemView.findViewById<TextInputEditText>(R.id.baseRates_text)
        val extraAdult = itemView.findViewById<TextInputEditText>(R.id.extraAdult_text)
        val extraChild = itemView.findViewById<TextInputEditText>(R.id.extraChild_text)
        val rateMin = itemView.findViewById<TextInputEditText>(R.id.rateMin_text)
        val rateMax = itemView.findViewById<TextInputEditText>(R.id.rateMax_text)
        val addRoom = itemView.findViewById<ImageView>(R.id.addRoomType)
        val removeRoom = itemView.findViewById<ImageView>(R.id.removeRoomType)
        val text =itemView.findViewById<TextView>(R.id.position_card)


    }
}