import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daditinternship.Listing
//import com.example.daditinternship.Apartment
import com.example.daditinternship.R
import java.text.NumberFormat
import java.util.Locale

class RecyclerViewAdapter(
    private val apartmentList: List<Listing>, // ngambil dari listing
    private val itemClickListener: OnItemClickListener // Custom listener interface
) : RecyclerView.Adapter<RecyclerViewAdapter.ApartmentViewHolder>() {

    // Define a ViewHolder that represents the view for each item
    inner class ApartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.tv_title)
        val textView2: TextView = itemView.findViewById(R.id.tv_isi1)
        val textView3: TextView = itemView.findViewById(R.id.tv_isi2)
        val textView4: TextView = itemView.findViewById(R.id.tv_bed)
        val textView5: TextView = itemView.findViewById(R.id.tv_wc)
        val textView6: TextView = itemView.findViewById(R.id.tv_luaskmr)
        val textView7: TextView = itemView.findViewById(R.id.harga)
        val textView8: TextView = itemView.findViewById(R.id.tv_d13)
        val textView9: TextView = itemView.findViewById(R.id.tv_2020)
        val textView10: TextView = itemView.findViewById(R.id.tv_99yrs)
        val btn: CardView = itemView.findViewById(R.id.btn)
// declare item"
        init {
            // Set onClickListener for the button (or frame layout in this case)
            btn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(apartmentList[position])
                }
            }
        }
    }

    // Inflate the layout for each item of the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_apart, parent, false) // ini untuk meynagkutkan ke layout
        return ApartmentViewHolder(itemView)
    }

    // Bind data to the views in each ViewHolder
    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        val apartment = apartmentList[position]
        //masukin data ke tampilan agar valuenya seesuai

        // Load image from URL using Glide
        //untuk nampilin gambar dr internet
        Glide.with(holder.itemView.context)
            .load(apartment.photo)
            .centerCrop()  // Memastikan gambar memenuhi bingkai
            .into(holder.imageView)


        // Set other data
        holder.textView1.text = apartment.project_name
        holder.textView2.text = apartment.address.street_name
        holder.textView3.text = apartment.category
        holder.textView4.text = "${apartment.attributes.bedrooms}" + " Beds"
        holder.textView5.text = "${apartment.attributes.bathrooms} Bath"
        holder.textView6.text = "${apartment.attributes.area_size} sqft"
//        val hargaFormat = String.format("%,d", apartment.attributes.price)
        holder.textView7.text = "$" + String.format("%,d", apartment.attributes.price) + "/mo"
        holder.textView8.text = apartment.address.district
        holder.textView9.text = "• "+" ${apartment.completed_at}" + "  •"
        holder.textView10.text = "${apartment.tenure} yrs"
    }
    //nyesuain data

    // Return the total count of items
    override fun getItemCount(): Int {
        return apartmentList.size
    }

    // Custom interface to handle click events
    interface OnItemClickListener {
        fun onItemClick(apartment: Listing)
    }
}
