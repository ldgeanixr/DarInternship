package com.example.queuestack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(), StudentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        supportFragmentManager.beginTransaction()
            .add(R.id.container, StudentsFragment(this))
            .addToBackStack(null)
            .commit()
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStudentClicked(pos: Int?, id: Int?) {
        var stud = StudentsDataProvider.getStudentById(id!!)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, DetailsFragment.newInstance(stud!!))
            .addToBackStack(null)
            .commit()
    }
}
