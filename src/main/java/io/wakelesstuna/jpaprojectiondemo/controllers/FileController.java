package io.wakelesstuna.jpaprojectiondemo.controllers;

import io.wakelesstuna.jpaprojectiondemo.entities.File;
import io.wakelesstuna.jpaprojectiondemo.repositories.projections.FileInfoView;
import io.wakelesstuna.jpaprojectiondemo.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/file")
public record FileController(FileService fileService) {

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable final String fileId) {
        File file = fileService.findFileById(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, file.getType());
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        return ResponseEntity.ok().headers(httpHeaders).body(file.getContent());
    }

    @GetMapping("/owner/interface/{owner}")
    public ResponseEntity<List<FileInfoView>> fileByOwnerInterface(@PathVariable final String owner) {
        List<FileInfoView> fileByOwner = fileService.getFileByOwnerInterface(owner);
        return ResponseEntity.ok(fileByOwner);
    }

    @GetMapping("/owner/all/{owner}")
    public ResponseEntity<List<File>> fileByOwnerAll(@PathVariable final String owner) {
        List<File> fileByOwner = fileService.getFileByOwner(owner);
        return ResponseEntity.ok(fileByOwner);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(final String owner, final List<MultipartFile> files) {
        fileService.uploadFiles(owner, files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable final String fileId) {
        fileService.deleteFile(fileId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
