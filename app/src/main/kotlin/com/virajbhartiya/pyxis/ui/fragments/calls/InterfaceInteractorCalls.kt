package com.virajbhartiya.pyxis.ui.fragments.calls

import com.virajbhartiya.pyxis.di.PerActivity
import com.virajbhartiya.pyxis.ui.activities.base.InterfaceInteractor


@PerActivity
interface InterfaceInteractorCalls<V : InterfaceViewCalls> : InterfaceInteractor<V> {
    fun stopAudioCallHolder()
}