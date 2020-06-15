package sabo10o29.chapter9.section1;

public class FakeConnection implements IRGHConnection {

    public RFDIReport report;


    @Override
    public void connect() { }

    @Override
    public void disconnect() { }

    @Override
    public RFDIReport RFDIReportFor() {
        return report;
    }

    @Override
    public ACTIOReport ACTIOReportFor(int customerID) {
        return null;
    }
}
