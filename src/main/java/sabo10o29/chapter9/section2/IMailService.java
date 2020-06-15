package sabo10o29.chapter9.section2;

public interface IMailService {
    int getStatus();

    void registration(MailingListDispatcher mailingListDispatcher, int clientType, boolean markMessagesOff);

    void setParam(int clientType, int i);
}
