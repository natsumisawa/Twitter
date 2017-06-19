package javatry.enjoy.sawa;

import javatry.colorbox.AbstractColorBox;
import javatry.colorbox.ColorBox;
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
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<ColorBox> redBoxList = getColorBoxList().stream()
            .filter(colorBox -> colorBox.getColor().getColorName().equals("red"))
            .collect(Collectors.toList());
        for (ColorBox box: redBoxList) {
            log("変更前の赤いカラーボックスの高さは" + box.getSize().getHeight());
            AbstractColorBox abstractColorBox = (AbstractColorBox) box;
            Class<AbstractColorBox> abstractColorBoxClass = AbstractColorBox.class;
            Field boxField;
            try {boxField = abstractColorBoxClass.getDeclaredField("size");
                boxField.setAccessible(true);
                BoxSize boxSize = abstractColorBox.getSize();
                BoxSize upDateHeightSize = new BoxSize(160, boxSize.getWidth(), boxSize.getWidth());
                try {
                    boxField.set(abstractColorBox, upDateHeightSize);
                } catch (IllegalAccessException e) {
                    log("値を変更できませんでした", e);
                }
            } catch (NoSuchFieldException e) {
                log("フィールドsizeが見つかりませんでした", e);
            }
            log("変更後の赤いカラーボックスの高さは" + box.getSize().getHeight());
            // TODO sawa 下の2つの「{」のインデントがずれているよぅー by tominaga (2017/06/19)
            }
        }
    // done sawa これだとリフレクション使って「height」の変更できてないよぅ「ColorBox」のフィールド「Sizeクラス」を160に変更してそのままログ出力されているように見える by tominaga (2017/06/09)

    /**
     * nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっているスペースの中のリストの中のBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？
     */
    public void test_devil2() {
        List<Optional<Integer>> collectList = getColorBoxList().stream()
            .filter(colorbox -> {
                String colorName = colorbox.getColor().getColorName();
                String endChar = colorName.substring(colorName.length() - 1);
                return getColorBoxList().stream()
                    .filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents).anyMatch(Objects::isNull))
                    .map(colorBox -> colorBox.getColor().getColorName().substring(2, 3)).anyMatch(s -> s.equals(endChar));
            })
            //ここまでで ~nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックス~ -> blue
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
                    .findAny();
                // ここまで ~ カラーボックスの深さの十の位の数字が小数点第二桁目になっているスペースの中のリストの中のBigDecimalの一の位の数字と同じ色 ~
                // done sawa findFirstはnullの要素の場合、NullPointerExceptionが起きるからorElse(null) するのはやめよう by tominaga (2017/06/09)
            }).collect(Collectors.toList());

        if (!collectList.isEmpty()) {
            // TODO sawa Optionalだけだと型がなにかわからなくてキャストしないといけないからOptional<Integer>で変数にしてあげよう。 by tominaga (2017/06/19)
            for (Optional opt: collectList) {
                if (opt.isPresent()){
                    String answerLog = getColorBoxList().stream()
                        .filter(box -> box.getColor().getColorName().length() == (Integer) opt.get())
                        .flatMap(colorBox -> colorBox.getSpaceList().stream())
                        .map(BoxSpace::getContents)
                        .findAny()
                        .map(content -> "条件に一致するデータは" + content + "です")
                        .orElse("条件に一致するデータはありませんでした");
                    log(answerLog);
                    // done sawa もうちょっと丁寧にログ出力してあげよう by tominaga (2017/06/09)
                } else {
                    log("条件に一致するデータはありませんでした");
                }
            }
        } else {
        log("条件に一致するデータはありませんでした");
    }
    // done sawa 該当するデータがない場合はその旨をログに出力してあげよう by tominaga (2017/06/09)
    }

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
                    log("ファイルに書き込めませんでした", e);
                    // done sawa 例外が起きたときはその内容もログに出力してあげよう (e.g. log("デバッグ用メッセージ", e) by tominaga (2017/06/09)
                    return null;
                }
            })
            .forEach(file -> {
                Path sourcePath = FileSystems.getDefault().getPath("/tmp/jflute.txt");
                Path targetPath = FileSystems.getDefault().getPath("copy.txt");
                try {
                    // done sawa ファイルが存在するときは上書きするようにしよう by tominaga (2017/06/09)
                    Files.copy(sourcePath, targetPath , StandardCopyOption.REPLACE_EXISTING);
                    File targetFile = new File("copy.txt");
                    BufferedReader sourceBr = new BufferedReader(new FileReader(file));
                    BufferedReader targetBr = new BufferedReader(new FileReader(targetFile));
                    // done sawa もうちょっと丁寧にログ出力してあげよう by tominaga (2017/06/09)
                    log("コピー元のファイルの中身：" + sourceBr.readLine());
                    log("コピー先のファイルの中身：" + targetBr.readLine());
                } catch (IOException e) {
                    // done sawa 例外が起きたときはその内容もログに出力してあげよう (e.g. log("デバッグ用メッセージ", e) by tominaga (2017/06/09)
                    log("ファイルがコピーできませんでした" ,e);
                }
            }
        );
    }
}
