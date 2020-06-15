package sabo10o29.chapter9.section4;

public interface IPermitRepository {

    Permit findAssociatedPermit(PermitNotice notice);

    public void addPermit(Permit permit);
}
