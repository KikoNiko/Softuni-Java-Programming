package bank.core;

import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static bank.common.ConstantMessages.*;
import static bank.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private LoanRepository loanRepository;
    private Map<String, Bank> banks;

    public ControllerImpl() {
        this.loanRepository = new LoanRepository();
        this.banks = new LinkedHashMap<>();
    }

    @Override
    public String addBank(String type, String name) {
        boolean isValidType = "BranchBank".equals(type) || "CentralBank".equals(type);
        if (!isValidType) {
            throw new IllegalArgumentException(INVALID_BANK_TYPE);
        }
        Bank bank;
        if ("BranchBank".equals(type)) {
            bank = new BranchBank(name);
        } else {
            bank = new CentralBank(name);
        }
        this.banks.put(name, bank);
        return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        boolean isValidType = "StudentLoan".equals(type) || "MortgageLoan".equals(type);
        if (!isValidType) {
            throw new IllegalArgumentException(INVALID_LOAN_TYPE);
        }
        Loan loan;
        if ("StudentLoan".equals(type)) {
            loan = new StudentLoan();
        } else {
            loan = new MortgageLoan();
        }
        this.loanRepository.addLoan(loan);
        return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loan = this.loanRepository.findFirst(loanType);

        if (loan == null) {
            throw new IllegalArgumentException(String.format(NO_LOAN_FOUND, loanType));
        }

        this.banks.get(bankName).addLoan(loan);
        this.loanRepository.removeLoan(loan);

        return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Client client;
        switch (clientType) {
            case "Student":
                client = new Student(clientName, clientID, income);
                break;
            case "Adult":
                client = new Adult(clientName, clientID, income);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CLIENT_TYPE);
        }

        Bank bank = this.banks.get(bankName);
        String output;

        if (!suitableForBank(clientType, bank)) {
            output = UNSUITABLE_BANK;
        } else {
            bank.addClient(client);
            output = String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
        }
        return output;
    }

    private boolean suitableForBank(String clientType, Bank bank) {
        String bankType = bank.getClass().getSimpleName();
        if ("Student".equals(clientType) && "BranchBank".equals(bankType)) {
            return true;
        } else if ("Adult".equals(clientType) && "CentralBank".equals(bankType)) {
            return true;
        }
        return false;
    }

    @Override
    public String finalCalculation(String bankName) {
        Bank bank = this.banks.get(bankName);

        double sumClientIncome = bank.getClients().stream()
                .mapToDouble(Client::getIncome).sum();
        double sumLoansAmount = bank.getLoans().stream()
                .mapToDouble(Loan::getAmount).sum();

        double finalFunds = sumClientIncome + sumLoansAmount;

        return String.format(FUNDS_BANK, bankName, finalFunds);
    }

    @Override
    public String getStatistics() {
        return this.banks.values().stream()
                .map(Bank::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
