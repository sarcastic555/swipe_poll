package com.example.swipepoll.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.swipepoll.R
import java.io.ByteArrayOutputStream
import java.io.FileDescriptor
import java.io.IOException

class CreateFragmentEntry : Fragment() {

    private var bmp1: Bitmap? = null
    private var bmp2: Bitmap? = null
    private var view_: View? = null
    private var setImage1 = false
    private var setImage2 = false
    private var spinnerText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_entry, container, false)
        view_ = view

        // ======== Initial setting ===========-
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        var progressText = view.findViewById<TextView>(R.id.textView)
        progressText.visibility = View.GONE

        // 作成ボタン
        val createButton = view.findViewById<Button>(R.id.button3)
        createButton.setOnClickListener {
            if (setImage1 && setImage2) {
                progressBar.visibility = View.VISIBLE
                progressText.visibility = View.VISIBLE
                // Feed image via bundle object
                val bundle = Bundle()
                bundle.putParcelable("image1", bmp1)
                bundle.putParcelable("image2", bmp2)
                bundle.putString("question", spinnerText)
                bundle.putString("origin", "create")
                val handler = Handler()
                val resultFragment = ResultFragment()
                resultFragment.arguments = bundle
                handler.postDelayed({
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentCreate, resultFragment)
                    transaction?.commit()
                }, 3000)
            }
        }

        // 画像アップロードボタン
        val uploadButton1 = view.findViewById<Button>(R.id.button4)
        uploadButton1.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 1)
        }
        val uploadButton2 = view.findViewById<Button>(R.id.button5)
        uploadButton2.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 2)
        }

        // Spinnerの設定
        val spinnerItems = arrayOf(
            "どちらが好き？",
            "どちらをよく使う？",
            "どっちが似合う？",
            "どっちが食べたい？",
            "かっこいい？",
            "どっちがかわいい？"
        )
        val activity = getActivity()
        if (activity != null) {
            val adapter = ArrayAdapter(
                activity.applicationContext,
                R.layout.spinner_item,
                spinnerItems
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val spinner = view.findViewById<Spinner>(R.id.spinner3)
            spinner.adapter = adapter
            spinnerText = spinner.getSelectedItem().toString();
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    spinnerText = spinner.getSelectedItem().toString();
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
        return view
    }

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri?): Bitmap? {
        if (uri == null) return null
        val contentResolver = getActivity()?.getContentResolver()
        val parcelFileDescriptor =
            contentResolver?.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            //var uri: Uri
            if (resultData != null) {
                try {
                    var uri: Uri? = resultData.data
                    if (requestCode == 1) {
                        bmp1 = getBitmapFromUri(uri)
                        val imageView = view_?.findViewById<ImageView>(R.id.imageView);
                        imageView?.setImageBitmap(bmp1)
                        setImage1 = true
                    } else if (requestCode == 2) {
                        bmp2 = getBitmapFromUri(uri)
                        val imageView = view_?.findViewById<ImageView>(R.id.imageView2);
                        imageView?.setImageBitmap(bmp2)
                        setImage2 = true
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}