package com.example.iseven.data.model

import android.os.Parcel
import android.os.Parcelable

data class KnownListItem(val number: Int, val parity: Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeByte(if (parity) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnownListItem> {
        override fun createFromParcel(parcel: Parcel): KnownListItem {
            return KnownListItem(parcel)
        }

        override fun newArray(size: Int): Array<KnownListItem?> {
            return arrayOfNulls(size)
        }
    }
}