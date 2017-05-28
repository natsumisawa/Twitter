package javatry.enjoy.sawa;

import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.Comparator;

/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa
 */
public class SawaEx0StreamStringTest extends ColorBoxTestCase {

    // done sawa 不要なimport文削除 by yuto (2017/05/27)

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        // done sawa 文字列を取り出すので、Oprtional<String> っていう型にしよう  by yuto.eguma (2017/05/23)
        String log = getColorBoxList().stream().flatMap(colorBox -> colorBox
                .getSpaceList().stream().map(BoxSpace::getContents)).filter(s -> s instanceof String)
                .max(Comparator.comparing(Object::toString)).map(object -> (String) object).map(s -> "カラーボックスに入ってる文字列の中で、一番長い文字列は" + s + "です").orElse("文字列は存在しません");
        // done sawa [修行] 文字列が一つも存在しない場合、存在しない旨をログに出そう。ただし、if文は使わないこと by yuto.eguma (2017/05/23)
        // done sawa [修行++] map()を使ってみよう。文字列があればそれ、なければないことを説明する文字列を返すって使い方で。 by yuto (2017/05/27)
        log(log);
    }
    // done sawa 思い出コードはインデントをコードと合わせて欲しい by yuto.eguma (2017/05/23)
    // TODO done [コメント] 今みたら思い出でもなんでもなくただの意味不明コードだったので消しました by sawa (2017/05/24)
    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        int sumLength = getColorBoxList().stream().flatMap(colorBox -> colorBox
                .getSpaceList().stream().map(BoxSpace::getContents)).filter(s -> s instanceof String).map(s -> {
                    int count = 0;
                    count += ((String) s).length();
                    return count;
                }
        ).mapToInt(Integer::intValue).sum();
        log("カラーボックスに入ってる文字列の長さの合計は" + sumLength + "です");
    }
    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        // done sawa かまくらが、複数のカラーボックスの中に入っていてもこれだと一つだけになってしまう by yuto.eguma (2017/05/23)
        // done sawa filterの中身、"&&"を使って1行にできない？ retrun false って書いた時点でもっとシンプルに書こうって気持ちになれるといいな。 by yuto.eguma (2017/05/23)
        // done sawa javafx.util.Pair っていうのが一応あるよ by yuto.eguma (2017/05/23)
        // TODO done [コメント] filterでもできると気づいたので全体的に書き直しました！ by sawa (2017/05/26)
        // done sawa findFirstしてしまうと、複数のカラーボックスに文字列が入っていたらどうなるか問題が発生する by yuto (2017/05/27)
        String log = getColorBoxList().stream()
                .filter(colorBox -> colorBox
                        .getSpaceList().stream().map(BoxSpace::getContents).filter(obj -> obj instanceof String)
                        .anyMatch(str -> ((String) str).startsWith("かまくら")))
                .map(colorBox -> colorBox.getColor().getColorName()).reduce((value1, value2) -> value1 + "と" + value2)
                .map(colorName -> "「かまくら」で始まる文字列をしまっているカラーボックスは" + colorName + "です").orElse("「かまくら」で始まる文字列をしまっているカラーボックスはありません");
        log(log);
    }
}