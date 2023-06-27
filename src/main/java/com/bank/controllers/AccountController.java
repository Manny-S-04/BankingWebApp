package com.bank.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.entities.CurrAccount;
import com.bank.entities.SavAccount;
import com.bank.entities.Transaction;
import com.bank.entities.User;
import com.bank.services.CurrAccountService;
import com.bank.services.SavAccountService;
import com.bank.services.TransactionService;
import com.bank.services.UserRegistrationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	private CurrAccountService accountService;

	@Autowired
	private SavAccountService accountService2;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserRegistrationService registrationService;

	@GetMapping("/addaccount")
	public String addAccount(HttpSession session) {

		User loggedinUser = (User) session.getAttribute("loggedinuser");

		if (loggedinUser != null) {
			return "addaccount";
		} else {
			return "login";
		}

	}

	@PostMapping("/addaccount")
	public String addAccount(@RequestParam("accountType") String accountType, HttpSession session) {
		User loggedinUser = (User) session.getAttribute("loggedinuser");

		if (loggedinUser != null) {
			int userid = registrationService.findEmail(loggedinUser.getEmail()).getUserid();

			if ("current".equals(accountType)) {
				CurrAccount currAccount = new CurrAccount();
				currAccount.setBalance(0);
				currAccount.setUserid(userid);
				try {
					CurrAccount accountExists = accountService.findByUserid(userid);
					if (accountExists == null) {
						accountService.save(currAccount);
					}
				} catch (DataIntegrityViolationException e) {
					return "accountdetails";
				}

			} else if ("savings".equals(accountType)) {
				SavAccount savAccount = new SavAccount();
				savAccount.setBalance(0);
				savAccount.setUserid(userid);
				// Set other properties for the savings account if needed

				try {
					SavAccount accountExists = accountService2.findByUserid(userid);
					
					if(accountExists == null) {
					accountService2.save(savAccount);
					}
				} catch (DataIntegrityViolationException e) {
					return "accountdetails";
				}
			}

			return "redirect:/accountdetails";
		} else {
			return "login";
		}
	}
	
	@GetMapping("/transactionhistory")
	public String getHistory(HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedinuser");

		if (loggedInUser != null) {
			int userId = registrationService.findEmail(loggedInUser.getEmail()).getUserid();
			
			String currAccnumber = accountService.findByUserid(userId).getCurr_account_number();
			
			String savAccnumber = accountService2.findByUserid(userId).getSav_account_number();
			
			
			List<Transaction> currTransactions = transactionService.findByAccountNumber(currAccnumber);
			
			List<Transaction> savTransactions = transactionService.findByAccountNumber(savAccnumber);
			
			try {
			session.setAttribute("currtransactions", currTransactions);
			session.setAttribute("savtransactions", savTransactions);
			}catch (NullPointerException e) {
				return "accountdetails";
			}
			
			return "transactionhistory";
			
		} else {
			return "login";
		}
		
	}

	@GetMapping("/accountdetails")
	public String getAccount(HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedinuser");

		if (loggedInUser != null) {
			int userId = registrationService.findEmail(loggedInUser.getEmail()).getUserid();

			CurrAccount currAccount = accountService.findByUserid(userId);
			if (currAccount != null) {
				session.setAttribute("currnumber", currAccount.getCurr_account_number());
				session.setAttribute("balance", currAccount.getBalance());
			} else {
				session.removeAttribute("currnumber");
				session.removeAttribute("balance");
			}

			SavAccount savAccount = accountService2.findByUserid(userId);
			if (savAccount != null) {
				session.setAttribute("savnumber", savAccount.getSav_account_number());
				session.setAttribute("savbalance", savAccount.getBalance());
			} else {
				session.removeAttribute("savnumber");
				session.removeAttribute("savbalance");
			}

			if (currAccount == null && savAccount == null) {
				return "addaccount";
			}

			return "accountdetails";
		} else {
			return "login";
		}
	}

	@PostMapping("/depositcurr")
	public String deposit(@RequestParam("depositcurr") double deposit, HttpSession session) {

		User loggedinUser = (User) session.getAttribute("loggedinuser");

		if (loggedinUser != null) {

			int userid = registrationService.findEmail(loggedinUser.getEmail()).getUserid();

			CurrAccount currAccount = accountService.deposit(deposit, userid);

			Transaction transaction = new Transaction(currAccount.getCurr_account_number(), "deposit", deposit,
					currAccount.getBalance(), LocalDateTime.now());

			transactionService.save(transaction);

			return "redirect:/accountdetails";

		} else {

			return "login";
		}
	}

	@PostMapping("/withdrawcurr")
	public String withdraw(@RequestParam("withdrawcurr") double withdraw, HttpSession session) {

		User loggedinUser = (User) session.getAttribute("loggedinuser");

		if (loggedinUser != null) {

			int userid = registrationService.findEmail(loggedinUser.getEmail()).getUserid();

			CurrAccount currAccount = accountService.withdraw(withdraw, userid);

			Transaction transaction = new Transaction(currAccount.getCurr_account_number(), "withdraw", withdraw,
					currAccount.getBalance(), LocalDateTime.now());

			transactionService.save(transaction);

			return "redirect:/accountdetails";

		} else {

			return "login";
		}
	}

	@PostMapping("/depositsav")
	public String depositSav(@RequestParam("depositsav") double deposit, HttpSession session) {

		User loggedinUser = (User) session.getAttribute("loggedinuser");

		if (loggedinUser != null) {

			int userid = registrationService.findEmail(loggedinUser.getEmail()).getUserid();

			SavAccount savAccount = accountService2.deposit(deposit, userid);

			Transaction transaction = new Transaction(savAccount.getSav_account_number(), "deposit", deposit,
					savAccount.getBalance(), LocalDateTime.now());

			transactionService.save(transaction);

			return "redirect:/accountdetails";

		} else {

			return "login";
		}
	}

}
