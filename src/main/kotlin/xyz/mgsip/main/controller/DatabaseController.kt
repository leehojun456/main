package xyz.mgsip.main.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.mgsip.main.model.Database
import xyz.mgsip.main.service.DatabaseService
import xyz.mgsip.main.service.ServerService

@RestController
@RequestMapping("/api/database")
class DatabaseController(private val databaseService: DatabaseService) {

    @PostMapping
    fun dbConnectStatus(@RequestBody database: Database): ResponseEntity<Boolean>
    {
        val result:Boolean = databaseService.dbConnectStatus(database)
        return ResponseEntity(result, HttpStatus.OK)
    }
}