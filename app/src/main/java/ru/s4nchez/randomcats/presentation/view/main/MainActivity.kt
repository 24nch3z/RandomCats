package ru.s4nchez.randomcats.presentation.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.s4nchez.randomcats.R

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
