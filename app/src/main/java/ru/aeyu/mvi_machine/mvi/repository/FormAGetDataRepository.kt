package ru.aeyu.mvi_machine.mvi.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.aeyu.mvi_machine.mvi.models.FormADataObject
import java.lang.Exception
import kotlin.random.Random

class FormAGetDataRepository {

    fun getFormA1Data(id: Int): Flow<Result<FormADataObject>> = flow<Result<FormADataObject>> {
        delay(1500)
        val random = Random(System.nanoTime()).nextInt(150)
        if (random <= 25)
            throw Exception("Test Error!!!")
        else
            emit(
                Result.success(
                    FormADataObject(id, "Data Id: $id ", listOf(3, 40, 10))
                )
            )
    }.catch { throwable ->
        println("Ошибка in getFormA1Data ${throwable.localizedMessage}")
        emit(Result.failure(throwable))
    }
}