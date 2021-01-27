package com.example.queuestack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_item.view.deleteBtn
import kotlinx.android.synthetic.main.student_item.view.idTv
import kotlinx.android.synthetic.main.student_item.view.nameTv

class StudentsAdapter(
    var students: ArrayList<Student>,
    private var onStudentClickListener: StudentClickListener,
    private var onStudentRemoveListener: StudentRemoveListener
    ) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsAdapter.StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentsAdapter.StudentViewHolder, position: Int) {
        holder.itemView.idTv.text = students[position].id.toString()
        holder.itemView.nameTv.text =
            "${students[position].name}   $position   ${students[position].surname}"

        holder.itemView.setOnClickListener{
            onStudentClickListener.onStudentClicked(position, students[position].id)
        }

        holder.itemView.deleteBtn.setOnClickListener {
            onStudentRemoveListener.onStudentRemoved(position, students[position].id)
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }


    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}



interface StudentClickListener {
    fun onStudentClicked(pos: Int?, id: Int?)
}

interface StudentRemoveListener {
    fun onStudentRemoved(pos: Int?, id: Int?)
}