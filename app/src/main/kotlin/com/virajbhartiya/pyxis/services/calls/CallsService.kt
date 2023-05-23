package com.virajbhartiya.pyxis.services.calls

import android.content.Intent
import com.virajbhartiya.pyxis.services.base.BaseService
import com.virajbhartiya.pyxis.utils.Consts.COMMAND_TYPE
import com.virajbhartiya.pyxis.utils.Consts.PHONE_NUMBER
import com.virajbhartiya.pyxis.utils.Consts.STATE_CALL_END
import com.virajbhartiya.pyxis.utils.Consts.STATE_CALL_START
import com.virajbhartiya.pyxis.utils.Consts.STATE_INCOMING_NUMBER
import com.virajbhartiya.pyxis.utils.Consts.TYPE_CALL
import javax.inject.Inject


class CallsService : BaseService(), InterfaceServiceCalls {

    private var phoneNumber: String? = null
    private var callType = 0

    @Inject
    lateinit var interactor: InterfaceInteractorCalls<InterfaceServiceCalls>

    override fun onCreate() {
        super.onCreate()
        if (getComponent() != null) {
            getComponent()!!.inject(this)
            interactor.onAttach(this)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.setCallIntent()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun Intent.setCallIntent() {

        val commandType = getIntExtra(COMMAND_TYPE, 0)

        if (commandType != 0) {
            when (commandType) {
                STATE_INCOMING_NUMBER -> if (phoneNumber == null) {
                    phoneNumber = getStringExtra(PHONE_NUMBER)
                    callType = getIntExtra(TYPE_CALL,0)
                }
                STATE_CALL_START -> if (phoneNumber != null) interactor.startRecording(phoneNumber,callType)
                STATE_CALL_END -> {
                    phoneNumber = null
                    interactor.stopRecording()
                }
            }
        }
    }

    override fun stopServiceCalls() {
        stopSelf()
    }

    override fun onDestroy() {
        interactor.onDetach()
        clearDisposable()
        super.onDestroy()
    }




}