package com.systechafrica.part3.jdbc;

public class App {
    public static void main(String[] args) {
        DbHelper db=new DbHelper();
        db.dbConnect();
        DbHelper.depositFunds("CALL depositFunds(3000,1)");
        DbHelper.showTable();

    }

   
}
