package com.imran.friends.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Id(
    val name: String?,
    val value: String?
) : Parcelable