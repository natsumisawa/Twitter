package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.List;

/**
 * 数値関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ikezaki
 */
public class SawaEx1StreamNumberTest extends ColorBoxTestCase {
    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        getColorBoxList().stream().map(colorBox -> colorBox.getSpaceList()
        .stream().map(BoxSpace::getContents))
        .forEach(objectStream -> objectStream
        .forEach(System.out::println));
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
    }
}
