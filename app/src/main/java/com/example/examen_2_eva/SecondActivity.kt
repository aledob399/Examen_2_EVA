package com.example.examen_2_eva

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var videojuegos= intent.getParcelableArrayListExtra<Videojuego>("videojuegos")!!
        val editTextEmpresa=findViewById<EditText>(R.id.editTextEmpresa)
        val editTextAño_lanzamiento=findViewById<EditText>(R.id.editTextAño_lanzamiento)
        val btnContinuar=findViewById<Button>(R.id.btnContinuar2)
        val btnAtras=findViewById<Button>(R.id.btnAtras)
        val esBucle=intent.getBooleanExtra("bucle",false)

        btnContinuar.setOnClickListener {
            if(editTextEmpresa.text.toString().isNotBlank() && editTextAño_lanzamiento.text.toString().toFloat()>=0 && editTextAño_lanzamiento.text.toString().toFloat()<=2024){
                videojuegos[videojuegos.size-1].setEmpresa(editTextEmpresa.text.toString())
                videojuegos[videojuegos.size-1].setAño_lanzamiento(editTextAño_lanzamiento.text.toString().toInt())
                val intent2=Intent(this,ThirdActivity::class.java)
                intent2.putParcelableArrayListExtra("videojuegos",videojuegos)
                startActivity(intent2)
            }else{
                Toast.makeText(this,"Empresa o año de lanzamiento incorrecto incorrecta", Toast.LENGTH_LONG).show()
            }

        }
        btnAtras.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            if(videojuegos.size<=1){
                videojuegos= ArrayList<Videojuego>()
                intent.putParcelableArrayListExtra("videojuegos",videojuegos)

            }else{
                videojuegos.removeAt(videojuegos.size - 1)
                intent.putParcelableArrayListExtra("videojuegos",videojuegos)
                intent.putExtra("bucle",true)
            }
            startActivity(intent)
        }
    }
}