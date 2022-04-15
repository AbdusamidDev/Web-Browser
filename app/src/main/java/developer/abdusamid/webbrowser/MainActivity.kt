package developer.abdusamid.webbrowser

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

const val url1 =
    "https://docs.google.com/spreadsheets/d/1iudfce9NzsI7qyzE7V0vi7j-7zDFrXlOwjcHCTFhv2s/edit?usp=sharing"

class MainActivity : AppCompatActivity() {
    var url = "https://"
    lateinit var progressDialog: ProgressDialog

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        edt_url.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                web_view.loadUrl("$url${edt_url.text}")
            }
        })

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")

        web_view.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java", ReplaceWith("true"))
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("")
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_bar.visibility = View.VISIBLE
                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress_bar.visibility = View.INVISIBLE
                progressDialog.hide()
            }
        }
    }
}