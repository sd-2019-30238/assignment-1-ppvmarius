package model;

public class DiscountFactory {
    public Discount getDiscount(String discountType){
        if(discountType.equals("None")){
            return null;
        }
        if(discountType.equals("20%")){
            return new Discount20();
        }
            if(discountType.equals("50%")){
            return new Discount50();
        }
        if(discountType.equals("1+1")){
            return new Discount1P1();
        }
        return null;
    }
}
