package com.virajbhartiya.pyxis.ui.fragments.message

import android.content.Context
import androidx.fragment.app.FragmentManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.virajbhartiya.pyxis.R
import com.virajbhartiya.pyxis.data.model.DataDelete
import com.virajbhartiya.pyxis.data.rxFirebase.InterfaceFirebase
import com.virajbhartiya.pyxis.ui.activities.base.BaseInteractor
import com.virajbhartiya.pyxis.ui.adapters.smsadapter.InterfaceSmsAdapter
import com.virajbhartiya.pyxis.ui.adapters.smsadapter.SmsRecyclerAdapter
import com.virajbhartiya.pyxis.utils.Consts.DATA
import com.virajbhartiya.pyxis.utils.Consts.SMS
import com.google.firebase.database.DatabaseError
import javax.inject.Inject


class InteractorMessage<V : InterfaceViewMessage> @Inject constructor(supportFragment: FragmentManager, context: Context, firebase: InterfaceFirebase) : BaseInteractor<V>(supportFragment, context, firebase), InterfaceInteractorMessage<V>, InterfaceSmsAdapter {

    private var recyclerAdapter: SmsRecyclerAdapter? = null

    override fun setSearchQuery(query: String) {
        if (recyclerAdapter!=null) recyclerAdapter!!.setFilter(query)
    }

    override fun setRecyclerAdapter() {
        recyclerAdapter = SmsRecyclerAdapter(firebase().getDatabaseReference("$SMS/$DATA").limitToLast(300))
        getView()?.setRecyclerAdapter(recyclerAdapter!!)
        recyclerAdapter!!.onRecyclerAdapterListener(this)
    }

    override fun startRecyclerAdapter() {
        if (recyclerAdapter != null) recyclerAdapter!!.startListening()
    }

    override fun stopRecyclerAdapter() {
        if (recyclerAdapter != null) recyclerAdapter!!.stopListening()
    }

    override fun notifyDataSetChanged() {
        if (recyclerAdapter!=null) recyclerAdapter!!.notifyDataSetChanged()
    }

    override fun notifyItemChanged(position: Int) {
        if (recyclerAdapter!=null) recyclerAdapter!!.notifyItemChanged(position)
    }

    override fun successResult(result: Boolean, filter:Boolean) {
        if (getView() != null) getView()!!.successResult(result,filter)
    }

    override fun failedResult(error: DatabaseError) {
        if (getView() != null) getView()!!.failedResult(Throwable(error.message))
    }

    override fun onItemClick(keySms: String,position:Int) {
        if (getMultiSelected()) if (isNullView()) getView()!!.onItemClick(keySms,"","",position)
    }

    override fun onItemLongClick(keySms: String,position:Int) {
        if (isNullView()) getView()!!.onItemLongClick(keySms,"","",position)
    }

    override fun onDeleteData(data: MutableList<DataDelete>) {
        getView()!!.showDialog(SweetAlertDialog.WARNING_TYPE, R.string.title_dialog, getContext().getString(R.string.message_dialog_delete_sms),
                R.string.delete, true) {
            setConfirmClickListener {
                setMultiSelected(false)
                for (i in 0 until data.size){
                    firebase().getDatabaseReference("$SMS/$DATA/${data[i].key}").removeValue().addOnCompleteListener {
                        if (i==data.size-1) getView()!!.setActionToolbar(false)
                    }
                }
                getView()?.hideDialog()
            }
            show()
        }
    }

}