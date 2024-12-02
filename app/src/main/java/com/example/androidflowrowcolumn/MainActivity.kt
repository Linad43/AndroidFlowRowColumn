package com.example.androidflowrowcolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

val arrayFirstName = arrayListOf(
    "Даниил", "Ефим", "Кирилл", "Максим", "Егор", "Борис"
)
val arraySecondName = arrayListOf(
    "Цивилев", "Баданин", "Шнайдер", "Фролов", "Рогачев", "Фомин"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            fillingPersons(12)
            AllKarts(persons)
        }
    }
}

enum class Role {
    ENGINEER, DEVELOPER, DOCTOR, TEACHER
}

class Person(
    val firstName: String,
    val secondName: String,
    val role: Role,
    val salary: Int,
    val image: Int,
)

val persons = arrayListOf<Person>()
fun fillingPersons(count: Int) {
    var i = 0
    while (i < count) {
        persons.add(
            Person(
                arrayFirstName.random(),
                arraySecondName.random(),
                Role.entries.toTypedArray()[i % Role.entries.toTypedArray().size],
                (50..120).random() * 1000,
                R.drawable.ic_launcher_foreground
            )
        )
        i++
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AllKarts(persons: ArrayList<Person>) {
    FlowColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .horizontalScroll(rememberScrollState()),
        maxItemsInEachColumn = 3
    ) {
        Role.entries.forEach { role ->
            persons.forEach { person ->
                if (role == person.role) {
                    ItemKart(person)
                }
            }
        }
    }
}

@Composable
fun ItemKart(person: Person) {
    Column(
        modifier = Modifier
            .padding(2.dp)

    ) {
        Image(
            painter = painterResource(id = person.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(Color.LightGray)
                .size(100.dp)
                .padding(5.dp)
        )
        Text(
            text = "Имя: ${person.firstName}"
        )
        Text(
            text = "Фамилия: ${person.secondName}"
        )
        Text(
            text = "Должность: ${person.role}"
        )
        Text(
            text = "Зарплата: ${person.salary}"
        )
    }
}
