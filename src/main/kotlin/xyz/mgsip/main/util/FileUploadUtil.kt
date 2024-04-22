package xyz.mgsip.main.util




import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.net.URL
import java.nio.file.Path


interface FileUploadUtil {
    abstract fun getFile(objectKey: String?): String?
    abstract fun putFile(multipartFile: MultipartFile,objectKey:String)
    abstract fun deleteFile(objectKey: String?)


}
@Component
class  FileUploadUtilImpl(private val s3Client: S3Client): FileUploadUtil {

    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucket: String? = null

    private val logger = KotlinLogging.logger {}

    override fun getFile(objectKey: String?): String? {
        if(objectKey != null)
        {
            val url:URL = s3Client.utilities().getUrl { req -> req.key(objectKey).bucket(bucket) }
            return url.toString()
        }
        return null
    }

    override fun putFile(multipartFile: MultipartFile,objectKey:String) {
        logger.info { "파일 업로드 시작" }

        logger.info { "버킷 : $bucket" }


        val metadataVal = mutableMapOf<String, String>()
        metadataVal["Content-Type"] = multipartFile.contentType.toString()


        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(objectKey)
            .metadata(metadataVal)
            .build()

        s3Client.putObject(request, RequestBody.fromBytes(multipartFile.bytes))
    }

    override fun deleteFile(objectKey: String?) {

        logger.info { "파일 삭제 시작" }

        s3Client.deleteObject{req -> req.bucket(bucket)
            .key(objectKey)}
    }

    public fun uploadFile(filePaths: List<Path>, files: List<MultipartFile>) {

        logger.info { "파일 업로드 시작" }

        logger.info { "버킷 : $bucket" }
        // 파일 경로 리스트와 파일 리스트를 동시에 반복합니다.
        for ((index, filePath) in filePaths.withIndex()) {



            val file = files[index]


            val metadataVal = mutableMapOf<String, String>()
            metadataVal["Content-Type"] = file.contentType.toString()

            println(filePath)
            // 컨텐츠 타입을 설정합니다.
            val request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(filePath.toString())
                .metadata(metadataVal)
                .build()

            // MultipartFile을 File로 변환하여 S3에 업로드합니다.
            val fileContent = file.bytes
            
            s3Client.putObject(request, RequestBody.fromBytes(fileContent))
        }
    }
}

