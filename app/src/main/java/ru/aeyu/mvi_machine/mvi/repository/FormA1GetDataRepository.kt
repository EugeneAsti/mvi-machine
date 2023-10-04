package ru.aeyu.mvi_machine.mvi.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.aeyu.mvi_machine.mvi.models.FormA1DataObject
import java.lang.Exception
import kotlin.random.Random

class FormA1GetDataRepository {

    fun getFormA1Data(id: Int): Flow<Result<FormA1DataObject>> = flow<Result<FormA1DataObject>> {
        delay(1500)
        val random = Random(System.nanoTime()).nextInt(150)
        if (random <= 25)
            throw Exception("Test Error!!!")
        else
            emit(
                Result.success(
                    FormA1DataObject(id, "Data Id: $id ", listOf(3, 40, 10))
                )
            )
    }.catch { throwable ->
        println("Ошибка in getFormA1Data ${throwable.localizedMessage}")
        emit(Result.failure(throwable))
    }
}