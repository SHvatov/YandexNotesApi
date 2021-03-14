package com.shvatov.notes.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

private const val TABLE_NAME = "note"

/**
 * @author shvatov
 */
@Entity
@Table(name = TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
class Note @JsonCreator constructor(
    /**
     * Id of the entity.
     */
    @get:Id
    @param:JsonProperty("id")
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    /**
     * Title of the note.
     */
    @param:JsonProperty("title")
    @get:Column(name = "title") var title: String? = null,
    /**
     * Content of the note.
     */
    @param:JsonProperty("content")
    @get:Column(name = "content") var content: String? = null
)