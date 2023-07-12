package com.efub.lakkulakku.domain.file.repository;

import com.efub.lakkulakku.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("SELECT f FROM File f WHERE f.url=:url")
    Optional<File> findAllByUrl(@Param("url") String url);

    @Transactional
    @Modifying
    @Query(value = "DELETE f FROM file f LEFT OUTER JOIN profile p ON f.file_id=p.profile_image_id WHERE p.users_id IS NULL AND f.url LIKE \"https://s3.ap-northeast-2.amazonaws.com/lakku-lakku.com/profile%\";", nativeQuery = true)
    void deleteUnusedFiles();
}
