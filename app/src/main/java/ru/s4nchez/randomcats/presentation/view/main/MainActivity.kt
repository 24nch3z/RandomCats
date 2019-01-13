package ru.s4nchez.randomcats.presentation.view.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import ru.s4nchez.randomcats.App
import ru.s4nchez.randomcats.R
import ru.s4nchez.randomcats.presentation.presenter.main.MainPresenter
import java.lang.Exception
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
        presenter.bindView(this)

        if (savedInstanceState?.containsKey(LAST_URL_SAVE) != null) {
            showCat(savedInstanceState?.getString(LAST_URL_SAVE))
        } else {
            presenter.loadCat()
        }

        photo_view.setOnClickListener { presenter.loadCat() }
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

    override fun showPhoto() {
        photo_view.visibility = View.VISIBLE
    }

    override fun hidePhoto() {
        photo_view.visibility = View.GONE
    }

    override fun showCat(url: String) {
        lastUrl = url
        Picasso.get()
            .load(url)
            .into(photo_view, object : Callback {

                override fun onSuccess() {
                    hideProgress()
                }

                override fun onError(e: Exception?) {

                }
            })
    }
}
