package javatry.enjoy.sawa;

import javafx.util.Pair;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.Optional;
import java.util.Comparator;
/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa
 */
public class SawaEx0StreamStringTest extends ColorBoxTestCase {


    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        // TODO done sawa 文字列を取り出すので、Oprtional<String> っていう型にしよう  by yuto.eguma (2017/05/23)
        Optional<String> maxStrOpt = getColorBoxList().stream().flatMap(colorBox -> colorBox
                .getSpaceList().stream().map(BoxSpace::getContents)).filter(s -> s instanceof String)
                .max(Comparator.comparing(Object::toString)).map(object -> (String) object);
        // TODO done sawa [修行] 文字列が一つも存在しない場合、存在しない旨をログに出そう。ただし、if文は使わないこと by yuto.eguma (2017/05/23)
        maxStrOpt.ifPresent(str -> log("カラーボックスに入ってる文字列の中で、一番長い文字列は" + str + "です"));
        log(maxStrOpt.orElse("文字列は存在しません"));
    }

    // TODO done sawa 思い出コードはインデントをコードと合わせて欲しい by yuto.eguma (2017/05/23)
    // TODO [コメント] 今みたら思い出でもなんでもなくただの意味不明コードだったので消しました by sawa (2017/05/24)
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

        //------------------思い出コード--------------------------------------------------
        //Stream<Integer> integerStream = getColorBoxList().stream().flatMap(colorBox ->
        //        colorBox.getSpaceList().stream().map(boxSpace -> {
        //            Object content = boxSpace.getContents();
        //            int count = 0;
        //            if (content instanceof String) {
        //                count++;
        //            }
        //            return count;
        //        })).collect(Collectors.toList()).stream();
        //log(integerStream);
        //------------------思い出コード--------------------------------------------------

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        // TODO sawa かまくらが、複数のカラーボックスの中に入っていてもこれだと一つだけになってしまう by yuto.eguma (2017/05/23)
        // TODO sawa filterの中身、"&&"を使って1行にできない？ retrun false って書いた時点でもっとシンプルに書こうって気持ちになれるといいな。 by yuto.eguma (2017/05/23)

        Optional<String> kamakuraBoxColor = getColorBoxList().stream()
                .map(colorBox -> new Pair<>(colorBox.getColor(), colorBox.getSpaceList()
                        .stream().map(BoxSpace::getContents).filter(rightObj -> rightObj instanceof String).filter(str -> ((String) str).startsWith("かまくら"))).getKey().getColorName()).findFirst();
        //かまくらフィルターしているのに、他の文字列も入ってしまっている
        if (kamakuraBoxColor.isPresent()) {
            // TODO done sawa "「"が足りない by yuto.eguma (2017/05/23)
            log("「かまくら」で始まる文字列をしまっているカラーボックスの色は" + kamakuraBoxColor.get() + "です");
        } else {
            log("「かまくら」で始まる文字列をしまっているカラーボックスはありません");
        }
    // TODO done sawa javafx.util.Pair っていうのが一応あるよ by yuto.eguma (2017/05/23)

    //TODO [コメント] filterでもできると(ゆいとえいちゃん様のおかげで)気づいたのでもうひとつコード書きます
        Optional<String> boxColorOpt = getColorBoxList().stream()
                .filter(colorBox -> colorBox
                        .getSpaceList().stream().map(BoxSpace::getContents).filter(obj -> obj instanceof String)
                        .anyMatch(str -> ((String) str).startsWith("かまくら")))
                .map(colorBox -> colorBox.getColor().getColorName()).findFirst();
        if (boxColorOpt.isPresent()) {
            log(boxColorOpt.get());
        } else {
            log("「かまくら」で始まる文字列をしまっているカラーボックスはありません");
        }
    }
}