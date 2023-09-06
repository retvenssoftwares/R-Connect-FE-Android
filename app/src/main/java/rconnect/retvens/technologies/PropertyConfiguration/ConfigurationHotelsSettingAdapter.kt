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

class ConfigurationHotelsSettingAdapter(val context: Context, private val itemList: List<HotelDataClass>) : RecyclerView.Adapter<ConfigurationHotelsSettingAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]


        holder.hotelImage.setImageResource(item.hotelImage)
        holder.hotelName.text = item.hotelName
        holder.hotelReview.text = item.hotelReview
        holder.hotelStars.rating = item.hotelStars
        holder.hotelStars.cancelDragAndDrop()
        holder.hotelrating.text = item.hotelRate
        holder.roomPrice.text = item.roomPrice
        holder.hotelVenue.text = item.hotelVenue

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick()
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roomPrice = itemView.findViewById<TextView>(R.id.room_price)
        val hotelImage = itemView.findViewById<ImageView>(R.id.hotel_img)
        val hotelName = itemView.findViewById<TextView>(R.id.hotel_name)
        val hotelVenue = itemView.findViewById<TextView>(R.id.hotel_venue)
        val hotelrating = itemView.findViewById<TextView>(R.id.hotel_rate)
        val hotelStars = itemView.findViewById<DragRatingView>(R.id.hotel_stars)
        val hotelReview = itemView.findViewById<TextView>(R.id.total_reviews)

    }
}