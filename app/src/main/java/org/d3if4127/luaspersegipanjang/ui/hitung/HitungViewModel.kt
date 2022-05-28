package org.d3if4127.luaspersegipanjang.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4127.luaspersegipanjang.db.PersegiPanjangDao
import org.d3if4127.luaspersegipanjang.db.PersegiPanjangEntity
import org.d3if4127.luaspersegipanjang.model.HasilPersegiPanjang
import org.d3if4127.luaspersegipanjang.model.KategoriPersegiPanjang
import org.d3if4127.luaspersegipanjang.model.hitungPersegiPanjang

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
}