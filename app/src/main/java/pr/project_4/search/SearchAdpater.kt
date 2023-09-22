package pr.project_4.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pr.project_4.data.Utils.getDateFromTimestampWithFormat
import pr.project_4.databinding.ItemBinding
import pr.project_4.model.SearchItemModel

class SearchAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    var items = ArrayList<SearchItemModel>()
    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(mContext)
            .load(currentItem.url)
            .into(holder.search_image)
        Log.d("Thumbnail URL", "좀 나와라: ${currentItem.url}")

        holder.tv_title.text = currentItem.title
        holder.tv_datetime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        var search_image: ImageView = binding.searchImage
        var tv_title: TextView = binding.searchTitle
        var tv_datetime: TextView = binding.searchDatatime
        var itemviewlo: ConstraintLayout = binding.itemviewlo

        init {
            search_image.setOnClickListener(this)
            itemviewlo.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return

            notifyItemChanged(position)
        }

    }
}
