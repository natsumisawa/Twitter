package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import jdk.internal.util.xml.impl.Pair;
import org.omg.CORBA.MARSHAL;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数値関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa
 */
public class SawaEx1StreamNumberTest extends ColorBoxTestCase {
    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        Optional<String> blue = getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents)
                        .filter(obj -> obj instanceof Map<?, ?>))
                .map(map -> ((Map<?, ?>) map).entrySet().stream())
                .filter(m -> m.mapToInt(map -> (Integer) map.getValue()).max().isPresent())
                .flatMap(setm -> setm.map(map -> (String) map.getKey())).findFirst();
        log(blue.get());

        Optional<?> maxContent = getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents)
                        .filter(obj -> obj instanceof Map<?, ?>))
                .filter(map -> ((Map<?, ?>) map).entrySet().stream().mapToInt(m -> (Integer) m.getValue()).max().isPresent())
                .flatMap(map -> ((Map<?, ?>) map).entrySet().stream().map(Map.Entry::getKey)).findFirst();
        log(maxContent.get());

        getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents)
                        .filter(obj -> obj instanceof Map<?, ?>)
                        .flatMap(map -> ((Map<?, ?>) map).entrySet().stream().map(Map.Entry::getValue))).max(Comparator.comparing(Object::toString));

//        Stream<Object> mapContents = getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
//                .flatMap(colorBox -> colorBox.getSpaceList().stream().map(boxSpace ->
//                        boxSpace.getContents()).filter(object ->
//                        object instanceof Map<?, ?>));
//        Object o = mapContents.collect(Collectors.toList()).get(0);
//        (Map<?, ?>)o.entrySet().
//        log(blue.collect(Collectors.toList()).get(0));
//        getColorBoxList().stream().map(colorBox -> colorBox.getSpaceList()
//        .stream().map(BoxSpace::getContents))
//        .forEach(objectStream -> objectStream
//        .forEach(System.out::println));
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
    }
}
