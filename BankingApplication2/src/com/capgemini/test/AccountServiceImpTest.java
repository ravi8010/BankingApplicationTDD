package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceExceptions;
import com.capgemini.exceptions.InsufficientOpeningBalanceExceptions;
import com.capgemini.exceptions.InvalidAccountNumberExceptions;
import com.capgemini.repository.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImp;

public class AccountServiceImpTest {
	
	
	
	
	AccountService accountService;
	   @Mock
	   AccountRepo accountRepo;
		@Before
		public void setUp() throws Exception {
			MockitoAnnotations.initMocks(this);
			accountService=new  AccountServiceImp(accountRepo);
		}

		@Test(expected=com.capgemini.exceptions.InsufficientOpeningBalanceExceptions.class)
		public void whenAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficientOpeningBalanceExceptions
		{
			accountService.createAccount(101,400);
		}
		@Test
		public void whenValidInfoIsPassedAccountIsSuccessfullyCreated() throws InsufficientOpeningBalanceExceptions
		{
			Account account=new Account();
			account.setAccountNumber(101);
			account.setAmount(600);
			when(accountRepo.save(account)).thenReturn(true);
			assertEquals(account,accountService.createAccount(101,600));
			
		}
		@Test(expected=com.capgemini.exceptions.InvalidAccountNumberExceptions.class)
		public void whenTheValidAccountNumberIsNotPassedforDepositSystemShouldThrowException() throws InvalidAccountNumberExceptions, InsufficientOpeningBalanceExceptions
		{
			Account account = new Account();
			account.setAccountNumber(102);
			account.setAmount(5000);
			accountService.createAccount(103,5000);
			//assertEquals(account,accountService.createAccount(102,5000));
			accountService.depositAccount(102,5000);
		}
		@Test
		public void whenTheValidAccountNumberIsPassedAmountShouldBeDeposited() throws InvalidAccountNumberExceptions
		{
			Account account=new Account();
			account.setAccountNumber(101);
			account.setAmount(600);
			when(accountRepo.searchAccount(101)).thenReturn(account);
			assertEquals(account.getAmount()+2000,accountService.depositAccount(101, 2000));
		}
		@Test(expected=com.capgemini.exceptions.InvalidAccountNumberExceptions.class)
		public void whenTheValidAccountNumberIsNotPassedForWithdrawSystemShouldThrowException() throws InvalidAccountNumberExceptions, InsufficientBalanceExceptions
		{
			accountService.withdrawAmount(101,5000);
		}
		
		@Test(expected=com.capgemini.exceptions.InsufficientBalanceExceptions.class)
		public void whenAmountIsNotSufficientSystemThrowException() throws InsufficientBalanceExceptions, InvalidAccountNumberExceptions
		{
			Account account=new Account();
			account.setAccountNumber(101);
			account.setAmount(100);
			when(accountRepo.searchAccount(101)).thenReturn(account);
			accountService.withdrawAmount(101,2000);
			
		}
		@Test
		public void whenTheValidAccountNumberIsPassedWithdrawIsSuccessfully() throws InvalidAccountNumberExceptions, InsufficientBalanceExceptions
		{
			Account account=new Account();
			account.setAccountNumber(101);
			account.setAmount(2000);
			when(accountRepo.searchAccount(101)).thenReturn(account);
			assertEquals(account.getAmount()-500,accountService.withdrawAmount(101, 500));
			
		}
		
	
	

}
