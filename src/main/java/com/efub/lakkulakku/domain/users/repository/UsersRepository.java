package com.efub.lakkulakku.domain.users.repository;

import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
	boolean existsByNickname(String nickname);

	boolean existsByEmail(String email);

	@Query("SELECT u FROM Users u WHERE u.email=:email")
	Optional<Users> findByEmail(String email);

	@Query("SELECT u FROM Users u WHERE u.nickname=:nickname")
	Optional<Users> findByNickname(String nickname);

	Optional<Users> findByUid(Long uid);

}
