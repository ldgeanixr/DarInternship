package com.example.queuestack

import java.util.*
import kotlin.collections.ArrayList

class StudentsDataProvider {

    companion object{

        var id = 1
        var list = ArrayList<Student>()

        var stack: Stack<Student> = Stack<Student>()

        fun generateStudents() : ArrayList<Student> {
            for(i in 1..10){
                list.add(Student(id, "Name$id", "Surname$id"))
                id++
            }
            return list
        }

        fun newStudent(name: String?){
            list.add(Student(id = id, name = name?: ""))
            id++
        }

        fun addRemovedStudent(){
            if (stack.isNotEmpty()){
                list.add(stack.pop())
            }
        }

        fun removeStudentById(id: Int){
            var toRemove = list.find{
                it.id == id
            }

            if (stack.size == 5){
                stack.removeAt(0)
            }

            list.remove(toRemove)

            stack.add(toRemove)
        }

        fun getStudentById(id: Int) : Student?{
            return list.find{
                it.id == id
            }
        }
    }


}