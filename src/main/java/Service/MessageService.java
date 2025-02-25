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

    public List<Message> getMessagesFromID(int message_id){
        if(messageDAO.getMessagesFromID(message_id) != null){
            return messageDAO.getMessagesFromID(message_id);
        }
        return null;
        
    }

    public Message createMessage(Message message){
        if(message.getMessage_text().length() < 255 && message.getMessage_text().length() > 0 && accountDAO.getAccountbyID(message.getMessage_id()) != null){
            return messageDAO.createNewMessage(message);
        }
        return null;
    }
    
}
