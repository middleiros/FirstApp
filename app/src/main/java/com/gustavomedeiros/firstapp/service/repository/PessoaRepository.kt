package com.gustavomedeiros.firstapp.service.repository

import android.content.Context
import com.gustavomedeiros.firstapp.service.model.Pessoa
import com.gustavomedeiros.firstapp.service.repository.local.FirstAppDataBase

class PessoaRepository(context: Context) {
    private val firstAppDb = FirstAppDataBase.getDataBase(context).pessoaDAO()

    suspend fun insertPessoa(pessoa: Pessoa){
        firstAppDb.insert(pessoa)
    }
    suspend fun getPessoa(id: Int){
        firstAppDb.getPessoa(id)
    }
    suspend fun updatePessoa(pessoa: Pessoa){
        firstAppDb.update(pessoa)
    }

    suspend fun deletePessoa(id:Int) {
        firstAppDb.delete(id)
    }
}

