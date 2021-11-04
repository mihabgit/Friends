package com.imran.friends.ui.activity

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.imran.friends.R
import com.imran.friends.ui.model.Result
import com.imran.friends.util.Constants
import com.squareup.picasso.Picasso
import android.widget.Toast

import android.content.Intent
import android.net.Uri
import androidx.appcompat.widget.Toolbar


class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar()

        val result = intent.getParcelableExtra<Result>(Constants.FRIEND_DATA)

        val ivProfilePicture = findViewById<ImageView>(R.id.ivProfilePicture)
        val tvFullName = findViewById<TextView>(R.id.tvFullName)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val tvCityStateCountry = findViewById<TextView>(R.id.tvCityStateCountry)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)


        // Set the Friend's details
        Picasso.get().load(result?.picture?.large).into(ivProfilePicture)

        val fullName = result?.name?.first + " " + result?.name?.last
        tvFullName.text = "Full Name: $fullName"

        val streetNumber = result?.location?.street?.number.toString()
        val streetName = result?.location?.street?.name
        tvAddress.text = "Address: $streetNumber $streetName"

        val city = result?.location?.city
        val state = result?.location?.state
        val country = result?.location?.country
        tvCityStateCountry.text = "City: $city, State: $state, Country: $country"

        val email = result?.email
        tvEmail.text = "Email: $email"

        val phone = result?.phone
        tvPhone.text = "Cell Phone: $phone"

        tvEmail.setOnClickListener { openEmailApp() }
    }

    private fun openEmailApp() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "your_email"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject")
            intent.putExtra(Intent.EXTRA_TEXT, "your_text")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "There is no email client installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}