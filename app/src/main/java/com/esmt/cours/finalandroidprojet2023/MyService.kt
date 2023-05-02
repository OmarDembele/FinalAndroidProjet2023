package com.esmt.cours.finalandroidprojet2023


import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyService : Service() {

    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreate(){
        super.onCreate()
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}
