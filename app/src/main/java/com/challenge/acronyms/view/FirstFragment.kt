package com.challenge.acronyms.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.acronyms.R
import com.challenge.acronyms.databinding.ActivityMainBinding
import com.challenge.acronyms.databinding.FragmentFirstBinding
import com.challenge.acronyms.model.AcronymResponseItem
import com.challenge.acronyms.utils.UIState
import com.challenge.acronyms.view.adapter.AcronymAdapter
import com.challenge.acronyms.viewmodel.AcronymViewModel
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {

    private val binding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }

    private val acronymAdapter by lazy {
        AcronymAdapter {
        }
    }

    private val acronymViewModel by lazy {
        ViewModelProvider(requireActivity())[AcronymViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = binding.searchView

        searchView
            .editText
            .setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
                if ( searchView.text.toString() != "") {
                    acronymViewModel.getMeaning(searchView.text.toString())
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else {
                    Snackbar.make(requireView(), "Type acronym: No Results", Snackbar.LENGTH_SHORT).show()
                }

                false
            }
    }

}