package com.alekseysamoylov.social.entity

import javax.persistence.*


@Entity
@Table(name = "social_user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    lateinit var username: String
    lateinit var password: String

}
