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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        contactBtnConfirmar.setOnClickListener {
            insertContact(contactEditTextName.text.toString(), contactEditTextEmail.text.toString(),
                contactEditTextPhone.text.toString()) }

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
}