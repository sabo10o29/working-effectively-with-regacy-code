package sabo10o29.chapter9.section3;

public class WatercolorPane {

    private D d;
    private IFocusWidget focusWidget;

    //パラメータを外に出そうとするとパラメータリストが大きくなる
    //Cursor生成までを呼び出し側に移すと呼び出し側に負荷がかかる
    public WatercolorPane(A a, B b, C c){
        d = new D( a, b, c);
        focusWidget = new FocusWidget(d);
    }

    //インスタンス変数の入れ替え(418)＆インタフェースの抽出(377)
    protected void supersedeCursor(IFocusWidget newCursor){
        //古いインスタンスはGCで開放される
        focusWidget = newCursor;
    }


    //https://www.jpcert.or.jp/java-rules/met05-j.html
}
