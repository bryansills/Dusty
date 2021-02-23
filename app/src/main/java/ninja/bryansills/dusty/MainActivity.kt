package ninja.bryansills.dusty

import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import ninja.bryansills.dusty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginWebView.settings.javaScriptEnabled = true
        binding.loginWebView.addJavascriptInterface(WebViewJavascriptInterface(), "dusty")
        binding.loginWebView.webViewClient = WebViewClient()
        binding.loginWebView.loadUrl("https://dusty-server.herokuapp.com/start")
    }
}

class WebViewJavascriptInterface {
    @JavascriptInterface
    fun passToken(accessToken: String, refreshToken: String, expiresIn: Int) {
        Log.d("BLARG", "$accessToken\n$refreshToken\n$expiresIn")
    }
}