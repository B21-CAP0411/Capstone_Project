package com.dicoding.medikaltech.data

import android.os.Parcel
import android.os.Parcelable
import android.widget.EditText

data class Stroke(
    var rgEverMarried : String? = null,
    var rgWorkType : String? = null,
    var rgResidenceType : String? = null,
    var rgSmokingStatus : String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(rgEverMarried)
        parcel.writeString(rgWorkType)
        parcel.writeString(rgResidenceType)
        parcel.writeString(rgSmokingStatus)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stroke> {
        override fun createFromParcel(parcel: Parcel): Stroke {
            return Stroke(parcel)
        }

        override fun newArray(size: Int): Array<Stroke?> {
            return arrayOfNulls(size)
        }
    }
}