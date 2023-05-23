package com.virajbhartiya.pyxis.ui.fragments.message

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.virajbhartiya.pyxis.ui.adapters.smsadapter.SmsRecyclerAdapter


interface InterfaceViewMessage : InterfaceView {

    fun setRecyclerAdapter(smsRecyclerAdapter: SmsRecyclerAdapter)

}