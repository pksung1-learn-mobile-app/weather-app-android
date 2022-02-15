package com.example.wether_app.model

import android.os.Parcel
import android.os.Parcelable

class Weather() : Parcelable {
    var whereLocation: String = ""
    var locationName: String = ""
    var temperature: Int? = null
    var status: String = ""

    constructor(whereLocation: String, locationName: String, temperature: Int, status: String) : this() {
        this.whereLocation = whereLocation
        this.locationName = locationName
        this.temperature = temperature
        this.status = status
    }

    constructor(parcel: Parcel) : this() {
        whereLocation = parcel.readString().toString()
        locationName = parcel.readString().toString()
        temperature = parcel.readValue(Int::class.java.classLoader) as? Int
        status = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(whereLocation)
        parcel.writeString(locationName)
        parcel.writeValue(temperature)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weather> {
        override fun createFromParcel(parcel: Parcel): Weather {
            return Weather(parcel)
        }

        override fun newArray(size: Int): Array<Weather?> {
            return arrayOfNulls(size)
        }
    }

    fun getTemperatureText (): String {
        return "${this.temperature}C"
    }


}