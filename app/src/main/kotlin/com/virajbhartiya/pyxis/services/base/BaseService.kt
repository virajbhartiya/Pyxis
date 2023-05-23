package com.virajbhartiya.pyxis.services.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.virajbhartiya.pyxis.app.Pyxis
import com.virajbhartiya.pyxis.di.component.DaggerServiceComponent
import com.virajbhartiya.pyxis.di.component.ServiceComponent
import com.virajbhartiya.pyxis.di.module.ServiceModule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseService : Service(), InterfaceService {


    private lateinit var compositeDisposable: CompositeDisposable

    companion object {
        @JvmStatic
        lateinit var serviceComponent: ServiceComponent
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(ServiceModule(this))
                .appComponent(Pyxis.appComponent).build()
        compositeDisposable = CompositeDisposable()
    }

    override fun getComponent(): ServiceComponent? = serviceComponent


    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun clearDisposable() = compositeDisposable.clear()


}