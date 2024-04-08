package com.gustavomedeiros.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.gustavomedeiros.firstapp.databinding.FragmentCalcBinding
import com.gustavomedeiros.firstapp.service.model.Pessoa
import com.gustavomedeiros.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

    private var _binding: FragmentCalcBinding? = null

    private val binding: FragmentCalcBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviar.setOnClickListener {

            val anoNascimento = binding.edtIdade.editableText.toString()
            val nome = binding.edtNome.editableText.toString()

            if (nome !=""&& anoNascimento !="") {
                binding.tvNome.text = "Nome: " + nome

                binding.btnEnviar.setOnClickListener {


                    val anoAtual = LocalDateTime.now().year
                    val idade = anoAtual - anoNascimento.toInt()

                    binding.tvIdade.text = "Idade: ${idade}"

                    val pessoa = Pessoa(
                        nome = nome,
                        idade = idade
                    )
                    viewModel.insert(pessoa)

                }
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
                }


            }
        }
    }

