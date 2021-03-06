#
# 新卒研修用のJavaプロジェクト javatry
#

# ========================================================================================
#                                                                               新卒研修準備
#                                                                               ==========
src/test/java の javatry.enjoy.new20xx (卒業年) パッケージの下に、
自分の名前 (ニックネームでOK) のパッケージを作成してください。すべて小文字です。

　e.g.
　javatry
　 |-enjoy
　 |  |-new2016
　 |  |  |-kubo // ★これ
　 |  |  |  |-...

あとは、研修に従っていきます。


# ========================================================================================
#                                                                   カラーボックスチャレンジ準備
#                                                                   ======================
自分の名前のパッケージの下に、"colorbox" というパッケージを作成してください。

　e.g.
　javatry
　 |-enjoy
　 |  |-new2016
　 |  |  |-kubo
　 |  |  |  |-colorbox // ★これ

jfluteパッケージの下にある JFluteStringTest を、そのパッケージの下にコピーして、
クラス名の先頭を自分の名前に変えてください。先頭は大文字です。

　e.g.
　javatry
　 |-enjoy
　 |  |-new2016
　 |  |  |-kubo
　 |  |  |  |-colorbox
　 |  |  |  |  |-KuboStringTest.java // ★これ

Javaファイルを開いて、author を自分に変更してください。

/**
 * {@link java.lang.String}のテスト。<br />
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author kubo // ★これ
 */

StringTestが終わったら、別の XxxDateTest なども同じ要領でコピーして、やっていきましょう。


# ========================================================================================
#                                                                      カラーボックスの実装方法
#                                                                      ===================
問題を見てきましょう。

複数のカラーボックスが並んでいると想像してください。

    /**
     * 最初のカラーボックスの色の名前の文字数は？
     */
    public void test_length_basic() {

テストメソッド (実行メソッド) の JavaDoc コメントに問題が書いてあります。
何々は？と言われたら、その回答をログに出力してください。log()メソッドを使います。

getColorBoxList() というメソッドがあらかじめ用意されているので、
戻り値として List<ColorBox> が取得できます。

　e.g.
　　List<ColorBox> colorBoxList = getColorBoxList();

この複数のカラーボックスから、回答を導いてください。

　e.g.
　　List<ColorBox> colorBoxList = getColorBoxList(); // 複数のカラーボックスを取得
　　ColorBox colorBox = colorBoxList.get(0); // 最初のカラーボックスを取得
　　BoxColor boxColor = colorBox.getColor(); // カラーボックスの色を取得
　　String colorName = boxColor.getColorName(); // カラーボックスの色の名前を取得
　　log(colorName, colorName.length()); // "色の名前" と "名前の長さ" を出力

テストメソッドにカーソルを置いて、
(Eclipseであれば) ctrl + 0 を押すと、そのテストを実行できます。
成功すればグリーン、失敗すればレッドになります。
また、回答が Console に出力されるので、確認をしてください。


# ========================================================================================
#                                                                              レビューの流れ
#                                                                              ===========
レビューワーは、何か指摘事項を見つけたら、このように todo コメントを入れ、コミット＆プッシュします。
レビューイーに、チャットなどでお知らせします。

　e.g.
　　// TODO xxx これはこうです by jflute

レビューイーは、フェッチ＆プルして、確認して修正したら todo の右隣に done を入れ、コミット＆プッシュします。
レビューワーに、チャットなどでお知らせします。 

　e.g.
　　// TODO done xxx これはこうです by jflute

レビューワーは、フェッチ＆プルして、修正の確認ができたら、todoだけを削除し、コミット＆プッシュします。

　e.g.
　　// done xxx これはこうです by jflute

レビューの流れとしては、ここでおしまい。


ちまみに、通常の業務では、こういったやり取りは削除しますが、研修のコードに関しては "おもいで" のために残しておきます。
(そもそも通常の業務では、あまりコード自体にレビューコメントは書かない)
