package com.springmvc.study.chapter02.test;

import com.vdurmont.emoji.EmojiParser;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class MainClass {

    public static void main(String[] args) {

        String str = "An :grinning: awesome :smiley: ";
        String result = EmojiParser.parseToUnicode(str);
        System.out.println(result);

        String str_01 = "An \uD83D\uDE00 awesome \uD83D\uDE03 ";

        System.out.println(EmojiParser.parseToAliases(str_01));


    }
}
