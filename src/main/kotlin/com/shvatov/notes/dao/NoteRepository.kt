package com.shvatov.notes.dao

import com.shvatov.notes.model.Note
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * @author shvatov
 */
@Repository
interface NoteRepository : JpaRepository<Note, Long> {
    fun findByTitleContaining(query: String): List<Note>

    fun findByContentContaining(query: String): List<Note>
}