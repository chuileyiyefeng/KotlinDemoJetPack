package com.example.kotlindemojetpack.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemojetpack.utils.logE
import kotlinx.coroutines.*

/**
 * 扩展ViewModel对网络请求异常捕获及正常数据回调
 */
fun ViewModel.requestScope(
    block: suspend CoroutineScope.() -> Unit,
    onError: (String) -> Unit = {},
) {
    viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            run {
                val baseErr = ExceptionUtil.catchException(throwable)
                logE("执行异常捕获->${baseErr.msg} ${baseErr.result}")
                onError(baseErr.msg)
            }
        }
    ) {
        block()
    }

}
