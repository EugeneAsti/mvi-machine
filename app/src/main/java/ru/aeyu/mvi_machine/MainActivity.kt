package ru.aeyu.mvi_machine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.aeyu.mvi_machine.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, MainFragment())
                .commitNow()
        }
    }
}