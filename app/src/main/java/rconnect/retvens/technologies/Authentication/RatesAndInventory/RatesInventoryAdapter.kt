package rconnect.retvens.technologies.Authentication.RatesAndInventory

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import rconnect.retvens.technologies.Authentication.DataCollections.BookingDataClass
import rconnect.retvens.technologies.R

class RatesInventoryAdapter(val context: Context, private val itemList: List<RatesInventoryDataClass>) : RecyclerView.Adapter<RatesInventoryAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(add: Int)
        fun onRemoveItem(remove:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_distribution_rates, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        Log.e("res",item.toString())

        holder.otaName.text = item.otaName
        holder.otaCode.text = item.otaCode
        holder.otaRateType.text = item.rateType

        for (x in item.roomDetails) {
            holder.roomPlan.text = x.roomPlan
            holder.roomType.text = x.roomType
            holder.inventory.text = x.day1Inventory
            holder.roommPrice.text = x.day1Price
            holder.inventory1.text = x.day2Inventory
            holder.roommPrice1.text = x.day2Price
            holder.inventory2.text = x.day3Inventory
            holder.roommPrice2.text = x.day3Price
            holder.inventory3.text = x.day4Inventory
            holder.roommPrice3.text = x.day4Price
            holder.inventory4.text = x.day5Inventory
            holder.roommPrice4.text = x.day5Price
            holder.inventory5.text = x.day6Inventory
            holder.roommPrice5.text = x.day6Price
            holder.inventory6.text = x.day7Inventory
            holder.roommPrice6.text = x.day7Price

        }

    }

    override fun getItemCount(): Int {
        return itemList[0].roomDetails.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val otaName = itemView.findViewById<TextView>(R.id.ota_Name)
        val otaCode = itemView.findViewById<TextView>(R.id.ota_code)
        val otaRateType = itemView.findViewById<TextView>(R.id.rate_type)
        val roomType = itemView.findViewById<TextView>(R.id.room_Type)
        val roomPlan = itemView.findViewById<TextView>(R.id.room_plan)
        val inventory = itemView.findViewById<TextView>(R.id.available_inventory)
        val roommPrice = itemView.findViewById<TextView>(R.id.rooms_price)
        val inventory1 = itemView.findViewById<TextView>(R.id.available_inventory2)
        val roommPrice1 = itemView.findViewById<TextView>(R.id.rooms_price2)
        val inventory2 = itemView.findViewById<TextView>(R.id.available_inventory3)
        val roommPrice2 = itemView.findViewById<TextView>(R.id.rooms_price3)
        val inventory3 = itemView.findViewById<TextView>(R.id.available_inventory4)
        val roommPrice3 = itemView.findViewById<TextView>(R.id.rooms_price4)
        val inventory4 = itemView.findViewById<TextView>(R.id.available_inventory5)
        val roommPrice4 = itemView.findViewById<TextView>(R.id.rooms_price5)
        val inventory5 = itemView.findViewById<TextView>(R.id.available_inventory6)
        val roommPrice5 = itemView.findViewById<TextView>(R.id.rooms_price6)
        val inventory6 = itemView.findViewById<TextView>(R.id.available_inventory7)
        val roommPrice6 = itemView.findViewById<TextView>(R.id.rooms_price7)


    }
}