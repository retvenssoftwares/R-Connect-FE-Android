package rconnect.retvens.technologies.PropertyConfiguration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.saran2020.dragrating.DragRatingView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import rconnect.retvens.technologies.DataCollections.BookingDataClass
import rconnect.retvens.technologies.R

class ConfigurationPlacesSettingAdapter(val context: Context, private val itemList: List<CountryDataClass>) : RecyclerView.Adapter<ConfigurationPlacesSettingAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(add: Int)
        fun onRemoveItem(remove:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_countries_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.countryImage.setImageResource(item.countryImage)
        holder.countryName.text = item.countryName
        holder.countryPlace.text = item.countryPlace

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val countryImage = itemView.findViewById<ImageView>(R.id.country_image)
        val countryName = itemView.findViewById<TextView>(R.id.country_name)
        val countryPlace = itemView.findViewById<TextView>(R.id.countryPlace)

    }
}