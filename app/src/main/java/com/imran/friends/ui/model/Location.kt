package com.imran.friends.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Location(
    val city: String,
    val coordinates: @RawValue Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: @RawValue Street,
    val timezone: @RawValue Timezone
) : Parcelable