package javatry.enjoy.sawa;

import javatry.colorbox.AbstractColorBox;
import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * デビルテスト。<br>
 * 何かやれって言われたらやること。
 *
 * @author sawa
 */
public class SawaEx2DevilTest extends ColorBoxTestCase {

    /**
     * (このテストメソッドの中だけで) 赤いカラーボックスの高さを 160 に変更せよ
     */
    public void test_devil1() {
        getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("red"))
            .map(colorBox -> {
                try {
                    Class<BoxSize> reflection = BoxSize.class;
                    Field height = reflection.getDeclaredField("height");
                    log("あったよん");
                    height.set(, 160);
                    return height.get(reflection);
                } catch (NoSuchFieldException e) {
                    // TODO done sawa NoSuchFieldExceptionだからクラス生成できなかったわけじゃないかなー by tominaga (2017/06/09)
                    // TODO done sawa 例外が出たときはどんなエラーがでたらもログに出力してあげよう e.g. log("デバッグ用メッセージ", e) by tominaga (2017/06/09)
                    log("フィールドがありませんでした", e);
                    return null;
                } catch (IllegalAccessException e) {
                    log("オブジェクトがおかしい", e);
                    return null;
                }
            })
            // TODO sawa これだとリフレクション使って「height」の変更できてないよぅ「ColorBox」のフィールド「Sizeクラス」を160に変更してそのままログ出力されているように見える by tominaga (2017/06/09)
            .forEach(height -> log("高さを" + height + "に変更しました"));
    }

    /**
     * nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっているスペースの中のリストの中のBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？
     */
    public void test_devil2() {
        Optional<Integer> onesPlaceOpt = getColorBoxList().stream()
            .filter(colorbox -> {
                String colorName = colorbox.getColor().getColorName();
                String endChar = colorName.substring(colorName.length() - 1);
                return getColorBoxList().stream()
                    .filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents).anyMatch(Objects::isNull))
                    .map(colorBox -> colorBox.getColor().getColorName().substring(2, 3)).anyMatch(s -> s.equals(endChar));
            })
            //ここまでで「nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックス」を取得 -> blue
            .map(colorBox -> {
                Integer depthNum = Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1));
                return getColorBoxList().stream()
                    .flatMap(box -> box.getSpaceList().stream())
                    .map(BoxSpace::getContents)
                    .filter(o -> o instanceof List<?>)
                    .map(list -> (List<?>) list)
                    .flatMap(Collection::stream)
                    .filter(o -> o instanceof BigDecimal)
                    .map(o -> (BigDecimal) o)
                    .filter(bigDecimal -> bigDecimal.scale() >= 2)
                    .filter(bigDecimal -> Integer.parseInt(bigDecimal.toString().substring(3, 4)) == depthNum)
                    .map(bigDecimal -> Integer.parseInt(bigDecimal.toString().substring(0, 1)))
                    .findFirst()
                    // TODO sawa findFirstはnullの要素の場合、NullPointerExceptionが起きるからorElse(null) するのはやめよう by tominaga (2017/06/09)
                    .orElse(null);
            }).findFirst();
        if (onesPlaceOpt.isPresent()) {
            Optional<Object> finalOpt = getColorBoxList().stream().filter(box -> box.getColor().getColorName().length() == onesPlaceOpt.get())
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContents).findFirst();
            // TODO sawa もうちょっと丁寧にログ出力してあげよう by tominaga (2017/06/09)
            finalOpt.ifPresent(this::log);
        }
        // TODO sawa 該当するデータがない場合はその旨をログに出力してあげよう by tominaga (2017/06/09)
    }

    //¬¬¬¬¬¬¬¬¬¬¬¬思い出コード¬¬¬¬¬¬¬¬¬¬¬¬¬¬
    //カラーボックスの深さの十の位の数字
    //Integer integer = getColorBoxList().stream().map(colorBox -> Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1))).findFirst().get();
    //log(integer);
    //小数第二位より大きいbd
    //List<Integer> collect = getColorBoxList().stream().flatMap(box -> box.getSpaceList().stream())
    //    .map(BoxSpace::getContents)
    //    .filter(o -> o instanceof List<?>)
    //    .map(list -> (List<?>) list)
    //    .flatMap(Collection::stream)
    //    .filter(o -> o instanceof BigDecimal)
    //    .map(o -> (BigDecimal) o)
    //    .filter(bigDecimal -> bigDecimal.scale() >= 2)
    //    .map(bigDecimal -> Integer.parseInt(bigDecimal.toString().substring(2, 3))).collect(Collectors.toList());
    //for (Integer i: collect) {
    //    log(i);
    //}
    //nullを含んでいるカラーボックスの色の名前の3文字目
    //String s = getColorBoxList().stream()
    //    .filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents).anyMatch(Objects::isNull))
    //    .map(colorBox -> colorBox.getColor().getColorName().substring(2, 3)).findFirst().get();
    //log(s);

    /**
     * ボックスのどこかにtxtファイルが存在しています。その中身に今日の日付、名前(+あだ名)、本日学んだ内容を書いてください。<br>
     * そしてそのファイルを自分専用のディレクトリにコピーしてください。<br>
     * 書いたのち、コピー元、コピー先それぞれの中身を表示し、差がないことを確認してください。
     */
    public void test_devil3() {
        getColorBoxList().stream()
            .flatMap(colorbox -> colorbox.getSpaceList().stream())
            .map(BoxSpace::getContents)
            .filter(o -> o instanceof File)
            .map(o -> (File) o)
            .map(file -> {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(LocalDate.now().toString());
                    bw.write("澤なつみ(つんく♀), DB設計について学習しました");
                    bw.flush();
                    bw.close();
                    return file;
                } catch (IOException e) {
                    // TODO sawa 例外が起きたときはその内容もログに出力してあげよう (e.g. log("デバッグ用メッセージ", e) by tominaga (2017/06/09)
                    return null;
                }
            })
            .forEach(file -> {
                Path sourcePath = FileSystems.getDefault().getPath("/tmp/jflute.txt");
                Path targetPath = FileSystems.getDefault().getPath("copy.txt");
                try {
                    // TODO sawa ファイルが存在するときは上書きするようにしよう by tominaga (2017/06/09)
                    Files.copy(sourcePath, targetPath);
                    File targetFile = new File("copy.txt");
                    BufferedReader sourceBr = new BufferedReader(new FileReader(file));
                    BufferedReader targetBr = new BufferedReader(new FileReader(targetFile));
                    // TODO sawa もうちょっと丁寧にログ出力してあげよう by tominaga (2017/06/09)
                    log(sourceBr.readLine());
                    log(targetBr.readLine());
                } catch (IOException e) {
                    // TODO sawa 例外が起きたときはその内容もログに出力してあげよう (e.g. log("デバッグ用メッセージ", e) by tominaga (2017/06/09)
                    log("ファイルがコピーできませんでした");
                }
            }
        );
    }
}
