package com.longbeom.userservice.controller

import com.longbeom.userservice.model.*
import com.longbeom.userservice.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/users")
@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    suspend fun signUp(@RequestBody request: SignUpRequest) =
        userService.signUp(request)

    @PostMapping("/signin")
    suspend fun signIn(@RequestBody request: SignInRequest) : SignInResponse =
        userService.signIn(request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    suspend fun logout(@AuthToken token: String) {
        userService.logout(token)
    }

    @GetMapping("/me")
    suspend fun get(@AuthToken token: String) : MeResponse =
        MeResponse(userService.getByToken(token))

    @GetMapping("/{userId}/username")
    suspend fun getUsername(@PathVariable userId: Long) : Map<String, String> =
        mapOf("reporter" to userService.get(userId).username)
}