package com.test.faurecia.userCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UserCaseBase<I, O>() {

    suspend operator fun invoke(input: I) = withContext(Dispatchers.Default) {
     action(input)
    }

    protected abstract fun action(input: I): O

}