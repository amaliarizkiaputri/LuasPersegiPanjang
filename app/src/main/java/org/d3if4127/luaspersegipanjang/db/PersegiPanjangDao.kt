package org.d3if4127.luaspersegipanjang.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersegiPanjangDao {
    @Insert
    fun insert(hasil: PersegiPanjangEntity)

    @Query("SELECT * FROM luas ORDER BY id DESC")
    fun getLastPersegiPanjang(): LiveData<List<PersegiPanjangEntity>>

    @Query("DELETE FROM luas")
    fun clearData()
}

