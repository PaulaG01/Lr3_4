package com.example.lr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.lr3.malina.Skill
import com.example.lr3.malina.Vacancy
import com.example.lr3.malina.VacancyFilesHelper
import com.example.lr3.malina.VacancyVar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class VacancyDetailsActivity : AppCompatActivity() {
    private lateinit var vacancy: VacancyVar

    private lateinit var title: EditText
    private lateinit var position: Spinner
    private lateinit var city: Spinner
    private lateinit var days: EditText
    private lateinit var chipGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacancy_details)

        try {
            initData()
            displayData()
        } catch (ex: Exception) {
            Toast.makeText(this, "Ой, ошибочка!!", Toast.LENGTH_SHORT).show()
        }

        registerPevButton()
    }

    private fun initData() {
        vacancy = VacancyFilesHelper.
            readVacancies(this).
            getOrNull(intent.getIntExtra("vacancyId", 0))!!

        title = findViewById(R.id.title)
        position = findViewById(R.id.position)
        city = findViewById(R.id.city)
        days = findViewById(R.id.days)
        chipGroup = findViewById(R.id.chipGroup)
    }

    private fun displayData() {
        title.setText(vacancy.title)
        days.setText(vacancy.days.toString())

        val positionId = vacancy.getPositions()[vacancy.position]
        if (positionId != null) {
            position.setSelection(positionId)
        }

        val cityId = vacancy.getCities()[vacancy.city]
        if (cityId != null) {
            city.setSelection(cityId)
        }

        showChips()
    }

    private fun showChips() {
        for (i in 0 until vacancy.skills.count()) {
            vacancy.skills[i]?.let { addChip(it) }
        }
    }

    private fun addChip(skill: Skill){
        val chip = Chip(this)
        chip.text = skill.title

        chip.isCloseIconVisible = true

        chip.setOnCloseIconClickListener{
            chipGroup.removeView(chip)
        }

        chipGroup.addView(chip)
    }

    private fun registerPevButton() {
        val prevBtn = findViewById<Button>(R.id.backBtn)

        prevBtn.setOnClickListener {
            val intent = Intent(this, FinalActivity::class.java)

            startActivity(intent)
        }
    }
}