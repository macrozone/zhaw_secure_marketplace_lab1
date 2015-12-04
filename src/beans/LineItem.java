package beans;

import java.io.Serializable;
import java.text.NumberFormat;

public class LineItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Product product;
    
    public LineItem() {}
    
    public void setProduct(Product p) {
        product = p;
    }

    public Product getProduct() { 
        return product;
    }

    public double getPrice() {
    	return product.getPrice();
    }
    
    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getPrice());
    }
}