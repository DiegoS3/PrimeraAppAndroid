package android.com.diego.primeraappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_cita.*
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddCitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cita)

        eventoBtnConfirmar.setOnClickListener { addCalendarEvent(eventoEditTextTitulo.text.toString(), eventoEditTextDesc.text.toString()
                , eventoEditTextLocalizacion.text.toString()) }

    }

    private fun addCalendarEvent(title: String, desc: String, location: String) {
        val calendarEvent: Calendar = Calendar.getInstance()
        val intent = Intent(Intent.ACTION_EDIT).apply {
        type = "vnd.android.cursor.item/event"
        putExtra("beginTime", calendarEvent.timeInMillis)
        putExtra("allDay", true)
        putExtra("rule", "FREQ=YEARLY")
        putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
        putExtra("title", title)
            putExtra(CalendarContract.Events.DESCRIPTION, desc)
        putExtra(CalendarContract.Events.EVENT_LOCATION, location)}

        if (title.isEmpty()){

            Toast.makeText(this, R.string.rellenarObliga , Toast.LENGTH_SHORT).show()
        }
        else if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}