package com.virajbhartiya.pyxis.ui.adapters.notifyadapter

import com.virajbhartiya.pyxis.ui.adapters.basedapter.InterfaceAdapter



interface InterfaceNotifyMessageAdapter : InterfaceAdapter{
    fun onItemClick(key: String?, child: String,position:Int)
    fun onItemLongClick(key: String?, child: String,position:Int)
}