package app.web.onboardgenix.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true, nullable = false)
    private String uid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Default
    @Column
    private boolean verified = false;

    /*
     * If you don't use CascadeType.MERGE you can't add a user in app startup using
     * CommandLineRunner you will get an error : detached entity passed to persist
     * OR just remove the CascadeType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role_id")
    private Role role;
}