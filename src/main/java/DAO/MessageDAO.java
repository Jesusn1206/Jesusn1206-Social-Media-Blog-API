package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    //DAO Problem 1 
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public List<Message> getMessagesFromID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message WHERE message_id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);           
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;

    }

    public Message createNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = (int) rs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



}
