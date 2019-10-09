package database;

import domain.GuestBookBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDao{
    Connection c;

    public GuestBookDao(String url, String login, String password) throws SQLException{
        c = DriverManager.getConnection(url,login,password);
    }

    public GuestBookDao(){}

    public List<GuestBookBean> getGuestBookItems() throws SQLException{
        List<GuestBookBean> items = new ArrayList<>();
        Statement statement = c.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM GuestBook");
        int i = 0;

        while(set.next()){
            String name = set.getString("Name");
            String message = set.getString("Message");
            Date date = set.getDate("Date");
            i = i+1 ;
            items.add(new GuestBookBean(date,name,message));
        }

        return items;
    }

    public void addGuestBookItem(GuestBookBean item) throws SQLException{
        String name = item.getName();
        String message = item.getMessage();
        PreparedStatement statement = c.prepareStatement("INSERT INTO GuestBook (Name,Message) value (?,?)");
        statement.setString(1,name);
        statement.setString(2,message);
        statement.execute();
    }

    @Override
    public String toString() {
        return "GuestBookDao{" +
                "c=" + c +
                '}';
    }
}
