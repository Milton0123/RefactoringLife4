package com.example.refactoringlife4.utils

import com.example.refactoringlife4.model.dto.UserModelResponse

object DataModal {
    fun getEmailNotExist() = UserModelResponse.ModalDialog(
        title = "Email no existe",
        description = "El Email ingresado no existe, quiere ir a register?",
        firstAction = "Registrar",
        secondAction = "Cerrar"
    )

    fun getErrorPassword() = UserModelResponse.ModalDialog(
        title = "Error en contraseña",
        description = "Su contraseña o email estan incorrectos",
        firstAction = "",
        secondAction = "Cerrar"
    )

    fun getEmailInUse() = UserModelResponse.ModalDialog(
        title = "Email ya en uso",
        description = "Su Email ya esta registrado, quiere ir a login?",
        firstAction = "Login",
        secondAction = "Cerrar"
    )

    fun getFailConnection() = UserModelResponse.ModalDialog(
        title = "No hay conexion",
        description = "No se pudo conectar a internet",
        firstAction = "",
        secondAction = "Cerrar"
    )

    fun getErrorGeneric() = UserModelResponse.ModalDialog(
        title = "Algo salio mal",
        description = "Ocurrio un error desconocido",
        firstAction = "",
        secondAction = "Cerrar"
    )
}
