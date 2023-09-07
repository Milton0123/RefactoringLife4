package com.example.refactoringlife4.model.response

data class UsResponse(
    val imageUs: List<String>,
    val statusUs: String
)

data class MembersResponse(
    val imageMembers: List<String>,
    val statusMembers: String
)
