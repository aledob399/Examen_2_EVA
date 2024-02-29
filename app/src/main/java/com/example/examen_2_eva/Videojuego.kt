package com.example.examen_2_eva

import android.os.Parcel
import android.os.Parcelable

class Videojuego(private var nombre: String?, private var valoracion:Float, private var empresa: String?, private var año_lanzamiento: Int?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }
    fun getNombre(): String? {
        return nombre
    }
    fun getEmpresa(): String? {
        return empresa
    }
    fun getValoracion(): Float? {
        return valoracion
    }
    fun getAño_lanzamiento(): Int? {
        return año_lanzamiento
    }
    fun setEmpresa(nuevaEmpresa:String){
        empresa=nuevaEmpresa
    }
    fun setAño_lanzamiento(nuevoAño:Int){
        año_lanzamiento=nuevoAño
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeFloat(valoracion)
        parcel.writeString(empresa)
        parcel.writeInt(año_lanzamiento!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Videojuego(nombre=$nombre, valoracion=$valoracion, empresa=$empresa, año_lanzamiento=$año_lanzamiento)"
    }

    companion object CREATOR : Parcelable.Creator<Videojuego> {
        override fun createFromParcel(parcel: Parcel): Videojuego {
            return Videojuego(parcel)
        }

        override fun newArray(size: Int): Array<Videojuego?> {
            return arrayOfNulls(size)
        }
    }

}