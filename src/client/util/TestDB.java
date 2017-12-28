package client.util;

public class TestDB {
    public static void main(String[] args) {
        ConnDB connDB = new ConnDB();
        System.out.println(connDB.getCompany());
    }
}
