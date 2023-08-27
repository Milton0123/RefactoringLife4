package com.example.refactoringlife4.model.service
import android.util.Log
import com.example.refactoringlife4.model.response.DogsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import com.example.refactoringlife4.model.dto.Result
import retrofit2.converter.gson.GsonConverterFactory
import com.example.refactoringlife4.utils.CodesError.CODE_401
import com.example.refactoringlife4.utils.CodesError.CODE_404
import com.example.refactoringlife4.utils.CodesError.CODE_500

class DogServiceImp {

    private val url = "https://dog.ceo/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceImp = retrofit.create(DogServices::class.java)

    suspend fun getDogs(): Result<DogsResponse> {
        val call: Response<DogsResponse> = serviceImp.getDogs()
        Log.i("HTTP",call.code().toString())
        return when (call.code()) {
            200 -> {
                Result.success(call.body())
            }
            401 -> {
                Result.error(null,message = CODE_401, status = Result.Status.ERROR_CODE)
            }
            500 -> {
                Result.errorCode(message = CODE_500)
            }
            404 -> {
                Result.errorCode(message = CODE_404)
            }
            else -> {
                Result.errorCode(message = CODE_404)
            }
        }
    }
}
