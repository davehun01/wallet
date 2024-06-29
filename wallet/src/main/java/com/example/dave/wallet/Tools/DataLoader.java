package com.example.dave.wallet.Tools;

import com.example.dave.wallet.Entity.Player;
import com.example.dave.wallet.Entity.Transaction;
import com.example.dave.wallet.Repository.PlayerRepository;
import com.example.dave.wallet.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private PlayerRepository playerRepository;

    private TransactionRepository transactionRepository;

    @Autowired
    public DataLoader(PlayerRepository playerRepository, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }

    public void run(ApplicationArguments args) {

        if (playerRepository.findAll().size() == 0 ) {
            Player player = new Player();
            player.setName("John");
            player.setBalance(5000);
            playerRepository.save(player);

            player = new Player();
            player.setName("James");
            player.setBalance(3000);
            playerRepository.save(player);
        }

        if (transactionRepository.findAll().size() == 0) {
            Transaction transaction = new Transaction();
            transaction.setName("John");
            transaction.setWithdrawal(true);
            transaction.setAmount(2000);
            transactionRepository.save(transaction);

            transaction = new Transaction();
            transaction.setName("James");
            transaction.setWithdrawal(false);
            transaction.setAmount(1000);
            transactionRepository.save(transaction);
        }

    }
}