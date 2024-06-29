package com.example.dave.wallet.View;

import com.example.dave.wallet.Entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletView {
    public StringBuilder printPlayer(String name,Integer balance) {
        StringBuilder str = new StringBuilder();
        str.append("Name: ").
            append(name).
            append(" || ").
            append("balance: ").
            append(balance);
        System.out.println(str);
        return str;
    }

    public StringBuilder printTransactions(List<Transaction> transactions) {
        StringBuilder str = new StringBuilder();
        str.append("transaction history: ");
        for (int i = 0; i < transactions.size(); i++) {
            str.append(System.getProperty("line.separator")).
                append(transactions.get(i).getName()).
                append(" || ");
            if (transactions.get(i).getWithdrawal()) {
                str.append("-");
            } else {
                str.append("+");
            }
            str.append(transactions.get(i).getAmount());
        }
        str.append(System.getProperty("line.separator"));
        System.out.println(str);
        return str;
    }
}
