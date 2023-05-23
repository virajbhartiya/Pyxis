package com.virajbhartiya.pyxis.services.sms

import android.content.Intent
import com.virajbhartiya.pyxis.services.base.BaseService
import com.virajbhartiya.pyxis.utils.Consts.SMS_ADDRESS
import com.virajbhartiya.pyxis.utils.Consts.SMS_BODY
import com.virajbhartiya.pyxis.utils.Consts.TYPE_SMS
import javax.inject.Inject


class SmsService : BaseService(), InterfaceServiceSms {

    @Inject lateinit var interactor: InterfaceInteractorSms<InterfaceServiceSms>

    override fun onCreate() {
        super.onCreate()
        if (getComponent() != null) {
            getComponent()!!.inject(this)
            interactor.onAttach(this)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.setSmsIntent()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun Intent.setSmsIntent() {
        getStringExtra(SMS_ADDRESS)?.let { getStringExtra(SMS_BODY)?.let { it1 ->
            interactor.setPushSms(it,
                it1,getIntExtra(TYPE_SMS,0))
        } }
    }

    override fun stopServiceSms() {
        stopSelf()
    }

    override fun onDestroy() {
        interactor.onDetach()
        super.onDestroy()
    }


}