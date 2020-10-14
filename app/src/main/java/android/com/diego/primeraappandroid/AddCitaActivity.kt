package android.com.diego.primeraappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.text.Editable
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_cita.*
import java.util.*

/**
 *
 * Clase que permite incluir unevento en el calendario
 *
 * No incluye las diferentes fases del ciclo de la vida ya que no es necesario
 *
 * @author Diego
 * @version 1.0
 *
 */
class AddCitaActivity : AppCompatActivity() {

    //Variables
    private var titulo : String = ""
    private var descripcion : String = ""
    private var localizacion : String = ""

    /**
     *
     * Metodo que se activa nada mas crearse la aplicación
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //Guardamos los datos
        setContentView(R.layout.activity_add_cita)

        this.titulo = eventoEditTextTitulo.text.toString()
        this.descripcion = eventoEditTextLocalizacion.text.toString()
        this.localizacion = eventoEditTextLocalizacion.text.toString()

        //LLamada al metodo addCalendarEvent al hacer click
        eventoBtnConfirmar.setOnClickListener { addCalendarEvent(titulo, descripcion
                , localizacion) }

    }

    /**
     *
     * Metodo que añade un evento en el calendario
     * con un titulo, descripcion y localizacion
     *
     * @param title String con el titulo
     * @param desc String con la descripcion
     * @param location String con la localizacion del evento
     *
     */
    private fun addCalendarEvent(title: String, desc: String, location: String) {

        //Uso del intent que nos pemite acceder al calendario
        val calendarEvent: Calendar = Calendar.getInstance()
        val intent = Intent(Intent.ACTION_EDIT).apply {
        type = "vnd.android.cursor.item/event"
        putExtra("beginTime", calendarEvent.timeInMillis) //Obtenemos fecha actual
        putExtra("allDay", true)
        putExtra("rule", "FREQ=YEARLY")
        putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000) //incrementamos fecha actual
        putExtra("title", title)
            putExtra(CalendarContract.Events.DESCRIPTION, desc)
        putExtra(CalendarContract.Events.EVENT_LOCATION, location)}

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    // Para salvar el estado por ejemplo es usando un Bundle en el ciclo de vida
    override fun onSaveInstanceState(outState: Bundle) {
        // Salvamos en un bundle estas variables o estados de la interfaz
        outState.run {
            // Actualizamos los datos o los recogemos de la interfaz
            putString("TITULO", titulo)
            putString("DESC", descripcion)
            putString("LUGAR", localizacion)
        }
        // Siempre se llama a la superclase para salvar las cosas
        super.onSaveInstanceState(outState)
    }

    // Para recuperar el estado al volver al un estado de ciclo de vida de la Interfaz
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // Recuperamos en un bundle estas variables o estados de la interfaz
        super.onRestoreInstanceState(savedInstanceState)
        // Recuperamos del Bundle
        savedInstanceState.run {
            titulo = getString("TITULO").toString()
            localizacion = getString("LUGAR").toString()
            descripcion = getString("DESC").toString()

        }
    }

}