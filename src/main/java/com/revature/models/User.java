package com.revature.models;


import com.revature.util.RegexUtil;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

    // Marks as a required argument
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Pattern(regexp = RegexUtil.PASSWORD_REGEX)
    @Column(nullable = false)
    private String password;

    @Pattern(regexp = RegexUtil.EMAIL_REGEX)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "register_datetime", updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime registerDateTime;

    @Enumerated(EnumType.STRING)
    private UserRole role;


//    public static void main(String[] args) {
//        User user = new User(5);
//        System.out.println(user.getId());
//    }
}
