package org.d3if4127.luaspersegipanjang.model

import org.d3if4127.luaspersegipanjang.db.PersegiPanjangEntity

fun PersegiPanjangEntity.hitungPersegiPanjang(): HasilPersegiPanjang {
    val luas = panjang * lebar
    val kategoriPersegiPanjang = when {
        luas < 100 -> KategoriPersegiPanjang.KECIL
        luas <= 1000 -> KategoriPersegiPanjang.SEDANG
        else -> KategoriPersegiPanjang.BESAR
    }
    return HasilPersegiPanjang(luas, kategoriPersegiPanjang)
}