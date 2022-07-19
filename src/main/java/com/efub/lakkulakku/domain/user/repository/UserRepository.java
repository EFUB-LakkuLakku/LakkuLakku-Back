package com.efub.lakkulakku.domain.user.repository;

import com.efub.lakkulakku.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);
}
