package com.dicoding.medikaltech.data

import android.os.Parcel
import android.os.Parcelable
import com.dicoding.medikaltech.R

data class Heart(
    var rgHeartDisease:String? = null,
    var hypertensi :String? = null,
    var rgHeartdCp:String? = null,
    var edtTrestBps:String? = null,
    var edtBloodPressure:String? = null,
    var edtChol :String? = null,
    var edtFbs :String? = null,
    var rgRestecg:String? = null,
    var edtThalach:String? = null,
    var rgExang :String? = null,
    var edtOldPeak :String? = null,
    var rgSlope :String? = null,
    var edtCa :String? = null,
    var rgThal :String? = null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(rgHeartDisease)
        parcel.writeString(hypertensi)
        parcel.writeString(rgHeartdCp)
        parcel.writeString(edtTrestBps)
        parcel.writeString(edtBloodPressure)
        parcel.writeString(edtChol)
        parcel.writeString(edtFbs)
        parcel.writeString(rgRestecg)
        parcel.writeString(edtThalach)
        parcel.writeString(rgExang)
        parcel.writeString(edtOldPeak)
        parcel.writeString(rgSlope)
        parcel.writeString(edtCa)
        parcel.writeString(rgThal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Heart> {
        override fun createFromParcel(parcel: Parcel): Heart {
            return Heart(parcel)
        }

        override fun newArray(size: Int): Array<Heart?> {
            return arrayOfNulls(size)
        }
    }
}