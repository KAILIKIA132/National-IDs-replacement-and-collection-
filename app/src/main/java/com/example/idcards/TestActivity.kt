package com.example.idcards

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Transaction.Function
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.hashMapOf as hashMapOf1

class TestActivity : AppCompatActivity() {

    private lateinit var db:FirebaseFirestore
    private lateinit var idEt: EditText
    private lateinit var certEt: EditText
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var replacement: Button
    lateinit var picView: ImageView
    lateinit var button1: Button
    private val pickImage = 100
    private val pickPicture = 1000
    private var imageUri: Uri? = null
    private var imageUri1: Uri? = null
    private var year = 0
    private var month = 0
    private var day = 0
    lateinit var textView: TextView
    private lateinit var calendar: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        db= FirebaseFirestore.getInstance()
        idEt = findViewById(R.id.idEt)
        certEt = findViewById(R.id.certEt)
        var replacement=findViewById<Button>(R.id.idBtnSendData)
        var textView=findViewById<TextView>(R.id.textView);
        textView.setOnClickListener { title = "KotlinApp"
            calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
            textView = findViewById(R.id.textView)
            val dialog = DatePickerDialog(this, { _, year, month, day_of_month ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month + 1
                calendar[Calendar.DAY_OF_MONTH] = day_of_month
                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                textView.text = sdf.format(calendar.time)
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
           // dialog.datePicker.minDate = calendar.timeInMillis
            calendar.add(Calendar.YEAR, -18)
            dialog.datePicker.maxDate = calendar.timeInMillis
            dialog.show()}
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.buttonLoadPicture)
        picView = findViewById(R.id.picView)
        button1 = findViewById(R.id.loadPicture)

        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
//            Toast.makeText(this,"hello",Toast.LENGTH_LONG
//            ).show()
        }
        button1.setOnClickListener {
            val internal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(internal, pickPicture)
//            Toast.makeText(this,"hello",Toast.LENGTH_LONG
//            ).show()
        }

        replacement.setOnClickListener {// Create a new user with a first and last name
            var idNo: String = idEt.text.toString()
            var certificate: String = certEt.text.toString()
        val user = hashMapOf1(
                "id" to idNo,
                "certificates" to certificate
//                "DATE OF BIRTH" to 1815
//                "FRONT IMAGE" to 1815
//                "BACK IMAGE" to 1815
//                "COUNTY" to 1815
//                "SUB COUNTY" to 1815
//                "WARD OFFICE" to 1815
            )
            if(TextUtils.isEmpty(idNo) || TextUtils.isEmpty(certificate)) {
                Toast.makeText(this@TestActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            }
            else{
                db.collection("replacement")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
                      //Toast.makeText(this, "Submitted Successfully",Toast.LENGTH_LONG).show()
            }
//
//            else{
//                db.collection(idNo, certificate).addOnCompleteListener(this, OnCompleteListener { task ->
//
////                    if(task.isSuccessful) {
////                        Toast.makeText(this, "Submitted Successfully", Toast.LENGTH_LONG).show()
////                      // val intent = Intent(this, Home::class.java)
////                       //startActivity(intent)
////                        finish()}
////                    }
////                    else {
////                      Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
////                   }
//                })
//            }

// Add a new document with a generated ID

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
        if (resultCode == RESULT_OK && requestCode == pickPicture) {
            imageUri1 = data?.data
            picView.setImageURI(imageUri1)
        }
    }





}

