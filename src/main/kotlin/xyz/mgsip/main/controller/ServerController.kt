package xyz.mgsip.main.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import xyz.mgsip.main.model.ApiResult
import xyz.mgsip.main.model.Server
import xyz.mgsip.main.service.ServerService
import java.lang.module.ModuleDescriptor.Requires

@RestController
@RequestMapping("/api/server")
class ServerController(private val serverService: ServerService) {


    @PostMapping
    fun getServerList():ResponseEntity<List<Server>>{

        val servers:List<Server> = serverService.getServerList()

        return ResponseEntity(servers,HttpStatus.OK)
    }

    @PutMapping
    fun putServer(@RequestPart(required = false) multipartFile: MultipartFile?,
                  @RequestPart server:Server ):ResponseEntity<String>?{

        val apiResult:ApiResult = serverService.putServer(multipartFile,server)

        return ResponseEntity(apiResult.message,apiResult.status)
    }

    @DeleteMapping
    fun deleteServer(@RequestParam serverId:Int):ResponseEntity<String>{
        serverService.deleteServer(serverId)

        return ResponseEntity("삭제가 완료되었습니다.",HttpStatus.OK)
    }
}