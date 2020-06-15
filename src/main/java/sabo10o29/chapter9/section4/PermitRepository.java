package sabo10o29.chapter9.section4;

public class PermitRepository implements IPermitRepository {

    //解決策4
    //private static PermitRepository ourInstance = new PermitRepository();
    //public static PermitRepository getInstance() { return ourInstance; }
    private static IPermitRepository ourInstance = null;
    public static IPermitRepository getInstance() {
        if(ourInstance == null){
            ourInstance = new PermitRepository();
        }
        return ourInstance;
    }

    //解決策3
    //可視性の変更＆サブクラス作成
    //private PermitRepository() {
    //}
    protected PermitRepository() {
    }

    //解決策1
    //外部から差し替えられるようにメソッドを作成
    public static void setTestingInstance(IPermitRepository repository) {
        ourInstance = repository;
    }

    @Override
    public Permit findAssociatedPermit(PermitNotice notice){
        //permitのデータベースにアクセスする

        //noticeで使用する値を選ぶ

        //該当するPermitが一つだけかを確認、存在しない場合にはエラーを通知

        //該当するPermitを返す

        return null;
    }

    //解決策2
    //外部から設定を変更できる場合の手段
    @Override
    public void addPermit(Permit abc) {
    }
}
