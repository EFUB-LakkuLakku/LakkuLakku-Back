package com.efub.lakkulakku.domain.user.repository;

import com.efub.lakkulakku.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.nickname=:nickname")
    Optional<User> findByNickname(@Param("nickname") String nickname);
}
