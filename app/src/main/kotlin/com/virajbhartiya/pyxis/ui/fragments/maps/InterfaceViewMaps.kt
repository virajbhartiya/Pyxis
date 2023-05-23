package com.virajbhartiya.pyxis.ui.fragments.maps

import com.virajbhartiya.pyxis.data.model.Location
import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.google.firebase.database.DataSnapshot


interface InterfaceViewMaps : InterfaceView {
    fun setLocation(location: Location)
    fun setValueState(dataSnapshot: DataSnapshot)
    fun setValuePermission(dataSnapshot: DataSnapshot)
}