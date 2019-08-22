package com.example.repositoriesandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repositoriesandroid.R
import com.example.repositoriesandroid.model.Repository
import kotlinx.android.synthetic.main.list_item_repository.view.*

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
    private var listRepository: MutableList<Repository> = mutableListOf()

    var itemListener: itemClickListener? = null


    interface itemClickListener {
        fun itemClick(beneficiario: Repository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = listRepository[position]
        holder.bindView(product)
        holder.let {
            it.itemView.setOnClickListener {
                itemListener?.itemClick(listRepository[position])
            }
        }

    }

    fun setListRepository(repositories: List<Repository>) {
        this.listRepository.clear()
        this.listRepository.addAll(repositories)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(repository: Repository) {
            val repositoryName = itemView.repositoty_item_name
            val repositoryFullname = itemView.lbl_fullname
            val repositoryOrganization = itemView.lbl_organization
            val repositoryLanguage = itemView.lbl_language


            repositoryName.text = repository.name
            repositoryFullname.text = repository.full_name
            repositoryOrganization.text = repository.owner.login
            repositoryLanguage.text = repository.language


        }
    }
}