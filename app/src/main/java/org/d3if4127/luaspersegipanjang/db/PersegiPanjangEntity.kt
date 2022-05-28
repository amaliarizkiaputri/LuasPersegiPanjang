package org.d3if4127.luaspersegipanjang.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "luas")
data class PersegiPanjangEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var panjang: Float,
    var lebar: Float,
)
