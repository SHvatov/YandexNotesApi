package com.shvatov.notes.service.impl

import com.shvatov.notes.dao.NoteRepository
import com.shvatov.notes.model.Note
import com.shvatov.notes.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import javax.persistence.EntityNotFoundException
import javax.validation.constraints.Min
import kotlin.math.min

/**
 * @author shvatov
 */
@Validated
@Service
class NoteServiceImpl @Autowired constructor(
    private val repository: NoteRepository
) : NoteService {
    @Min(1)
    @Value("\${config.content-symbols-to-use}")
    val contentSymbolsToUse: Int? = null

    override fun create(title: String?, content: String?): Note {
        return repository.save(
            prepareEntity(
                title = title,
                content = content
            )
        )
    }

    override fun update(id: Long, title: String?, content: String?): Note {
        val existingEntity = repository.findById(id).orElseThrow {
            EntityNotFoundException("Could not find entity by id: $id")
        }

        return repository.save(
            prepareEntity(
                existingEntity.id,
                title,
                content
            )
        )
    }

    override fun findAll(): List<Note> =
        repository.findAll()

    override fun findById(id: Long): Note? =
        repository.findById(id).orElseThrow {
            EntityNotFoundException("Could not find entity by id: $id")
        }

    override fun findAllByQuery(query: String): List<Note> {
        return if (query.isNotBlank()) {
            return repository.findByTitleContaining(query) + repository.findByContentContaining(query)
        } else emptyList()
    }

    override fun delete(id: Long) {
        val existingEntity = repository.findById(id).orElseThrow {
            EntityNotFoundException("Could not find entity by id: $id")
        }
        repository.deleteById(existingEntity.id!!)
    }

    private fun prepareEntity(id: Long? = null, title: String?, content: String?): Note {
        if (content.isNullOrBlank()) {
            throw IllegalArgumentException("Content is an empty string or absent")
        }

        return Note(
            id = id,
            title = title ?: content.substring(0..min(contentSymbolsToUse!!, content.length)),
            content = content
        )
    }
}


