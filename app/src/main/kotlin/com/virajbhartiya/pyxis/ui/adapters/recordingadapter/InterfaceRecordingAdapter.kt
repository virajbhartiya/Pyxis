package com.virajbhartiya.pyxis.ui.adapters.recordingadapter

import com.virajbhartiya.pyxis.ui.adapters.basedapter.InterfaceAdapter
import java.io.File


interface InterfaceRecordingAdapter : InterfaceAdapter{
    fun onCheckPermissionAudioRecord(key:String?,file: File, childName: String, fileName: String, holder: RecordingViewHolder,position:Int)
    fun onClickDownloadAudioRecord(file: File, childName: String, holder: RecordingViewHolder)
    fun onLongClickDeleteFileRecord(keyFileName: String?, fileName: String, childName: String,position:Int)

}