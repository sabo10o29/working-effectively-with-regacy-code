package sabo10o29.chapter9.section1;

import java.io.IOException;

public class RGHConnection implements IRGHConnection {

    public RGHConnection(int port, String name, String password)throws IOException {

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(){

    }

    @Override
    public void disconnect(){

    }

    @Override
    public RFDIReport RFDIReportFor(){
        return null;
    }

    @Override
    public ACTIOReport ACTIOReportFor(int customerID){
        return null;
    }

    private void retry(){

    }

    private RFPacket formPacket(){
        return null;
    }


}
