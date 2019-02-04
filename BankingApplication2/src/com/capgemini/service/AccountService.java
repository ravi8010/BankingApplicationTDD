package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceExceptions;
import com.capgemini.exceptions.InsufficientOpeningBalanceExceptions;
import com.capgemini.exceptions.InvalidAccountNumberExceptions;

public interface AccountService {
	Account createAccount(int accountNumber,int amount)throws InsufficientOpeningBalanceExceptions;
	int depositAccount(int accountNumber,int amount) throws InvalidAccountNumberExceptions;
	int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberExceptions,InsufficientBalanceExceptions;

	

}
