package com.virajbhartiya.pyxis.ui.fragments.calls

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.virajbhartiya.pyxis.ui.adapters.callsadapter.CallsRecyclerAdapter


interface InterfaceViewCalls : InterfaceView {

    fun setRecyclerAdapter(recyclerAdapter: CallsRecyclerAdapter)

}