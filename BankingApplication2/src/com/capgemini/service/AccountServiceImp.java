package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceExceptions;
import com.capgemini.exceptions.InsufficientOpeningBalanceExceptions;
import com.capgemini.exceptions.InvalidAccountNumberExceptions;
import com.capgemini.repository.AccountRepo;

public  class AccountServiceImp implements AccountService {
	
AccountRepo accountrepo;

public AccountServiceImp(AccountRepo accountrepo) {
	super();
	this.accountrepo = accountrepo;
}

	@Override
	public Account createAccount(int accountNumber, int amount) throws  InsufficientOpeningBalanceExceptions {
		// TODO Auto-generated method stub
		if(amount<500)
		{
			throw new InsufficientOpeningBalanceExceptions();
		}
		Account account=new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountrepo.save(account))
		{
			return account;
		}
		return null;
		
	}

	@Override
	public int   depositAccount(int accountNumber, int amount) throws InvalidAccountNumberExceptions {
		// TODO Auto-generated method stub
		Account account=accountrepo.searchAccount(accountNumber);
		if(account==null)
		{
			
			System.out.println("from If Statement");
		  throw new InvalidAccountNumberExceptions();
		}
		account.setAmount(account.getAmount()+amount);
		accountrepo.save(account);
		System.out.println("After save");
         return account.getAmount();		
			}

	@Override
	public int withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberExceptions, InsufficientBalanceExceptions {
		// TODO Auto-generated method stub
		Account account=accountrepo.searchAccount(accountNumber);
		if(account==null)
		{
			throw new InvalidAccountNumberExceptions();
		}
		if(account.getAmount()<amount)
		{
			throw new InsufficientBalanceExceptions();
		}
		account.setAmount(account.getAmount()-amount);
		accountrepo.save(account);
		
		return account.getAmount();
	}	
	

	
	
	
	
}
	


