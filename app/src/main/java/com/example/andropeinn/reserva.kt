package com.example.andropeinn

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.andropeinn.databinding.ActivityReservaBinding
import com.google.android.material.snackbar.Snackbar
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.colors.Color
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.TextAlignment
import org.intellij.lang.annotations.JdkConstants.TitledBorderTitlePosition
import java.io.File
import java.io.FileOutputStream



class reserva : AppCompatActivity() {

     private var contador=1;
    lateinit var reservaBinding: ActivityReservaBinding
    private val STORAGE_CODE=1999;
    private lateinit var nombre_local:TextView
    private lateinit var cantidad:EditText

   private lateinit var metodoPago:String

   private lateinit var fecha:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reservaBinding=ActivityReservaBinding.inflate(layoutInflater)
        setContentView(reservaBinding.root)


        //editext cantidad

        cantidad=findViewById(R.id.edtCantidad)

        val bundle=intent.extras

        val local=bundle?.getString("nombre_local")

        nombre_local=findViewById<TextView>(R.id.nombre_local)
        nombre_local.setText(local)



        val rbPagoEfectivo=findViewById<RadioButton>(R.id.pagoEfectivo)
        var rbPagoTarjeta=findViewById<RadioButton>(R.id.pagoTarjeta)


        reservaBinding.edtCantidad.setText(contador.toString())


        reservaBinding.btnMas.setOnClickListener{

              aumentarContador()
        }

        reservaBinding.btnMenos.setOnClickListener{
           disminuirContador()
        }





        reservaBinding.btnHacerReserva.setOnClickListener {

            if (rbPagoEfectivo.isChecked){

                metodoPago="efectivo"
                createPdf()
            }else{
              selecMetodoPago(rbPagoEfectivo)

            }


            if(rbPagoTarjeta.isChecked){
                metodoPago="Tarjeta de credito/debito"
                createPdf()
            }else{
                selecMetodoPago(rbPagoTarjeta)
            }

        }


        //fecha

        val edtFecha=findViewById<EditText>(R.id.edtFecha)
        edtFecha.setOnClickListener{
            showDatePicker()
        }

    }

    private  fun showDatePicker(){
        val datePicker=DataPickerFragment({day, month, year->onDateSelected(day,month, year)})

        datePicker.show(supportFragmentManager, "datePicker")

    }

    private fun onDateSelected(day:Int, month:Int, year:Int){

        fecha="$day/$month/$year"

    }



   /* private fun generatePdf() {
        // Verificar si tenemos permiso de escritura
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Si no tenemos el permiso, solicitarlo al usuario
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_CODE)
        } else {
            // Tenemos permiso, continuar con la creación del PDF
            createPdf()
        }
    }


    */
    private fun createPdf() {
        try {
            // Obtener la ruta del directorio de documentos
            val values = ContentValues().apply {
                put(MediaStore.Files.FileColumns.DISPLAY_NAME, "comprobante.pdf")
                put(MediaStore.Files.FileColumns.MIME_TYPE, "application/pdf")
                put(MediaStore.Files.FileColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }

            val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), values)

            if (uri != null) {
                contentResolver.openOutputStream(uri)?.use { outputStream ->
                    val writer = PdfWriter(outputStream)
                    val pdf = PdfDocument(writer)
                    val document = Document(pdf)

                    // Agregar contenido al PDF
                    val titulo=Paragraph("ANDROPE INN")
                    titulo.setTextAlignment(TextAlignment.CENTER)
                    titulo.setFontSize(40f)

                    val local= Paragraph(nombre_local.text.toString())
                    local.setTextAlignment(TextAlignment.CENTER)
                    local.setFontSize(20f)

                    val txtFecha=Paragraph(fecha)
                    txtFecha.setTextAlignment(TextAlignment.CENTER )
                    txtFecha.setFontSize(15f)


                    val cantidad= Paragraph("Cantidad de personas: " + cantidad.text.toString())
                    cantidad.setTextAlignment(TextAlignment.CENTER )
                    cantidad.setFontSize(15f)

                    val metodo= Paragraph("Método de pago: $metodoPago")
                    metodo.setTextAlignment(TextAlignment.CENTER)
                    metodo.setFontSize(15f)


                    //color del documento


                    //agregar elementos

                    document.add(titulo)
                    document.add(local)
                    document.add(cantidad)
                    document.add(metodo)
                    document.add(txtFecha)

                    // Cerrar el documento
                    document.close()
                }
            }

            // Mostrar mensaje de éxito
            Toast.makeText(this, "PDF generado correctamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            // Manejar errores
            Toast.makeText(this, "Error al generar el PDF", Toast.LENGTH_SHORT).show()
        }
    }



    private fun selecMetodoPago(vista: View){
        Snackbar.make(vista, "Debes seleccionar un método de pago", Snackbar.LENGTH_SHORT)
    }

    private  fun aumentarContador(){
        if(contador<150){

            val valorC=reservaBinding.edtCantidad.text.toString().toInt()
            contador=valorC
            contador ++
        }

        reservaBinding.edtCantidad.setText(contador.toString())
    }


    private fun disminuirContador(){
        if(contador>1){
            val valorC=reservaBinding.edtCantidad.text.toString().toInt()
            contador=valorC
            contador--
        }

        reservaBinding.edtCantidad.setText(contador.toString())
    }





  /*  private fun generatePdf() {
        // Verificar si tenemos permiso de escritura
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Si no tenemos el permiso, solicitarlo al usuario
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_CODE)
        } else {

            createPdf()
        }
    }

   */

   /* private fun createPdf() {
        try {
            // Obtener la ruta del directorio de descargas
            val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

            // Crear el archivo PDF
            val file = File(directory, "comprobante.pdf")
            val fos = FileOutputStream(file)
            val writer = PdfWriter(fos)
            val pdf = PdfDocument(writer)
            val document = com.itextpdf.layout.Document(pdf)

            // Agregar contenido al PDF
            val titulo=Paragraph("ANDROPE INN")
            titulo.setTextAlignment(TextAlignment.CENTER)
            titulo.setFontSize(40f)

            val local= Paragraph(nombre_local.text.toString())
            local.setTextAlignment(TextAlignment.CENTER)
            local.setFontSize(20f)

            val cantidad= Paragraph("Cantidad de personas: " + cantidad.text.toString())
            cantidad.setTextAlignment(TextAlignment.CENTER )
            cantidad.setFontSize(15f)


            val metodo= Paragraph("Método de pago: $metodoPago")
            metodo.setTextAlignment(TextAlignment.CENTER)
            metodo.setFontSize(15f)

            val backgroundColor: Color = DeviceRgb(255, 255, 204)
            document.setBackgroundColor(backgroundColor)

            document.add(titulo)
            document.add(local)
            document.add(cantidad)
            document.add(metodo)

            // Cerrar el documento
            document.close()

            // Mostrar mensaje de éxito
            Toast.makeText(this, "PDF generado correctamente en ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            // Manejar errores
            Toast.makeText(this, "Error al generar el PDF", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado, continuar con la generación del PDF
                createPdf()
            } else {
                // Permiso denegado, mostrar mensaje al usuario o tomar otra acción
                Toast.makeText(this, "Permiso de escritura en almacenamiento externo denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }


    */






}
