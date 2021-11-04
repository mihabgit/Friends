package com.imran.friends.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Friend(
    val info: @RawValue Info,
    val results: @RawValue List<Result>
) : Parcelable