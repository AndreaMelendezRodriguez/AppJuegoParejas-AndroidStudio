package com.example.juegofinal

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    //<editor-fold desc="IMAGENES GUI">
    lateinit var iv_11: ImageView
    lateinit var iv_12: ImageView
    lateinit var iv_13: ImageView
    lateinit var iv_14: ImageView

    lateinit var iv_21: ImageView
    lateinit var iv_22: ImageView
    lateinit var iv_23: ImageView
    lateinit var iv_24: ImageView

    lateinit var iv_31: ImageView
    lateinit var iv_32: ImageView
    lateinit var iv_33: ImageView
    lateinit var iv_34: ImageView
    //</editor-fold>

    //<editor-fold desc="OTROS GUI">
    lateinit var tv_j1: TextView
    lateinit var tv_j2: TextView
    lateinit var ib_sonido: ImageButton

    lateinit var mp: MediaPlayer
    lateinit var mpFondo: MediaPlayer
    lateinit var imagen1: ImageView
    lateinit var imagen2: ImageView
    //</editor-fold>

    //<editor-fold desc="VARIABLES">
    var imagenesArray = arrayOf(11,12,13,14,15,16,21,22,23,24,25,26)
    var diezcorazones = 0;
    var diezpicas = 0;
    var ochotreboles = 0;
    var reinapicas = 0;
    var reydiamantes = 0;
    var reypicas = 0;

    var turno = 1
    var puntosj1 = 0
    var puntosj2 = 0
    var numeroImagen = 1
    var escuchar = true
    //</editor-fold>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enlazarGUI()
    }

    private fun enlazarGUI() {
        iv_11 = findViewById(R.id.iv_11)
        iv_12 = findViewById(R.id.iv_12)
        iv_13 = findViewById(R.id.iv_13)
        iv_14 = findViewById(R.id.iv_14)
        iv_21 = findViewById(R.id.iv_21)
        iv_22 = findViewById(R.id.iv_22)
        iv_23 = findViewById(R.id.iv_23)
        iv_24 = findViewById(R.id.iv_24)
        iv_31 = findViewById(R.id.iv_31)
        iv_32 = findViewById(R.id.iv_32)
        iv_33 = findViewById(R.id.iv_33)
        iv_34 = findViewById(R.id.iv_34)

        ib_sonido = findViewById(R.id.imageButton_sonido)
        ib_sonido.setColorFilter(Color.GREEN)

        sonido("background",true)

        iv_11.tag = "0"
        iv_12.tag = "1"
        iv_13.tag = "2"
        iv_14.tag = "3"
        iv_21.tag = "4"
        iv_22.tag = "5"
        iv_23.tag = "6"
        iv_24.tag = "7"
        iv_31.tag = "8"
        iv_32.tag = "9"
        iv_33.tag = "10"
        iv_34.tag = "11"

        diezcorazones = R.drawable.diezcorazones
        diezpicas = R.drawable.diezpicas
        ochotreboles = R.drawable.ochotreboles
        reinapicas = R.drawable.reinapicas
        reydiamantes = R.drawable.reydiamantes
        reypicas = R.drawable.reypicas

        imagenesArray.shuffle()
        // Inicializar tv_j1 y tv_j2 antes de acceder a ellos
        tv_j1 = findViewById(R.id.tv_j1)
        tv_j2 = findViewById(R.id.tv_j2)

        // Establecer colores después de la inicialización
        tv_j2.setTextColor(Color.GRAY)
        tv_j1.setTextColor(Color.WHITE)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun sonido(sonidoName: String, loop: Boolean=false) {
        var resID = resources.getIdentifier(
            sonidoName,"raw", packageName
        )
        if (sonidoName == "background"){
            mpFondo= MediaPlayer.create(this,resID)
            mpFondo.isLooping = loop
            mpFondo.setVolume(1.0F, 1.0F)
            if (!mpFondo.isPlaying){
                mpFondo.start()
            }
        }else{
            mp= MediaPlayer.create(this,resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying){
                mp.start()
            }
        }
    }

    fun musicaFondo(v: View){
        if(escuchar ){
            mpFondo.pause()
            ib_sonido.setImageResource(R.drawable.ic_volume_of)
            ib_sonido.setColorFilter(Color.RED)
        }else{
            mpFondo.start()
            ib_sonido.setImageResource(R.drawable.ic_volumen_on)
            ib_sonido.setColorFilter(Color.GREEN)
        }
        escuchar = !escuchar
    }

    fun seleccionar(imagen:View){
        sonido("touch")
        verificar(imagen)

        
    }

    private fun verificar(imagen: View) {
        var iv = (imagen as ImageView)
        var tag = imagen.tag.toString().toInt()
        if(imagenesArray[tag]==11){
            imagen.setImageResource(diezcorazones)
        }else if(imagenesArray[tag]==12){
            imagen.setImageResource(diezpicas)
        }else if(imagenesArray[tag]==13){
            imagen.setImageResource(ochotreboles)
        }else if(imagenesArray[tag]==14){
            imagen.setImageResource(reinapicas)
        }else if(imagenesArray[tag]==15){
            imagen.setImageResource(reydiamantes)
        }else if(imagenesArray[tag]==16){
            imagen.setImageResource(reypicas)
        }else if(imagenesArray[tag]==21){
            imagen.setImageResource(diezcorazones)
        }else if(imagenesArray[tag]==22){
            imagen.setImageResource(diezpicas)
        }else if(imagenesArray[tag]==23){
            imagen.setImageResource(ochotreboles)
        }else if(imagenesArray[tag]==24){
            imagen.setImageResource(reinapicas)
        }else if(imagenesArray[tag]==25){
            imagen.setImageResource(reydiamantes)
        }else if(imagenesArray[tag]==26){
            imagen.setImageResource(reypicas)
        }

        // guardar temporalmente imagen seleccionada
        if(numeroImagen == 1){
            imagen1 = iv
            numeroImagen = 2
            iv.isEnabled = false
        }else if(numeroImagen == 2){
            imagen2 = iv
            numeroImagen = 1
            iv.isEnabled = false

            deshabilitarImagenes()
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({sonImagenesIguales()}, 1000)
        }



    }

    private fun sonImagenesIguales() {
        if(imagen1.drawable.constantState == imagen2.drawable.constantState){
            sonido("success")
            if(turno ==1){
                puntosj1++
                tv_j1.text = "Jugador 1: $puntosj1"
            }else if(turno ==2){
                puntosj2++
                tv_j2.text = "Jugador 2: $puntosj2"
            }

            imagen1.isEnabled = false
            imagen2.isEnabled = false
            imagen1.tag = ""
            imagen2.tag = ""
        }else{
            sonido("no")
            imagen1.setImageResource(R.drawable.oculta)
            imagen2.setImageResource(R.drawable.oculta)
            if(turno==1){
                turno = 2
                tv_j1.setTextColor(Color.GRAY)
                tv_j2.setTextColor(Color.WHITE)
            }else if(turno==2){
                turno = 1
                tv_j1.setTextColor(Color.WHITE)
                tv_j2.setTextColor(Color.GRAY)
            }
        }
        iv_11.isEnabled = !iv_11.tag.toString().isEmpty()
        iv_12.isEnabled = !iv_12.tag.toString().isEmpty()
        iv_13.isEnabled = !iv_13.tag.toString().isEmpty()
        iv_14.isEnabled = !iv_14.tag.toString().isEmpty()
        iv_21.isEnabled = !iv_21.tag.toString().isEmpty()
        iv_22.isEnabled = !iv_22.tag.toString().isEmpty()
        iv_23.isEnabled = !iv_23.tag.toString().isEmpty()
        iv_24.isEnabled = !iv_24.tag.toString().isEmpty()
        iv_31.isEnabled = !iv_31.tag.toString().isEmpty()
        iv_32.isEnabled = !iv_32.tag.toString().isEmpty()
        iv_33.isEnabled = !iv_33.tag.toString().isEmpty()
        iv_34.isEnabled = !iv_34.tag.toString().isEmpty()

        verificarFinJuego()
    }

    private fun verificarFinJuego() {
        if(
            iv_11.tag.toString().isEmpty() &&
            iv_12.tag.toString().isEmpty() &&
            iv_13.tag.toString().isEmpty() &&
            iv_14.tag.toString().isEmpty() &&
            iv_21.tag.toString().isEmpty() &&
            iv_22.tag.toString().isEmpty() &&
            iv_23.tag.toString().isEmpty() &&
            iv_24.tag.toString().isEmpty() &&
            iv_31.tag.toString().isEmpty() &&
            iv_32.tag.toString().isEmpty() &&
            iv_33.tag.toString().isEmpty() &&
            iv_34.tag.toString().isEmpty()
            ){
            mp.stop()
            mp.release()
            sonido("win")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("FIN DEL JUEGO").
            setMessage("PUNTOS: \nJugador 1: "+puntosj1+"\nJugador 2: "+puntosj2).
            setCancelable(false).setPositiveButton("Jugar de nuevo",
                DialogInterface.OnClickListener{dialogInterface, i ->
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                })
                .setNegativeButton("SALIR",
                    DialogInterface.OnClickListener{dialogInterface, i ->
                        finish()
                    })
            val ad = builder.create()
            ad.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mpFondo.isPlaying){
            mpFondo.stop()
            mpFondo.release()
        }
    }

    private fun deshabilitarImagenes() {
        iv_11.isEnabled = false
        iv_12.isEnabled = false
        iv_13.isEnabled = false
        iv_14.isEnabled = false
        iv_21.isEnabled = false
        iv_22.isEnabled = false
        iv_23.isEnabled = false
        iv_24.isEnabled = false
        iv_31.isEnabled = false
        iv_32.isEnabled = false
        iv_33.isEnabled = false
        iv_34.isEnabled = false
    }

}


