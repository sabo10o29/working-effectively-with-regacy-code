package sabo10o29.chapter9.section1;

public interface IRGHConnection {
    void connect();

    void disconnect();

    RFDIReport RFDIReportFor();

    ACTIOReport ACTIOReportFor(int customerID);
}
