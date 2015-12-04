package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<LineItem> items;
    
    public Cart() {
        items = new ArrayList<LineItem>();
    }
    
    public ArrayList<LineItem> getItems() { 
        return items;
    }
    
    public int getCount() { 
        return items.size();
    }
    
    public void addItem(LineItem item) {
    	
    	// Only add the item if it's not yet in the cart
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
            	return;
            }
        }
        items.add(item);
    }
    
    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
    
    public double getTotal() {
    	double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            total += lineItem.getPrice();
        }
        return total;
    }
}