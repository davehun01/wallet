package com.example.dave.wallet.Repository;

import com.example.dave.wallet.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByName(String name);
    @Transactional
    @Modifying
    @Query("update Player p set p.balance = ?1 where p.name = ?2")
    int updateCardBalance(int balance, String name);
}
