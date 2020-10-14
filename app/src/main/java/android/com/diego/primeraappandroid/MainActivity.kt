package android.com.diego.primeraappandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainIbtnSendMail.setOnClickListener{ composeEmail()}
        mainIbtnAbout.setOnClickListener{abrirAcercaDe()}
        mainIbtnAddNota.setOnClickListener { abrirAddNota() }

    }

    private fun composeEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun abrirAcercaDe(){

        val intent = Intent(this, AcercaDeActivity::class.java).apply{}

        startActivity(intent)

    }

    private fun abrirAddNota(){

        val intent = Intent(this, AddNotaActivity::class.java).apply {

            startActivity(intent)
        }

    }
}