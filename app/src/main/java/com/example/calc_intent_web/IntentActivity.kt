package com.example.calc_intent_web

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class IntentActivity : AppCompatActivity() {
    lateinit var buttonsms: Button
    lateinit var buttoncall: Button
    lateinit var buttoncamera: Button
    lateinit var buttonmpesa: Button
    lateinit var buttondial: Button
    lateinit var buttonshare: Button
    lateinit var buttonemail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        buttonsms = findViewById(R.id.Btn_Sms)
        buttoncall = findViewById(R.id.Btn_Call)
        buttoncamera = findViewById(R.id.Btn_Camera)
        buttonmpesa = findViewById(R.id.Btn_Mpesa)
        buttondial = findViewById(R.id.Btn_Dial)
        buttonshare = findViewById(R.id.Btn_Share)
        buttonemail = findViewById(R.id.Btn_Email)

        buttonsms.setOnClickListener {
            val uri = Uri.parse("smsto:070689483")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("Hello", "Good morning")
            startActivity(intent)
        }

        buttoncall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254511812660"))

            if (ContextCompat.checkSelfPermission(
                    this@IntentActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@IntentActivity,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }


        }

        buttonemail.setOnClickListener {
            val emailintent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("Ibrahim", "omoiibrahim@gmail.com", null))
            emailintent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailintent.putExtra(Intent.EXTRA_TEXT, "Body")
            startActivity(Intent.createChooser(emailintent,"send email...."))
        }

       buttoncamera.setOnClickListener {
            val takepic = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takepic, 1)
        }

        buttonmpesa.setOnClickListener {
            val simToolKitLaunchIntent = applicationContext.packageManager.getLaunchIntentForPackage("com.android.mpesa")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }

        buttondial.setOnClickListener {
            val nambari = "+2547693820"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nambari, null))
            startActivity(intent)
        }

        buttonshare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi, click to download")
            startActivity(shareIntent)
        }

    }
}