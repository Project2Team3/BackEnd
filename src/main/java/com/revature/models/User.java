package com.revature.models;


import com.revature.util.RegexUtil;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @Length(min=2)
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*d)[A-Za-zd]{8,}$")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int points;



//    @Pattern(regexp = RegexUtil.EMAIL_REGEX)
//    @Column(nullable = false, unique = true)
//    private String email;

//    @Column(name = "register_datetime", updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
//    private LocalDateTime registerDateTime;

//    @Enumerated(EnumType.STRING)
//    private UserRole role;



//    public static void main(String[] args) {
//        User user = new User(5);
//        System.out.println(user.getId());
//    }
}
