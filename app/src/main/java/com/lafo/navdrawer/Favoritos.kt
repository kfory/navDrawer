package com.lafo.navdrawer

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.lafo.dark.SaveData

class Favoritos : AppCompatActivity() {
    private var switchFav: Switch? = null
    private lateinit var saveData: SaveData
    var drawerLayout: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        //shared preferences states look
        saveData = SaveData(this)
        if(saveData.loadDarkModeState()==true){
            setTheme(R.style.darkTheme)
        }else {
            setTheme(R.style.lightTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        drawerLayout = findViewById(R.id.drawer_layout);


        //Switch for On Off
        switchFav = findViewById<View>(R.id.On_Off_fav) as Switch?
        if(saveData.loadDarkModeState()== true ){
            switchFav!!.isChecked = true
        }

        //On click ON Off
        switchFav!!.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                saveData.setDarkModeState(true)
                restartApplication()
            }else{
                saveData.setDarkModeState(false)
                restartApplication()
            }
        }

    }

    //Restart aplication on click ON OFF
    private fun restartApplication(){
        val i = Intent(applicationContext, Favoritos::class.java)
        startActivity(i)
        finish()
    }

    fun ClickMenu(view: View?) {
        openDrawer(drawerLayout)
    }

    fun openDrawer(drawerLayout: DrawerLayout?) {
        drawerLayout!!.openDrawer(GravityCompat.START);
    }

    fun ClickLogo(view: View?) {
        closeDrawer(drawerLayout!!)
    }

    private fun closeDrawer(drawerLayout: DrawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    fun ClickInicio(view: View?) {
        redirectActivity(this,MainActivity::class.java)
    }

    fun ClickFav(view: View?) {
        recreate()
    }

    fun ClickAct(view: View?) {
        redirectActivity(this,Actividad::class.java)
    }
    fun ClickInfo(view: View?) {
        redirectActivity(this,Info::class.java)
    }


    fun ClickCerrar(view: View?) {
        logout(this)
    }

    private fun logout(activity: Activity) {
        //Inicializar Alerta
        val builder = AlertDialog.Builder(activity)
        //Titulo
        builder.setTitle("CERRAR SESIÓN")
        builder.setMessage("¿Estás seguro de que quieres cerrar sesión?")
        builder.setPositiveButton("SALIR",  DialogInterface.OnClickListener { dialog, which ->
            activity.finishAffinity()
            System.exit(0)
            //     Toast.makeText(applicationContext, "presiono salir", Toast.LENGTH_LONG).show()
        })
        builder.setNegativeButton("CANCELAR",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        builder.show()
        //   Toast.makeText(applicationContext, "presiono cancelar", Toast.LENGTH_LONG).show()
    }



    private fun redirectActivity(activity: Activity, aClass: Class<*>) {
        val intent = Intent(activity, aClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        closeDrawer(drawerLayout!!)
    }
}