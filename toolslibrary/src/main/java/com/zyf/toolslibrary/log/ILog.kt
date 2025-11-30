package com.zyf.toolslibrary.log

interface ILog {

    fun logI(message: String?)

    fun logD(message: String?)

    fun logE(throwable: Throwable?)

    fun logE(message: String?, throwable: Throwable?)
}