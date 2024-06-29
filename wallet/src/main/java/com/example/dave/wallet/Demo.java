package com.example.dave.wallet;

import com.example.dave.wallet.Controller.WalletController;
import com.example.dave.wallet.Exception.OutOfMoneyException;
import com.example.dave.wallet.Tools.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @Autowired
    WalletController walletController;

    @RequestMapping("/")
    public String getGreeting() {
        return "Hello World";
    }

    @RequestMapping("/demo")
    public String getDemo() throws OutOfMoneyException {
        StringBuilder str = new StringBuilder();
        walletController.fillPlayersList();

        str = walletController.printPlayers();

        walletController.fillTransactionsList();

        str.append(walletController.printTransactions());

        str.append(walletController.printTransactionsPerPlayer("John"));

        str.append("Adding 5000 to John's account: ")
            .append(System.getProperty("line.separator"));

        if (walletController.updateBalance("John", "+5000")) {
            walletController.addTransaction("John", "+5000");
        };

        walletController.fillPlayersList();
        walletController.fillTransactionsList();

        str.append(walletController.printTransactions());

        str.append(walletController.printPlayers());

        Printer printer = new Printer();

        printer.createFile();
        printer.writeToFile(str);

        return str.toString();
    }
}
