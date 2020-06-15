package sabo10o29.chapter9.section2;

public class MailingListDispatcher {

    private static final int MS_VALIABLE = 200;
    private static final boolean MARK_MESSAGES_OFF = false;
    private static final int ML_NOBOUNCE = 1;
    private static final int ML_REPEATOFF = 1;
    private MailService service;
    private int status;

    public MailingListDispatcher(){

        int clientType = 12;
        //コンストラクタ内に依存関係が隠れていることが問題
        service = new MailService();
        if(service.getStatus() == MS_VALIABLE){
            status = 200;
            service.registration(this, clientType, MARK_MESSAGES_OFF);
            service.setParam(clientType, ML_NOBOUNCE | ML_REPEATOFF);
        }else{
            status = 0;
        }

    }

    //解決策1
    public MailingListDispatcher(IMailService service){

        int clientType = 12;
        if(service.getStatus() == MS_VALIABLE){
            status = 200;
            service.registration(this, clientType, MARK_MESSAGES_OFF);
            service.setParam(clientType, ML_NOBOUNCE | ML_REPEATOFF);
        }else{
            status = 0;
        }

    }


    //解決策2
    private void initialize(IMailService service){
        int clientType = 12;
        if(service.getStatus() == MS_VALIABLE){
            status = 200;
            service.registration(this, clientType, MARK_MESSAGES_OFF);
            service.setParam(clientType, ML_NOBOUNCE | ML_REPEATOFF);
        }else{
            status = 0;
        }
    }
//    public MailingListDispatcher(){
//        initialize(new MailService());
//    }
//
//    public MailingListDispatcher(IMailService service){
//        initialize(service);
//    }
//または
//    public MailingListDispatcher(){
//        this(new MailService());
//    }



    public void sendMessage(final String message){

    }

    public void addRecipient(final MailTxmId id, final MailAddress address){

    }
}
