package com.example.repositoriesandroid.dao

import com.example.repositoriesandroid.model.Repository

class RepositoryDao {

    companion object{
        private val repositories = ArrayList<Repository>()
    }


    fun saveRepository(newRepositories: List<Repository>) {
        repositories.addAll(newRepositories)
    }

    fun getAll(): List<Repository> {
        return ArrayList(repositories)

    }

}