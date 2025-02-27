package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO appDAO){
        this.messageDAO = appDAO;
        accountDAO = new AccountDAO();
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessagesFromID(int message_id){
        return messageDAO.getMessagesFromID(message_id);
    }

    public Message createMessage(Message message){
        if(message.getMessage_text().length() < 255 && message.getMessage_text().length() > 0 && accountDAO.getAccountbyID(message.getPosted_by()) != null){
            return messageDAO.createNewMessage(message);
        }
        return null;
    }

    public Message deletMessageFromID(int message_id){
        return messageDAO.deleteMessageFromID(message_id);
    }
    
}
