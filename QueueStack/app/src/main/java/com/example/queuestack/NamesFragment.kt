package com.example.queuestack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_names.btnContinue
import kotlinx.android.synthetic.main.fragment_names.etPlayerOneName
import kotlinx.android.synthetic.main.fragment_names.etPlayerTwoName

const val FIRST_PLAYER = "Player 1"
const val SECOND_PLAYER = "Player 2"

class NamesFragment : Fragment(R.layout.fragment_names) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnContinue.setOnClickListener {
            var firstPlayer = etPlayerOneName.text.toString()
            if (firstPlayer == ""){
                firstPlayer = FIRST_PLAYER
            }

            var secondPlayer = etPlayerTwoName.text.toString()
            if (secondPlayer == ""){
                secondPlayer = SECOND_PLAYER
            }

            PlayerRepository.addPlayer(firstPlayer)
            PlayerRepository.addPlayer(secondPlayer)


            val action = NamesFragmentDirections.actionNamesFragmentToGameFragment(
                firstPlayer, secondPlayer
            )

            findNavController().navigate(action)
        }
    }
}