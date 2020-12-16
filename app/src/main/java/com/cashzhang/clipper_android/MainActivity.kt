package com.cashzhang.clipper_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cashzhang.clipper_android.network.NetworkService
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        confim.setOnClickListener {
            NetworkService.send(editText.text.toString())
                .subscribeBy(
                    onComplete = {
                        editText.setText("")
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    },
                    onError = {
                        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                    }
                )
        }
    }
}