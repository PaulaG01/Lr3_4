package com.example.lr3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.lr3.malina.ElementsCreationHelper
import com.example.lr3.malina.VacancyFilesHelper


class FinalActivity : AppCompatActivity() {
    private lateinit var linLayout: LinearLayout
    private lateinit var toStartBtn: Button
    private lateinit var toInfoBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        linLayout = findViewById<LinearLayout>(R.id.ll)
        toStartBtn = findViewById<Button>(R.id.toStart)
        toInfoBtn = findViewById<Button>(R.id.toInfo)

        displayVacancies()
        registerToStartBtn()
        registerInfoButton()
    }


    private fun displayVacancies() {
        val vacancies = VacancyFilesHelper.readVacancies(this)

        for (i in 0 until vacancies.count()) {
            ElementsCreationHelper.createCard(this, linLayout, vacancies[i])
        }
    }

    private fun registerToStartBtn() {
        toStartBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerInfoButton() {
        toInfoBtn.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
    }
}