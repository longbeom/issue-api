package com.longbeom.userservice.service

import com.longbeom.userservice.domain.entity.User
import com.longbeom.userservice.domain.repository.UserRepository
import com.longbeom.userservice.exception.UserExistsException
import com.longbeom.userservice.model.SignUpRequest
import com.longbeom.userservice.utils.BCryptUtils
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    suspend fun signUp(signUpRequest: SignUpRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserExistsException()
            }
            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username,
            )
            userRepository.save(user)
        }
    }
}