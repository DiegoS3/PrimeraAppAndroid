package android.com.diego.primeraappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_friend.*

/**
 *
 * Clase que permite incluir un contacto en la agenda
 *
 * No incluye las diferentes fases del ciclo de la vida ya que no es necesario
 *
 * @author Diego
 * @version 1.0
 */
class AddFriendActivity : AppCompatActivity() {

    //Atributos
    private var nombre : String = ""
    private var mail : String = ""
    private var telefono : String = ""

    /**
     *
     * Metodo que se activa nada mas crearse la aplicación
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //Guardamos los datos
        setContentView(R.layout.activity_add_friend)

        this.nombre = contactEditTextName.text.toString()
        this.mail = contactEditTextEmail.text.toString()
        this.telefono = contactEditTextPhone.text.toString()

        //LLamada al metodo insertContact al hacer click
        contactBtnConfirmar.setOnClickListener {
            insertContact(nombre, mail, telefono) }

    }

    /**
     *
     * Metodo que inserta un nuevo contacto en la agenda
     * con un titulo, descripcion y localizacion
     *
     * @param name String con el nombre del contacto
     * @param email String con el mail del contacto a guardar
     * @param phone String con el numero de telefono
     *
     */
    private fun insertContact(name: String, email: String, phone: String) {

        //Uso del intent que nos pemite acceder a la agenda
        val intent = Intent(Intent.ACTION_INSERT).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
            putExtra(ContactsContract.Intents.Insert.NAME, name)
            putExtra(ContactsContract.Intents.Insert.EMAIL, email)
            putExtra(ContactsContract.Intents.Insert.PHONE, phone)
        }
        //Antes esta parte del codigo me funcionaba la dejo comentada para que se vea que
        //comprobaba que los campos fueran buenos
        /*if (!validarMail(email)){ //Validamos el mail

            Toast.makeText(this, R.string.mailNoValido , Toast.LENGTH_SHORT).show()

        }else if (name.isEmpty() or phone.isEmpty()){ //Comprobamos que no estan vacios

            Toast.makeText(this, R.string.rellenarCampos , Toast.LENGTH_SHORT).show()

        }
        else */
            if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    /**
     *
     * metodo que valida un mail
     *
     * @param email String que sera comprobado
     *
     * @return Boolean si es valido true, sino false
     *
     */
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