//import android.R
//import android.os.Build
//import android.os.Bundle
//import android.text.TextUtils
//import android.view.View
//import android.widget.*
//import androidx.annotation.RequiresApi
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import java.util.*
//
//class ReplacementInfo : AppCompatActivity() {
//    // creating variables for
//    // EditText and buttons.
//
//    private lateinit var id_Number: EditText
//    private lateinit var birth_Number: EditText
//    private lateinit var text_View: EditText
//    private lateinit var image_View: ImageView
//    private lateinit var pic_View: ImageView
//    private lateinit var planets_spinner: Spinner
//    private lateinit var sub_County: Spinner
//    private lateinit var ward_Office: Spinner
//
//    private var sendDatabtn: Button? = null
//
////    private lateinit var signUpBtn: Button
////    private lateinit var loginBtn: Button
////    private var employeeNameEdt: EditText? = null
////    private var employeePhoneEdt: EditText? = null
////    private var employeeAddressEdt: EditText? = null
//
//
//    // creating a variable for our
//    // Firebase Database.
//    var firebaseDatabase: FirebaseDatabase? =null
//
//    // creating a variable for our Database
//    // Reference for Firebase.
//    var databaseReference: DatabaseReference? = null
//
//    // creating a variable for
//    // our object class
//    var personInfo: ReplacementInfo? = null
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
//
//        // initializing our edittext and button
//
//        id_Number = findViewById(R.id.idNumber)
//        birth_Number = findViewById(R.id.birthNumber)
//        text_View = findViewById(R.id.textView)
//        image_View = findViewById(R.id.imageView)
//        pic_View = findViewById(R.id.picView)
//        planets_spinner = findViewById(R.id.planetsspinner)
//        sub_County = findViewById(R.id.subCounty)
//        ward_Office = findViewById(R.id.wardOffice)
//
//
//        // below line is used to get the
//        // instance of our FIrebase database.
//        firebaseDatabase = FirebaseDatabase.getInstance()
//
//        // below line is used to get reference for our database.
//        databaseReference = firebaseDatabase.getReference("ReplacementInfo")
//
//        // initializing our object
//        // class variable.
//        personInfo = ReplacementInfo()
//        sendDatabtn = findViewById(R.id.idBtnSendData)
//
//        // adding on click listener for our button.
//        sendDatabtn.setOnClickListener(View.OnClickListener {
//            // getting text from our edittext fields.
//            val id = id_Number.getText().toString()
//            val cert = birth_Number.getText().toString()
//            val dob = text_View.getText().toString()
//            val front = image_View.post().toString()
//            val back = pic_View.post().toString()
//            val  county= planets_spinner.autofillValue.toString()
//            val  sub= sub_County.autofillValue.toString()
//            val  ward= ward_Office.autofillValue.toString()
//
//
//            // below line is for checking weather the
//            // edittext fields are empty or not.
//            if (TextUtils.isEmpty(id) && TextUtils.isEmpty(cert) && TextUtils.isEmpty(dob) && TextUtils.isEmpty(front) && TextUtils.isEmpty(back) && TextUtils.isEmpty(county)&& TextUtils.isEmpty(sub) && TextUtils.isEmpty(ward)) {
//                // if the text fields are empty
//                // then show the below message.
//                Toast.makeText(this@TestActivity, "Please add some data.", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                // else call the method to add
//                // data to our database.
//                addDatatoFirebase(id, cert, dob, front, back, county, sub, ward)
//            }
//        })
//    }
//
//    private fun addDatatoFirebase(id: String, cert: String, dob: String,front: String, back: String, county: String,sub: String, ward: String) {
//        // below 3 lines of code is used to set
//        // data in our object class.
//        personInfo.setReplacementInfo(id)
//        personInfo.setReplacementInfo(cert)
//        personInfo.setReplacementInfo(dob)
//        personInfo.setReplacementInfo(front)
//        personInfo.setReplacementInfo(back)
//        personInfo.setReplacementInfo(county)
//        personInfo.setReplacementInfo(sub)
//        personInfo.setReplacementInfo(ward)
//
//        // we are use add value event listener method
//        // which is called with database reference.
//        databaseReference.addValueEventListener(object : ValueEventListener() {
//            fun onDataChange(snapshot: DataSnapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                databaseReference.setValue(personInfo)
//
//                // after adding this data we are showing toast message.
//                Toast.makeText(this@TestActivity, "data added", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // if the data is not added or it is cancelled then
//                // we are displaying a failure toast message.
//                Toast.makeText(this@TestActivity, "Fail to add data $error", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
//    }
//}