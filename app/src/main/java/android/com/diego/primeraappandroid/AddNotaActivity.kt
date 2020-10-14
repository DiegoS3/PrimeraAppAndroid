package android.com.diego.primeraappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.actions.NoteIntents
import kotlinx.android.synthetic.main.activity_add_nota.*

class AddNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        notaBtnConfirmar.setOnClickListener { createNote(notaTextAddTitulo.text.toString(), notaTextAddTexto.text.toString()) }

    }

    private fun createNote(subject: String, text: String) {
        val intent = Intent(NoteIntents.ACTION_CREATE_NOTE).apply {
            putExtra(NoteIntents.EXTRA_NAME, subject)
            putExtra(NoteIntents.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            Toast.makeText(this, R.string.confirmarNota , Toast.LENGTH_SHORT).show()
        } else {

            Toast.makeText(this, R.string.confirmarNotaNull , Toast.LENGTH_SHORT).show()

        }

    }
}