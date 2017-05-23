package javatry.enjoy.sawa;

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
        // TODO sawa 文字列を取り出すので、Oprtional<String> っていう型にしよう  by yuto.eguma (2017/05/23)
        Optional<Object> maxStrOpt = getColorBoxList().stream().flatMap(colorBox -> colorBox
                .getSpaceList().stream().map(BoxSpace::getContents)).filter(s -> s instanceof String)
                .max(Comparator.comparing(Object::toString));
        // TODO sawa [修行] 文字列が一つも存在しない場合、存在しない旨をログに出そう。ただし、if文は使わないこと by yuto.eguma (2017/05/23)
        maxStrOpt.ifPresent(str -> log("カラーボックスに入ってる文字列の中で、一番長い文字列は" + str + "です"));
    }

    // TODO sawa 思い出コードはインデントをコードと合わせて欲しい by yuto.eguma (2017/05/23)
//-----------------思い出コード------------------------
//Object content = boxSpace.getContents();
//String str = "";
//if (content instanceof String) {
//    str = (String) content;
//}
//return str;s
//getColorBoxList().stream().flatMap(colorBox ->
//    colorBox.getSpaceList().stream().map(
//            boxSpace -> {
//                Object content = boxSpace.getContents();
//                List<String> strContentList = new ArrayList<>();
//                if (content instanceof String) {
//                    strContentList.add((String) content);
//                }
//                return Stream.of(strContentList);
//            }
//    )
//).max(Comparator.comparing());

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

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        // TODO sawa かまくらが、複数のカラーボックスの中に入っていてもこれだと一つだけになってしまう by yuto.eguma (2017/05/23)
        // TODO sawa filterの中身、"&&"を使って1行にできない？ retrun false って書いた時点でもっとシンプルに書こうって気持ちになれるといいな。 by yuto.eguma (2017/05/23)
        Optional<String> kamakuraOptional = getColorBoxList().stream()
                .map(colorBox ->
                        new Pair<>(colorBox.getColor().getColorName(), colorBox.getSpaceList().stream().map(BoxSpace::getContents)))
                .filter(pair -> pair.getRight().anyMatch(object -> {
                    if (object instanceof String) {
                        return ((String) object).startsWith("かまくら");
                    } else {
                        return false;
                    }
                })).findFirst().map(Pair::getLeft);
        if (kamakuraOptional.isPresent()) {
            // TODO sawa "「"が足りない by yuto.eguma (2017/05/23)
            log("かまくら」で始まる文字列をしまっているカラーボックスの色は" + kamakuraOptional.get() + "です");
        } else {
            log("「かまくら」で始まる文字列をしまっているカラーボックスはありません");
        }
    }
    // TODO sawa javafx.util.Pair っていうのが一応あるよ by yuto.eguma (2017/05/23)
    class Pair<LEFT, RIGHT> {
        private LEFT left;
        private RIGHT right;

        public Pair(LEFT left, RIGHT right) {
            this.left = left;
            this.right = right;
        }

        public LEFT getLeft() {
            return left;
        }

        public RIGHT getRight() {
            return right;
        }
    }
}