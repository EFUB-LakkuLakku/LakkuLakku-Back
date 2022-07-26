package com.efub.lakkulakku.domain.friend.repository;

import com.efub.lakkulakku.domain.friend.entity.Friend;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UUID> {

	Optional<Friend> findById(UUID id);
	Optional<Users> findByUserId(Users userId);
	Optional<Users> findByTargetId(Users targetId);
	List<Friend> findAllByUserId(Users user);
	List<Friend> findAllByTargetId(Users user);
	void deleteById(UUID id);

	Optional<Friend> findByUserIdAndTargetId(Users id1, Users id2);
}
