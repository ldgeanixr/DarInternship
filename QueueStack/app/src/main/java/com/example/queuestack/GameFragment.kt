package com.example.queuestack

import android.graphics.Color
import android.graphics.Color.GREEN
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_game.btnContinue
import kotlinx.android.synthetic.main.fragment_game.btnResetBoard
import kotlinx.android.synthetic.main.fragment_game.button_00
import kotlinx.android.synthetic.main.fragment_game.button_01
import kotlinx.android.synthetic.main.fragment_game.button_02
import kotlinx.android.synthetic.main.fragment_game.button_10
import kotlinx.android.synthetic.main.fragment_game.button_11
import kotlinx.android.synthetic.main.fragment_game.button_12
import kotlinx.android.synthetic.main.fragment_game.button_20
import kotlinx.android.synthetic.main.fragment_game.button_21
import kotlinx.android.synthetic.main.fragment_game.button_22
import kotlinx.android.synthetic.main.fragment_game.tvTurnName

const val DRAW = "Draw!"
const val TAG = "GameFragment"

class GameFragment : Fragment(R.layout.fragment_game), View.OnClickListener {


    private val args: GameFragmentArgs by navArgs()
    private lateinit var firstPlayer: String
    private lateinit var secondPlayer: String
    private lateinit var buttons: Array<Array<Button>>
    private var round: Int = -1
    private var firstPlayerTurn = true



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstPlayer = args.playerOneName
        secondPlayer = args.playerTwoName
        round = 0

        tvTurnName.text = firstPlayer

        buttons = arrayOf(
            arrayOf(button_00, button_01, button_02),
            arrayOf(button_10, button_11, button_12),
            arrayOf(button_20, button_21, button_22)
        )

        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener(this)
            }
        }

        btnResetBoard.setOnClickListener {
            resetBoard()
        }

        btnContinue.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToRecordsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onClick(view: View?) {
        val btn = view as Button
        if (btn.text.toString() == "") {
            round++
            when {
                firstPlayerTurn -> {
                    btn.text = "X"
                    tvTurnName.text = secondPlayer
                }
                else -> {
                    btn.text = "O"
                    tvTurnName.text = firstPlayer
                }
            }

            when {
                winCondition() -> {
                    if (firstPlayerTurn) {
                        PlayerRepository.addWin(firstPlayer)
                        PlayerRepository.addLoss(secondPlayer)
                        showToast("$firstPlayer won!")
                    } else {
                        PlayerRepository.addWin(secondPlayer)
                        PlayerRepository.addLoss(firstPlayer)
                        showToast("$secondPlayer won!")
                    }
                    btnContinue.visibility = View.VISIBLE
                    freezeBoard()
                }
                round == 9 -> {
                    PlayerRepository.addDraw(firstPlayer)
                    PlayerRepository.addDraw(secondPlayer)
                    showToast(DRAW)
                    freezeBoard()
                }
                else -> {
                    firstPlayerTurn = !firstPlayerTurn
                }
            }

        }
    }

    private fun freezeBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].isEnabled = false
            }
        }
    }

    private fun unfreezeBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].isEnabled = true
            }
        }
    }

    private fun resetBoard() {
        unfreezeBoard()
        tvTurnName.text = firstPlayer
        firstPlayerTurn = true
        round = 0

        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
                buttons[i][j].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
            }
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun winCondition(): Boolean {

        for (i in 0..2) {
            if (buttons[i][0].text.toString() != "" &&
                buttons[i][0].text == buttons[i][1].text &&
                buttons[i][0].text == buttons[i][2].text
            ) {
                buttons[i][0].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)
                buttons[i][1].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)
                buttons[i][2].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)
                return true
            }
        }


        for (i in 0..2) {
            if (buttons[0][i].text.toString() != "" &&
                buttons[0][i].text.toString() == buttons[1][i].text.toString() &&
                buttons[0][i].text.toString() == buttons[2][i].text.toString()
            ) {
                buttons[0][i].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)
                buttons[1][i].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)
                buttons[2][i].backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)
                return true
            }
        }

        if (buttons[0][0].text.toString() != "" &&
            buttons[0][0].text.toString() == buttons[1][1].text.toString() &&
            buttons[1][1].text.toString() == buttons[2][2].text.toString()
        ) {
            buttons[0][0].backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.green)
            buttons[1][1].backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.green)
            buttons[2][2].backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.green)
            return true
        } else if (buttons[0][2].text.toString() != "" &&
            buttons[0][2].text.toString() == buttons[1][1].text.toString() &&
            buttons[1][1].text.toString() == buttons[2][0].text.toString()
        ) {
            buttons[0][2].backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.green)
            buttons[1][1].backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.green)
            buttons[2][0].backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.green)
            return true

        }

        return false
    }


}