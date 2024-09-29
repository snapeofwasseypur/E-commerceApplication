package com.example.e_commerce_application

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var productAdapter: ProductAdapter
    val listOfProduct = mutableListOf<product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        FirebaseApp.initializeApp(this)

        rv = findViewById(R.id.rv)
        fab = findViewById(R.id.fab)

        FirebaseDatabase.getInstance().getReference("products")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapShot in snapshot.children) {
                        val product = dataSnapShot.getValue(product::class.java)
                        if (product != null) {
                            listOfProduct.add(product!!)
                        }
                    }

                    // Set up adapter and layout manager only once
                    productAdapter = ProductAdapter(listOfProduct, this@MainActivity)
                    rv.adapter = productAdapter
                    rv.layoutManager = GridLayoutManager(this@MainActivity, 2)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle any errors
                }
            })

        fab.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }
}
