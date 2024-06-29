package com.example.dave.wallet.Controller;

import com.example.dave.wallet.Entity.Player;
import com.example.dave.wallet.Entity.Transaction;
import com.example.dave.wallet.Exception.OutOfMoneyException;
import com.example.dave.wallet.Repository.PlayerRepository;
import com.example.dave.wallet.Repository.TransactionRepository;
import com.example.dave.wallet.View.WalletView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
@Service
public class WalletController {
    private List<Transaction> transactions;
    private List<Player> players;

    @Autowired
    private WalletView walletView;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void fillTransactionsList() {
        transactions = transactionRepository.findAll();
    }

    public void fillPlayersList() {
        players = playerRepository.findAll();
    }

    public StringBuilder printPlayers() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            str.append(walletView.printPlayer(players.get(i).getName(), players.get(i).getBalance()))
                .append(System.getProperty("line.separator"));
        }
        return str;
    }

    public StringBuilder printTransactions() {
        StringBuilder str = new StringBuilder();
        str = walletView.printTransactions(transactions);
        return str;
    }

    public StringBuilder printTransactionsPerPlayer(String name) {
        List<Transaction> trList = transactionRepository.findByName(name);
        StringBuilder str = new StringBuilder();
        str.append(name)
            .append("'s ");
        System.out.print(str);
        str.append(walletView.printTransactions(trList));
        return str;
    }

    public boolean updateBalance(String name, String amount) throws OutOfMoneyException {
        Player player = playerRepository.findByName(name).get(0);
        int balance = player.getBalance();
        if (amount.startsWith("+")) balance += Integer.valueOf(amount.substring(1));
        if (amount.startsWith("-")) {
            if (Integer.valueOf(amount.substring(1)) > balance) {
                throw new OutOfMoneyException("Insufficient funds to make the withdrawal");
            } else {
                balance -= Integer.valueOf(amount.substring(1));
            }

        }
        playerRepository.updateCardBalance(balance, name);
        return true;
    }

    public boolean addTransaction(String name, String amount) {
        Transaction transaction = new Transaction();
        transaction.setName(name);
        if (amount.startsWith("+")) {
            transaction.setWithdrawal(false);
        } else {
            transaction.setWithdrawal(true);
        }
        transaction.setAmount(Integer.valueOf(amount.substring(1)));
        transactionRepository.save(transaction);
        return true;
    }
}
