package com.example.queuestack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.btnNextPage
import kotlinx.android.synthetic.main.activity_main.btnSave
import kotlinx.android.synthetic.main.activity_main.etName
import kotlinx.android.synthetic.main.activity_main.etSurname
import kotlinx.android.synthetic.main.activity_main.tvCount
import java.util.*

const val STUDENTS_LIST = "STUDENTS_LIST"

class MainActivity : AppCompatActivity() {
    private var count = 0
    private val students = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            var name = etName.text.toString()
            var surname = etSurname.text.toString()
            if(name.isNotBlank() && surname.isNotBlank()){
                students.add(Student(name, surname))
                incrementCount()
                updateView()
            }else{
                if (name.isEmpty()){
                    etName.error = "Name can't be empty!"
                }
                if (surname.isEmpty()){
                    etSurname.error = "Surname can't be empty!"
                }
            }
        }

        btnNextPage.setOnClickListener {
            var intent = Intent(this, ListActivity::class.java)
            intent.putExtra(STUDENTS_LIST, students)
            startActivity(intent)
        }

        etName.doAfterTextChanged {
            etName.error = null
        }

        etSurname.doAfterTextChanged {
            etSurname.error = null
        }
    }

    private fun incrementCount() {
        count++
        tvCount.text = count.toString()
    }

    private fun fieldsNotEmpty(): Boolean {
        return etName.text.toString().isNotBlank()
            && etSurname.text.toString().isNotBlank()
    }

    private fun updateView(){
        etSurname.text.clear()
        etName.text.clear()
    }
}
