package com.virajbhartiya.pyxis.ui.activities.register

import com.virajbhartiya.pyxis.di.PerActivity
import com.virajbhartiya.pyxis.ui.activities.base.InterfaceInteractor


@PerActivity
interface InterfaceInteractorRegister<V : InterfaceViewRegister> : InterfaceInteractor<V> {

    fun signUpDisposable(email: String, pass: String)

}