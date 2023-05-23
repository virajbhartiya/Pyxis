package com.virajbhartiya.pyxis.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.virajbhartiya.pyxis.data.preference.DataSharePreference.typeApp
import com.virajbhartiya.pyxis.services.sms.SmsService
import com.virajbhartiya.pyxis.utils.Consts.TYPE_SMS_INCOMING
import com.virajbhartiya.pyxis.utils.ConstFun.startServiceSms



class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        var smsAddress = ""
        var smsBody = ""

        for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
            smsAddress = smsMessage.displayOriginatingAddress
            smsBody += smsMessage.messageBody
        }

        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            if (!context.typeApp) context.startServiceSms<SmsService>(smsAddress,smsBody, TYPE_SMS_INCOMING)
        }
    }

}