package com.example.e_commerce_application

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UploadProduct : AppCompatActivity() {
    private lateinit var ProductImage:ImageView
    private lateinit var ProductName:EditText
    private lateinit var ProductPrice:EditText
    private lateinit var ProductDes:EditText
    private lateinit var SelectProduct:Button
    private lateinit var UploadProduct:Button
    private lateinit var ProgressBar:ProgressBar
    private var  selectedImageUri: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                ProductImage.setImageURI(selectedImageUri)
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ProductImage=findViewById(R.id.img_product_preview)
        ProductName=findViewById(R.id.edt_ProductName)
        ProductDes=findViewById(R.id.edt_ProductDes)
        ProductPrice=findViewById(R.id.edt_ProductPrice)
        SelectProduct=findViewById(R.id.btn_selectImage)
        UploadProduct=findViewById(R.id.btn_uploadImage)
        ProgressBar=findViewById(R.id.progress_bar)

        SelectProduct.setOnClickListener(){
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(galleryIntent)
        }
        UploadProduct.setOnClickListener {
            if (selectedImageUri != null) {
                ProgressBar.visibility = View.VISIBLE

                val productname = ProductName.text.toString()
                val productdes = ProductDes.text.toString()
                val productprice = ProductPrice.text.toString()

                val filename = UUID.randomUUID().toString() + ".jpg"
                val storageRef = FirebaseStorage.getInstance().reference.child("productImages/$filename")
                storageRef.putFile(selectedImageUri!!).addOnSuccessListener {

                    storageRef.downloadUrl.addOnSuccessListener { uri ->

                        ProgressBar.visibility = View.GONE
                        Log.i("UploadSuccess", "Image uploaded. Download link: ${uri.toString()}")

                        uploadProduct(productname,productprice,productdes,it.toString())

                        val intent=Intent(this,MainActivity::class.java)
                    }
                }
            } else {
                Log.e("UploadError", "No image selected")
            }
        }


    }
    private fun uploadProduct(Name:String,Price:String,Des:String,Image:String){
        Firebase.database.getReference("products").child(Name).setValue(
            product(
                productName=Name,
                productPrice=Price,
                productDes=Des,
                productImage=Image
            )
        ).addOnSuccessListener {
            ProgressBar.visibility = View.GONE
            Toast.makeText(this,"Product Uploaded Succesfully",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Log.e("UploadError", "Upload failed", e)
            Toast.makeText(this,"Product Upload Unsuccesfull",Toast.LENGTH_SHORT).show()
        }
    }
}