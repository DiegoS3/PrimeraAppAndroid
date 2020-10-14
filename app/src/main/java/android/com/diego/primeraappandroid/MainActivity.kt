package android.com.diego.primeraappandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val intent = Intent(this, AcercaDeActivity::class.java)

        startActivity(intent)

    }

    private fun abrirAddNota(){

        val intent = Intent(this, AddNotaActivity::class.java)

        startActivity(intent)

    }

    private fun abrirAddFriend(){

        val intent = Intent(this, AddFriendActivity::class.java)

        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

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
                composeEmail()
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