package com.virajbhartiya.pyxis.services.calls

import com.virajbhartiya.pyxis.di.PerService
import com.virajbhartiya.pyxis.services.base.InterfaceInteractorService


@PerService
interface InterfaceInteractorCalls<S : InterfaceServiceCalls> : InterfaceInteractorService<S> {

    fun startRecording(phoneNumber:String?,type:Int)
    fun stopRecording()

}