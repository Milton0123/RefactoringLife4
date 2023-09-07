package com.example.refactoringlife4.model.mapper

import com.example.refactoringlife4.model.dto.MembersModel
import com.example.refactoringlife4.model.dto.UsModel
import com.example.refactoringlife4.model.response.MembersResponse
import com.example.refactoringlife4.model.response.UsResponse

fun UsResponse.toUsModel(state: Boolean): UsModel {
    return UsModel(this.imageUs, state)
}

fun MembersResponse.toUsModel(state: Boolean): MembersModel {
    return MembersModel(this.imageMembers, state)
}
