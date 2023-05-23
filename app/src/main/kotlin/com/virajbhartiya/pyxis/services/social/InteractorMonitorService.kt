package com.virajbhartiya.pyxis.services.social

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import com.virajbhartiya.pyxis.data.rxFirebase.InterfaceFirebase
import com.virajbhartiya.pyxis.receiver.MonitorReceiver
import com.virajbhartiya.pyxis.utils.Consts.CHILD_PERMISSION
import com.virajbhartiya.pyxis.utils.Consts.CHILD_SOCIAL_MS
import com.virajbhartiya.pyxis.utils.Consts.RESTART_MONITOR_RECEIVER
import com.virajbhartiya.pyxis.utils.Consts.SOCIAL
import com.virajbhartiya.pyxis.utils.Consts.TAG
import com.pawegio.kandroid.e
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class InteractorMonitorService @Inject constructor(private val context: Context, private val firebase: InterfaceFirebase) : InterfaceMonitorService {

    @SuppressLint("CheckResult")
    override fun gerSocialStatus() {
        firebase.valueEvent("$SOCIAL/$CHILD_SOCIAL_MS")
                .map { data -> data.exists() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            val intent = Intent(context,MonitorReceiver::class.java)
                            intent.action = RESTART_MONITOR_RECEIVER
                            context.sendBroadcast(intent)
                        }else context.sendBroadcast(Intent(RESTART_MONITOR_RECEIVER))

                    }
                }, { e(TAG, it.message.toString()) })
    }

    override fun setPermission(status: Boolean) {
        firebase.getDatabaseReference("$SOCIAL/$CHILD_PERMISSION").setValue(status)
    }

}