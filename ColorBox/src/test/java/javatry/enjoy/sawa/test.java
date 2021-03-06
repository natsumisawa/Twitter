package javatry.enjoy.sawa;

import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by natsumi.sawa on 2017/06/01.
 */
public class test extends ColorBoxTestCase {

    public void test_findMax() {
        List<String> list = getColorBoxList().stream()
            .filter(colorBox -> {
                return colorBox.getSpaceList().stream()
                    .map(BoxSpace::getContents)
                    .filter(obj -> obj instanceof Map<?, ?>)
                    .map(o -> (Map<?, ?>) o)
                    .flatMap(map -> ((Map<?, ?>) map).entrySet().stream())
                    .filter(entry -> entry.getValue() instanceof Number)
                    .max(Comparator.comparing(entry -> ((Number) (entry.getValue())).intValue())).isPresent();
            })
            .map(colorBox -> colorBox.getColor().getColorName())
            .collect(Collectors.toList());
        for (String str : list) {
            log(str);
        }

        String log2 = getColorBoxList().stream()
            .filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
            .flatMap(colorBox -> colorBox.getSpaceList().stream())
            .map(BoxSpace::getContents)
            .filter(obj -> obj instanceof Map<?, ?>)
            .map(o -> (Map<?, ?>) o)
            .flatMap(map -> ((Map<?, ?>) map).entrySet().stream())
            .filter(entry -> entry.getValue() instanceof Number)
            .max(Comparator.comparing(entry -> ((Number) (entry.getValue())).intValue()))
            .map(entry -> "青色のカラーボックスに入ってる Map の中の商品で一番高いものは" + entry.getKey() + "です")
            .orElse("青色のカラーボックスに入ってる Map はありません");
        log(log2);
    }
}

