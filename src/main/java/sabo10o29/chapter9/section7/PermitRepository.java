package sabo10o29.chapter9.section7;

public class PermitRepository {

    private static PermitRepository ourInstance = new PermitRepository();
    public static PermitRepository getInstance() { return ourInstance; }

    public Permit findAssociatedFromOrigination(OriginationPermit permit) {
        return new Permit();

    }
}
