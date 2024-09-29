package com.example.e_commerce_application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private val listOfProduct: List<product>,
    private val context: Context
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImg: ImageView = itemView.findViewById(R.id.product_img)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productDes: TextView = itemView.findViewById(R.id.product_des)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfProduct.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = listOfProduct[position]
        holder.productName.text = currentProduct.productName
        holder.productDes.text = currentProduct.productDes
        holder.productPrice.text = currentProduct.productPrice
        Glide.with(context)
            .load(currentProduct.productImage)
            .into(holder.productImg)
    }
}
