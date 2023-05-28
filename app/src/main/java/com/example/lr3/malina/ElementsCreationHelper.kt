package com.example.lr3.malina

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import com.example.lr3.MainActivity
import com.example.lr3.VacancyDetailsActivity

class ElementsCreationHelper {
    companion object {
        fun createCard(context: Context, rootLayout: LinearLayout, vacancy: VacancyVar) {
            val cardView = CardView(context)
            cardView.id = vacancy.id

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // CardView width
                LinearLayout.LayoutParams.WRAP_CONTENT // CardView height
            )

            // Set margins for card view
            layoutParams.setMargins(20, 20, 20, 20)

            // Set the card view layout params
            cardView.layoutParams = layoutParams

            // Set the card view corner radius
            cardView.radius = 16F

            // Set the card view content padding
            cardView.setContentPadding(25,25,25,25)

            // Set the card view background color
            cardView.setCardBackgroundColor(Color.rgb(243, 145, 160))

            // Set card view elevation
            cardView.cardElevation = 8F

            // Set card view maximum elevation
            cardView.maxCardElevation = 12F

            val dataStr = "${vacancy.id}. ${vacancy.title}, ${vacancy.city}"
            cardView.addView(createTextView(context, vacancy.id, dataStr))
            rootLayout.addView(cardView)

            addCardOnClickListener(cardView, context, vacancy)
        }

        private fun addCardOnClickListener(cardView: CardView, context: Context, vacancy: VacancyVar) {
            cardView.setOnClickListener {
                val intent = Intent(context, VacancyDetailsActivity::class.java)
                intent.putExtra("vacancyId", vacancy.id-1)
                context.startActivity(intent)
            }
        }

        private fun createTextView(context: Context, id: Int, str: String): TextView {
            val line = TextView(context)
            line.id = id
            line.text = str
            line.textSize = 12F
            line.setTextColor(Color.WHITE)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(20, 20, 20, 20)
            line.layoutParams = params
            return line
        }
    }
}