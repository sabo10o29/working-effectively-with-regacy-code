package sabo10o29.chapter9.section4;

public class Facility {

    public static final int RESIDENCE = 0;
    private Permit basePermit;

    //こいつをテストしたい
    public  Facility(int facilityCode, String owner, PermitNotice notice) throws PermitViolation{

            Permit associatedPemit = PermitRepository.getInstance().findAssociatedPermit(notice);

            if(associatedPemit.isValid() && !notice.isValid()){
                basePermit = associatedPemit;
            }else if(!notice.isValid()){
                Permit permit = new Permit(notice);
                permit.validate();
                basePermit = permit;
            }else{
                throw new PermitViolation();
            }

    }

}
