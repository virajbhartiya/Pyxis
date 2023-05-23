package com.virajbhartiya.pyxis.ui.fragments.keylog

import com.virajbhartiya.pyxis.di.PerActivity
import com.virajbhartiya.pyxis.ui.activities.base.InterfaceInteractor


@PerActivity
interface InterfaceInteractorKeys<V : InterfaceViewKeys> : InterfaceInteractor<V> {
    fun valueEventEnableKeys()
}