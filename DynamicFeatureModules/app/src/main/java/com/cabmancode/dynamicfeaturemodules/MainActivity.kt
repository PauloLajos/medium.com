package com.cabmancode.dynamicfeaturemodules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cabmancode.dynamicfeaturemodules.dynamic_module.DynamicModuleDownloadUtil
import com.cabmancode.dynamicfeaturemodules.ui.theme.DynamicFeatureModulesTheme

class MainActivity : ComponentActivity(), DynamicModuleListener {

    private val CUSTOMER_SUPPORT_DYNAMIC_MODULE = "customer_support"
    private lateinit var dynamicModuleDownloadUtil: DynamicModuleDownloadUtil
    private var logState = mutableStateOf("Activity Log:\n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dynamicModuleDownloadUtil = DynamicModuleDownloadUtil(baseContext, this)

        setContent {
            DynamicFeatureModulesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onDownloading() {
        logState.value += "${getCurrentTimestamp()}: Downloading...\n"
    }

    override fun onDownloadCompleted() {
        logState.value += "${getCurrentTimestamp()}: Module download completed.\n"
    }

    override fun onInstallSuccess() {
        logState.value += "${getCurrentTimestamp()}: Module install Success!\n"
        startCustomerSupportActivity()
    }

    override fun onFailed() {
        logState.value += "${getCurrentTimestamp()}: Module download or installation failed.\n"
    }

    private fun openCustomerSupportFeature() {
        if (dynamicModuleDownloadUtil.isModuleDownloaded(CUSTOMER_SUPPORT_DYNAMIC_MODULE)) {
            logState.value += "${getCurrentTimestamp()}: Module is already downloaded.\n"
            startCustomerSupportActivity()
        } else {
            dialogState.value = true
        }
    }

    private fun startCustomerSupportActivity() {
        val intent = Intent()
        intent.setClassName(
            "com.hellohasan.hasanerrafkhata",
            "com.hellohasan.customer_support.CustomerSupportActivity"
        )
        startActivity(intent)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DynamicFeatureModulesTheme {
        Greeting("Android")
    }
}