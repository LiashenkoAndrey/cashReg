package com.cashReg.util.sql;

import java.sql.SQLException;

public class Updater extends AbstractExecutor {

    public void updateProductQty(long productId, long quantity){
        String query = String.format(UPDATE, "\"product\"", "quantity = %d", "id = %d");
        try {
            query = String.format(query, productId, quantity);
            execute(query);

        }catch(SQLException sqlE){
            throw new RuntimeException(sqlE.getMessage());
        }
    }

    public void updateOrderItemQuantity(long orderId, long productId, long quantity) {
        String query = String.format(UPDATE, "\"order_item\"(quantity)", "%d", "order_id=%d AND product_id=%d");
        try{
            execute(String.format(query, quantity, orderId, productId));
        }catch(SQLException sqlE){
            throw new RuntimeException(sqlE.getMessage());
        }
    }

}
