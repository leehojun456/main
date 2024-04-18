package xyz.mgsip.main.model

import org.springframework.http.HttpStatus

data class ApiResult(
    var message:String,
    var status:HttpStatus
)
