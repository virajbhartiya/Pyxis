package com.virajbhartiya.pyxis.services.base

import com.virajbhartiya.pyxis.di.component.ServiceComponent
import io.reactivex.disposables.Disposable


interface InterfaceService {

    fun getComponent(): ServiceComponent?

    fun addDisposable(disposable: Disposable)

    fun clearDisposable()

}