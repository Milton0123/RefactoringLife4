package com.example.refactoringlife4.model.service

import android.util.Log
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.example.refactoringlife4.utils.DataModal
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
            db.collection("Users").document(email).get().addOnCompleteListener { documentSnapshot ->
                if (documentSnapshot.isSuccessful) {
                    val existingUser = documentSnapshot.result?.data
                    if (existingUser != null) {
                        continuation.resume(
                            Result.error(
                                UserModelResponse(
                                    userName,
                                    password,
                                    email,
                                    "denied",
                                    DataModal.getEmailInUse(), true
                                ), "existing email",
                                Result.Status.ERROR_EMAIL_EXIST
                            )
                        )
                    } else {
                        db.collection("Users").document(email)
                            .set(
                                hashMapOf(
                                    "Email" to email,
                                    "Name" to userName,
                                    "pass" to password,
                                    "type" to "fullAccess",
                                    "newUser" to true
                                )
                            ).addOnCompleteListener { setUserTask ->
                                if (setUserTask.isSuccessful) {
                                    continuation.resume(
                                        Result.success(
                                            UserModelResponse(
                                                userName,
                                                password,
                                                email,
                                                "fullAccess",
                                                null, true
                                            ), "ok"
                                        )
                                    )
                                } else {
                                    continuation.resume(
                                        Result.error(
                                            UserModelResponse(
                                                userName,
                                                password,
                                                email,
                                                "denied",
                                                DataModal.getErrorGeneric(), true
                                            ), "ERROR",
                                            Result.Status.ERROR
                                        )
                                    )
                                }

                            }
                    }
                } else {
                    continuation.resume(
                        Result.error(
                            UserModelResponse(
                                userName,
                                password,
                                email,
                                "denied",
                                DataModal.getErrorGeneric(), true
                            ), "ERROR",
                            Result.Status.ERROR
                        )
                    )
                }
            }
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<UserModelResponse> {
        return suspendCoroutine { continuation ->
            db.collection("Users").document(email).get().addOnCompleteListener { documentSnapshot ->
                if (documentSnapshot.isSuccessful) {
                    val user = documentSnapshot.result?.data
                    if (user != null) {
                        val storedPassword = user["pass"] as? String
                        if (password == storedPassword) {
                            continuation.resume(
                                Result.success(
                                    UserModelResponse(
                                        "",
                                        password,
                                        email,
                                        "fullAccess",
                                        null,
                                        documentSnapshot.result.getBoolean("newUser") ?: true
                                    ), "ok"
                                )
                            )
                        } else {
                            continuation.resume(
                                Result.error(
                                    UserModelResponse(
                                        "",
                                        password,
                                        email,
                                        "denied",
                                        DataModal.getErrorPassword(),
                                        documentSnapshot.result.getBoolean("newUser") ?: true
                                    ), "ERROR",
                                    Result.Status.ERROR_PASSWORD
                                )
                            )
                        }
                    } else {
                        continuation.resume(
                            Result.error(
                                UserModelResponse(
                                    "",
                                    password,
                                    email,
                                    "denied",
                                    DataModal.getEmailNotExist(),
                                    documentSnapshot.result.getBoolean("newUser") ?: true
                                ), "ERROR",
                                Result.Status.EMAIL_DONT_EXIST
                            )
                        )
                    }
                } else {
                    continuation.resume(
                        Result.error(
                            UserModelResponse(
                                "",
                                password,
                                email,
                                "denied",
                                DataModal.getErrorGeneric(),
                                documentSnapshot.result.getBoolean("newUser") ?: true
                            ), "ERROR",
                            Result.Status.ERROR
                        )
                    )
                }
            }
        }
    }

    suspend fun userUpdate(email: String): Result<UserModelResponse> {
        return suspendCoroutine { continuation ->
            db.collection("Users").document(email).update(
                hashMapOf<String, Any>(
                    "newUser" to false
                )
            ).addOnSuccessListener {
                continuation.resume(Result.success(UserModelResponse("", "", "", "", null, false)))
            }
                .addOnFailureListener {
                    // Manejar el error
                }
        }
    }

}
