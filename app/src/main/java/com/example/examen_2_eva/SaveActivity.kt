package com.example.examen_2_eva

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SaveActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        var db=DatabaseHelper(this)
        var videojuegos= db.lectura()
        val textViewDatos=findViewById<TextView>(R.id.textViewDatos)
        val btnContinuar=findViewById<Button>(R.id.btnAtras)
        repeat(videojuegos.size){
            textViewDatos.text=textViewDatos.text.toString()+ "\n\n"+videojuegos[it].toString()
        }
        btnContinuar.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}