package ru.s4nchez.randomcats.presentation.view.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import ru.s4nchez.randomcats.App
import ru.s4nchez.randomcats.R
import ru.s4nchez.randomcats.presentation.presenter.main.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private val LAST_URL_SAVE = "lastUrl"
    private var lastUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).dagger.inject(this)

        showProgress()
        presenter.bindView(this)
        presenter.loadCat()

        update_view.setOnClickListener { presenter.loadCat() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.removeView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_URL_SAVE, lastUrl)
    }

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun showUpdateButton() {
        update_view.visibility = View.VISIBLE
    }

    override fun hideUpdateButton() {
        update_view.visibility = View.GONE
    }

    override fun showCat(url: String) {
        lastUrl = url

        Picasso.get()
            .load(url)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .noPlaceholder()
            .into(photo_view, object : Callback {

                override fun onSuccess() {
                    hideProgress()
                    presenter.loadCatWithTimer()
                }

                override fun onError(e: Exception?) {
                    showCommonError()
                    hideProgress()
                    showUpdateButton()
                }
            })
    }

    override fun showCommonError() {
        Snackbar.make(findViewById<View>(android.R.id.content), R.string.common_error, Snackbar.LENGTH_SHORT).show()
    }
}
