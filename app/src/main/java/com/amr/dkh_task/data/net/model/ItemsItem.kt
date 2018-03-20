package com.amr.dkh_task.data.net.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@RealmClass
@Parcelize
open class ItemsItem(
        var photoUrl: String = "",
        var name: String = "",
        var description: String = "",
        @PrimaryKey
        var id: Int = 0
) : RealmObject(), Parcelable
