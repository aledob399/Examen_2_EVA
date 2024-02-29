package com.example.examen_2_eva

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var videojuegos:ArrayList<Videojuego>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextNombre=findViewById<EditText>(R.id.editTextNombre)
        val editTextValoracion=findViewById<EditText>(R.id.editTextValoracion)
        val btnContinuar=findViewById<Button>(R.id.btnContinuar)
        val esBucle=intent.getBooleanExtra("bucle",false)
        if(!esBucle){
            videojuegos= ArrayList<Videojuego>()
        }else{
            videojuegos= intent.getParcelableArrayListExtra<Videojuego>("videojuegos")!!
        }
        btnContinuar.setOnClickListener {
            if(editTextNombre.text.toString().isNotBlank() && editTextValoracion.text.toString().toFloat()>=0 && editTextValoracion.text.toString().toFloat()<=10){
                val nombre=editTextNombre.text.toString()
                val valoracion=editTextValoracion.text.toString().toFloat()
                val videojuego=Videojuego(nombre,valoracion,"a",1)
                videojuegos.add(videojuego)
                val intent=Intent(this,SecondActivity::class.java)
                intent.putParcelableArrayListExtra("videojuegos",videojuegos)
                if(esBucle){
                    intent.putExtra("bucle",true)
                }
                startActivity(intent)
            }else{
                Toast.makeText(this,"Nombre o valoracion incorrecta",Toast.LENGTH_LONG).show()
            }


        }
    }
}