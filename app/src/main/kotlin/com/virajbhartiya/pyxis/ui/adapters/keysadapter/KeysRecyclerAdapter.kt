package com.virajbhartiya.pyxis.ui.adapters.keysadapter

import android.view.ViewGroup
import com.virajbhartiya.pyxis.R
import com.virajbhartiya.pyxis.data.model.KeyData
import com.virajbhartiya.pyxis.ui.adapters.basedapter.BaseAdapter
import com.virajbhartiya.pyxis.utils.ConstFun.firebaseOptions
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.pawegio.kandroid.inflateLayout


class KeysRecyclerAdapter(private val query: Query) : BaseAdapter<KeyData, KeysViewHolder>(firebaseOptions(query)) {

    private lateinit var interfaceKeysAdapter : InterfaceKeysAdapter

    fun setFilter(filter:String){
        startFilter()
        if (filter=="") updateOptions(firebaseOptions(query))
        else updateOptions(firebaseOptions(query,filter,"keyText"))
    }

    override fun startFilter() {
        interfaceKeysAdapter.successResult(result = false, filter = true)
    }

    override fun onDataChanged() {
        if (getSnapshots().size == 0) interfaceKeysAdapter.successResult(false)
        else interfaceKeysAdapter.successResult(true)
    }

    override fun onError(e: DatabaseError) {
        interfaceKeysAdapter.failedResult(e)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): KeysViewHolder =
            KeysViewHolder(p0.context.inflateLayout(R.layout.item_keylog, p0, false))

    override fun onBindViewHolder(holder: KeysViewHolder, position: Int, model: KeyData) =
            holder.bind(model)

    fun onRecyclerAdapterListener(interfaceKeysAdapter: InterfaceKeysAdapter){
        this.interfaceKeysAdapter = interfaceKeysAdapter
    }

}