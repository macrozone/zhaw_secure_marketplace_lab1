package beans;

import java.io.Serializable;
import java.text.NumberFormat;

public class Purchase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
    private String lastName;
    private String ccNumber;
    private double totalPrice;
    
    public Purchase() {
    	id = 0;
    	firstName = "";
    	lastName = "";
    	ccNumber = "";
        totalPrice = 0;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
   public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() { 
        return firstName; 
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() { 
        return lastName; 
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }
    
    public String getCcNumber() { 
        return ccNumber; 
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public String getTotalPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(totalPrice);
    }    
}