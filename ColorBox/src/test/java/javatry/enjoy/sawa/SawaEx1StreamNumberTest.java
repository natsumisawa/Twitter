package javatry.enjoy.sawa;

import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Comparator;
import java.util.Optional;
import java.util.List;
import java.util.Collection;
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
        String log = getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream()
                        .map(BoxSpace::getContents)
                        .filter(obj -> obj instanceof Map<?, ?>))
                .flatMap(map -> ((Map<?, ?>) map).entrySet().stream())
                .max(Comparator.comparing(entry -> (Integer) entry.getValue()))
                .map(entry -> "青色のカラーボックスに入ってる Map の中の商品で一番高いものは" + entry.getKey() + "です")
                .orElse("青色のカラーボックスに入ってる Map はありません");
        log(log);
        //------------思い出コード------------------------------
        //getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
        //        .flatMap(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents)
        //                .filter(obj -> obj instanceof Map<?, ?>)
        //                .flatMap(map -> ((Map<?, ?>) map).entrySet().stream().map(Map.Entry::getValue))).max(Comparator.comparing(Object::toString));
        //
        //Stream<Object> mapContents = getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
        //        .flatMap(colorBox -> colorBox.getSpaceList().stream().map(boxSpace ->
        //                boxSpace.getContents()).filter(object ->
        //                object instanceof Map<?, ?>));
        //Object o = mapContents.collect(Collectors.toList()).get(0);
        //(Map<?, ?>)o.entrySet().
        //log(blue.collect(Collectors.toList()).get(0));
        //getColorBoxList().stream().map(colorBox -> colorBox.getSpaceList()
        //.stream().map(BoxSpace::getContents))
        //.forEach(objectStream -> objectStream
        //.forEach(System.out::println));
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {

        Optional<BigDecimal> listBdOpt = getColorBoxList().stream().flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContents)
                .filter(content -> content instanceof List)
                .map(listContent -> (List) listContent)
                .flatMap(Collection::stream)
                .filter(list -> list instanceof BigDecimal)
                .map(content -> (BigDecimal) content)
                .reduce(BigDecimal::add);
        Optional<BigDecimal> bdOpt = getColorBoxList().stream().flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContents)
                .filter(contents -> contents instanceof BigDecimal)
                .map(bdContent -> (BigDecimal) bdContent)
                .reduce(BigDecimal::add);
        BigDecimal answer = getAnswer(listBdOpt, bdOpt);
        if (answer == null) {
            log("カラーボックスの中に BigDecimal は入っていません");
        } else {
            log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると" + answer + "です");
        }
    }

    private BigDecimal getAnswer(Optional<BigDecimal> listBdOpt, Optional<BigDecimal> bdOpt) {
        if (!listBdOpt.isPresent() && !bdOpt.isPresent()) {
            return null;
        } else if(listBdOpt.isPresent() && !bdOpt.isPresent()) {
            return listBdOpt.get();
        } else if(!listBdOpt.isPresent()) {
            return bdOpt.get();
        }
        return listBdOpt.get().add(bdOpt.get());
    }
}
