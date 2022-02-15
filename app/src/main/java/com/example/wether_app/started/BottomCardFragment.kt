package com.example.wether_app.started

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.wether_app.R
import com.example.wether_app.databinding.BottomCardFragmentBinding
import kotlin.properties.Delegates

class BottomCardFragment : Fragment() {


    companion object {
        fun newInstance(position: Int): BottomCardFragment {
            val fragment: BottomCardFragment = BottomCardFragment().apply {
//                this.viewModel.position.value = position
                arguments = Bundle().apply {
                    putInt("position", position)
                }
            }
            return fragment
        }
    }

    private lateinit var binding: BottomCardFragmentBinding
    private lateinit var viewModel: BottomCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 뷰모델은 Fragment 생성시 바로 생성
        viewModel = ViewModelProvider(this).get(BottomCardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_card_fragment,container, false )
        binding.startButton.setOnClickListener { view ->
            view.findNavController()?.navigate(R.id.action_started_to_mainFragment)
        }

        viewModel.position.observe(this, updateTitle)
        viewModel.position.value = receiveData

        return binding.root
    }

    private val receiveData by lazy { requireArguments().getInt("position") }
    private val updateTitle by lazy {
        Observer<Int> { position ->
            binding.title = "Expore global map of wind, weather, and ocean conditions $position"
        }
    }



}