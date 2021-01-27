package com.example.queuestack

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_students.addStudentBtn
import kotlinx.android.synthetic.main.fragment_students.nameEt
import kotlinx.android.synthetic.main.fragment_students.restoreBtn
import kotlinx.android.synthetic.main.fragment_students.studentsRv
import kotlinx.android.synthetic.main.fragment_students.view.studentsRv

class StudentsFragment(context: Context) : Fragment(R.layout.fragment_students), StudentRemoveListener {

    private lateinit var students : ArrayList<Student>
    private var studentsAdapter: StudentsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        students = StudentsDataProvider.generateStudents()
        initRv()
    }

    private fun initRv() {
        studentsAdapter = StudentsAdapter(students, context as StudentClickListener, this)
        studentsRv.adapter = studentsAdapter
        studentsRv.layoutManager = LinearLayoutManager(requireContext())

        addStudentBtn.setOnClickListener {
            StudentsDataProvider.newStudent(nameEt.text.toString())
            studentsAdapter?.students = StudentsDataProvider.list
            studentsAdapter?.notifyDataSetChanged()
        }

        restoreBtn.setOnClickListener {
            StudentsDataProvider.addRemovedStudent()
            studentsAdapter?.students = StudentsDataProvider.list
            studentsAdapter?.notifyDataSetChanged()
        }
    }


    override fun onStudentRemoved(pos: Int?, id: Int?) {

        StudentsDataProvider.removeStudentById(id!!)
//        studentsAdapter?.students?.removeAt(pos!!)
        studentsAdapter?.notifyItemRemoved(pos!!);
        studentsAdapter?.notifyItemRangeChanged(pos!!,  studentsAdapter?.students?.size!! - pos!!);
    }
}