package mentees.jamilxt.borrowmybook.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mentees.jamilxt.borrowmybook.constant.AppConstant;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(columnDefinition = "uniqueidentifier", nullable = false, updatable = false)
    @Type(type = "uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    private boolean deleted;

    @PrePersist
    void onCreate() {
        this.createdAt = OffsetDateTime.now();
        if (this.createdBy == null) {
            this.createdBy = AppConstant.ANONYMOUS_USER;
        }
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
