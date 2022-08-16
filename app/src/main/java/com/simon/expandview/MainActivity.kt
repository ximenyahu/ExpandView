package com.simon.expandview

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.simon.expandview.ui.data.DataRepository
import com.simon.expandview.ui.data.SchoolCategory
import com.simon.expandview.ui.data.Student
import com.simon.expandview.ui.theme.ExpandViewTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
                    LazyColumnFun(items = DataRepository.data)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Composable
fun LazyColumnFun(
    items: List<SchoolCategory>,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        itemsIndexed(items = items) { index, item ->
//            Log.i(TAG, "index: $index \n item: $item")
            var levelOneExpandValue by remember(index) { mutableStateOf(false) }
            LevelOne(
                schoolCategory = item,
                onItemClick = { levelOneExpandValue = !levelOneExpandValue })
            if (item.schoolGrades.isNotEmpty() && levelOneExpandValue) {
//                Log.i(TAG, "child index: $index \n child item: $item")
                item.schoolGrades.forEachIndexed { schoolGradesIndex, schoolGrade ->
                    var levelTwoExpandValue by remember(schoolGradesIndex) { mutableStateOf(false) }
                    LevelTwo(
                        childItem = schoolGrade.gradeName,
                        onLevelTwoClick = { levelTwoExpandValue = !levelTwoExpandValue })
                    if (schoolGrade.students.isNotEmpty() && levelTwoExpandValue) {
                        schoolGrade.students.forEachIndexed { studentIndex, student ->
                            LevelTree(student = student)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LevelOne(
    schoolCategory: SchoolCategory,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onItemClick() }
    ) {
        Text(
            text = schoolCategory.schoolName, modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .background(color = Color.Red)
        )
        Icon(
            imageVector = Icons.Default.RestoreFromTrash,
            contentDescription = stringResource(id = R.string.cd_restore),
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun LevelTwo(
    childItem: String,
    modifier: Modifier = Modifier,
    onLevelTwoClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onLevelTwoClick() }
    ) {
        Text(
            text = childItem, modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                .background(color = Color.Green)
        )
        Icon(
            imageVector = Icons.Default.PrivacyTip,
            contentDescription = stringResource(id = R.string.cd_restore),
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun LevelTree(
    student: Student,
    modifier: Modifier = Modifier,
    onLevelTreeClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
                onLevelTreeClick()
                Log.i(TAG, "student: $student")
            }
    ) {
        Text(
            text = student.name, modifier = Modifier
                .weight(1f)
                .padding(start = 48.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                .background(color = Color.Magenta)
        )
        Icon(
            imageVector = Icons.Default.Sailing,
            contentDescription = stringResource(id = R.string.cd_restore),
            modifier = Modifier.padding(4.dp)
        )
    }
}