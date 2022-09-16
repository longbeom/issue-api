package issueservice.web

import issueservice.config.AuthUser
import issueservice.domain.enums.IssueStatus
import issueservice.model.IssueRequest
import issueservice.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/issues")
@RestController
class IssueController(
    private val issueService: IssueService,
) {
    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ) = issueService.create(authUser.userId, request)

    @GetMapping
    fun getAll(
        authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
    ) = issueService.getAll(status)

    @GetMapping("/{id}")
    fun get(
        authUser: AuthUser,
        @PathVariable id: Long,
    ) = issueService.get(id)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(
        authUser: AuthUser,
        @PathVariable id: Long,
    ) {
        issueService.delete(id)
    }
}