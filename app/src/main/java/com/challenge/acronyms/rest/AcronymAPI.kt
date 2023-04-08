package com.challenge.acronyms.rest

import com.challenge.acronyms.model.AcronymResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymAPI {

    @GET(PATH)
    suspend fun getAcronym(
        @Query("sf") sf: String?
    ): Response<List<AcronymResponseItem>>



    companion object {
        //http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        const val PATH = "dictionary.py"
    }
}
