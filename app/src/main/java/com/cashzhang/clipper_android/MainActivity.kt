package com.cashzhang.clipper_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cashzhang.clipper_android.network.NetworkService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        confim.setOnClickListener {
            NetworkService.send(editText.text.toString())
                .subscribe()
        }
    }
}