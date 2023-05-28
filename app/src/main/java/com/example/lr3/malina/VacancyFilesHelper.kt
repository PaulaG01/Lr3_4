package com.example.lr3.malina

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Paths

class VacancyFilesHelper {
    companion object {
        private const val fileName: String = "vacancies.json"

        fun saveVacancy(vacancy: VacancyVar, context: Context) {
            val path: File = context.filesDir

            var vacancies: List<VacancyVar> = listOf()

            if (File(path, fileName).exists()) {
                vacancies = readVacancies(context)
            }
            vacancy.id = vacancies.count() + 1
            vacancies = vacancies.plus(vacancy)

            val mapper = ObjectMapper().writerWithDefaultPrettyPrinter()
            val jsonVacancy = mapper.writeValueAsString(vacancies)

            val writer = FileOutputStream(File(path, fileName))

            writer.write(jsonVacancy.toByteArray())

            writer.close()
        }

        fun readVacancies(context: Context): List<VacancyVar> {
            val mapper = ObjectMapper()

            val path: File = context.filesDir
            val file: File = File(path, fileName)

            return listOf(*mapper.readValue(file, Array<VacancyVar>::class.java))
        }
    }
}
