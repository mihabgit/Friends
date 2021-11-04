package com.imran.friends.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Result(
    val cell: String,
    val dob: @RawValue Dob,
    val email: String,
    val gender: String,
    val id: @RawValue Id,
    val location: @RawValue Location,
    val login: @RawValue Login,
    val name: @RawValue Name,
    val nat: String,
    val phone: String,
    val picture: @RawValue Picture,
    val registered: @RawValue Registered
) : Parcelable