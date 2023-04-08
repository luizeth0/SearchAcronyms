package com.challenge.acronyms.rest

import com.challenge.acronyms.model.AcronymResponseItem
import com.challenge.acronyms.utils.FailureResponse
import com.challenge.acronyms.utils.NullResponse
import com.challenge.acronyms.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AcronymRepository {
    suspend fun getMeaning(acronym: String): Flow<UIState<List<AcronymResponseItem>>>
}

class AcronymRepositoryImpl @Inject constructor(
    private val acronymAPI: AcronymAPI
): AcronymRepository{

    override suspend fun getMeaning(acronym: String): Flow<UIState<List<AcronymResponseItem>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = acronymAPI.getAcronym(acronym)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                }?: throw NullResponse()
            } else throw FailureResponse(response.body()?.toString())
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}