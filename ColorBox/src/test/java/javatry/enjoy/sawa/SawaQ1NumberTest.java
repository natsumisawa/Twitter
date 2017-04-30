package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        // TODO sawa これだとログだけ見たときに, 何を出力しているのかわかりづらいかなぁ。もう少し丁寧にログを書いてみましょう！ by hakiba (2017/04/30)
        log(monthSum);
    }

    // TODO sawa JavaDocの@throwsでWarning!多分もともとなのだけど, 修正しましょう！ -> 「@throwsの一行を削除する」 or 「どんなときに例外が投げられるかを書いてあげる」 by hakiba (2017/04/30)
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
                // TODO sawa 「数値」は Integer だけですか？他の「数値」も考慮したコードにしてみましょう！ by hakiba (2017/04/30)
                if (contents instanceof Integer) {
                    // TODO sawa さすがにこの場合の「(Integer) contents」は一旦変数に置き換えてあげてほうが見やすいかな！ by sawa (2017/04/30)
                    if ((0 <= (Integer) contents) && ((Integer) contents <= 100)) {
                        count++;
                    }
                }
            }
        }
        // TODO sawa ここも、もう少し丁寧にログを書いてみましょう！ by hakiba (2017/04/30)
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
                        // TODO sawa 全体的に言えることだけど, 「e」っていう変数名は何を表していますか？ by hakiba (2017/04/30)
                        for (Map.Entry<?, ?> e : ((Map<?, ?>) contents).entrySet()) {
                            // TODO sawa このままだとMapのValueが Integer じゃなかったとき落ちる気がするよ！落ちないように修正しましょう！ by hakiba (2017/04/30)
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
        // TODO sawa maxとmaxBoxはちょっとわかりづらい。変数名を変えてみよう！ by hakiba (2017/04/30)
        int max = 0;
        String maxBox = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace e : spaceList) {
                Object contents = e.getContents();
                // TODO sawa And条件を使ってif文のネストを一段減らしてみよう！（ちなみにifの条件は手前から判定されるよ） by hakiba (2017/04/30)
                if (contents instanceof Integer) {
                    if (max < colorBox.getSize().getWidth()) {
                        max = colorBox.getSize().getWidth();
                        maxBox = colorBox.getColor().getColorName();
                    }
                }
            }
        }
        // TODO sawa Integerを持っているカラーボックスが存在しなかった場合どうなる？その場合も考慮してログ表示してみよう！ by hakiba (2017/04/30)
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
                    // TODO sawa さすがに「ob」は「obj」にしましょう！ by hakiba (2017/04/30)
                    for (Object ob: (List)contents) {
                        if (ob instanceof BigDecimal) {
                            sum = sum.add((BigDecimal) ob);
                        }
                    }
                }
            }
        }
        // TODO sawa インデント揃えて！基本中の基本やで！ by hakiba (2017/04/30)
     log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると" + sum + "です");
    }
}
