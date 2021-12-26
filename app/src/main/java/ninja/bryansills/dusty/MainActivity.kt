package ninja.bryansills.dusty

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
                ) {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                settings.javaScriptEnabled = true
                                addJavascriptInterface(WebViewJavascriptInterface(), "dusty")
                                webViewClient = WebViewClient()
                                loadUrl("https://dusty-server.herokuapp.com/start")
                            }
                        }
                    )

                }
            }
        }
    }
}

class WebViewJavascriptInterface {
    @JavascriptInterface
    fun passToken(accessToken: String, refreshToken: String, expiresIn: Int) {
        Log.d("BLARG", "$accessToken\n$refreshToken\n$expiresIn")
    }
}