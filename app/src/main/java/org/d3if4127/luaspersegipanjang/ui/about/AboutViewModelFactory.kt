package org.d3if4127.luaspersegipanjang.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if4127.luaspersegipanjang.network.PersegiPanjangApi
import java.lang.IllegalArgumentException

class AboutViewModelFactory(

    private val api: PersegiPanjangApi
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return AboutViewModel(api) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel Class")
    }
}