package com.example.api.controller

import com.example.api.dto.response.ProfileInfoResponse
import com.example.api.dto.response.QuizResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("\${sigma.root-url}/profile")
internal class ProfileController {

    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER_READ', 'SCOPE_ADMIN_READ', 'SCOPE_OWNER_READ')")
    internal fun getOwnProfileInfo(): ResponseEntity<ProfileInfoResponse> {

        return ResponseEntity.ok(ProfileInfoResponse(true))  // TODO
    }

    @GetMapping("/quiz")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER_READ', 'SCOPE_ADMIN_READ', 'SCOPE_OWNER_READ')")
    internal fun getQuiz(): ResponseEntity<QuizResponse> {

        return ResponseEntity.ok(QuizResponse(emptyList()))  // TODO
    }

    @PostMapping("/quiz")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER_WRITE', 'SCOPE_ADMIN_WRITE', 'SCOPE_OWNER_WRITE')")
    internal fun answerQuiz(): ResponseEntity<QuizResponse> {

        return ResponseEntity.ok(QuizResponse(emptyList()))  // TODO
    }
}
