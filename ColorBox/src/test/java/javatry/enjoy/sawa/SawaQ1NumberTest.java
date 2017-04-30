package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数値関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa
 */
public class SawaQ1NumberTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                             Convert
    //                                                                             =======

    /**
     * カラーボックスに入ってる日付の月を全て足したら？
     */
    public void test_sumMonth() {
        List<ColorBox> colorBoxList = getColorBoxList();
        int monthSum = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDate) {
                    monthSum += ((LocalDate) contents).getMonthValue();
                } else if (contents instanceof LocalDateTime) {
                    monthSum += ((LocalDateTime) contents).getMonthValue();
                }
            }
        }
        log(monthSum);
    }

    /**
     * カラーボックの中に入っている、0~100までの数値の数は？
     *
     * @throws Exception
     */
    public void test_countZeroToHundred() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        int count = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Integer) {
                    if ((0 <= (Integer) contents) && ((Integer) contents <= 100)) {
                        count++;
                    }
                }
            }
        }
        log(count);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========

    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            if (colorBox.getColor().getColorName().equals("blue")) {
                for (BoxSpace boxSpace : spaceList) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof Map<?, ?>) {
                        int maxValue = 0;
                        String product = "";
                        for (Map.Entry<?, ?> e : ((Map<?, ?>) contents).entrySet()) {
                            if (maxValue < (Integer) e.getValue()) {
                                maxValue = ((Integer) e.getValue());
                                product = (String) e.getKey();
                            }
                        }
                        log("一番高い商品は" + product + "です");
                    }
                }
            }
        }
    }

    /**
     * カラーボックスの中で、一番幅が大きいものでInteger型を持っているカラーボックスの色は？
     */
    public void test_findColorBigWidthHasInteger() {
        List<ColorBox> colorBoxList = getColorBoxList();
        int max = 0;
        String maxBox = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace e : spaceList) {
                Object contents = e.getContents();
                if (contents instanceof Integer) {
                    if (max < colorBox.getSize().getWidth()) {
                        max = colorBox.getSize().getWidth();
                        maxBox = colorBox.getColor().getColorName();
                    }
                }
            }
        }
        log("一番幅が大きいものでInteger型を持っているカラーボックスの色は" + maxBox + "です");
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
        List<ColorBox> colorBoxList = getColorBoxList();
        BigDecimal sum = new BigDecimal("0.00");
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace e : spaceList) {
                Object contents = e.getContents();
                if (contents instanceof List) {
                    for (Object ob: (List)contents) {
                        if (ob instanceof BigDecimal) {
                            sum = sum.add((BigDecimal) ob);
                        }
                    }
                }
            }
        }
     log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると" + sum + "です");
    }
}
