package com.example.queuestack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.recyclerView
import kotlinx.android.synthetic.main.student_item.view.tvNameSurname

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var students = intent.getSerializableExtra(STUDENTS_LIST) as ArrayList<Student>
        recyclerView.adapter = StudentsAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}



class StudentsAdapter(
    private var students: ArrayList<Student>
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsAdapter.StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentsAdapter.StudentViewHolder, position: Int) {
        holder.itemView.tvNameSurname.text =
            "${students[position].name} ${students[position].surname}"
    }

    override fun getItemCount(): Int {
        return students.size
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}