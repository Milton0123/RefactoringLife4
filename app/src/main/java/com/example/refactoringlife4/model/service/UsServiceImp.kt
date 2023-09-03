package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.response.MembersResponse
import com.example.refactoringlife4.model.response.UsResponse

class UsServiceImp : UsService {

    private val linksMembers: List<String> = listOf(
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link"
    )

    private val linksUs = listOf(
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link",
        "https://drive.google.com/file/d/1gBl-DT_cRb6X0vFIvyGiTbJkksweADEI/view?usp=drive_link"
    )

    override fun getMembers(): MembersResponse {
        return MembersResponse(linksMembers, "success")
    }

    override fun getUs(): UsResponse {
        return UsResponse(linksUs, "success")
    }

}
