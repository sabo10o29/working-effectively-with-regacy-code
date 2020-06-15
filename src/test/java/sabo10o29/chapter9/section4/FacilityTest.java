package sabo10o29.chapter9.section4;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FacilityTest {

    //問題点
    //内部でPermitRepositoryを使用している
    //グローバル変数の状態を意識しながらテストを作成しなければならない
    //コンストラクタのパラメータ化(394)をしても良いが、他のメソッド、コンストラクタでも同様の作業が発生する
    @Test
    public void test() throws Exception {

        PermitNotice notice = new PermitNotice(0, "a");
        Facility facility = new Facility(Facility.RESIDENCE, "b", notice);
    }


    //解決策1
    //静的setメソッドの導入(386)
    //シングルトンの可視性を変更して、外部から変更できるようにする
    //
    //シングルトンにする理由
    //1.現実世界に一つしか無い場合
    //2.2つ以上存在すると重大な問題になる場合
    //3.2つ以上あるとリソースを使いすぎる場合
    //4.グローバル変数を持ちたい場合
    //4番の場合はシングルトンにする必要なし
    //1、2、3も「ビルド時のチェック」や「setTestingInstance内で本番環境かどうか判断して警告する」などで対応できる
    //シングルトンが破られても深刻な状態にならなければ、規約として徹底するでも良い
    public void setUp(){
        //テスト用のPemitRepositoryを生成
        PermitRepository repository = new PermitRepository();
        PermitRepository.setTestingInstance(repository);
    }

    @Test
    public void test1() throws Exception {
        setUp();
        PermitNotice notice = new PermitNotice(0, "a");
        Facility facility = new Facility(Facility.RESIDENCE, "b", notice);
    }

    //解決策2
    //PermitRepositoryを差し替えられなくても、メソッドaddPermitを開けてセットアップができる場合もある
    //データベース接続など行っている場合には不可能なので、使用は限定的
    @Test
    public void test2() throws Exception{
        IPermitRepository repository = PermitRepository.getInstance();
        //テスト用メソッドで必要な建築許可を追加していく
        repository.addPermit(new Permit(new PermitNotice(0, "ABC")));
        //テスト
        PermitNotice notice = new PermitNotice(0, "a");
        Facility facility = new Facility(Facility.RESIDENCE, "b", notice);
    }

    //解決策3
    //サブクラス化とオーバーライド(415)
    //PermitRepositoryのコンストラクタの可視性をProtectedにする。可視性の変更をProtectedまでに抑えることができる。
    //PermitRepositoryのサブクラスTestingPermitRepositoryを作成してシングルトンインスタンスの差し替え
    @Test
    public void test3()throws Exception{
        TestingPermitRepository repository = new TestingPermitRepository();
        PermitRepository.setTestingInstance(repository);
        //テストに必要な建築許可を追加していく
        PermitNotice notice = new PermitNotice(0, "a");
        repository.addPermit(new Permit(notice));
        //テスト
        Facility facility = new Facility(Facility.RESIDENCE, "a", notice);
    }

    //解決策4
    //インタフェースの抽出(377)、コンパイラまかせ(331)
    //依存が多くサブクラスを作成する負荷が高い場合には、インタフェースを作成しテスト用に一から実装を行うと簡単
    //インタフェースの抽出にはIDEの機能で可能
    //サブクラスを作成しなくても良いのでテスト内で一時的なクラスを作成してテストができる
    @Test
    public void test4()throws Exception{
        IPermitRepository repository = new IPermitRepository() {

            private Map<PermitNotice, Permit> permits = new HashMap<>();

            @Override
            public Permit findAssociatedPermit(PermitNotice notice) {
                System.out.println("testRepository#findAssociatedPermit");
                Permit result = permits.get(notice);
                return result == null ? new Permit(null) : result;
            }

            @Override
            public void addPermit(Permit permit){
                //add permit
            }

        };
        PermitRepository.setTestingInstance(repository);
        //テストに必要な建築許可を追加していく
        PermitNotice notice = new PermitNotice(0, "a");
        repository.addPermit(new Permit(notice));
        //テスト
        Facility facility = new Facility(Facility.RESIDENCE, "a", notice);

    }

    //テストは可能になるが、グローバル変数への依存がなくなったわけではない。
    //依存を排除するならコンストラクタ/メソッドのパラメータ化を行う必要がある


}