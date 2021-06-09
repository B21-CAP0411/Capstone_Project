package com.dicoding.medikaltech.data

import android.os.Parcel
import android.os.Parcelable

data class Diabetes(
        var edtPregnancies: String? = null,
        var edtSkinThickness: String? = null,
        var edtInsulin: String? = null,
        var edtHeight: String? = null,
        var edtWeight: String? = null,
        var rgPredigreeFunction: String? = null,
        var bmi: String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(edtPregnancies)
        parcel.writeString(edtSkinThickness)
        parcel.writeString(edtInsulin)
        parcel.writeString(edtHeight)
        parcel.writeString(edtWeight)
        parcel.writeString(rgPredigreeFunction)
        parcel.writeString(bmi)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Diabetes> {
        override fun createFromParcel(parcel: Parcel): Diabetes {
            return Diabetes(parcel)
        }

        override fun newArray(size: Int): Array<Diabetes?> {
            return arrayOfNulls(size)
        }
    }
}