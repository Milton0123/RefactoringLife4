package com.example.refactoringlife4.model.mapper

import com.example.refactoringlife4.model.dto.LoginModel
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.example.refactoringlife4.model.dto.Result

fun Result<UserModelResponse>.toLoginModel(): LoginModel {
    return LoginModel(this.status, this.data?.toModalDialog())
}

private fun UserModelResponse.toModalDialog(): LoginModel.ModalDialog {

    return LoginModel.ModalDialog(
        this.modelDialog!!.title,
        this.modelDialog!!.description,
        this.modelDialog!!.firstAction,
        this.modelDialog!!.secondAction
    )//validar estos campos
}