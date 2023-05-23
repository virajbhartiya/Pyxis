package com.virajbhartiya.pyxis.ui.fragments.notifications

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceInteractor


interface InterfaceInteractorNotifyMessage<V : InterfaceViewNotifyMessage> : InterfaceInteractor<V> {
    fun valueEventEnableNotifications()
}