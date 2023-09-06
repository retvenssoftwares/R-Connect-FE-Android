package rconnect.retvens.technologies.Authentication.RatesAndInventory

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import rconnect.retvens.technologies.DataCollections.BookingDataClass
import rconnect.retvens.technologies.R

class RatesInventoryAdapter(val context: Context, private val itemList: List<RatesInventoryDataClass>) : RecyclerView.Adapter<RatesInventoryAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null
    private lateinit var childAdapter: RatesInventoryChildAdapter

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

        holder.childRecycler.layoutManager = LinearLayoutManager(context)
        childAdapter =  RatesInventoryChildAdapter(context,item.roomDetails)
        holder.childRecycler.adapter = childAdapter
        childAdapter.notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val otaName = itemView.findViewById<TextView>(R.id.ota_Name)
        val otaCode = itemView.findViewById<TextView>(R.id.ota_code)
        val otaRateType = itemView.findViewById<TextView>(R.id.rate_type)
        val childRecycler = itemView.findViewById<RecyclerView>(R.id.recycler_child_distribution_rates)


    }
}