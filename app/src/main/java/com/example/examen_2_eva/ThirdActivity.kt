package com.example.examen_2_eva

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        var db=DatabaseHelper(this)
        var videojuegos= intent.getParcelableArrayListExtra<Videojuego>("videojuegos")!!
        val textViewDatos=findViewById<TextView>(R.id.textViewDatos)

        val btnContinuar=findViewById<Button>(R.id.btnContinuar)
        val btnAtras=findViewById<Button>(R.id.btnAtras)
        val btnGuardar=findViewById<Button>(R.id.btnGuardar)
        repeat(videojuegos.size){
            textViewDatos.text=textViewDatos.text.toString()+ "\n\n"+videojuegos[it].toString()
        }
        btnContinuar.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putParcelableArrayListExtra("videojuegos",videojuegos)
            intent.putExtra("bucle",true)
            startActivity(intent)
        }
        btnAtras.setOnClickListener {
           // videojuegos[videojuegos.size-1].setEmpresa("a")
           // videojuegos[videojuegos.size-1].setAño_lanzamiento(1)
            val intent= Intent(this,SecondActivity::class.java)
            intent.putParcelableArrayListExtra("videojuegos",videojuegos)
            startActivity(intent)
        }
        btnGuardar.setOnClickListener{
            db.escribir(videojuegos)
           val intent=Intent(this,SaveActivity::class.java)
           startActivity(intent)
        }

    }
}