package android.com.diego.primeraappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.actions.NoteIntents
import kotlinx.android.synthetic.main.activity_add_nota.*

/**
 *
 * Clase que permite incluir una nota en la app que este descargada
 * con esa funcionalidad
 *
 * No incluye las diferentes fases del ciclo de la vida ya que no es necesario
 *
 * @author Diego
 * @version 1.0
 *
 */
class AddNotaActivity : AppCompatActivity() {

    //Variables
    private var titulo : String = ""
    private var texto : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        this.titulo = notaTextAddTitulo.text.toString()
        this.texto = notaTextAddTexto.text.toString()

        //LLamada al metodo createNote al hacer click
        notaBtnConfirmar.setOnClickListener { createNote(titulo, texto) }

    }

    /**
     *
     * Metodo que añade una nota en la app que este descargada
     * con esa funcionalidad (en este caso no existe esa app no creara nota)
     * con un titulo, descripcion y localizacion
     *
     * @param subject String con el titulo
     * @param text String con la descripcion
     *
     */
    private fun createNote(subject: String, text: String) {
        val intent = Intent(NoteIntents.ACTION_CREATE_NOTE).apply {
            putExtra(NoteIntents.EXTRA_NAME, subject)
            putExtra(NoteIntents.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            Toast.makeText(this, R.string.confirmarNota , Toast.LENGTH_SHORT).show()
        } else {
            //en caso de no poder aparece mensaje flotante
            Toast.makeText(this, R.string.confirmarNotaNull , Toast.LENGTH_SHORT).show()

        }

    }

    // Para salvar el estado por ejemplo es usando un Bundle en el ciclo de vida
    override fun onSaveInstanceState(outState: Bundle) {
        // Salvamos en un bundle estas variables o estados de la interfaz
        outState.run {
            // Actualizamos los datos o los recogemos de la interfaz
            putString("TITULO", titulo)
            putString("TEXTO", texto)
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
            texto = getString("TEXTO").toString()

        }
    }
}