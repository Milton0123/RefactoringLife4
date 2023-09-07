package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.dto.MembersModel
import com.example.refactoringlife4.model.dto.UsModel
import com.example.refactoringlife4.model.mapper.toUsModel
import com.example.refactoringlife4.model.repository.UsRepository

class UsUseCase(private val usRepository: UsRepository = UsRepository()) {

    suspend fun invokeUs(): UsModel {
        val call = usRepository.getUs()
        return call.toUsModel(true)
    }

    suspend fun invokeMembers(): MembersModel {
        val call = usRepository.getMembers()
        return call.toUsModel(true)
    }
}
