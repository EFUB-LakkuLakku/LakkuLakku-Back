package com.efub.lakkulakku.domain.user.repository;

import com.efub.lakkulakku.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByNickname(String nickname); //아이디 중복 확인을 위해
}
