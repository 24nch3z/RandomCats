package ru.s4nchez.randomcats.presentation.view.main

interface MainView {
    fun showCat(url: String)
    fun showProgress()
    fun hideProgress()
    fun showPhoto()
    fun hidePhoto()
    fun showUpdateButton()
    fun hideUpdateButton()
    fun showCommonError()
}