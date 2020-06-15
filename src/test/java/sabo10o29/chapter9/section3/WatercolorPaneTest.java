package sabo10o29.chapter9.section3;

import org.junit.Test;
import sabo10o29.chapter9.section4.TestingPermitRepository;

import static org.junit.Assert.*;

public class WatercolorPaneTest {

    //問題点
    //コンストラクタ内で複数のインスタンス生成が行われている
    //パラメータを外に出そうとするとパラメータリストが大きくなる
    //Cursor生成までを呼び出し側に移すと呼び出し側に負荷がかかる
    @Test
    public void test(){
        WatercolorPane pane = new WatercolorPane(new A(), new B(), new C());
    }

    //解決策
    //インスタンス変数の入れ替え(418)＆インタフェースの抽出(377)
    //生成後にインスタンスを差し替える
    @Test
    public void test1(){
        WatercolorPane pane = new WatercolorPane(new A(), new B(), new C());
        //FocusWidgetをテスト用のWidgetに差し替えてテストを行う
        pane.supersedeCursor(new IFocusWidget() {});
    }

}