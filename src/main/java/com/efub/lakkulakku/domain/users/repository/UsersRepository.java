package com.efub.lakkulakku.domain.users.repository;

import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}
