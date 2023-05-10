package com.example.contagemdecarboidrato

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_alimento")
class Alimento(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "nome_alimento")
    val nomeAlimento: String,

    @ColumnInfo(name = "carboidrato_por_grama")
    val carboPorGrama: Float


)

