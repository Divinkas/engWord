package com.divinkas.app.words.helper.ext

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

//fun <T> Continuation<Result<T>>.resumeWithError(errorDetails: TaskErrorDetails) {
//    resumeWithError("", errorDetails)
//}

//@SuppressWarnings("unused")
//fun <T> Continuation<Result<T>>.resumeWithError(taskId: String, errorDetails: TaskErrorDetails) {
//    resume(Result(null, WalletSubscriptions.convertToWalletError(errorDetails)))
//}

//fun <T> Continuation<Result<T>>.resumeWithError(walletError: WalletError) {
//    resume(Result(null, walletError))
//}

//fun Continuation<SimpleResult>.resumeSimpleWithError(taskId: String, errorDetails: TaskErrorDetails) {
//    resume(SimpleResult(WalletSubscriptions.convertToWalletError(errorDetails)))
//}

//fun <T> Continuation<Result<T>>.resumeWithResult(data: T?) {
//    resumeWithResult("", data)
//}

//fun <T> Continuation<Result<T>>.resumeWithResult(taskId: String, data: T?) {
//    resume(Result(data, null))
//}

//suspend fun <T> suspendWork(operation: (continuation: Continuation<Result<T?>>) -> Unit): Result<T?> =
//    suspendCoroutine { operation(it) }

inline fun <T> doWorkAsync(
    crossinline operation: (continuation: Continuation<Result<T>>) -> Unit
): Deferred<Result<T?>> {
    return GlobalScope.async {
        return@async suspendCoroutine<Result<T?>> {
            operation(it)
        }
    }
}

//inline fun doSimpleWorkAsync(
//    crossinline operation: (continuation: Continuation<SimpleResult>) -> Unit
//): Deferred<SimpleResult> {
//    return GlobalScope.async {
//        return@async suspendCoroutine<SimpleResult> {
//            operation(it)
//        }
//    }
//}