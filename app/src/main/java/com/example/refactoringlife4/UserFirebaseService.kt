package com.example.refactoringlife4

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
    ): FireBaseResponse<UserModel> {
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
                            FireBaseResponse.success(
                                UserModel(
                                    userName,
                                    password, email, "fullAccess"
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
                                    FireBaseResponse.error(
                                        UserModel(
                                            userName,
                                            password, email, "denied"
                                        ), "existing email",
                                        FireBaseResponse.Status.ERROR_EMAIL_EXIST
                                    )
                                )
                            }
                        } catch (networkException: FirebaseNetworkException) {
                            continuation.resume(
                                FireBaseResponse.error(
                                    UserModel(
                                        userName,
                                        password, email, "denied"
                                    ), "connection lost",
                                    FireBaseResponse.Status.ERROR
                                )
                            )
                        } catch (e: Exception) {
                            continuation.resume(
                                FireBaseResponse.error(
                                    UserModel(
                                        userName,
                                        password, email, "denied"
                                    ), "ERROR",
                                    FireBaseResponse.Status.ERROR
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
    ): FireBaseResponse<UserModel> {
        return suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        continuation.resume(
                            FireBaseResponse.success(
                                UserModel(
                                    "",
                                    password, email, "fullAccess"
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
                                    FireBaseResponse.error(
                                        UserModel(
                                            "",
                                            password, email, "denied"
                                        ), "ERROR",
                                        FireBaseResponse.Status.EMAIL_DONT_EXIST
                                    )
                                )
                            }
                        } catch (invalidCredentialsException: FirebaseAuthInvalidCredentialsException) {
                            val errorCode =
                                invalidCredentialsException.errorCode
                            if (errorCode == "ERROR_WRONG_PASSWORD") {
                                continuation.resume(
                                    FireBaseResponse.error(
                                        UserModel(
                                            "",
                                            password, email, "denied"
                                        ), "ERROR",
                                        FireBaseResponse.Status.ERROR_PASSWORD
                                    )
                                )
                            }
                        } catch (invalidCredentialsException: FirebaseNetworkException) {
                            continuation.resume(
                                FireBaseResponse.error(
                                    UserModel(
                                        "",
                                        password, email, "denied"
                                    ), "ERROR",
                                    FireBaseResponse.Status.ERROR
                                )
                            )
                        } catch (e: Exception) {
                            continuation.resume(
                                FireBaseResponse.error(
                                    UserModel(
                                        "",
                                        password, email, "denied"
                                    ), "ERROR",
                                    FireBaseResponse.Status.ERROR
                                )
                            )
                        }
                    }
                }
        }
    }
}
