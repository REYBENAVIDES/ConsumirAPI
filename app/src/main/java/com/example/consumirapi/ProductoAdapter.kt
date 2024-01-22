package com.example.consumirapi
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private val dispositivos: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.txtProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val prodcuto = dispositivos[position]
        holder.nombre.text = prodcuto.nombre
    }

    override fun getItemCount(): Int {
        return dispositivos.size
    }
}
