package com.gustavomedeiros.firstapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gustavomedeiros.firstapp.databinding.FragmentPessoaBinding
import com.gustavomedeiros.firstapp.service.model.Pessoa
import com.gustavomedeiros.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

    private var _binding: FragmentPessoaBinding? = null

    private val binding: FragmentPessoaBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Carregar a pessoa caso tenha selecionado
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaID"))
        }


        binding.btnEnviar.setOnClickListener {

            binding.btnDeletar.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle("Exclusão de pessoa")
                    .setMessage("Você realmente quer excluir?")
                    .setPositiveButton("Sim") { _,_ ->
                        viewModel.delete(viewModel.pessoa.value?.id ?: 0)
                        findNavController().navigateUp()
                    }
                    .setNegativeButton("Não"){_,_-> }
                    .show()
            }

            val anoNascimento = binding.edtIdade.editableText.toString()
            val nome = binding.edtNome.editableText.toString()
            var faixaEtaria = " "
            var sexo = " "


            if (nome != "" && anoNascimento != "" && binding.rbMasculino.isChecked || binding.rbFeminino.isChecked) {

                if (binding.rbMasculino.isChecked) {
                    sexo = "Masculino"
                } else {
                    sexo = "Feminino"
                }

                val anoAtual = LocalDateTime.now().year
                val idade = anoAtual - anoNascimento.toInt()

                if (idade <= 12) {
                    faixaEtaria = "Infatil"
                } else if (idade <= 17) {
                    faixaEtaria = "Adolescente"
                } else if (idade <= 64) {
                    faixaEtaria = "Adulto"
                } else {
                    faixaEtaria = "Idoso"
                }

                binding.tvIdade.text = "Idade: ${idade}"

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    faixaEtaria = faixaEtaria
                )
                viewModel.pessoa.value?.let {
                    pessoa.id = it.id
                    viewModel.update(pessoa)
                } ?: run {
                    viewModel.insert(pessoa)
                }



            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }
     viewModel.pessoa.observe(viewLifecycleOwner){pessoa ->
          binding.edtNome.setText(pessoa.nome)
          binding.edtIdade.setText((LocalDateTime.now().year - pessoa.idade).toString())

         if (pessoa.sexo == "Masculino"){
             binding.rbMasculino.isChecked = true
         } else {
             binding.rbFeminino.isChecked = true
         }
     }
    }
}


