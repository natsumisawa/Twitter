package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.*;
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
        getColorBoxList().stream().flatMap(colorBox ->
                colorBox.getSpaceList().stream().map(BoxSpace::getContents))
                .filter(s -> s instanceof String)
                .sorted().forEach(c -> log(c));
    }

//                        {
//                            Object content = boxSpace.getContents();
//                            String str = "";
//                            if (content instanceof String) {
//                                str = (String) content;
//                            }
//                            return str;s
//                        }
//                )
//        ).sorted().collect(Collectors.toList());
//        log(collect.get(collect.size() - 1));
//    }
//        getColorBoxList().stream().flatMap(colorBox ->
//                colorBox.getSpaceList().stream().map(
//                        boxSpace ->
//                        {
//                            Object content = boxSpace.getContents();
//                            List<String> strContentList = new ArrayList<>();
//                            if (content instanceof String) {
//                                strContentList.add((String) content);
//                            }
//                            return Stream.of(strContentList);
//                        }
//                )
//        ).max(Comparator.comparing());

//                .map(BoxSpace -> BoxSpace.getContents())
//                .flatMap(content -> {
//                    if (content instanceof  String) {
//                        return content;
//                    }
//                });
// 直してくれるやつ.flatMap(Collection::stream)
//                .max(Comparator.naturalOrder()));

//        List<String> strList = Arrays.asList("A", "B", "C");
//        Stream<String> stream = strList.stream();
    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        Stream<Integer> integerStream = getColorBoxList().stream().flatMap(colorBox ->
                colorBox.getSpaceList().stream().map(boxSpace ->
                {
                    Object conent = boxSpace.getContents();
                    int count = 0;
                    if (conent instanceof String) {
                        count++;
                    }
                    return count;
                })).collect(Collectors.toList()).stream();
    }
    
    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
    }
}