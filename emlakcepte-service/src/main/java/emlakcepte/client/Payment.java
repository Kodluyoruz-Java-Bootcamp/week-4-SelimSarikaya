package emlakcepte.client;

public class Payment {
	
	private Integer amount;
	private boolean didPay = false;
	public Payment() {
		super();
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public boolean isDidPay() {
		return didPay;
	}
	public void setDidPay(boolean didPay) {
		this.didPay = didPay;
	}
	

}
