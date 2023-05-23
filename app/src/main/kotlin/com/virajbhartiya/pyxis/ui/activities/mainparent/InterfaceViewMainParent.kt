package com.virajbhartiya.pyxis.ui.activities.mainparent

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.google.firebase.database.DataSnapshot


interface InterfaceViewMainParent : InterfaceView {
    fun signOutView()
    fun onFinishCount()
    fun setCheckedNavigationItem(id: Int)
    fun closeNavigationDrawer()
    fun requestApplyInsets()
    fun setDataAccounts(data: DataSnapshot)
}