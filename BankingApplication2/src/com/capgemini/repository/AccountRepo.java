package com.capgemini.repository;

import com.capgemini.beans.Account;

public interface AccountRepo {
	boolean save(Account account);
	Account searchAccount(int accountNumber);
	

}
