package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO appDAO){
        this.messageDAO = appDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    
}
