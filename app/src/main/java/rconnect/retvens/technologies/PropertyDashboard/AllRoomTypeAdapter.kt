package rconnect.retvens.technologies.PropertyDashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import rconnect.retvens.technologies.R


class AllRoomTypeAdapter(val list : ArrayList<AllAmenitiesData>, val context : Context) :
    RecyclerView.Adapter<AllRoomTypeAdapter.RoomTypeViewHolder>() {

    class RoomTypeViewHolder(itemView: View) : ViewHolder(itemView){
        val status : TextView = itemView.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val inflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.item_room_type, parent, false)
        return RoomTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        val data = list[position]

        holder.status.text = data.status
    }
}