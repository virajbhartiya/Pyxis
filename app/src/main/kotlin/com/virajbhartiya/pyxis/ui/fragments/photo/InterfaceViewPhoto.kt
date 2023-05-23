package com.virajbhartiya.pyxis.ui.fragments.photo

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.virajbhartiya.pyxis.ui.adapters.photoadapter.PhotoRecyclerAdapter
import com.google.firebase.database.DataSnapshot


interface InterfaceViewPhoto : InterfaceView{

    fun setRecyclerAdapter(recyclerAdapter: PhotoRecyclerAdapter)

    fun setValueState(dataSnapshot: DataSnapshot)
    fun setValuePermission(dataSnapshot: DataSnapshot)

}