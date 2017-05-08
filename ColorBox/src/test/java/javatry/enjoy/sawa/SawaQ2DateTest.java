package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Locale.JAPAN;

// [コメント] 焦ってやる必要はないので、コメントの意味を考えたり、
// 自分のコードを一度読み直したりしながら、着実に進めていこう by ukwksk (2017/05/01)
/**
 * 日付関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa.natsumi
 */
// done sawa @author by yuki.wakisaka (2017/04/30)
// sawa おーい by yuki.wakisaka (2017/05/01)
// [コメント] レビューありがとうございます by sawa (2017/05/01)
// sawa そうじゃない by yuki.wakisaka (2017/05/01)
// [コメント] ...∧( 'Θ' )∧ by sawa (2017/05/01)
public class SawaQ2DateTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                             Convert
    //                                                                             =======

    /**
     * カラーボックスに入っている日付をスラッシュ区切りのフォーマットで表示したら？
     */
    public void test_convert() {
        // done sawa colorBoxListはforの引数でしか呼ばれないので、変数に出さずに書いてみよう by yuki.wakisaka (2017/04/30)
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd");
        // done sawa dateだと単体みたいなニュアンス。dateListとかかな by yuki.wakisaka (2017/05/01)
        // ついでに、dateListだけどStringが入ってるってのも気持ち悪いので、Listの中の型を変えよう
        List<LocalDate> dateList = newArrayList();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDate) {
                    // done sawa 毎回この形式に合わせるの、きっといい感じのclassがあるよ by yuki.wakisaka (2017/04/30)
                    // [コメント] ＼(^o^)／ by sawa
                    dateList.add(((LocalDate) contents));
                    // done sawa FormatterはLocalDateでもLocalDateTimeでも使いまわすので、外で定義しちゃうのがいいかな。
                    // 今回の出力形式を定義するという意味では、1行目とかでもいいくらい。 by yuki.wakisaka (2017/05/01)
                    // done sawa log(...) はfor文の外に出しておこう。処理の切り分け。 by yuki.wakisaka (2017/05/01)
                } else if (contents instanceof LocalDateTime) {
                    dateList.add(((LocalDateTime) contents).toLocalDate());
                }
            }
        }
        // done sawa リストが空の時の処理も考えよう by yuki.wakisaka (2017/05/01)
        // done sawa リストが空とnullは違うものだよ
        // 試しに53行目と58行目をコメントアウトして空のリストとして実行してみよう（コメントアウトは戻してね）by yuki.wakisaka (2017/05/02)
        if (dateList.isEmpty()) {
            log("カラーボックスの中には日付は入っていません");
        } else {
            for (LocalDate dateContent : dateList) {
                log("カラーボックスの中に入っている日付は" + dateContent.format(format) + "です");
            }
        }
    }


    // ===================================================================================
    //                                                                              Basic
    //                                                                             =======

    /**
     * カラーボックスに入っている最初の日付は何曜日？
     */
    public void test_weekOfDay() {
        // TODO done これとかも変数一回しか呼ばれないのでforの引数に直でいいですね。他のやつもこの形式で修正しましょう。 by yuki.wakisaka (2017/05/07)
        LocalDate date = null;
        // done sawa for文では日付の抽出だけをして、最後にlogを書き出すときに曜日にしよう。機能の切り分け。 by yuki.wakisaka (2017/04/30)
        // done ↑ log(...)はfor文の外に出しちゃおう、の意も含む！ by yuki.wakisaka (2017/05/01)
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                // done sawa ここで宣言する意味ないな by yuki.wakisaka (2017/04/30)
                if (contents instanceof LocalDate) {
                    date = ((LocalDate) contents);
                    break;
                } else if (contents instanceof LocalDateTime) {
                    date = ((LocalDateTime) contents).toLocalDate();
                    break;
                }
            }
        }
        if (date != null) {
            log("カラーボックスに入っている最初の日付の曜日は" + date.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN) + "です");
        } else {
            log("カラーボックスに日付は入っていません");
        }
    }

    /**
     * 色がyellowのカラーボックスに入っている二つの日付にそれぞれ3日足すと、それぞれ何曜日になる？
     */
    public void test_addThreeDays() {
        List<DayOfWeek> dayOfWeekList = newArrayList();
        for (ColorBox colorBox : getColorBoxList()) {
            // done sawa "yellow".equals(...)の方が良い
            // 理由を考えてみて、コメントに残してくださいなー by yuki.wakisaka (2017/04/30)
            // [コメント] "yellow"が先にくれば、colorBox.getColor().getColorName()で色の名前を取り出していることがすぐにわかるから(?) by sawa (2017/05/01)
            // done sawa ↑ この問題に限らず、文字列比較する際は定数を先に持ってきたほうがいい、という意味合いですね。 "hoge".equals(someStr) みたいな
            // もっと、実害がでうるかも..というところの理由があります！ by yuki.wakisaka (2017/05/01)
            // done sawa ↑ 理由を書いてからdoneにしてください... by yuki.wakisaka (2017/05/01)
            // [コメント] sawa (~~~).equels()だと、()を忘れたときに~~~の部分的な文字列との比較になってしまうかもしれないから(?) by sawa (2017/05/02)
            // done sawa どういう状況を想定しているのか不明だけど、多分それは起きない...
            // [コメント] ぬるぽで落ちるかもしれないから！！！（なるほど） by sawa (2017/05/02)
            // 忘れるみたいなものではなく、getColorName()の戻り値のパターンを考えてみてけろ by yuki.wakisaka (2017/05/02)
            // done sawa "yellow"の前後の()不要 by yuki.wakisaka (2017/05/01)
            if ("yellow".equals(colorBox.getColor().getColorName())) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate) {
                        LocalDate plusThreeDate = ((LocalDate) contents).plus(3, DAYS);
                        // done sawa weekだと「週」って意味なので、変数名と実態が異なる感じになっちゃいます... by yuki.wakisaka (2017/05/01)
                        dayOfWeekList.add(plusThreeDate.getDayOfWeek());
                    } else if (contents instanceof LocalDateTime) {
                        LocalDateTime plusThreeDate = ((LocalDateTime) contents).plus(3, DAYS);
                        dayOfWeekList.add(plusThreeDate.getDayOfWeek());
                    }
                }
            }
        }
        // [コメント] 機能の切り分けしました by sawa
        // TODO done sawa 同上（一問目） by yuki.wakisaka (2017/05/02)
        // TODO done doneになってるけど何も変わってないよね...? by yuki.wakisaka (2017/05/07)
        if (dayOfWeekList.isEmpty()) {
            log("yellowのカラーボックスには日付は入っていません");
        } else {
            for (DayOfWeek dayOfWeek : dayOfWeekList) {
                log("日付に3日足すと曜日は" + dayOfWeek.getDisplayName(TextStyle.FULL, JAPAN) + "です");
            }
        }
    }

    /**
     * 来年(2018年)の新卒が入社する日は何曜日？
     */
    public void test_weekOfDayOf2017Newcomer() {
        LocalDate entryDay = LocalDate.of(2018, 4, 1);
        // done sawa entryWeekでは「入社する週」なので entryDayのがいいすね by yuki.wakisaka (2017/05/01)
        // done ↑はentryDayでいいんだよ...日だから。↓が曜日なのでentryDay。 by yuki.wakisaka (2017/05/01)
        // done これをStringではなくDayOfWeekで持てば、比較するときも楽 by yuki.wakisaka (2017/05/01)
        DayOfWeek entryDayOfWeek = entryDay.getDayOfWeek();
        // done sawa 文字列ではなく、DayOfWeekで比較したほうが安全（タイポとか、TextStyleの差異とか） by yuki.wakisaka (2017/04/30)
        if (entryDayOfWeek.equals(DayOfWeek.SATURDAY) || entryDayOfWeek.equals(DayOfWeek.SUNDAY)) {
            // done sawa ここでentryDayOfWeekに月曜を再代入してlogに出すほうが、出力形式にブレが減る by yuki.wakisaka (2017/05/01)
            entryDayOfWeek = DayOfWeek.MONDAY;
            // done そしてDayOfWeekに再代入したなら、logの書き出しをif elseでかき分ける必要がなくなる by yuki.wakisaka (2017/05/01)
        }
        log("来年(2018年)の新卒が入社する日は" + entryDayOfWeek.getDisplayName(TextStyle.FULL, JAPAN) + "です");
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========

    /**
     * 色がyellowのカラーボックスに入っている二つの日付の日数の差は？
     */
    public void test_diffDay() {
        for (ColorBox colorBox : getColorBoxList()) {
            // done sawa 上と同じですね。コメントされたところは、他のコードでも同じことしてないか確認して、直す癖をつけましょう。 by yuki.wakisaka (2017/05/01)
            if ("yellow".equals(colorBox.getColor().getColorName())) {
                // done sawa 両方LocalDateに落とし込んでるのはGOOD
                // だけど、二つの日付がLocalDateとLocalDateTimeのときしか対応してなくない？
                // （両方LocalDateだと動かなそう） by yuki.wakisaka (2017/04/30)
                List<LocalDate> date = new ArrayList<>();
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate) {
                        date.add((LocalDate) contents);
                    }
                    if (contents instanceof LocalDateTime) {
                        date.add(((LocalDateTime) contents).toLocalDate());
                    }
                }
                // done sawa 1つの場合のlogも出そう。& 3つ以上の場合は？ by yuki.wakisaka (2017/05/01)
                // [コメント] 日付が二つの場合以外は問題の要件を満たさないので、elseを通るようにしました！ by sawa (2017/05/01)
                // [コメント] おけ、それでいいと思います！ by ukwksk (2017/05/01)
                if (date.size() == 2) {
                    long gap = ChronoUnit.DAYS.between(date.get(1), date.get(0));
                    log("二つの日付の日数の差は" + (gap) + "日です");
                } else {
                    log("yellowに入っている日付は2つではありません");
                }
            }
        }
    }
}
