package com.shvatov.notes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YandexNotesApiApplication

fun main(args: Array<String>) {
	runApplication<YandexNotesApiApplication>(*args)
}
