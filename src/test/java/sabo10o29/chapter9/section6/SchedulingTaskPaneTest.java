package sabo10o29.chapter9.section6;

import org.junit.Test;

import static org.junit.Assert.*;

public class SchedulingTaskPaneTest {

    //問題点
    //SchedulingTaskを渡す必要があるが、SchedulingTask生成にもSchedulerとMeetingResolverの生成が必要
    //SchedulingTaskはSerialTaskを継承しており、インタフェースの抽出(377)でも影響範囲が大きい
    @Test
    public void teset(){
        //SchedulingTaskPane pane = new SchedulingTaskPane();
    }

    //解決案1
    //SchedulingTaskにインタフェースを追加する
    //SchedulingTaskPaneのコンストラクタの引数をISchedulingTaskに変更
    @Test
    public void test1(){
        //運用時にはSerialTaskのrunが呼ばれる
        SchedulingTask taskProduction = new SchedulingTask(null, null);
        SchedulingTaskPane paneProduction = new SchedulingTaskPane(taskProduction);
        //テスト時にはテスト用のSchedulingTaskを渡せる
        SchedulingTaskPane paneTest = new SchedulingTaskPane(()-> {
            System.out.println("ISchedulingTask");
        });
    }

}