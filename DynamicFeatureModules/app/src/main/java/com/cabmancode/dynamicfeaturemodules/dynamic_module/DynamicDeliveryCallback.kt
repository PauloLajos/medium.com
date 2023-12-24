package com.cabmancode.dynamicfeaturemodules.dynamic_module

interface DynamicDeliveryCallback {
    fun onDownloading()
    fun onDownloadCompleted()
    fun onInstallSuccess()
    fun onFailed(errorMessage: String)
}