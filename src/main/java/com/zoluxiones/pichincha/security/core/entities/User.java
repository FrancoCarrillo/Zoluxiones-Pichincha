package com.zoluxiones.pichincha.security.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "dbuser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String names;
    private String lastNames;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Set<String> getRolName() {
        Set<String> rolesName = new HashSet<>();

        roles.forEach(role -> {
            rolesName.add(role.getName());
        });

        return rolesName;
    }
}
