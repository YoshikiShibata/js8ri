/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex13;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Repeat the preceding exercise, but build a source-level annotation processor
 * emitting a program that, when executed, runs the tests in its main method.
 * (See Horstmann and Cornell, Core Java, 9th Edition, Volume 2, Section 10.6
 * for an introduction into processing source-level annotations.)
 *
 * 練習問題12 を繰り返しなさい。ただし、ソースレベルのアノテーションプロセッサを 構築しなさい。そのプロセッサは実行されると、main
 * メソッドでテストを実行するプ ログラムを生成します（ソースレベルのアノテーション処理については、Horstmann、 Cornell 著、Core
 * Java, 9th Edition, Volume 2 の10.6 節を参照してください）。
 */
@SupportedAnnotationTypes({"ch08.ex13.TestCase", "ch08.ex13.TestCases"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
