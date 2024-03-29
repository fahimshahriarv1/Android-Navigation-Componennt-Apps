package com.fahim.navapp2

import adapter.PaymentPanelAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fahim.navapp2.databinding.FragmentPaymentHistoryBinding
import com.google.gson.Gson
import data.ResponseClient
import viewModels.PaymentHistoryViewModel

class PaymentHistory : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rvPaymentPanel: RecyclerView
    lateinit var binding: FragmentPaymentHistoryBinding
    lateinit var allinfo:ResponseClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model=activity?.let { ViewModelProvider(it).get(PaymentHistoryViewModel::class.java) }
        binding.model=model
        val g = Gson()
        allinfo=g.fromJson(arguments?.get("history").toString(),ResponseClient::class.java)
        rvPaymentPanel=view.findViewById(R.id.rvPanel)
        rvPaymentPanel.adapter= PaymentPanelAdapter(requireActivity(),allinfo.response.data)
        rvPaymentPanel.layoutManager= GridLayoutManager(requireContext(),1)


    }

}