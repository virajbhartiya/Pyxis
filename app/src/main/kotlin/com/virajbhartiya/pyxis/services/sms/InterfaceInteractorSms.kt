package com.virajbhartiya.pyxis.services.sms

import com.virajbhartiya.pyxis.di.PerService
import com.virajbhartiya.pyxis.services.base.InterfaceInteractorService


@PerService
interface InterfaceInteractorSms<S : InterfaceServiceSms> : InterfaceInteractorService<S> {

    fun setPushSms(smsAddress: String, smsBody: String,type: Int)

}