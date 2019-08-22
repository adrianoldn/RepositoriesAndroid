package com.example.repositoriesandroid.ui

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.repositoriesandroid.R
import com.example.repositoriesandroid.model.Repository
import kotlinx.android.synthetic.main.activity_detail_view.*

class DetailViewActivity : AppCompatActivity() {

    var repository: Repository? = null
    private val APP_BAR_NAME = "Detail Repository"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupButtonBack()
        title = APP_BAR_NAME
        repository = intent.extras?.get("repository") as Repository
        setContentView(R.layout.activity_detail_view)
        setViews()
    }

    private fun setupButtonBack() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home
            -> {
                onBackPressed()
            }
            else -> {
            }
        }
        return true
    }

    private fun setViews() {
        if(repository != null) {
            txt_repository_name.text = repository?.name
            txt_desccription.text = repository?.description
            txt_fullname.text = repository?.full_name
            txt_id.text = repository?.id
            txt_language.text = repository?.language
            txt_organization.text = repository?.owner?.login
            txt_private.text = repository?.private.toString()
        }
    }
}
