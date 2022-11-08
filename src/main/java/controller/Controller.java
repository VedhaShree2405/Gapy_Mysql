package controller;

import dao.WalletDao;
import dao.WalletDaoImpl;
import dto.Wallet;
import exception.WalletException;
import service.WalletService;
import service.WalletServiceImpl;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args)  {
		
		WalletService ws = new WalletServiceImpl();

		WalletDao wc = new WalletDaoImpl();

		try {
			System.out.println("Adding new Account");
			Wallet x,y,z;
			x=new Wallet(11, "Ford1", 100.0, "123");
			y =new Wallet(12, "Ford2", 1000.0, "1234");
			z=new Wallet(15,"sudha",12000.0,"1234");
			System.out.println(ws.registerWallet(x));
			System.out.println(ws.registerWallet(y));
			System.out.println(ws.registerWallet(z));
			System.out.println("Login Account");
			System.out.println(ws.login(11,"1234"));
			System.out.println("Show Account Balance");
			System.out.println(ws.showWalletBalance(11));
			//System.out.println(ws.showWalletBalance(12));
			System.out.println("Add Account Balance");
			System.out.println(ws.addFundsToWallet(11,900.0));
			System.out.println("Amount Transfer");
			System.out.println(ws.fundTransfer(11,12,500.0));
			System.out.println("delete Account");
			System.out.println(ws.unRegisterWallet(11,"123"));
			System.out.println(ws.unRegisterWallet(15,"1234"));

			Scanner sc = new Scanner(System.in);
			System.out.println("1.fundtransfer Exp 2.deleexp 3.loginexp");
			int a=sc.nextInt();
			switch (a){
				case 1:
					//Exception for fundTransfer
					System.out.println("Exception for fundTransfer");
					ws.fundTransfer(10,111,500.0);
					break;
				case 2:
					//Exception for delete password wrong
					System.out.println("Exception for password wrong while deleting");
					System.out.println(ws.unRegisterWallet(111,"34"));
					break;
				case 3:

					//Exception for delete password wrong
					System.out.println("Exception for password wrong while Login");
					System.out.println(ws.unRegisterWallet(111,"1234"));
					break;



			}

		} catch (WalletException  a) {

			System.out.println("Password-mis_match");
		}
	}

}
