package com.virajbhartiya.pyxis.di.component

import com.virajbhartiya.pyxis.di.PerService
import com.virajbhartiya.pyxis.di.module.ServiceModule
import com.virajbhartiya.pyxis.services.calls.CallsService
import com.virajbhartiya.pyxis.services.sms.SmsService
import com.virajbhartiya.pyxis.services.social.MonitorService
import dagger.Component


@PerService
@Component(dependencies = [AppComponent::class], modules = [ServiceModule::class])
interface ServiceComponent {

    fun inject(callsService: CallsService)
    fun inject(smsService: SmsService)
    fun inject(monitorService: MonitorService)

}