package issueservice.web

import issueservice.config.AuthUser
import issueservice.model.CommentRequest
import issueservice.service.CommentService
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/issues/{issueId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ) = commentService.create(issueId, authUser.userId, authUser.username, request)
}