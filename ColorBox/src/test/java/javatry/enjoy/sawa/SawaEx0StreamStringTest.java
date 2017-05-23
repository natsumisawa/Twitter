package javatry.enjoy.sawa;

import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Optional<Object> maxStrOpt = getColorBoxList().stream().flatMap(colorBox -> colorBox
                .getSpaceList().stream().map(BoxSpace::getContents)).filter(s -> s instanceof String)
                .max(Comparator.comparing(Object::toString));
        maxStrOpt.ifPresent(str -> log("カラーボックスに入ってる文字列の中で、一番長い文字列は" + str + "です"));
    }

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
        getColorBoxList().stream()
                .map(colorBox ->
                        new Pair<>(colorBox.getColor().getColorName(), colorBox.getSpaceList().stream().map(BoxSpace::getContents)))
                .filter(pair -> pair.getRight().filter(content -> content instanceof String).anyMatch(str -> s))

        Stream<Object> かまくら = getColorBoxList().stream()
                .flatMap(colorBox -> {
                    Optional<Object> かまくら1 = colorBox
                            .getSpaceList().stream()
                            .map(BoxSpace::getContents)
                            .filter(content -> content instanceof String)
                            .filter(str -> str.toString().startsWith("かまくら"))
                            .findFirst();


                });
        log(かまくら.collect(Collectors.toList()));


        String colorName = getColorBoxList().stream()
                .map(box -> new Pair<>(box.getColor().getColorName(), box.getSpaceList().stream().map(BoxSpace::getContents)))
                .filter(pair -> pair.getRight().anyMatch(obj -> {
                    if (obj instanceof String) {
                        return ((String) obj).startsWith("かまくら");
                    }
                    return false;
                }))
                .findFirst()
                .map(Pair::getLeft)
                .orElse("そんな箱ねーよ");


    }



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