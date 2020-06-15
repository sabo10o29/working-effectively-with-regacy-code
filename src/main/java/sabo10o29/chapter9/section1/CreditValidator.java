package sabo10o29.chapter9.section1;

public class CreditValidator {

    public CreditValidator(RGHConnection connection,
                           CreditMaster master,
                           String validatorID){
        //TODO initialize
    }

    //インタフェース抽出後のコンストラクタ
    public CreditValidator(IRGHConnection connection,
                           CreditMaster master,
                           String validatorID){
        //TODO initialize
    }


    //このメソッドをテストしたい
    Certificate validateCustomer(Customer customer) throws InvalidCredit {
        //customerの検証処理
        return null;
    }

    //nullを本番コードで使いたい場合にはNullオブジェクトを使用する
    Certificate validateCustomer(ICustomer customer) throws InvalidCredit {
        //customerの検証処理
        return null;
    }

    public double getValidationPercent() {
        return 0.0;
    }
}
