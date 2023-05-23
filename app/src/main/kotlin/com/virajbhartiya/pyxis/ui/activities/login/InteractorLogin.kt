package com.virajbhartiya.pyxis.ui.activities.login

import android.content.Context
import androidx.fragment.app.FragmentManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.virajbhartiya.pyxis.R
import com.virajbhartiya.pyxis.data.rxFirebase.InterfaceFirebase
import com.virajbhartiya.pyxis.ui.activities.base.BaseInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class InteractorLogin<V : InterfaceViewLogin> @Inject constructor(supportFragment: FragmentManager, context: Context, firebase: InterfaceFirebase) : BaseInteractor<V>(supportFragment, context, firebase), InterfaceInteractorLogin<V> {

    override fun signInDisposable(email: String, pass: String) {
        getView()!!.addDisposable(firebase().signIn(email, pass)
                .map { authResult -> authResult.user != null }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {if (isNullView()) getView()!!.showDialog(SweetAlertDialog.PROGRESS_TYPE, R.string.logging_in, null, null) { show() } }
                .subscribe({ if (isNullView()) getView()!!.successResult(it) }, {if (isNullView()) getView()!!.failedResult(it) }))
    }

}