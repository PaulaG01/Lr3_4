package com.example.lr3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.lr3.malina.Vacancy

class MainActivity : AppCompatActivity() {
    private lateinit var city: Spinner
    private lateinit var position: Spinner
    private lateinit var title: EditText
    private lateinit var days: EditText
    private lateinit var positionImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        city = findViewById<Spinner>(R.id.spinner) as Spinner
        position = findViewById<Spinner>(R.id.spinner2) as Spinner
        title = findViewById<EditText>(R.id.editTextText) as EditText
        days = findViewById<EditText>(R.id.editTextNumber2) as EditText
        positionImg = findViewById<ImageView>(R.id.imageView2) as ImageView

        setSelectedData()

        registerPositionsSpinnerListener()
        registerNextButton()
        registerToListButton()
    }

    private fun registerToListButton() {
        val skipBtn = findViewById<Button>(R.id.toList)

        skipBtn.setOnClickListener {
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerNextButton() {
        val nextBtn = findViewById<Button>(R.id.nextButton)

        nextBtn.setOnClickListener {
            val intent = Intent(this, SpecificationsActivity::class.java)

            //Store selected data to global variable
            val vacancy = application as Vacancy
            vacancy.vacancyVar.city = city.selectedItem as String
            vacancy.vacancyVar.position = position.selectedItem as String
            vacancy.vacancyVar.title = (title.text.toString())
            vacancy.vacancyVar.days = (days.text.toString())

            startActivity(intent)
        }
    }

    private fun setSelectedData() {
        val vacancy = application as Vacancy

        val city = vacancy.vacancyVar.city
        val cityId = vacancy.vacancyVar.getCities()[city]

        val position = vacancy.vacancyVar.position
        val positionId = vacancy.vacancyVar.getPositions()[position]

        val positionImg = vacancy.vacancyVar.getPositionsImages()[position]

        this.title.setText(vacancy.vacancyVar.title)
        this.days.setText(vacancy.vacancyVar.days.toString())

        if (cityId != null) {
            this.city.setSelection(cityId)
        }

        if (positionId != null) {
            this.position.setSelection(positionId)
        }

        if (positionImg != null) {
            when (positionImg) {
                0 -> this.positionImg.setImageResource(R.drawable.junior)
                1 -> this.positionImg.setImageResource(R.drawable.middle)
                2 -> this.positionImg.setImageResource(R.drawable.senior)
            }
        }
    }

    private fun registerPositionsSpinnerListener(){
        position.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> positionImg.setImageResource(R.drawable.junior)
                    1 -> positionImg.setImageResource(R.drawable.middle)
                    2 -> positionImg.setImageResource(R.drawable.senior)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
