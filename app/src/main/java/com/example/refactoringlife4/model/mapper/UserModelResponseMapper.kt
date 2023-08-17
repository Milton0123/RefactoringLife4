package com.example.refactoringlife4.model.mapper

import android.util.Log
import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.example.refactoringlife4.model.dto.Result

fun Result<UserModelResponse>.toUserModel(): UserModel {
    return UserModel(this.status, this.data?.toModalDialog())
}

private fun UserModelResponse.toModalDialog(): UserModel.ModalDialog? {

    return this.modelDialog?.let {
        Log.i("tag","fail")
        UserModel.ModalDialog(
        it.title,
        it.description,
        it.firstAction,
        it.secondAction
    )
    }//validar estos campos
}
