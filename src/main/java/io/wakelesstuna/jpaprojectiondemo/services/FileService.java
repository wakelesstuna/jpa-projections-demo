package io.wakelesstuna.jpaprojectiondemo.services;

import io.wakelesstuna.jpaprojectiondemo.entities.File;
import io.wakelesstuna.jpaprojectiondemo.repositories.projections.FileInfoView;
import io.wakelesstuna.jpaprojectiondemo.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public File findFileById(final String fileId) {
        return fileRepository.findById(fileId)
                .orElse(null);
    }

    public List<FileInfoView> getFileByOwnerInterface(final String owner) {
        return fileRepository.findAllByOwner(owner);
    }

    public void uploadFiles(final String owner, final List<MultipartFile> files) {
        files.forEach(file -> {
            try {
                File newFile = new File(UUID.randomUUID().toString(), owner, file.getName(), file.getContentType(), file.getSize(), file.getBytes());
                fileRepository.save(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional
    public void deleteFile(final String fileId) {
        fileRepository.deleteById(fileId);
    }

    @Transactional
    public List<File> getFileByOwner(final String owner) {
        return fileRepository.findAllByOwnerOrderBySizeAsc(owner);
    }
}
