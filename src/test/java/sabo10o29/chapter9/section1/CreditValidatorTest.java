package sabo10o29.chapter9.section1;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CreditValidatorTest {

    private static final int DEFAULT_PORT = 8080;
    private static final double THRESHOLD = 50;

    //ポイント1
    //単にインスタンス生成してみる
    //IDEなら生成できない場合に何が足りないのか教えてくれる
    @Test
    public void testCreate1() {
//        CreditValidator validator = new CreditValidator();
    }

    //問題点1
    //サーバーとコネクションしないとテストできない
    @Test
    public void testCreate2() throws IOException {
        RGHConnection connection = new RGHConnection(DEFAULT_PORT, "admin", "hoge");
        CreditMaster master = new CreditMaster("filename", true);
        CreditValidator validator = new CreditValidator(connection, master, "a");
    }

    //ポイント2
    //インタフェース抽出(377)を行い偽装クラスを使う
    //IRGHConnectionインタフェースを作成
    //IRGHConnectionを実装したテスト用のFakeConnectionクラスを作成
    //CreditValidatorの引数をIRGHConnectionに変更
    @Test
    public void testNoSuccess() throws Exception {
        //インタフェース抽出を行い、モックConnectionでテストをしてみる
        FakeConnection connection = new FakeConnection();
        CreditMaster master = new CreditMaster("filename", true);
        CreditValidator validator = new CreditValidator(connection, master, "a");

        connection.report = new RFDIReport();
        Certificate result = validator.validateCustomer(new Customer());

        assertEquals(Certificate.VALID, result.getStatus());

    }
    @Test
    public void testAllPassed100Percent() throws Exception {
        //インタフェース抽出を行い、モックConnectionでテストをしてみる
        FakeConnection connection = new FakeConnection();
        CreditMaster master = new CreditMaster("filename", true);
        CreditValidator validator = new CreditValidator(connection, master, "a");

        connection.report = new RFDIReport();
        Certificate result = validator.validateCustomer(new Customer());

        assertEquals(100.0, validator.getValidationPercent(), THRESHOLD);

    }

    //ポイント2
    //テストコードでは本番コードと同じルールに従う必要はない
    //テストは簡単に理解、変更できるべき
    //重複するセットアップはくくりだす（カプセル化とかは気にしない）
    //全テスト共通の初期化、終了処理なら＠Before、@Afterなども有効
    private FakeConnection _connection;
    private CreditMaster _master;
    private CreditValidator _validator;

    public void successSetUp(){
        _connection = new FakeConnection();
        _master = new CreditMaster("filename", true);
        _validator= new CreditValidator(_connection, _master, "a");
    }

    @Test
    public void testAllPassed100Percent2() throws Exception {

        successSetUp();
        _connection.report = new RFDIReport();
        Certificate result = _validator.validateCustomer(new Customer());

        assertEquals(100.0, _validator.getValidationPercent(), THRESHOLD);

    }


    //ポイント3
    //Nullを渡す
    //テストならnullを渡してもいい（本番ではnullを渡さない）
    //nullを渡せない場合にパラメータを渡す
    //Java、C#などのテストハーネスならNullポインタの例外が発生する場所を特定できるので便利
    @Test
    public void testCreate3() {
        CreditValidator validator = new CreditValidator(null, null, "a");
    }

    //ポイント4
    //Nullオブジェクトパターン
    //Null使用を避けるためのパターン
    //Nullオブジェクトを実際のオブジェクトと混同して使用しないように注意が必要
    @Test
    public void testCreate4() throws Exception{
        successSetUp();
        _connection.report = new RFDIReport();
        Certificate result = _validator.validateCustomer(new NullCustomer());
    }


}