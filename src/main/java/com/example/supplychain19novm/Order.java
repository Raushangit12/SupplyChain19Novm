package com.example.supplychain19novm;

public class Order {
    public static boolean placeSingleOrder(product Product, String customerEmail) {
        String orderQuery = String.format("INSERT into orders (quantity,customer_id,product_id,status) VALUES (1,(SELECT cid FROM customer2 where email='%s'),%s,'ORDERED')",
                customerEmail, Product.getId());
        DatabaseConnection dbConn=new DatabaseConnection();
        if(dbConn.insertData(orderQuery)>=1){
            return true;
        }
        System.out.println(orderQuery);
        return false;
    }

}
