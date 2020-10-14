package android.com.diego.primeraappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_friend.*

class AddFriendActivity : AppCompatActivity() {

    private var nombre : String = ""
    private var mail : String = ""
    private var telefono : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        this.nombre = contactEditTextName.text.toString()
        this.mail = contactEditTextEmail.text.toString()
        this.telefono = contactEditTextPhone.text.toString()

        contactBtnConfirmar.setOnClickListener {
            insertContact(nombre, mail, telefono) }

    }

    private fun insertContact(name: String, email: String, phone: String) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
            putExtra(ContactsContract.Intents.Insert.NAME, name)
            putExtra(ContactsContract.Intents.Insert.EMAIL, email)
            putExtra(ContactsContract.Intents.Insert.PHONE, phone)
        }
        if (!validarMail(email)){

            Toast.makeText(this, R.string.mailNoValido , Toast.LENGTH_SHORT).show()

        }else if (name.equals("") or phone.equals("")){

            Toast.makeText(this, R.string.rellenarCampos , Toast.LENGTH_SHORT).show()

        }
        else if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun validarMail(email: String) : Boolean{

        val pattern = Patterns.EMAIL_ADDRESS;

        return pattern.matcher(email).matches()

    }

    // Para salvar el estado por ejemplo es usando un Bundle en el ciclo de vida
    override fun onSaveInstanceState(outState: Bundle) {
        // Salvamos en un bundle estas variables o estados de la interfaz
        outState.run {
            // Actualizamos los datos o los recogemos de la interfaz
            putString("NOMBRE", nombre)
            putString("MAIL", mail)
            putString("PHONE", telefono)
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
            nombre = getString("NOMBRE").toString()
            mail = getString("MAIL").toString()
            telefono = getString("PHONE").toString()

        }
    }

}