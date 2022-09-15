package issueservice.domain.issue

import issueservice.domain.BaseEntity
import issueservice.domain.enums.IssuePriority
import issueservice.domain.enums.IssueStatus
import issueservice.domain.enums.IssueType
import javax.persistence.*

@Table
@Entity
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var userId: Long,

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,
) : BaseEntity()