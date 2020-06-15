package sabo10o29.chapter9.section4;

import java.util.HashMap;
import java.util.Map;

public class TestingPermitRepository extends PermitRepository {

    private Map<PermitNotice, Permit> permits = new HashMap<> ();

    public void addAssociatedPermit(PermitNotice notice, Permit permit){
        permits.put(notice, permit);
    }

    @Override
    public Permit findAssociatedPermit(PermitNotice notice){
        System.out.println("TestingPermitRepository#findAssociatedPermit");
        Permit result = permits.get(notice);
        return result == null ? new Permit(null) : result;
    }

    //解決策3
    //テスト用、サーバーに接続しないようにpermitsに保持する実装
    @Override
    public void addPermit(Permit abc) {

    }
}
