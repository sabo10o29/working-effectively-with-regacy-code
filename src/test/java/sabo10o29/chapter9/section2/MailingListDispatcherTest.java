package sabo10o29.chapter9.section2;

import org.junit.Test;
import sabo10o29.chapter9.section4.PermitRepository;

import static org.junit.Assert.*;

public class MailingListDispatcherTest {

    //問題点
    //インスタンス生成は可能だけど、テストが難しい←メールリストの生成などを行う必要があるため
    @Test
    public void test(){
        MailingListDispatcher dispatcher = new MailingListDispatcher();

    }

    //解決策1
    //コンストラクタのパラメータ化(394)&インタフェース抽出(377)
    //テスト時にはモックを使用することができる
    //デメリット：呼び出し側を全て変更する必要がある
    @Test
    public void test1(){
        IMailService service = new IMailService() {
            @Override
            public int getStatus() { return 0; }

            @Override
            public void registration(MailingListDispatcher mailingListDispatcher, int clientType, boolean markMessagesOff) {

            }

            @Override
            public void setParam(int clientType, int i) { }
        };
        MailingListDispatcher dispatcher = new MailingListDispatcher(service);

    }

    //解決策2
    //シグネチャの維持(328)
    //コンストラクタをオーバーロードして、従来のコンストラクタを維持する

}