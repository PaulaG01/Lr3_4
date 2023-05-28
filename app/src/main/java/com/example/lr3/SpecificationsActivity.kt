package com.example.lr3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lr3.malina.Skill
import com.example.lr3.malina.Vacancy
import com.example.lr3.malina.VacancyFilesHelper
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.lang.Exception


class SpecificationsActivity : AppCompatActivity() {
    private lateinit var specPrevData: TextView
    private lateinit var addBtn: Button
    private lateinit var editText: EditText
    private lateinit var chipGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specifications)

        specPrevData = findViewById<TextView>(R.id.specPrevData) as TextView
        addBtn = findViewById<Button>(R.id.addBtn) as Button
        editText = findViewById(R.id.editTextText2)
        chipGroup = findViewById(R.id.chipGroup)

        registerPevButton()
        registerAddBtn()
        registerNextButton()

        showPrevData()
        showChips()
    }

    private fun registerPevButton() {
        val prevBtn = findViewById<Button>(R.id.prevSpecButton)

        prevBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            saveChips()
            startActivity(intent)
        }
    }

    private fun registerNextButton() {
        val nextBtn = findViewById<Button>(R.id.nextSpecButton)

        nextBtn.setOnClickListener {
            val intent = Intent(this, FinalActivity::class.java)

            saveChips()

            try {
                val vacancy: Vacancy = application as Vacancy

                VacancyFilesHelper.saveVacancy(vacancy.vacancyVar, this)
                vacancy.vacancyVar.resetData()

                Toast.makeText(this, "Данные сохранены!", Toast.LENGTH_LONG).show()

                startActivity(intent)
            } catch (ex: Exception) {
                Log.e("FILEE", ex.printStackTrace().toString())
                Toast.makeText(this, "Ой, ошибочка!!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun registerAddBtn(){
        addBtn.setOnClickListener{
            if(!editText.text.toString().isEmpty()) {
                val skill = Skill()
                skill.title = editText.text.toString()
                addChip(skill)
                editText.setText("")
            }
        }
    }

    private fun showPrevData() {
        val vacancy: Vacancy = application as Vacancy

        val prevData = "Позиция: '${vacancy.vacancyVar.title}' \n" +
                "Город: ${vacancy.vacancyVar.city} \n" +
                "Длительность: ${vacancy.vacancyVar.days}"

        specPrevData.text = prevData
    }

    private fun showChips() {
        val vacancy: Vacancy = application as Vacancy
        for (i in 0 until vacancy.vacancyVar.skills.count()) {
            vacancy.vacancyVar.skills[i]?.let { addChip(it) }
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

    private fun saveChips() {
        val vacancy: Vacancy = application as Vacancy
        //var tmpMutableMap: MutableMap<Int, String> = mutableMapOf()

//        var skills: List<Skill> = listOf()
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            val skill = Skill()
            skill.title = chip.text.toString()
            vacancy.vacancyVar.skills += skill
       //     tmpMutableMap.put(i, chip.text.toString())
        }
//
//        vacancy.skills = skills
    }
}
