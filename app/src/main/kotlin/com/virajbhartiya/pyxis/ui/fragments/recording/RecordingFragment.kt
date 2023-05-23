package com.virajbhartiya.pyxis.ui.fragments.recording

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.AppBarLayout
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.github.clans.fab.FloatingActionButton
import com.virajbhartiya.pyxis.ui.fragments.base.BaseFragment
import com.virajbhartiya.pyxis.data.model.DataDelete
import com.virajbhartiya.pyxis.data.preference.DataSharePreference.clearSelectedItem
import com.virajbhartiya.pyxis.data.preference.DataSharePreference.setSelectedItem
import com.virajbhartiya.pyxis.ui.animation.OvershootInRightAnimator
import com.virajbhartiya.pyxis.ui.adapters.recordingadapter.RecordingRecyclerAdapter
import com.virajbhartiya.pyxis.ui.widget.CustomRecyclerView
import com.virajbhartiya.pyxis.ui.widget.OnScrollListener
import com.virajbhartiya.pyxis.ui.widget.toolbar.CustomToolbar
import com.virajbhartiya.pyxis.utils.ConstFun.contentGlobalLayout
import com.virajbhartiya.pyxis.utils.ConstFun.convertCurrentDuration
import com.virajbhartiya.pyxis.utils.Dialogs.showViewGetRecordAudio
import com.virajbhartiya.pyxis.utils.ConstFun.isScrollToolbar
import com.google.firebase.database.DataSnapshot
import com.pawegio.kandroid.*
import kotterknife.bindView
import javax.inject.Inject


class RecordingFragment : BaseFragment(com.virajbhartiya.pyxis.R.layout.fragment_recording), InterfaceViewRecording{

    companion object { const val TAG = "RecordingFragment" }

    private var dataList : MutableList<DataDelete> = mutableListOf()

    private var recording = false

    private val viewProgress: LinearLayout by bindView(com.virajbhartiya.pyxis.R.id.progress_placeholder)
    private val viewNotHave: LinearLayout by bindView(com.virajbhartiya.pyxis.R.id.not_have_placeholder)
    private val viewFailed: LinearLayout by bindView(com.virajbhartiya.pyxis.R.id.failed_placeholder)
    private val txtNotHave: TextView by bindView(com.virajbhartiya.pyxis.R.id.txt_not_have_get)
    private val txtFailed: TextView by bindView(com.virajbhartiya.pyxis.R.id.txt_failed_get)
    private val list: CustomRecyclerView by bindView(com.virajbhartiya.pyxis.R.id.list)
    private val buttonAddRecord: FloatingActionButton by bindView(com.virajbhartiya.pyxis.R.id.floating_button_add_record)
    private val floatingButton: FloatingActionButton by bindView(com.virajbhartiya.pyxis.R.id.floating_button_record)
    private val content : ConstraintLayout by bindView(com.virajbhartiya.pyxis.R.id.content)
    private val appBar : AppBarLayout by bindView(com.virajbhartiya.pyxis.R.id.app_bar)
    private val toolbar : CustomToolbar by bindView(com.virajbhartiya.pyxis.R.id.toolbar)
    private val main : CoordinatorLayout by bindView(com.virajbhartiya.pyxis.R.id.main_view)

    private var recyclerAdapter : RecordingRecyclerAdapter?=null

    @Inject lateinit var interactor : InterfaceInteractorRecording<InterfaceViewRecording>

    @Inject lateinit var layoutM : LinearLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbar(toolbar,true,com.virajbhartiya.pyxis.R.string.search_records,com.virajbhartiya.pyxis.R.id.nav_clear_records,com.virajbhartiya.pyxis.R.string.search_records_date)
        contentGlobalLayout(content, appBar)
        list.setAppBar(appBar)
        if (getComponent() != null) {
            getComponent()!!.inject(this)
            interactor.onAttach(this)
            startData()
            onClick()
            timeConnection()
        }
    }

    private fun startData(){
        interactor.setRecyclerAdapter()
        interactor.valueEventEnableRecording()
        interactor.valueEventTimerRecording()
    }

    private fun onClick(){
        buttonAddRecord.setOnClickListener {
            if (!recording){
                context?.showViewGetRecordAudio{
                    interactor.getRecordAudio(it)
                    showSnackbar(getString(com.virajbhartiya.pyxis.R.string.msj_snackbar_record),main)
                }
            }else showSnackbar(com.virajbhartiya.pyxis.R.string.msj_snackbar_current_record,main)
        }
    }

    private fun timeConnection() {
        runDelayedOnUiThread(13000) {
            if (viewProgress.isShown) failedResult(Throwable(getString(com.virajbhartiya.pyxis.R.string.error_database_connection)))
        }
    }

    override fun onStart() {
        super.onStart()
        interactor.startRecyclerAdapter()
    }

    override fun successResult(result: Boolean, filter:Boolean) {
        isScrollToolbar(toolbar,result)
        if (result) {
            viewProgress.hide()
            viewNotHave.hide()
            viewFailed.hide()
            list.show()
            recyclerPosition()
        } else {
            floatingButton.hide(true)
            viewFailed.hide()
            list.hide()
            appBar.setExpanded(true)
            if (filter) { viewProgress.show() ; viewNotHave.hide() }
            else{
                viewProgress.hide()
                viewNotHave.show()
                txtNotHave.text = getString(com.virajbhartiya.pyxis.R.string.not_have_audios_yet)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun failedResult(throwable: Throwable) {
        viewProgress.hide()
        floatingButton.hide(true)
        viewNotHave.hide()
        isScrollToolbar(toolbar,false)
        list.hide()
        viewFailed.show()
        appBar.setExpanded(true)
        txtFailed.text = "${getString(com.virajbhartiya.pyxis.R.string.failed_record_audios)}, ${throwable.message}"
    }


    override fun setRecyclerAdapter(recyclerAdapter: RecordingRecyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter
        layoutM.stackFromEnd = true
        layoutM.reverseLayout = true
        list.apply {
            itemAnimator = OvershootInRightAnimator()
            itemAnimator?.addDuration = 500
            itemAnimator?.removeDuration = 500
            layoutManager = layoutM
            adapter = recyclerAdapter
            recycledViewPool.clear()
            addOnScrollListener(OnScrollListener(floatingButton, layoutM))
        }
        floatingButton.setOnClickListener {
            recyclerPosition()
        }
    }

    private fun recyclerPosition(){
        appBar.setExpanded(true)
        if (recyclerAdapter!=null) list.scrollToPosition(recyclerAdapter!!.itemCount-1)
    }

    override fun setValueState(dataSnapshot: DataSnapshot) {
        toolbar.enableStatePermission = true
        try {
            if (dataSnapshot.exists()) toolbar.statePermission = dataSnapshot.value as Boolean
            else toolbar.statePermission = false
        }catch (t:Throwable){
            e(TAG,t.message.toString())
        }
    }

    override fun setValueTimerRecording(timer: Long) {
        if (timer>0){
            if (!recording) toolbar.animateRecord(true)
            toolbar.timer = convertCurrentDuration(timer)
            recording = true
        }else {
            if (recording) toolbar.animateRecord(false)
            recording = false
        }
    }

    override fun onSearchConfirmed(text: CharSequence) {
        interactor.setSearchQuery(text.toString())
    }

    override fun onButtonClicked(buttonCode: Int) {
        when (buttonCode){
            CustomToolbar.BUTTON_BACK -> interactor.setSearchQuery("")
            CustomToolbar.BUTTON_ACTION_DELETE -> interactor.onDeleteData(dataList)
            CustomToolbar.BUTTON_STATE -> showSnackbar(if (toolbar.statePermission) com.virajbhartiya.pyxis.R.string.enable_recording else com.virajbhartiya.pyxis.R.string.disable_recording,main)
            CustomToolbar.BUTTON_CHILD_USER -> changeChild(TAG)
            else -> super.onButtonClicked(buttonCode)
        }
    }

    override fun onItemClick(key: String?, child: String,file: String,position:Int) {
        itemSelected(key,child,file)
    }

    override fun onItemLongClick(key: String?, child: String,file: String,position:Int) {
        if (!interactor.getMultiSelected()){
            interactor.setMultiSelected(true)
            setActionToolbar(true)
        }
        itemSelected(key,child,file)
    }

    private fun itemSelected(key:String?,child:String,file:String){
        if (!key.isNullOrEmpty()){
            val data = DataDelete(key,child,file)
            if (dataList.contains(data)) {
                dataList.remove(data)
                context!!.setSelectedItem(key,false)
            }else {
                dataList.add(data)
                context!!.setSelectedItem(key, true)
            }

            if (dataList.isNotEmpty()) toolbar.setTitle = "${dataList.size} ${getString(com.virajbhartiya.pyxis.R.string.selected)}"
            else setActionToolbar(false)

            interactor.notifyDataSetChanged()
        }
    }

    override fun onActionStateChanged(enabled: Boolean) {
        if (!enabled){
            dataList = mutableListOf()
            appBar.setExpanded(true)
            context!!.clearSelectedItem()
            if (interactor.getMultiSelected()){
                interactor.setMultiSelected(false)
                interactor.notifyDataSetChanged()
            }
        }
        super.onActionStateChanged(enabled)
    }

    override fun onBackPressed(): Boolean {
        return when {
            toolbar.isSearchEnabled -> { toolbar.disableSearch() ; true }
            toolbar.isActionEnabled -> { toolbar.disableAction() ; true }
            else -> super.onBackPressed()
        }
    }

    override fun onHideFragment() {
        if (interactor!=null) interactor.stopAudioRecordHolder()
    }

    override fun onChangeHeight() {
        contentGlobalLayout(content,appBar)
        recyclerPosition()
    }

    override fun onStop() {
        super.onStop()
        interactor.stopRecyclerAdapter()
        interactor.stopAudioRecordHolder()
    }

    override fun onDetach() {
        interactor.onDetach()
        super.onDetach()
    }

}