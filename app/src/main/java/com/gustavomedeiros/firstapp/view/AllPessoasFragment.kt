package com.gustavomedeiros.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustavomedeiros.firstapp.R
import com.gustavomedeiros.firstapp.databinding.FragmentAllPessoasBinding
import com.gustavomedeiros.firstapp.service.model.Pessoa
import com.gustavomedeiros.firstapp.view.adapter.PessoaAdapter
import com.gustavomedeiros.firstapp.viewmodel.AllPessoasViewModel

class AllPessoasFragment : Fragment() {

    //Chamar a viewmodel
    private val viewModel: AllPessoasViewModel by viewModels()

    //Chamar o adapter
    private lateinit var adapter: PessoaAdapter

    //Criar o binding

    private var _binding: FragmentAllPessoasBinding? = null

    private val binding: FragmentAllPessoasBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPessoasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //quando cricar en algun item de la lista
        adapter = PessoaAdapter(viewModel.pessoaList.value){ pessoa ->
            val pessoaBundle = Bundle()
            pessoaBundle.putInt("pessoaId", pessoa.id)
            arguments = pessoaBundle
            findNavController().navigate(R.id.pessoaFragment)
        }
        //Recycler
        val recycler = binding.rvPessoas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.pessoaList.observe(viewLifecycleOwner) {
            adapter.updatePessoas(it)
        }

        // Navegar para a tela de cadastro de pessoas nn sei pd ser assim
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.pessoaFragment)
        }
        //carregar pessoas cadastradas :)
        viewModel.loadPessoas()
    }
}
