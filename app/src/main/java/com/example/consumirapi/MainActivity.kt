package com.example.consumirapi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class MainActivity : AppCompatActivity() {

    interface ApiProducto {
        @GET("productos/") // Reemplazar url
        fun getCall(): Call<List<Producto>>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://861d-157-100-110-34.ngrok-free.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var rcvProducto: RecyclerView = findViewById(R.id.rcvProductos)
        var adapterProducto: ProductoAdapter

        rcvProducto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val apiService = retrofit.create(ApiProducto::class.java)
        val call = apiService.getCall()

        call.enqueue(object : Callback<List<Producto>> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<List<Producto>>, response: Response<List<Producto>>) {
                if (response.isSuccessful) {
                    val datos = response.body()
                    if (datos!= null) {
                        adapterProducto = ProductoAdapter(datos)
                        rcvProducto.adapter = adapterProducto
                    }
                } else {

                }
            }
            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {

            }
        })
    }
}