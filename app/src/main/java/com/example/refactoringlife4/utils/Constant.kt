package com.example.refactoringlife4.utils

import com.example.refactoringlife4.model.dto.UserModelResponse

object Constant {
    val EMAIL_DONT_EXIST = UserModelResponse.ModalDialog(
        title = "Email no existe",
        description = "El Email ingresado no existe, quiere ir a register?",
        firstAction = "Registrar",
        secondAction = "Cerrar"
    )
    val ERROR_WRONG_PASSWORD = UserModelResponse.ModalDialog(
        title = "Error en contraseña",
        description = "Su contraseña o email estan incorrectos",
        firstAction = "",
        secondAction = "Cerrar"
    )
    val ERROR_EMAIL_ALREADY_IN_USE = UserModelResponse.ModalDialog(
        title = "Email ya en uso",
        description = "Su Email ya esta registrado, quiere ir a login?",
        firstAction = "Login",
        secondAction = "Cerrar"
    )
    val ERROR_LOST_CONNECTION = UserModelResponse.ModalDialog(
        title = "No hay conexion",
        description = "No se pudo conectar a internet",
        firstAction = "",
        secondAction = "Cerrar"
    )
    val ERROR = UserModelResponse.ModalDialog(
        title = "Algo salio mal",
        description = "Ocurrio un error desconocido",
        firstAction = "",
        secondAction = "Cerrar"
    )
}
