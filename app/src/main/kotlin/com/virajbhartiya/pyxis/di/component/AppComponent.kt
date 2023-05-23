package com.virajbhartiya.pyxis.di.component

import com.virajbhartiya.pyxis.app.Pyxis
import com.virajbhartiya.pyxis.data.rxFirebase.InterfaceFirebase
import com.virajbhartiya.pyxis.di.module.AppModule
import com.virajbhartiya.pyxis.di.module.FirebaseModule
import com.virajbhartiya.pyxis.services.accessibilityData.AccessibilityDataService
import com.virajbhartiya.pyxis.services.notificationService.NotificationService
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, FirebaseModule::class])
interface AppComponent {

    fun inject(app: Pyxis)
    fun inject(accessibilityDataService: AccessibilityDataService)
    fun inject(notificationService: NotificationService)
    fun getInterfaceFirebase(): InterfaceFirebase

}