package xyz.mgsip.main.service

import com.ibasco.agql.core.util.GeneralOptions
import com.ibasco.agql.protocols.valve.source.query.SourceQueryClient
import com.ibasco.agql.protocols.valve.source.query.SourceQueryOptions
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import xyz.mgsip.main.dao.ServerDao
import xyz.mgsip.main.model.ApiResult
import xyz.mgsip.main.model.Server
import xyz.mgsip.main.util.FileUploadUtil
import java.net.InetSocketAddress


interface ServerService {
    abstract fun getServerList(): List<Server>
    abstract fun putServer(multipartFile:MultipartFile?,server: Server): ApiResult
    abstract fun deleteServer(serverId: Int)
    abstract fun serverConnectStatus(serverIp: String, serverPort: Int): Boolean

}

@Service
class ServerServiceImpl(private val serverDao: ServerDao,
                         private val fileUploadUtil: FileUploadUtil):ServerService{

    private val logger = KotlinLogging.logger {}

    override fun getServerList(): List<Server> {

        val servers:List<Server> = serverDao.getServerList()
        for ((index, server) in servers.withIndex())
        {

            val objectKey:String? = server.previewImage
            server.previewImage = fileUploadUtil.getFile(objectKey)
            logger.info { server.previewImage }
        }
        return servers
    }

    @Transactional
    override fun putServer(multipartFile:MultipartFile?,server: Server): ApiResult {

        if(server.serverId == null)
        {
            serverDao.addServer(server)
            if(multipartFile != null)
            {
                val fileExtension = multipartFile.originalFilename?.substringAfterLast(".")
                val objectKey = "server/gamepreviewimage_${server.serverId}.$fileExtension"
                server.previewImage = objectKey
                serverDao.updateServerImage(server)
                fileUploadUtil.putFile(multipartFile,objectKey)
            }
            return ApiResult(message = "서버 정보 추가가 완료되었습니다.",
                HttpStatus.CREATED)
        }
        else
        {
            serverDao.updateServer(server)
            return ApiResult(message = "서버 정보 업데이트가 완료되었습니다.",
                HttpStatus.OK)
        }
    }

    override fun deleteServer(serverId: Int) {
        val server:Server = serverDao.getServer(serverId)

        logger.info { "조회 완료 ${server}" }

        if(server.previewImage != null)
        {
            val objectKey = server.previewImage
            fileUploadUtil.deleteFile(objectKey)
        }

        serverDao.deleteServer(serverId)
    }

    override fun serverConnectStatus(serverIp: String, serverPort: Int): Boolean {


        val sourceQueryOption = SourceQueryOptions.builder()
            .option(GeneralOptions.READ_TIMEOUT,100).build()

        val client = SourceQueryClient(sourceQueryOption)
        val address = InetSocketAddress(serverIp, serverPort)
        return try{
            client.getInfo(address).join()
            return true
        }catch (e: Exception){
            return false
        }

    }

}