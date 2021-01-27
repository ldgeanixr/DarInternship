package com.example.queuestack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_details.nameTv
import kotlinx.android.synthetic.main.fragment_details.surnameTv
import kotlinx.android.synthetic.main.student_item.idTv

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable<Student>(ARG_PARAM1)
        }

        if (student != null){
//            idTv.text = student?.id.toString()
            nameTv.text = student?.name.toString()
            surnameTv.text = student?.name.toString()
        }
    }



    companion object {

        fun newInstance(stud: Student) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, stud)
                }
            }
    }
}