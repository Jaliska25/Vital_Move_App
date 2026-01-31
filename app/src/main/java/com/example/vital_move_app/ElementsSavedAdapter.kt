package com.example.vital_move_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vital_move_app.model.ActivitySession
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// --- RECYCLERVIEW ADAPTER TO DISPLAY SAVED ACTIVITY SESSIONS ---
class ElementsSavedAdapter(
    private val items: MutableList<ActivitySession> = mutableListOf()
) : RecyclerView.Adapter<ElementsSavedAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // LINKING THE ELEMENTS OF THE VIEW (layouts) USING THEIR IDs
        val tvName: TextView = itemView.findViewById(R.id.tvActivityName)
        val tvDuration: TextView = itemView.findViewById(R.id.tvDuration)
        val tvDateTime: TextView = itemView.findViewById(R.id.tvDate)
        val tvActivityType: TextView = itemView.findViewById(R.id.tvActivityType)

    }

    //Setting the date
    private val dateFormatter =
        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())


    // Inflates the XML layout into a View Object for a single list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.element_saved,
                parent,
                false
            )
        return VH(view)
    }

    //Draw the list row with the actual data
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        holder.tvName.text = item.activity
        holder.tvDuration.text = "${item.duration} min"
        holder.tvActivityType.text = "Type: ${item.activityType}"
        holder.tvDateTime.text = dateFormatter.format(Date(item.timestamp))
    }

    // Returns the total number of items in the data set
    override fun getItemCount(): Int = items.size

}