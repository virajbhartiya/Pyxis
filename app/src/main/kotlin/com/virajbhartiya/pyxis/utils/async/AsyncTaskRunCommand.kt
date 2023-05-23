package com.virajbhartiya.pyxis.utils.async

import android.os.AsyncTask
import com.virajbhartiya.pyxis.app.Pyxis


class AsyncTaskRunCommand(private val onPreFunc: (() -> Unit)? = null,
                          private val onPostFunc:((result: Boolean) -> Unit)? = null) :  AsyncTask<String, Boolean, Boolean>() {

    override fun onPreExecute() {
        super.onPreExecute()
        onPreFunc?.invoke()
    }

    override fun doInBackground(vararg params: String?): Boolean {
        return Pyxis.root.runCommand(params[0]).result
    }

    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        onPostFunc?.invoke(result)
    }


}