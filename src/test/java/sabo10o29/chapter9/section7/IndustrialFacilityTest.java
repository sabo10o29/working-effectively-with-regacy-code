package sabo10o29.chapter9.section7;

import org.junit.Test;

public class IndustrialFacilityTest {

    //問題点
    //OriginationPermitの生成が困難
    //フィールドでOriginationPermitの親クラスの型で持っているためIOriginationPermitを作成しても無理
    @Test
    public void test() throws PermitViolation {
        IndustrialFacility facility = new IndustrialFacility(Facility.HT_1, "b",
                new OriginationPermit());
    }

    //解決策１
    //インタフェースの抽出(377)
    //スーパークラスからインタフェースを作成しコンストラクタ、フィールドなどを置き換える
    //コンストラクタ、フィールドだけでなく、使用している他のクラスも変更する必要があるシングルトンのメソッドなど
    //何故依存関係が悪いかを考えるきっかけになる
    @Test
    public void test1() throws PermitViolation {
        IndustrialFacility facility = new IndustrialFacility(Facility.HT_1, "b",
                new IPermit() {
                    @Override
                    public boolean isValid() {
                        return false;
                    }

                    @Override
                    public void validate() {

                    }
                });
    }

    //解決策２
    //サブクラス化とメソッドのオーバライド(415)
    //呼び出したくないメソッドだけを変更してテストできる
    //部分的な変更だけでは依存関係を排除できない場合にはメソッド抽出を行う必要がある
    @Test
    public void test2() throws PermitViolation {
        IndustrialFacility facility = new IndustrialFacility(Facility.HT_1, "b",
                new AlwaysValidPermit());
    }

    static class AlwaysValidPermit extends FakeOriginationPermit{
        @Override
        public void validate() {
            //becomeValid();
        }
    };

}