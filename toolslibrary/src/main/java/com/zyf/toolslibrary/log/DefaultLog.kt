package com.zyf.toolslibrary.log

import android.util.Log

class DefaultLog(private val tag: String): ILog {


    override fun logI(message: String?) {
        if (message.isNullOrEmpty()){
            return
        }
        Log.i(tag, message)
    }

    override fun logD(message: String?) {
        if (message.isNullOrEmpty()){
            return
        }
        Log.d(tag, message)
    }

    override fun logE(throwable: Throwable?) {
        logE(null, throwable)
    }

    override fun logE(message: String?, throwable: Throwable?) {
        if (throwable == null && message.isNullOrEmpty()){
            return
        }
        val messageBuilder = StringBuilder()
        if (!message.isNullOrEmpty()){
            messageBuilder.append(message)
        }
        if (!message.isNullOrEmpty() && throwable != null){
            messageBuilder.append(":")
        }
        if (throwable != null){
            messageBuilder.append(Log.getStackTraceString(throwable))
        }
        Log.e(tag, messageBuilder.toString())
    }


}