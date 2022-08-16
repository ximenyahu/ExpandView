package com.simon.expandview.ui.data

data class SchoolCategory(val schoolName: String, val schoolGrades: List<SchoolGrade>)

data class SchoolGrade(val gradeName: String, val students: List<Student>)

data class Student(val name: String, val age: Int)
