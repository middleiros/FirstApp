package com.gustavomedeiros.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gustavomedeiros.firstapp.R
import com.gustavomedeiros.firstapp.databinding.FragmentPessoaBinding
import com.gustavomedeiros.firstapp.databinding.FragmentPessoaDetailBinding
import com.gustavomedeiros.firstapp.viewmodel.PessoaViewModel


class PessoaDetailFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

    private val _binding: FragmentPessoaDetailBinding? = null

    private val binding: FragmentPessoaDetailBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }
            viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
                binding.tvNome.text = pessoa.nome
                binding.tvNome.text = pessoa.idade.toString() + "anos"
                binding.tvFaixaEtaria.text = pessoa.faixaEtaria

                if (pessoa.sexo == "Masculino") {
                    binding.imgSexo.setImageResourco(R.drawable.masculino)
                } else {
                    binding.imgSexo.setImageResource(R.drawable.feminino)


                    binding.root.setOnClickListener{
                      clickListListener(pessoa)
                    }

                }
            }
        }
    }




