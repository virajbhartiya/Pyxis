package com.virajbhartiya.pyxis.ui.fragments.social

import com.virajbhartiya.pyxis.di.PerActivity
import com.virajbhartiya.pyxis.ui.activities.base.InterfaceInteractor


@PerActivity
interface InterfaceInteractorSocial<V : InterfaceViewSocial> : InterfaceInteractor<V> {

    fun valueEventSocial()
    fun valueEventEnablePermission()

}