package org.d3if4127.luaspersegipanjang.ui.hitung

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4127.luaspersegipanjang.MainActivity
import org.d3if4127.luaspersegipanjang.db.PersegiPanjangDao
import org.d3if4127.luaspersegipanjang.db.PersegiPanjangEntity
import org.d3if4127.luaspersegipanjang.model.HasilPersegiPanjang
import org.d3if4127.luaspersegipanjang.model.KategoriPersegiPanjang
import org.d3if4127.luaspersegipanjang.model.hitungPersegiPanjang
import org.d3if4127.luaspersegipanjang.network.UpdateWorker
import java.util.concurrent.TimeUnit

class HitungViewModel(private val db: PersegiPanjangDao) : ViewModel() {

    private val hasilLuas = MutableLiveData<HasilPersegiPanjang?>()
    private val navigasi = MutableLiveData<KategoriPersegiPanjang?>()

    fun hitungLuas(panjang: Float, lebar: Float) {
        val dataLuas = PersegiPanjangEntity (
            panjang = panjang,
            lebar = lebar
        )
        hasilLuas.value = dataLuas.hitungPersegiPanjang()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataLuas)
            }
        }
    }

    fun getHasilLuas(): LiveData<HasilPersegiPanjang?> = hasilLuas

    fun mulaiNavigasi() {
        navigasi.value = hasilLuas.value?.kategoriPersegiPanjang
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi() : LiveData<KategoriPersegiPanjang?> = navigasi

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}