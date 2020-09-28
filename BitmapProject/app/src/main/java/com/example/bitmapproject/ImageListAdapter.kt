package com.example.bitmapproject

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import kotlinx.android.synthetic.main.item_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors


class ImageListAdapter(
    reqWidth: Int, private val onRemoveClick: (Int) -> Unit,
    private val onItemClick: (Int) -> Unit
) : androidx.recyclerview.widget.ListAdapter<String, ImageListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(UtilDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    private val reqWidth = reqWidth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.setOnClickListener {
                onItemClick(position)
            }
            itemView.btnRemove.setOnClickListener {
                onRemoveClick(position)
            }
            if (reqWidth == -1) {
                GlobalScope.launch(Dispatchers.Main) {
                    val requestBuilder: RequestBuilder<Drawable> =
                        Glide.with(itemView.context).load(R.drawable.ic_launcher_background)
                    Glide.with(itemView.context)
                        .load(text)
                        .error(
                            Glide.with(itemView.context)
                                .asDrawable()
                                .apply { requestBuilder })
                        .into(itemView.itemImage)
                    //itemView.btnNumber.text = (position + 1).toString()
                    itemView.btnNumber.text =
                        "${itemView.context.getString(R.string.hinh, position + 1, 1)}"
                }
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    val options: BitmapFactory.Options = BitmapFactory.Options()
                    options.inJustDecodeBounds = true //just read information of image, not data
                    BitmapFactory.decodeFile(text, options) //read information of image
                    options.inSampleSize =
                        if (options.outWidth % reqWidth == 0) options.outWidth / reqWidth
                        else options.outWidth / reqWidth + 1
                    options.inJustDecodeBounds = false //permission to read information of image
                    val imgBitmap = BitmapFactory.decodeFile(text, options)
                    //println("After: ${imgBitmap.byteCount}")
                    withContext(Dispatchers.Main) {
                        Glide.with(itemView.context)
                            .asBitmap()
                            .load(imgBitmap)
                            .into(itemView.itemImage)
                        itemView.btnNumber.text = (position + 1).toString()
                    }
                }

            }
        }
    }
}

//            val options: BitmapFactory.Options = BitmapFactory.Options()
//            options.inJustDecodeBounds = false //permission to read information of image
//            val b = BitmapFactory.decodeFile(text, options)
//            println("Before: ${b.byteCount}")




