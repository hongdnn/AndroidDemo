package com.example.weekfiveproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.menu_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.concurrent.Executors


class FruitListAdapter(
    reqWidth: Int,
    private val onFavClick: (Int) -> Unit,
    private val onItemClick: (Int) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Fruit, FruitListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(UtilDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    private val reqWidth = reqWidth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fruit: Fruit) {
            itemView.ivItem.setOnClickListener {
                onItemClick(position)
            }
            itemView.tvTitle.setOnClickListener {
                onItemClick(position)
            }
            GlobalScope.launch(Dispatchers.IO) {
                val imgBitmap = resizeBitmap(fruit.image)
                withContext(Dispatchers.Main) {
                    Glide.with(itemView.context)
                        .asBitmap()
                        .load(imgBitmap)
                        .into(itemView.ivItem)
                }
            }
            itemView.tvTitle.text = fruit.title
            itemView.tvDescription.text = fruit.description
            fruit.sale?.let {
                itemView.tvSale.visibility = View.VISIBLE
                itemView.tvSale.text = it
                if (it == "Free Ship") {
                    itemView.tvSale.setBackgroundColor(Color.parseColor("#0280d0"))
                } else {
                    itemView.tvSale.setBackgroundColor(Color.parseColor("#f86208"))
                }
            }
            itemView.tvCurPrice.text = "$${fruit.currentPrice}"
            itemView.tvInitPrice.text = "$${fruit.initialPrice}"


        }
    }

    fun resizeBitmap(image: String): Bitmap? {
        val url = URL(image)
        val options: BitmapFactory.Options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options)
        options.inSampleSize =
            if (options.outWidth % reqWidth == 0) options.outWidth / reqWidth
            else options.outWidth / reqWidth + 1
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options)
    }
}





