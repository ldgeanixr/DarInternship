package com.example.queuestack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.btnNewGame
import kotlinx.android.synthetic.main.fragment_main.btnShowRecords

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnNewGame.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNamesFragment()
            findNavController().navigate(action)
        }

        btnShowRecords.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToRecordsFragment()
            findNavController().navigate(action)
        }

    }
}