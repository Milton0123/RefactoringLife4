package com.example.refactoringlife4.model.dataSource

import com.example.refactoringlife4.model.response.MembersResponse
import com.example.refactoringlife4.model.response.UsResponse
import com.example.refactoringlife4.model.service.UsServiceImp

class UsDataSource(private val UsService: UsServiceImp = UsServiceImp()) {

    suspend fun getMembers(): MembersResponse {
        return UsService.getMembers()
    }

    suspend fun getUs(): UsResponse {
        return UsService.getUs()
    }
}
