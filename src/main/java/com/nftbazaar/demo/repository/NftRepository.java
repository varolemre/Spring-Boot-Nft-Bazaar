package com.nftbazaar.demo.repository;

import com.nftbazaar.demo.entity.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NftRepository extends JpaRepository<Nft,Long> {
    List<Nft> findByuser_Id(Long userId);
    List<Nft> findBysellStatus(boolean a);
}

