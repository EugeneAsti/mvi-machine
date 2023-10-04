package ru.aeyu.mvi_machine.mvi.data.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import ru.aeyu.mvi_machine.mvi.models.FormA1DataObject
import ru.aeyu.mvi_machine.mvi.data.repository.FormA1GetDataRepository

class GetDataUseCase(
    private val formA1GetDataRepository: FormA1GetDataRepository
) {

    suspend fun getData(id: Int): Flow<Result<FormA1DataObject>> =
        formA1GetDataRepository.getFormA1Data(id)
            .catch { throwable -> emit(Result.failure(throwable)) }

}