package com.virajbhartiya.pyxis.ui.fragments.social

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.google.firebase.database.DataSnapshot


interface InterfaceViewSocial : InterfaceView {

    fun setValuePermission(dataSnapshot: DataSnapshot)
    fun successResult(dataSnapshot: DataSnapshot)

}