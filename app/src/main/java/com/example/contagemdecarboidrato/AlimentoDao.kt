package com.example.contagemdecarboidrato

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlimentoDao {

    @Query("SELECT nome_alimento FROM tb_alimento")
    suspend fun getAll(): List<String>

    @Query("SELECT carboidrato_por_grama FROM tb_alimento WHERE nome_alimento = :nomeAlimento")
    suspend fun carboPorGrama(nomeAlimento: String): String

    @Insert
    suspend fun insert(vararg alimento: Alimento)

    @Delete
    fun delete(alimento: Alimento): Int

    
}