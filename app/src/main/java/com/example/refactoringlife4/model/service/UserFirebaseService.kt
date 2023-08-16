package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserFirebaseService {
    private var db = FirebaseFirestore.getInstance()

    suspend fun register(
        email: String,
        userName: String,
        password: String
    ): Result<UserModelResponse> {
        return suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    email, password
                )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        db.collection("Users").document(email)
                            .set(
                                hashMapOf(
                                    "Email" to email,
                                    "Name" to userName,
                                    "pass" to password,
                                    "type" to "fullAccess"
                                )
                            )
                        continuation.resume(
                            Result.success(
                                UserModelResponse(
                                    userName,
                                    password, email, "fullAccess", null
                                ), "ok"
                            )
                        )

                    } else {
                        try {
                            throw it.exception!!
                        } catch (userCollisionException: FirebaseAuthUserCollisionException) {
                            val errorCode = userCollisionException.errorCode
                            if (errorCode == "ERROR_EMAIL_ALREADY_IN_USE") {
                                continuation.resume(
                                    Result.error(
                                        UserModelResponse(
                                            userName,
                                            password, email, "denied", null
                                        ), "existing email",
                                        Result.Status.ERROR_EMAIL_EXIST
                                    )
                                )
                            }
                        } catch (networkException: FirebaseNetworkException) {
                            continuation.resume(
                                Result.error(
                                    UserModelResponse(
                                        userName,
                                        password, email, "denied", null
                                    ), "connection lost",
                                    Result.Status.ERROR_LOST_CONNECTION
                                )
                            )
                        } catch (e: Exception) {
                            continuation.resume(
                                Result.error(
                                    UserModelResponse(
                                        userName,
                                        password, email, "denied", null
                                    ), "ERROR",
                                    Result.Status.ERROR
                                )
                            )
                        }
                    }
                }
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<UserModelResponse> {
        return suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        continuation.resume(
                            Result.success(
                                UserModelResponse(
                                    "",
                                    password, email, "fullAccess",null
                                ), "ok"
                            )
                        )
                    } else {
                        try {
                            throw it.exception!!
                        } catch (invalidUserException: FirebaseAuthInvalidUserException) {
                            val errorCode = invalidUserException.errorCode
                            if (errorCode == "ERROR_USER_NOT_FOUND") {
                                continuation.resume(
                                    Result.error(
                                        UserModelResponse(
                                            "",
                                            password, email, "denied",null
                                        ), "ERROR",
                                        Result.Status.EMAIL_DONT_EXIST
                                    )
                                )
                            }
                        } catch (invalidCredentialsException: FirebaseAuthInvalidCredentialsException) {
                            val errorCode =
                                invalidCredentialsException.errorCode
                            if (errorCode == "ERROR_WRONG_PASSWORD") {
                                continuation.resume(
                                    Result.error(
                                        UserModelResponse(
                                            "",
                                            password, email, "denied",null
                                        ), "ERROR",
                                        Result.Status.ERROR_PASSWORD
                                    )
                                )
                            }
                        } catch (invalidCredentialsException: FirebaseNetworkException) {
                            continuation.resume(
                                Result.error(
                                    UserModelResponse(
                                        "",
                                        password, email, "denied",null //se modifica , pasar el modalDialog
                                    ), "ERROR",
                                    Result.Status.ERROR_LOST_CONNECTION
                                )
                            )
                        } catch (e: Exception) {
                            continuation.resume(
                                Result.error(
                                    UserModelResponse(
                                        "",
                                        password, email, "denied",null
                                    ), "ERROR",
                                    Result.Status.ERROR
                                )
                            )
                        }
                    }
                }
        }
    }
}
