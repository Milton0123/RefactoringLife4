package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.response.MembersResponse
import com.example.refactoringlife4.model.response.UsResponse

interface UsService {
    fun getMembers(): MembersResponse
    fun getUs(): UsResponse
}
