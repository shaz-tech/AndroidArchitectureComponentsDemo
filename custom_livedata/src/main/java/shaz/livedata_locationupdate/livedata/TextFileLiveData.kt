package shaz.livedata_locationupdate.livedata

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import android.os.FileObserver
import java.io.File

/**
 * Created by ${Shahbaz} on 08-11-2017
 */
class TextFileLiveData(private val context: Context) : LiveData<List<String>>() {
    private val fileObserver: FileObserver

    init {
        val path = File(context.filesDir, "users.txt").path
        fileObserver = object : FileObserver(path) {
            override fun onEvent(event: Int, path: String?) {
                // The file has changed, so let’s reload the data
                loadData()
            }
        }
        loadData()
    }

    override fun onActive() {
        super.onActive()
        fileObserver.startWatching()
    }

    override fun onInactive() {
        super.onInactive()
        fileObserver.stopWatching()
    }

    private fun loadData() {
        object : AsyncTask<Void, Void, List<String>>() {
            override fun doInBackground(vararg params: Void?): List<String> {
                val file = File(context.filesDir, "doanloaded.json")
                return file.readLines()
            }

            override fun onPostExecute(result: List<String>?) {
                super.onPostExecute(result)
                postValue(result)
            }
        }.execute()
    }
}