package com.example.contagemdecarboidrato

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlimentoDao {

    @Query("SELECT * FROM tb_alimento")
    fun getAll(): List<Alimento>

    @Query("SELECT carboidrato_por_grama FROM tb_alimento WHERE nome_alimento = :nomeAlimento")
    fun carboPorGrama(nomeAlimento: Alimento): Float

    @Insert
    fun insertAll(vararg alimento: Alimento)

    @Delete
    fun delete(alimento: Alimento): Int
}