package com.challenge.acronyms.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.acronyms.R
import com.challenge.acronyms.databinding.FragmentSecondBinding
import com.challenge.acronyms.model.AcronymResponseItem
import com.challenge.acronyms.utils.UIState
import com.challenge.acronyms.view.adapter.AcronymAdapter
import com.challenge.acronyms.viewmodel.AcronymViewModel

class SecondFragment : Fragment() {

    private var countResults: String = ""

    private lateinit var binding: FragmentSecondBinding

    private val acronymViewModel by lazy {
        ViewModelProvider(requireActivity())[AcronymViewModel::class.java]
    }

    private val acronymAdapter by lazy {
        AcronymAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        getMeanings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAcronym.apply {
            layoutManager = LinearLayoutManager (
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = acronymAdapter
        }
    }

    private fun getMeanings() {
        acronymViewModel.meaning.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<List<AcronymResponseItem>> -> {
                    state.response.forEach {
                        acronymAdapter.updateItems(it.lfs ?: emptyList())
                        var countResults = it.lfs?.size.toString()
                        binding.countResults = "Num of results: $countResults items"
                    }
                }
                is UIState.ERROR -> {
                    state.error.localizedMessage?.let {
                        throw Exception("Error: $it")
                    }
                }
            }
        }
    }

}