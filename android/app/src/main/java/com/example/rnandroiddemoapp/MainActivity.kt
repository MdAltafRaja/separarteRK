package com.example.rnandroiddemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnGoRNScreen = findViewById<Button>(R.id.btnGoRNScreen)
     //   val edt = findViewById<EditText>(R.id.editText)
        btnGoRNScreen.setOnClickListener {
            val intent = Intent(this@MainActivity, MyReactActivity::class.java)
            intent.putExtra("message_from_native", "edt.text")
            startActivity(intent)
        }
    }
}