package service;

import dao.WalletDao;
import dao.WalletDaoImpl;
import dto.Wallet;
import exception.WalletException;

import java.util.Objects;

public class WalletServiceImpl implements WalletService {

	private WalletDao walletRepository = new WalletDaoImpl();
	
	
	public Wallet registerWallet(Wallet newWallet) throws WalletException {
		
		return this.walletRepository.addWallet(newWallet);
		
	}

	public Boolean login(Integer walletId, String password) throws WalletException {
		// TODO Auto-generated method stub
		try {
			Wallet gotaccount = walletRepository.getWalletById(walletId);
			if (Objects.equals(gotaccount.getPassword(), password)) {
				return true;
			} else {

				throw new WalletException(super.toString());


			}
		} catch (WalletException ae) {
			throw new WalletException("Password-NotMatch");

		}
	}

	public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException {
		// TODO Auto-generated method stub
		Double currntbalc,addbalc,finalbalc;

		Wallet getamount=walletRepository.getWalletById(walletId);
		currntbalc=getamount.getBalance();
		addbalc=currntbalc+amount;
		getamount.setBalance(addbalc);
		this.walletRepository.updateWallet(getamount);
		Wallet updatedaccount =walletRepository.getWalletById(walletId);
		finalbalc=updatedaccount.getBalance();



		return finalbalc;
	}

	public Double showWalletBalance(Integer walletId) throws WalletException {
		// TODO Auto-generated method stub
		Wallet getamount=walletRepository.getWalletById(walletId);
		Double balc;
		balc=getamount.getBalance();

		return balc;
	}

	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {

		boolean sc = true;
		Double frombalc, tobalc;
		Wallet fromAccount= walletRepository.getWalletById(fromId);
		Wallet toAccount =walletRepository.getWalletById(toId);
		frombalc=fromAccount.getBalance();
		if(frombalc>=amount) {

			frombalc =- amount;
			fromAccount.setBalance(frombalc);
			this.walletRepository.updateWallet(fromAccount);
			tobalc = amount + toAccount.getBalance();
			toAccount.setBalance(tobalc);


			System.out.println(this.walletRepository.updateWallet(toAccount));
			return sc;
		}else {
			throw new WalletException("Insufficient Balance");
		}
	}

	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException {
		// TODO Auto-generated method stub
		try {
			Wallet gotaccount = walletRepository.getWalletById(walletId);
			if (Objects.equals(gotaccount.getPassword(), password)) {
				this.walletRepository.deleteWalletById(walletId);

			} else {
				throw new WalletException(super.toString());
			}
		}catch (WalletException ae){
			throw new WalletException("Password-NotMatch");

		}

		return null;
	}

}
