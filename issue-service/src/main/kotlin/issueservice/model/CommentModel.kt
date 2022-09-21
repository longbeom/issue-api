package issueservice.model

import issueservice.domain.Comment

data class CommentRequest(
    val body: String,
)

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val body: String,
    val username: String,
)

// invoke 방식 대신 확장함수 사용
fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    body = body,
    username = username,
)