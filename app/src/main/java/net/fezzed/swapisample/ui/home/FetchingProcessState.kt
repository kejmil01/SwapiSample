package net.fezzed.swapisample.ui.home

import android.os.Parcel
import android.os.Parcelable
import net.fezzed.swapisample.data.network.model.ResultModel

data class FetchingProcessState(
	val inProgress: Boolean,
	val query: String,
	val result: List<ResultModel>,
	val error: String? = null
) : Parcelable {

	constructor(parcel: Parcel) : this(
		parcel.readByte() != 0.toByte(),
		parcel.readString()!!,
		parcel.createTypedArrayList(ResultModel)!!,
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeByte(if (inProgress) 1 else 0)
		parcel.writeString(query)
		parcel.writeTypedList(result)
		parcel.writeString(error)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<FetchingProcessState> {

		val DEFAULT = FetchingProcessState(false, "", emptyList())

		override fun createFromParcel(parcel: Parcel): FetchingProcessState {
			return FetchingProcessState(parcel)
		}

		override fun newArray(size: Int): Array<FetchingProcessState?> {
			return arrayOfNulls(size)
		}
	}
}