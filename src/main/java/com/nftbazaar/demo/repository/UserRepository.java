package com.nftbazaar.demo.repository;

import com.nftbazaar.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findBywalletId(String walletId);

    Optional<User>  findByuserName(String userName);

    Optional<User>  findByMail(String mail);


}
