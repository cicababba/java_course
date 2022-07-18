package com.example.database.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private double balance;

    private Date lastAccess;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Cart> carts;//orders
}
