package com.virajbhartiya.pyxis.ui.fragments.recording

import com.virajbhartiya.pyxis.ui.activities.base.InterfaceView
import com.virajbhartiya.pyxis.ui.adapters.recordingadapter.RecordingRecyclerAdapter
import com.google.firebase.database.DataSnapshot


interface InterfaceViewRecording : InterfaceView {

    fun setValueState(dataSnapshot: DataSnapshot)
    fun setValueTimerRecording(timer: Long)
    fun setRecyclerAdapter(recyclerAdapter: RecordingRecyclerAdapter)

}