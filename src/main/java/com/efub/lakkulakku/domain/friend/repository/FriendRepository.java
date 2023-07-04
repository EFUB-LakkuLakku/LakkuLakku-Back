package com.efub.lakkulakku.domain.friend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.efub.lakkulakku.domain.friend.entity.Friend;
import com.efub.lakkulakku.domain.users.entity.Users;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UUID> {

	Optional<Friend> findById(UUID id);

	Optional<Users> findBySender(Users userId);

	Optional<Users> findByTarget(Users targetId);

	List<Friend> findAllBySender(Users user);

	List<Friend> findAllByTarget(Users user);

	void deleteById(UUID id);

	Optional<Friend> findBySenderAndTarget(Users sender, Users target);

	@Query("SELECT f FROM Friend f WHERE f.sender = :user OR f.target = :user")
	List<Friend> findAllBySenderOrTarget(Users user);
}
