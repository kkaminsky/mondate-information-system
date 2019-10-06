package com.kkaminsky.mondateinformationsystem.model

import javax.persistence.*


@Entity
@Table(name="file",schema = "public")
class FileEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "file_name")
    var fileName: String? = null

    @Column(name = "text")
    var text: String = ""

    @Column(name = "level")
    var level: Int? = null

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "owner_id",nullable = false)
    var owner: UserEntity? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileEntity

        if (id != other.id) return false
        if (fileName != other.fileName) return false
        if (text != other.text) return false
        if (level != other.level) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (fileName?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (level ?: 0)
        return result
    }
}