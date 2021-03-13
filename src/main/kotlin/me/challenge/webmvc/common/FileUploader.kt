package me.challenge.webmvc.common

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Component
class FileUploader(
    @Value("\${upload.dir}")
    val uploadDir: String
) {
    fun uploadFile(file: MultipartFile){
        val path = Paths.get(uploadDir)
        if(!Files.exists(path)){
            Files.createDirectories(path)
        }
        val originalFilename = file.originalFilename
        file.transferTo(File("$uploadDir/$originalFilename"))
    }
}