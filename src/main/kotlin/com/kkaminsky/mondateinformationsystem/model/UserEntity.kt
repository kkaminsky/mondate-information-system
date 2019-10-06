package com.kkaminsky.mondateinformationsystem.model

import javax.persistence.*


@Entity
@Table(name="user",schema = "public")
class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "username")
    var username: String? = null

    @Column(name = "password")
    var password: String? = null

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id",nullable = false)
    var role: RoleEntity? = null

    @Column(name = "level")
    var level: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (level != other.level) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (level ?: 0)
        return result
    }
}