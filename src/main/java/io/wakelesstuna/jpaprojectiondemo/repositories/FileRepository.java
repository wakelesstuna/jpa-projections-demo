package io.wakelesstuna.jpaprojectiondemo.repositories;

import io.wakelesstuna.jpaprojectiondemo.entities.File;
import io.wakelesstuna.jpaprojectiondemo.repositories.projections.FileInfoView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, String> {

    List<FileInfoView> findAllByOwner(String owner);

    List<File> findAllByOwnerOrderBySizeAsc(String owner);
}
