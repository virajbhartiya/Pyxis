package com.virajbhartiya.pyxis.ui.adapters.keysadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.virajbhartiya.pyxis.R
import com.virajbhartiya.pyxis.data.model.KeyData
import kotterknife.bindView


class KeysViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtKeyData: TextView by bindView(R.id.txt_key_text)

    fun bind(item: KeyData) {
        txtKeyData.text = item.keyText
    }

}