package com.virajbhartiya.pyxis.ui.adapters.smsadapter

import com.virajbhartiya.pyxis.ui.adapters.basedapter.InterfaceAdapter


interface InterfaceSmsAdapter : InterfaceAdapter{
    fun onItemClick(keySms:String,position:Int)
    fun onItemLongClick(keySms:String,position:Int)
}