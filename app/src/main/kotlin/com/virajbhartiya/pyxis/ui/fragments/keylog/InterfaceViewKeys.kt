package com.virajbhartiya.pyxis.ui.fragments.keylog

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.virajbhartiya.pyxis.ui.adapters.keysadapter.KeysRecyclerAdapter
import com.google.firebase.database.DataSnapshot


interface InterfaceViewKeys : InterfaceView {

    fun setValueState(dataSnapshot: DataSnapshot)
    fun setRecyclerAdapter(recyclerAdapter: KeysRecyclerAdapter)

}