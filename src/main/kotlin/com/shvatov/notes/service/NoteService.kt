package com.shvatov.notes.service

import com.shvatov.notes.model.Note

/**
 * @author shvatov
 */
interface NoteService {
    fun create(title: String?, content: String?): Note

    fun update(id: Long, title: String?, content: String?): Note

    fun findAll(): List<Note>

    fun findById(id: Long): Note?

    fun findAllByQuery(query: String): List<Note>

    fun delete(id: Long)
}