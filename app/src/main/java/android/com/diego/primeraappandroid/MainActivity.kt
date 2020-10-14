package android.com.diego.primeraappandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * Clase  principal con un menu que nos llevara a las diferentes Actividades
 *
 * No incluye las diferentes fases del ciclo de la vida ya que no es necesario
 *
 * @author Diego
 * @version 1.0
 *
 */
class MainActivity : AppCompatActivity() {
    /**
     *
     * Metodo que se activa nada mas crearse la aplicación
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     *
     * Abre Actividad Mail
     *
     */
    private fun composeEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    /**
     *
     * Abre Actividad acerca de
     *
     */
    private fun abrirAcercaDe(){

        val intent = Intent(this, AcercaDeActivity::class.java)

        startActivity(intent)

    }

    /**
     *
     * Abre Actividad aádir nota
     *
     */
    private fun abrirAddNota(){

        val intent = Intent(this, AddNotaActivity::class.java)

        startActivity(intent)

    }

    /**
     *
     * Abre Actividad añadir amigo
     *
     */
    private fun abrirAddFriend(){

        val intent = Intent(this, AddFriendActivity::class.java)

        startActivity(intent)

    }
    /**
     *
     * Abre Actividad añadir cita
     *
     */
    private fun abrirAddCita(){

        val intent = Intent(this, AddCitaActivity::class.java)

        startActivity(intent)

    }
    /**
     *
     * Meotdo que crea el menu con sus opciones
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    /**
     *
     * Metodo que depende de la opcion que se haga clic llama a un
     * metodo u otro
     *
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menuAddNote -> {
                abrirAddNota()
                true
            }
            R.id.menuSendMail -> {
                composeEmail()
                true
            }
            R.id.menuAddCita -> {
                abrirAddCita()
                true
            }
            R.id.menuAddFriend -> {
                abrirAddFriend()
                true
            }
            R.id.menuAbout -> {
                abrirAcercaDe()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}