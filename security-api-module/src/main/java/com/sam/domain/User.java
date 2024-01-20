package com.sam.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity(name = "security_user")
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "firstName")
    private String firstName;

    @NonNull
    @Column(name = "lastName")
    private String lastName;

    public User() {
    }

    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))


    private List<Role> roles;
     */

    @Override
    public String toString() {
        return "User{id=%d, email='%s', password='%s', firstName='%s', lastName='%s'}"
                .formatted(id, email, password, firstName, lastName);
    }
}
