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
 * @author sawa.natsumi
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
        // done sawa これだとログだけ見たときに, 何を出力しているのかわかりづらいかなぁ。もう少し丁寧にログを書いてみましょう！ by hakiba (2017/04/30)
        log("カラーボックスに入ってる日付の月を全て足すと" + monthSum + "です");
    }

    // done sawa JavaDocの@throwsでWarning!多分もともとなのだけど, 修正しましょう！ -> 「@throwsの一行を削除する」 or 「どんなときに例外が投げられるかを書いてあげる」 by hakiba (2017/04/30)
    // done sawa JavaDocの「カラーボックの中に入っている、...」の下の空行を削除 by hakiba (2017/05/01)

    /**
     * カラーボックの中に入っている、0~100までの数値の数は？
     */
    public void test_countZeroToHundred() throws Exception {
        int count = 0;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                // done sawa 「数値」は Integer だけですか？他の「数値」も考慮したコードにしてみましょう！ by hakiba (2017/04/30)
                // done sawa 【修行】もしカラーボックスにDouble, Floatなどが含まれていたときにも対応できるようにしよう！ヒント: 数値系クラスの親クラスは「Number」 by hakiba (2017/05/01)
                // TODO sawa done【修行++】if (contents instanceof Number) {...}の中の処理が、NumberとListの中のNumberのときで重複している。メソッドに切り出して再利用してみよう！ by hakiba (2017/05/10)
                // done sawa さすがにこの場合の「(Integer) contents」は一旦変数に置き換えてあげてほうが見やすいかな！ by sawa (2017/04/30)
                // done sawa せっかくBoxSpaceの中身がNumberのときは対応したのであれば, Listの中身が Number 系だったときも対応したい by hakiba (2017/05/08)
                if (contents instanceof List) {
                    for (Object listContent : (List) contents) {
                            count += checkOfContent(listContent);
                            // done sawa さすがにこの場合の「(Integer) contents」は一旦変数に置き換えてあげてほうが見やすいかな！ by sawa (2017/04/30)
                    }
                } else {
                    count += checkOfContent(contents);
                }
            }
        }
    // done sawa ここも、もう少し丁寧にログを書いてみましょう！ by hakiba (2017/04/30)
    log("カラーボックの中に入っている、0~100までの数値の数は"+count +"個です");
    }
    private static int checkOfContent(Object content) {
        if (content instanceof Number) {
            if (0 <= ((Number)content).intValue() && ((Number)content).intValue() <= 100) {
                return 1;
            }
        }
        return 0;
    }


    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========

    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        for (ColorBox colorBox : getColorBoxList()) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            if ("blue".equals(colorBox.getColor().getColorName())) {
                for (BoxSpace boxSpace : spaceList) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof Map<?, ?>) {
                        int valueMax = 0;
                        String product = "";
                        // done sawa 全体的に言えることだけど, 「e」っていう変数名は何を表していますか？ by hakiba (2017/04/30)
                        for (Map.Entry<?, ?> mapContent : ((Map<?, ?>) contents).entrySet()) {
                            // done sawa instanceofでチェックしてあげないと落ちる！ by hakiba (2017/05/08)
                            if (mapContent.getValue() instanceof Integer && mapContent.getKey() instanceof String) {
                                Integer mapValue = (Integer) mapContent.getValue();
                                String mapKey = (String) mapContent.getKey();
                            // done sawa これも全体的に言えるかもしれないけど, もう少し変数に置換してもいいと思います。mapContent.getValue()とか3回くらい使ってるし。 by hakiba (2017/05/01)
                            // [コメント] 変数にしてみたのですが、これは型キャストする場合はぬるぽは起こり得ないってことですか、？(VALUEがIntegerのものだけ取りたい) by sawa (2017/05/02)
                            // done sawa このままだとMapのValueが Integer じゃなかったとき落ちる気がするよ！落ちないように修正しましょう！ by hakiba (2017/04/30)
                                if (valueMax < mapValue) {
                                    valueMax = mapValue;
                                    product = mapKey;
                                }
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
        // done sawa maxとmaxBoxはちょっとわかりづらい。変数名を変えてみよう！ by hakiba (2017/04/30)
        int maxWidth = 0;
        String maxWidthBoxColor = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace: spaceList) {
                Object contents = boxSpace.getContents();
                int width = colorBox.getSize().getWidth();
                // done sawa And条件を使ってif文のネストを一段減らしてみよう！（ちなみにifの条件は手前から判定されるよ） by hakiba (2017/04/30)
                if (contents instanceof Integer && maxWidth < width) {
                    maxWidth = width;
                    maxWidthBoxColor = colorBox.getColor().getColorName();
                }
            }
        }
        // done sawa Integerを持っているカラーボックスが存在しなかった場合どうなる？その場合も考慮してログ表示してみよう！ by hakiba (2017/04/30)
        // done sawa 空文字チェックをしたいならisEmpty()メソッドを使いましょう！ by hakiba (2017/05/01)
        if (maxWidthBoxColor.isEmpty()) {
            log("Integerを持っているカラーボックスが存在しません");
        } else {
            log("一番幅が大きいものでInteger型を持っているカラーボックスの色は" + maxWidthBoxColor + "です");
        }
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
        List<ColorBox> colorBoxList = getColorBoxList();
        // TODO done sawa 「new BigDecimal("0.00")」は「BigDecimal.ZERO」で書き換えられる！数値系クラスはだいたい境界値をstatic変数に持っているので, それを使ってあげましょう！ by hakiba (2017/05/10)
        BigDecimal bigDecimalSum = BigDecimal.ZERO;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace e : spaceList) {
                Object contents = e.getContents();
                // done sawa カラーボックスに直接BigDecimalが入っていた場合は？ by sawa (2017/05/08)
                if (contents instanceof List) {
                    // done sawa さすがに「ob」は「obj」にしましょう！ by hakiba (2017/04/30)
                    for (Object listContent: (List)contents) {
                        if (listContent instanceof BigDecimal) {
                            bigDecimalSum = bigDecimalSum.add((BigDecimal) listContent);
                        }
                    }
                }
                if (contents instanceof BigDecimal) {
                    bigDecimalSum = bigDecimalSum.add((BigDecimal) contents);
                }
            }
        }
        // sawa インデント揃えて！基本中の基本やで！ by hakiba (2017/04/30)
        log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると" + bigDecimalSum + "です");
    }
}
