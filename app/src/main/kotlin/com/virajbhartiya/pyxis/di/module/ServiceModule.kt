package com.virajbhartiya.pyxis.di.module

import android.app.Service
import android.content.Context
import com.virajbhartiya.pyxis.di.PerService
import com.virajbhartiya.pyxis.services.calls.InteractorCalls
import com.virajbhartiya.pyxis.services.calls.InterfaceInteractorCalls
import com.virajbhartiya.pyxis.services.calls.InterfaceServiceCalls
import com.virajbhartiya.pyxis.services.sms.InteractorSms
import com.virajbhartiya.pyxis.services.sms.InterfaceInteractorSms
import com.virajbhartiya.pyxis.services.sms.InterfaceServiceSms
import dagger.Module
import dagger.Provides


@Module
class ServiceModule(var service:Service) {

    @Provides
    fun provideContext(): Context = service.applicationContext

    @Provides
    @PerService
    fun provideInterfaceInteractorCalls(interactor: InteractorCalls<InterfaceServiceCalls>): InterfaceInteractorCalls<InterfaceServiceCalls> = interactor

    @Provides
    @PerService
    fun provideInterfaceInteractorSms(interactor: InteractorSms<InterfaceServiceSms>): InterfaceInteractorSms<InterfaceServiceSms> = interactor

}