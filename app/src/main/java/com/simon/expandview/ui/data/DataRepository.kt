package com.simon.expandview.ui.data

class DataRepository {

    companion object {

        val students1 = listOf<Student>(
            Student("小明", 5),
            Student("小梅", 6),
            Student("小强", 7)
        )

        val students2 = listOf<Student>(
            Student("明明", 5),
            Student("梅梅", 6),
            Student("强强", 7)
        )

        val students3 = listOf<Student>(
            Student("小明明", 5),
            Student("小梅梅", 6),
            Student("小强强", 7)
        )


        val data = listOf<SchoolCategory>(
            SchoolCategory(
                "幼儿园",
                listOf(
                    SchoolGrade("小班", students3),
                    SchoolGrade("中班", students2),
                    SchoolGrade("大班", students1),
                )
            ),
            SchoolCategory(
                "小学",
                listOf(
                    SchoolGrade("一年级", students3),
                    SchoolGrade("二年级", students2),
                    SchoolGrade("三年级", students1),
                )
            )
//            SchoolCategory("小学", listOf("一年级", "二年级", "三年级", "四年级", "五年级")),
//            SchoolCategory("中学", listOf("初一", "初二", "初三")),
//            SchoolCategory("大学", listOf())
        )
    }
}
