package com.example.refactoringlife4.model.repository

import android.content.Context
import com.example.refactoringlife4.model.dataSource.UsDataSource
import com.example.refactoringlife4.model.response.MembersResponse
import com.example.refactoringlife4.model.response.UsResponse

class UsRepository(private val usDataSource: UsDataSource = UsDataSource()) {

    suspend fun getUs(): UsResponse{
        return usDataSource.getUs()
    }

    suspend fun getMembers(): MembersResponse{
        return usDataSource.getMembers()
    }
}