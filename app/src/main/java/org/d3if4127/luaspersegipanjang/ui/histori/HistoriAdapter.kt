import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4127.luaspersegipanjang.R
import org.d3if4127.luaspersegipanjang.databinding.LayoutItemBinding
import org.d3if4127.luaspersegipanjang.db.PersegiPanjangEntity
import org.d3if4127.luaspersegipanjang.model.hitungPersegiPanjang
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<PersegiPanjangEntity,HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PersegiPanjangEntity>() {
                override fun areItemsTheSame(
                    oldData: PersegiPanjangEntity, newData: PersegiPanjangEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: PersegiPanjangEntity, newData: PersegiPanjangEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: LayoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: PersegiPanjangEntity) = with(binding) {
            val hasilPersegiPanjang = item.hitungPersegiPanjang()

            tanggal.text = dateFormatter.format(Date(item.tanggal))
            panjang.text = root.context.getString(
                R.string.tulisan_panjang,
                item.panjang
            )
            lebar.text = root.context.getString(
                R.string.tulisan_lebar,
                item.lebar
            )
            hasilTextView.text = root.context.getString(
                R.string.tulisan_luas,
                hasilPersegiPanjang.luas.toString()
            )
        }
    }
}