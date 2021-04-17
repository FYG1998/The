# The
Java 写的Android app


    Android - java -APP 框架结构说明

2021-04-17 FYG add 

Android - java -APP 框架结构说明




-- undefined
    |-- AndroidManifest.xml         app清单文件
    |-- assets                      存放本地html 文件
    |   |-- webpage
    |       |-- fileChooser.html
    |-- java
    |   |-- com
    |       |-- example
    |           |-- demo
    |               |-- APPAplication.java         实例化X5内核
    |               |-- BaseActivity.java          全局的BaseActivity继承 AppCompatActivity 后续Activity继承自己定义的BaseActivity即可，
    |               |-- BaseActivityTwo.java       定义了带个 toolbar_layout 的 继承后APP头部有标题和返回按钮
    |               |-- CoreFragment.java          CoreFragment 继承BaseActivity 控制APP 5 个page页面
    |               |-- MainActivity.java          app 第一次进入时的导播图界面，
    |               |-- activity                                          activity 界面
    |               |   |-- AboutActivity.java
    |               |   |-- Browser.java
    |               |   |-- PictureDetailActivity.java
    |               |   |-- SettingActivity.java
    |               |   |-- SettingSavePage.java
    |               |   |-- VideoActivity.java
    |               |   |-- VideoPlayActivity.java
    |               |-- adapter                                            adapter适配器
    |               |   |-- FruitAdapter.java
    |               |   |-- mAdapter.java
    |               |   |-- MvAdapter.java
    |               |-- mfragment                                          fragment 碎片界面
    |               |   |-- fiveFragment.java
    |               |   |-- fourFragment.java
    |               |   |-- ImgFragment.java
    |               |   |-- oneFragment.java
    |               |   |-- threeFragment.java
    |               |   |-- twoFragment.java
    |               |-- tencent_tbs                                         tencent_tbs腾讯x5框架
    |               |   |-- FilechooserActivity.java
    |               |   |-- WebViewJavaScriptFunction.java
    |               |   |-- X5WebView.java
    |               |-- tools                                               tools 工具
    |               |   |-- bitmap.java
    |               |   |-- Fruit.java
    |               |   |-- i.java
    |               |   |-- mCallback.java
    |               |   |-- mConfig.java
    |               |   |-- mFileTool.java
    |               |   |-- mOKHttp.java
    |               |   |-- playUrl.java
    |               |   |-- QQUtil.java
    |               |   |-- SongData.java
    |               |   |-- spInfo.java
    |               |   |-- TextNotice.java
    |               |   |-- URLinfo.java
    |               |-- utils                                                utils 也是工具
    |                   |-- PackageUtils.java
    |                   |-- SPDataUtils.java
    |-- res
        |-- anim                                                           定义的弹窗dialog 动画
        |   |-- dialog_style_enter.xml
        |   |-- dialog_style_exit.xml
        |-- drawable                                                        样式 xml 例如背景颜色，或者按钮背景颜色
        |   |-- actionbar_bottom_bg.xml
        |   |-- bg_color.xml
        |   |-- bg_source.xml
        |   |-- bg_source_white.xml
        |   |-- btn1.xml
        |   |-- btn_choose.xml
        |   |-- btn_choose2.xml
        |   |-- but_a.xml
        |   |-- color_progressbar.xml
        |   |-- dialog1.xml
        |   |-- dialog_bg.xml
        |   |-- ic_launcher_background.xml
        |   |-- search_bg.xml
        |   |-- shape_btn.xml
        |   |-- splash.jpg
        |-- drawable-v24                                                     样式 xml 例如背景颜色，或者按钮背景颜色
        |   |-- bg_round_rect.xml
        |   |-- ic_eighth.png
        |   |-- ic_fifth.png
        |   |-- ic_first.png
        |   |-- ic_fourth.png
        |   |-- ic_launcher_foreground.xml
        |   |-- ic_second.png
        |   |-- ic_seventh.png
        |   |-- ic_sixth.png
        |   |-- ic_third.png
        |   |-- shape_bg_about_text_bule.xml
        |   |-- shape_bg_about_text_green.xml
        |   |-- shape_bg_about_text_purple.xml
        |   |-- shape_bg_about_text_yellow.xml
        |   |-- test.jpg
        |-- layout                                                             界面xml                                                      
        |   |-- activity_about.xml
        |   |-- activity_browser.xml
        |   |-- activity_corefragment.xml
        |   |-- activity_filechooser.xml
        |   |-- activity_include.xml
        |   |-- activity_main.xml
        |   |-- activity_picturedetail.xml
        |   |-- activity_setting.xml
        |   |-- activity_setting_save_page.xml
        |   |-- activity_video.xml
        |   |-- activity_videoplay.xml
        |   |-- dialog.xml
        |   |-- dialog_path.xml
        |   |-- downmenu.xml
        |   |-- fragment_five.xml
        |   |-- fragment_four.xml
        |   |-- fragment_img.xml
        |   |-- fragment_one.xml
        |   |-- fragment_setting.xml
        |   |-- fragment_three.xml
        |   |-- fragment_two.xml
        |   |-- fruit_item.xml
        |   |-- item_list.xml
        |   |-- layout_title_bar.xml
        |   |-- search_item_list.xml
        |   |-- songbut.xml
        |   |-- songlist.xml
        |   |-- toolbar_layout.xml
        |-- mipmap-anydpi-v26
        |   |-- ic_launcher.xml
        |   |-- ic_launcher_round.xml
        |-- mipmap-hdpi
        |   |-- ic_launcher.png
        |   |-- ic_launcher_round.png
        |-- mipmap-mdpi
        |   |-- ic_launcher.png
        |   |-- ic_launcher_round.png
        |-- mipmap-xhdpi
        |   |-- back.png
        |   |-- ic_launcher.png
        |   |-- ic_launcher_round.png
        |   |-- theme_menu_btn_quit_fg_normal0.png
        |   |-- theme_toolbar_btn_back_fg_normal0.png
        |   |-- theme_toolbar_btn_forward_fg_normal0.png
        |   |-- theme_toolbar_btn_home_fg_normal2.png
        |   |-- theme_toolbar_btn_menu_fg_normal.png
        |-- mipmap-xxhdpi
        |   |-- ic_launcher.png
        |   |-- ic_launcher_round.png
        |-- mipmap-xxxhdpi                                            图片放 xxxhdpi会高清显示
        |   |-- a2.png 
        |   |-- a5.png
        |   |-- a9.png
        |   |-- as.png
        |   |-- b7.png
        |   |-- clean.png
        |   |-- go.png
        |   |-- header.png
        |   |-- ic.png
        |   |-- icon_collect.png
        |   |-- icon_left_arrow.png
        |   |-- icon_skin.png
        |   |-- ic_launcher.png
        |   |-- ic_launcher_round.png
        |   |-- mv.png
        |   |-- notice.png
        |   |-- search_love.png
        |   |-- songlist.png
        |   |-- songlist_1.png
        |   |-- version_img.png
        |-- values                                                      颜色，文字，样式
        |   |-- colors.xml
        |   |-- strings.xml
        |   |-- styles.xml
        |-- xml 
            |-- mpath.xml                                              文件路径好像不合适没完善
            |-- network_security_config.xml                            明文流量问题，就是http与https，访问https要加这个

