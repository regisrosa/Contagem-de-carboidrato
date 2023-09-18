package com.example.contagemdecarboidrato

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tb_alimento")
class Alimento(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "nome_alimento")
    @NotNull
    var nomeAlimento: String,

    @ColumnInfo(name = "carboidrato_por_grama")
    @NotNull
    var carboPorGrama: String

)

