package task9_3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class BankStatement {
    private String typeOfAccount;
    private String accountNumber;
    private String currency;
    private LocalDate dateOfAction;
    private String reference;
    private String descriptionOfOperation;
    private BigDecimal income;
    private BigDecimal expense;

public BankStatement(String typeOfAccount, String accountNumber, String currency, LocalDate dateOfAction, String reference, String descriptionOfOperation, BigDecimal income, BigDecimal expense){
    this.typeOfAccount=typeOfAccount;
    this.accountNumber=accountNumber;
    this.currency=currency;
    this.dateOfAction=dateOfAction;
    this.reference=reference;
    this.descriptionOfOperation=descriptionOfOperation;
    this.income=income;
    this.expense=expense;
}


    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDateOfAction() {
        return dateOfAction;
    }

    public String getReference() {
        return reference;
    }

    public String getDescriptionOfOperation() {
        return descriptionOfOperation;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDateOfAction(LocalDate dateOfAction) {
        this.dateOfAction = dateOfAction;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDescriptionOfOperation(String descriptionOfOperation) {
        this.descriptionOfOperation = descriptionOfOperation;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "BankStatement{" +
                "typeOfAccount='" + typeOfAccount + '\'' +
                ", accountNumber=" + accountNumber +
                ", currency='" + currency + '\'' +
                ", dateOfAction=" + dateOfAction +
                ", reference='" + reference + '\'' +
                ", descriptionOfOperation='" + descriptionOfOperation + '\'' +
                ", income=" + income +
                ", expense=" + expense +
                '}';
    }
}
