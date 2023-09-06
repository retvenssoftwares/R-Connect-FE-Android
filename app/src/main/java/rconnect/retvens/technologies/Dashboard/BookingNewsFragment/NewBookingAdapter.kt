package rconnect.retvens.technologies.Dashboard.BookingNewsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import rconnect.retvens.technologies.DataCollections.BookingDataClass
import rconnect.retvens.technologies.R

class NewBookingAdapter(val context: Context, private val itemList: List<BookingDataClass>) : RecyclerView.Adapter<NewBookingAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(add: Int)
        fun onRemoveItem(remove:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_booking_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.guestName.text = item.guestName
        holder.guestDetails.text = item.guestDetails
        holder.voucherNo.text = item.voucherNo
        holder.pricing.text = item.pricing
        holder.roomType.text = item.roomType
        holder.ratePlan.text = item.ratePlan
        holder.checkInDay.text = item.checkInDay
        holder.checkOutDay.text = item.checkOutDay
        holder.checkInDate.text = item.checkInDate
        holder.checkOutDate.text = item.checkOutDate

        Glide.with(context).load(item.guestProfile).into(holder.guestProfile)
        holder.ottImage.setImageResource(item.ottImages)


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val guestProfile = itemView.findViewById<ShapeableImageView>(R.id.guest_profile)
        val guestName = itemView.findViewById<TextView>(R.id.guest_name)
        val guestDetails = itemView.findViewById<TextView>(R.id.guest_detail)
        val voucherNo = itemView.findViewById<TextView>(R.id.voucher_no)
        val pricing = itemView.findViewById<TextView>(R.id.pricing)
        val roomType = itemView.findViewById<TextView>(R.id.room_Type)
        val ratePlan = itemView.findViewById<TextView>(R.id.rate_Plan)
        val ottImage = itemView.findViewById<ImageView>(R.id.ott_image)
        val checkInDay = itemView.findViewById<TextView>(R.id.checkin_day)
        val checkOutDay = itemView.findViewById<TextView>(R.id.checkout_day)
        val checkInDate = itemView.findViewById<TextView>(R.id.checkin_date)
        val checkOutDate = itemView.findViewById<TextView>(R.id.checkout_date)

    }
}