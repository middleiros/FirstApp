package com.gustavomedeiros.firstapp.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gustavomedeiros.firstapp.service.model.Pessoa

@Dao
interface PessoaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa")
    suspend fun getAll(): List<Pessoa>

    @Query("SELECT * FROM pessoa WHERE id = :id")
    suspend fun getPessoa (id: Int): Pessoa

    @Update
    suspend fun update(pessoa: Pessoa)

    @Query("DELETE FROM pessoa WHERE id = :id")
    suspend fun delete(id: Int)

}