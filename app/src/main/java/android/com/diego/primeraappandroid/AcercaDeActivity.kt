package android.com.diego.primeraappandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_acerca_de.*
/**
 *
 * Clase que muestra sobre el creador de la app
 *
 * No incluye las diferentes fases del ciclo de la vida ya que no es necesario
 *
 * @author Diego
 * @version 1.0
 *
 */
class AcercaDeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca_de)

        aboutIBtnTwitter.setOnClickListener { abrirURL("https://twitter.com/valsiror") }
        aboutIBtnGit.setOnClickListener { abrirURL("https://github.com/DiegoS3") }
        aboutIBtnPortfolio.setOnClickListener { abrirURL("https://portfoliodiegosanchez.webnode.es/") }

    }

    /**
     *
     * meotod que abre una URL en el navegador
     *
     * @param url String con la url
     *
     */
    private fun abrirURL(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(intent)
    }

}