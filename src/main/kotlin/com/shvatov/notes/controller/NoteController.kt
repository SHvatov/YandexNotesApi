package com.shvatov.notes.controller

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.shvatov.notes.model.Note
import com.shvatov.notes.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author shvatov
 */
@RestController(value = "notes")
class NoteController @Autowired constructor(
    private val service: NoteService
) {
    @PostMapping(value = ["/notes"])
    fun createNote(@RequestBody vo: NoteVo): ResponseEntity<Note> {
        val entity = service.create(vo.title, vo.content)
        return ResponseEntity.status(HttpStatus.CREATED).body(entity)
    }

    @GetMapping(value = ["/notes"])
    fun getNotes(@RequestParam(value = "query", required = false) query: String?): ResponseEntity<List<Note>> {
        val entities = if (query != null) {
            service.findAllByQuery(query)
        } else service.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(entities)
    }

    @GetMapping(value = ["/notes/{id}"])
    fun getNoteById(@PathVariable("id") id: Long): ResponseEntity<Note> {
        val entity = service.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(entity)
    }

    @PutMapping(value = ["/notes/{id}"])
    fun updateNote(@PathVariable("id") id: Long, @RequestBody vo: NoteVo): ResponseEntity<Note> {
        val entity = service.update(id, vo.title, vo.content)
        return ResponseEntity.status(HttpStatus.OK).body(entity)
    }

    @DeleteMapping(value = ["/notes/{id}"])
    fun deleteNote(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class NoteVo @JsonCreator constructor(
        @param:JsonProperty("title") val title: String? = null,
        @param:JsonProperty("content") val content: String? = null
    )
}