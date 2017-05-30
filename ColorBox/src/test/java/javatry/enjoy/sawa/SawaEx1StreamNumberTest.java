package javatry.enjoy.sawa;

import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Comparator;
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
        // TODO done sawa 「.flatMap(colorBox -> colorBox.getSpaceList().stream() ...」のあたりがネストしていて見づらい。修正してみよう！ by hakiba (2017/05/30)
        // TODO [コメント] sawa 解釈あってますでしょうか>< by sawa (2017/05/30)
        String log = getColorBoxList().stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
                .flatMap(colorBox -> colorBox
                        .getSpaceList().stream()
                        .map(BoxSpace::getContents)
                        .filter(obj -> obj instanceof Map<?, ?>))
                .flatMap(map -> ((Map<?, ?>) map)
                        .entrySet().stream())
                .max(Comparator.comparing(entry -> (Integer) entry.getValue()))
                .map(entry -> "青色のカラーボックスに入ってる Map の中の商品で一番高いものは" + entry.getKey() + "です")
                .orElse("青色のカラーボックスに入ってる Map はありません");
        log(log);
        //------------思い出コード------------------------------
        //getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
        //       .flatMap(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents)
        //                .filter(obj -> obj instanceof Map<?, ?>)
        //                .flatMap(map -> ((Map<?, ?>) map).entrySet().stream().map(Map.Entry::getValue))).max(Comparator.comparing(Object::toString));
        //
        //Stream<Object> mapContents = getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("blue"))
        //       .flatMap(colorBox -> colorBox.getSpaceList().stream().map(boxSpace ->
        //                boxSpace.getContents()).filter(object ->
        //                object instanceof Map<?, ?>));
        //Object o = mapContents.collect(Collectors.toList()).get(0);
        //(Map<?, ?>)o.entrySet().
        //log(blue.collect(Collectors.toList()).get(0));
        //getColorBoxList().stream().map(colorBox -> colorBox.getSpaceList()
        //      .stream().map(BoxSpace::getContents))
        //      .forEach(objectStream -> objectStream
        //      .forEach(System.out::println));
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
        // TODO done sawa 【修行】Optional<BigDecimal>で受け取っているから加算するときの処理が冗長。BigDecimalで受け取るようにしてみよう！（ヒント: OptionalクラスにはorElseというメソッドがある。） by hakiba (2017/05/30)
        // TODO done sawa 俺のintelliJだとlistBdOptを取得しているところでコンパイルエラーになる。多分Listにキャストするときに, List<?>ではなくListにキャストしているから by hakiba (2017/05/30)
        BigDecimal listContentBd = getColorBoxList().stream().flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContents)
                .filter(content -> content instanceof List<?>)
                .map(listContent -> (List<?>) listContent)
                .flatMap(Collection::stream)
                .filter(list -> list instanceof BigDecimal)
                .map(content -> (BigDecimal) content)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal contentBd = getColorBoxList().stream().flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContents)
                .filter(contents -> contents instanceof BigDecimal)
                .map(bdContent -> (BigDecimal) bdContent)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal bdSum = listContentBd.add(contentBd);
        if (bdSum.equals(BigDecimal.ZERO)) {
            log("カラーボックスの中に BigDecimal は入っていません");
        } else {
            log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると" + bdSum + "です");
        }
    }
}
