package com.gustavomedeiros.firstapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gustavomedeiros.firstapp.service.model.Pessoa
import com.gustavomedeiros.firstapp.service.repository.PessoaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PessoaViewModel (application: Application): AndroidViewModel(application) {
    private val repository = PessoaRepository(application)
    fun insert(pessoa: Pessoa) {
        viewModelScope.launch {
            repository.insertPessoa(pessoa)
        }
    }

    fun getPessoa(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPessoa(id)
        }
    }

    fun update(pessoa: Pessoa){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePessoa(pessoa)
        }
    }
 fun delete(id: Int){
     viewModelScope.launch(Dispatchers.IO) {
         repository.deletePessoa(id)
     }
 }
}

