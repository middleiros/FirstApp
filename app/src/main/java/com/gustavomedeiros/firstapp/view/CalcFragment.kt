package com.gustavomedeiros.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gustavomedeiros.firstapp.R
import com.gustavomedeiros.firstapp.databinding.ActivityMainBinding
import com.gustavomedeiros.firstapp.databinding.FragmentCalcBinding
import java.time.LocalDateTime

class CalcFragment : Fragment() {

    private var _binding: FragmentCalcBinding? = null

    private val binding: FragmentCalcBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalcBinding.inflate(inflater, container, false)
        return binding.root

    var nome = binding.edtNome.editableText.toString()

     binding.tvNome.text = "Nome: " + nome

       binding.btnEnviar.setOnClickListener {

           var anoNascimento = binding.edtIdade.editableText.toString()
           val anoAtual = LocalDateTime.now().year
           var idade = anoAtual - anoNascimento.toInt()

            binding.tvIdade.text = "Idade: ${idade}"
        }
        }
        }

