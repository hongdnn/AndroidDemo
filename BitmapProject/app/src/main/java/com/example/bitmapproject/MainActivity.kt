package com.example.bitmapproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var imageListAdapter: ImageListAdapter
    private var images: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        } else {
            images = getImagesPath()
        }
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
//        intent.type = "image/*"
//        startActivityForResult(intent,1)
        imageListAdapter = ImageListAdapter(-1, onBtnRemoveClicked) { position ->
            navigateToViewPager(position)
        }
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = imageListAdapter
        imageListAdapter.submitList(images)

        btnShow.setOnClickListener(View.OnClickListener {
            if (images.isNotEmpty()) {
                val metrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(metrics)
                val width: Int = metrics.widthPixels
                println("Width: $width")
                imageListAdapter = ImageListAdapter(width / 3,onBtnRemoveClicked) { position ->
                    navigateToViewPager(position)
                }
                recyclerView.adapter = imageListAdapter
                imageListAdapter.submitList(images)
            }
        })

    }

    private val onBtnRemoveClicked: (position: Int) -> Unit = { position ->
        images.removeAt(position)
        imageListAdapter.notifyItemRemoved(position)
        imageListAdapter.notifyItemRangeChanged(position,images.size-1)
    }

    fun navigateToViewPager(position: Int) {
        val intent = Intent(this, ViewPagerActivity::class.java)
        intent.putStringArrayListExtra("images", images as ArrayList<String>)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    fun getImagesPath(): MutableList<String> {
        val listOfAllImages = mutableListOf<String>()
        var columnIndexData: Int
        var imagePath: String
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        GlobalScope.launch(Dispatchers.IO) {
            val projection = arrayOf(
                MediaColumns.DATA,
                //MediaStore.Images.Media.BUCKET_DISPLAY_NAME
            )
            val cursor: Cursor? = this@MainActivity.contentResolver.query(
                uri, projection, null,
                null, null
            )
            cursor?.let {
                columnIndexData = it.getColumnIndexOrThrow(MediaColumns.DATA)
                //it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                while (it.moveToNext()) {
                    imagePath = it.getString(columnIndexData)
                    listOfAllImages.add(imagePath)
                }
            }
        }

        return listOfAllImages
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.isNotEmpty()) {
                // Permission granted.
                images = getImagesPath()
                imageListAdapter.notifyDataSetChanged()
            } else {
                // User refused to grant permission.
            }

        }
    }
}