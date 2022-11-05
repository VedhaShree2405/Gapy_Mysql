package exception;

import service.WalletService;

public class WalletException extends Exception{
    public WalletException(String message){
        super(message);
    }

}
