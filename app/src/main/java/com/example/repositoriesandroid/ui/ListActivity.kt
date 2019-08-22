package com.example.repositoriesandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repositoriesandroid.R
import com.example.repositoriesandroid.dao.RepositoryDao
import com.example.repositoriesandroid.model.Repository
import com.example.repositoriesandroid.network.RepositoryService
import com.example.repositoriesandroid.ui.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_lista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    val dao = RepositoryDao()
    lateinit var adapter : RepositoryAdapter
    private val APP_BAR_NAME = "Repositories List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = APP_BAR_NAME
        setContentView(R.layout.activity_lista)
        requestRepository()
        setupRecyclerView()
        setListenerRecycle()
    }

    private fun setListenerRecycle() {
        adapter.itemListener = object : RepositoryAdapter.itemClickListener {
            override fun itemClick(repository: Repository) {
                val intent = Intent(this@ListActivity, DetailViewActivity::class.java)
                intent.putExtra("repository", repository)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setListRepository(dao.getAll())
    }

    private fun requestRepository() {
        progressBar.visibility = View.VISIBLE
        Thread(Runnable {
            val call = RepositoryService.create().getRepositories("Android")
            call.enqueue(object : Callback<List<Repository>> {
                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Log.i("Fail", "connection fail " + t)
                }

                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    response.body().let {
                        if (it != null) {
                            dao.saveRepository(it)
                            adapter.setListRepository(dao.getAll())
                            progressBar.visibility = View.GONE
                        }
                    }
                }

            })
        }).start()
    }

    private fun setupRecyclerView() {
        repository_list_recyclerview.setHasFixedSize(true)
        repository_list_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter()
        repository_list_recyclerview.adapter = adapter
    }

}
