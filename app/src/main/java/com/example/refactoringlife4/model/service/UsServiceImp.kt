package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.response.MembersResponse
import com.example.refactoringlife4.model.response.UsResponse

class UsServiceImp : UsService {

    private val linksMembers: List<String> = listOf(
        "https://i.ibb.co/qsm4WcP/Member-Jere.jpg",
        "https://i.ibb.co/BgwZW2b/Member-Emi.jpg",
        "https://i.ibb.co/x1TGJkQ/Member-Milton.jpg",
        "https://i.ibb.co/y5WDhfM/Member-Hector.jpg",
        "https://i.ibb.co/6mxhQrx/Member-erik.jpg",
        "https://i.ibb.co/vwHtBVc/Member-cristian.jpg"
    )

    private val linksUs = listOf(
        "https://i.ibb.co/GQPrWYR/Us-Jere-1.jpg",
        "https://i.ibb.co/Kx3Vg22/Us-Jere2.jpg",
        "https://i.ibb.co/yR9FP0g/Us-Emi1.jpg",
        "https://i.ibb.co/0JR982F/Us-Emi2.jpg",
        "https://i.ibb.co/TYxsGSn/Us-Milton1.jpg",
        "https://i.ibb.co/sbnJzQb/Us-Milton2.jpg",
        "https://i.ibb.co/mtHc5zt/Us-Hector1.jpg",
        "https://i.ibb.co/TKn2g6r/Us-Hector2.jpg",
        "https://i.ibb.co/B6yjfXB/Us-Erik1.jpg",
        "https://i.ibb.co/mF8Zv2F/Us-Erik2.jpg",
        "https://i.ibb.co/y0T2FZq/Us-Cristian1.jpg",
        "https://i.ibb.co/qJJr3Qf/Us-Cristian2.jpg"
    )

    override fun getMembers(): MembersResponse {
        return MembersResponse(linksMembers, "success")
    }

    override fun getUs(): UsResponse {
        return UsResponse(linksUs, "success")
    }

}
