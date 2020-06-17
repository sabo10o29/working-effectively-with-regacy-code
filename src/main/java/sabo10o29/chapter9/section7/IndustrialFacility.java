package sabo10o29.chapter9.section7;

public class IndustrialFacility extends Facility {

    Permit basePemit;

    public IndustrialFacility(int facilityCode, String owner,
                              OriginationPermit permit) throws PermitViolation {

        Permit associatedPermit = PermitRepository.getInstance()
                .findAssociatedFromOrigination(permit);

        if(associatedPermit.isValid() && !permit.isValid()){
            basePemit = associatedPermit;
        }else if(!permit.isValid()){
            permit.validate();
            basePemit = permit;
        }else{
            throw new PermitViolation(permit);
        }

    }

    public IndustrialFacility(int facilityCode, String owner,
                              IPermit permit) throws PermitViolation {

//        Permit associatedPermit = PermitRepository.getInstance()
//                .findAssociatedFromOrigination(permit);
//
//        if(associatedPermit.isValid() && !permit.isValid()){
//            basePemit2 = associatedPermit;
//        }else if(!permit.isValid()){
//            permit.validate();
//            basePemit2 = permit;
//        }else{
//            throw new PermitViolation(permit);
//        }

    }

}
