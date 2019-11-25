package com.tom.demokotlin.login.entity

import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "users")
class LoginUserEntity : Serializable{
    @Id
    @Column(name = "id", nullable = false)
    var id:Int = 0

    @Column(name = "name", nullable = false)
    val name: String = ""

    @Column(name = "password", nullable = false)
    val password:String = ""

    @Column(name = "create_at", nullable = false)
    val create_at:Timestamp? = null


    @Column(name = "updated_at", nullable = false)
    val updated_at:Timestamp? = null
}