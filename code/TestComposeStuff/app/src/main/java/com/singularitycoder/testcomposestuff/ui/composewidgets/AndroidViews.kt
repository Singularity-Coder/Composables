package com.singularitycoder.testcomposestuff.ui.composewidgets

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.Board
import com.singularitycoder.testcomposestuff.utils.Composables
import com.singularitycoder.testcomposestuff.utils.VerticalSpace

@Composable
fun ComposeAndroidViews() {
    Board(title = Composables.ANDROID_VIEW.value) {
        val state = remember { mutableStateOf(0) }

        AndroidView(factory = { it: Context ->
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600)
                webViewClient = object : WebViewClient() {
                    @TargetApi(Build.VERSION_CODES.N)
                    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean = false
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean = false
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) = Unit
                    override fun onPageFinished(view: WebView, url: String) = CookieManager.getInstance().flush()   // Clear Cookies
                    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, er: SslError?) = handler.proceed()   // Ignoring SSL error
                    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) = Unit
                }
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    blockNetworkLoads = false
                    allowContentAccess = true
                    allowFileAccess = true
                    webChromeClient = WebChromeClient()
                }
            }
        }, update = {
            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().setAcceptThirdPartyCookies(it, true)
            it.loadUrl("https://www.android.com/intl/en_in/")
        })

        8.dp.VerticalSpace()

        AndroidView(factory = { ctx ->
            ImageView(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300)
                val drawable = ContextCompat.getDrawable(ctx, R.drawable.pan8)
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageDrawable(drawable)
            }
        })

        AndroidView(
            factory = { ctx ->
                TextView(ctx).apply {
                    layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
            },
            modifier = Modifier.padding(top = 10.dp),
            update = { it.text = "You clicked the button ${state.value} times." }
        )

        AndroidView(
            factory = { ctx ->
                Button(ctx).apply {
                    text = "Old Android Button"
                    layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    setOnClickListener { state.value++ }
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeAndroidViewsPreview() = ComposablesApp { ComposeAndroidViews() }