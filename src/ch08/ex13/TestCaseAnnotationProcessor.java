/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex13;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

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
        Map<ClassInfo, List<MethodInfo>> files = new HashMap<>();

        for (TypeElement t : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
                ClassInfo ci = new ClassInfo(e);
                List<MethodInfo> methodInfos = files.get(ci);
                if (methodInfos == null) {
                    methodInfos = new ArrayList<>();
                }

                MethodInfo mi = MethodInfo.createInstance(e);
                if (mi == null) {
                    continue;
                }

                methodInfos.add(mi);
                files.put(ci, methodInfos);
            }
        }

        generateCode(files);
        return true;
    }

    private static class MethodInfo {

        final String name;
        final TestCase[] testCases;

        static MethodInfo createInstance(Element e) {
            ExecutableElement et = (ExecutableElement) e;
            List<? extends VariableElement> params = et.getParameters();
            if (params.size() != 1) {
                System.out.println("illegal number of parameters: " + params);
                return null;
            }
            TypeKind paramType = params.get(0).asType().getKind();

            if (paramType != TypeKind.INT) {
                System.out.println("int parameter is expected, but " + paramType);
                return null;
            }
            TypeKind returnType = et.getReturnType().getKind();
            if (returnType != TypeKind.INT) {
                System.out.println("int return is expected, but " + returnType);
                return null;
            }
            return new MethodInfo(e);
        }

        MethodInfo(Element e) {
            ExecutableElement et = (ExecutableElement) e;
            name = e.getSimpleName().toString();
            testCases = e.getAnnotationsByType(TestCase.class);
        }

        void generateTestCode(PrintWriter out, String className) {
            for (TestCase tc : testCases) {
                generateTestCodeForOneTestCase(tc, out, className);
            }

        }

        private void generateTestCodeForOneTestCase(TestCase tc, PrintWriter out, String className) {
            out.println("\t\t{");
            out.printf("\t\t\t%s target = new %s();%n", className, className);
            out.printf("\t\t\tint param = %d;%n", tc.param());
            out.printf("\t\t\tint expected = %d;%n", tc.expected());
            out.printf("\t\t\tint result = target.%s(param);%n", name);
            out.printf("\t\t\tif (result != expected) {%n");
            out.printf("\t\t\t\tSystem.out.printf(\"%%s: exptected 0, but %%d%%n\", \"%s\", expected);%n", name);
            out.printf("\t\t\t} else {%n");
            out.printf("\t\t\t\tSystem.out.printf(\"%%s(%%d) passed%%n\", \"%s\", param);", name);
            out.printf("\t\t\t}%n");
            out.println("\t\t}");
            out.flush();
        }
    }

    private static class ClassInfo {

        final String packageName;
        final String className;

        ClassInfo(Element e) {
            String[] names = ((TypeElement) e.getEnclosingElement()).getQualifiedName().toString().split("\\.");
            packageName = getPackageName(names);
            className = getClassName(names);
        }

        static String getPackageName(String[] names) {
            String packageName = "";
            if (names.length == 1) {
                return "";
            }

            for (int i = 0; i < (names.length - 2); i++) {
                packageName += names[i] + ".";
            }
            return packageName + names[names.length - 2];
        }

        private String getClassName(String[] names) {
            return names[names.length - 1];
        }

        @Override
        public String toString() {
            return "[" + packageName + ", " + className + "]";
        }

        @Override
        public int hashCode() {
            return Objects.hash(packageName, className);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof ClassInfo)) {
                return false;
            }

            ClassInfo oci = (ClassInfo) o;

            return Objects.equals(oci.packageName, this.packageName)
                    && Objects.equals(oci.className, this.className);
        }

    }

    private static void generateCode(Map<ClassInfo, List<MethodInfo>> files) {
        for (Map.Entry<ClassInfo, List<MethodInfo>> e : files.entrySet()) {
            ClassInfo ci = e.getKey();
            List<MethodInfo> methods = e.getValue();

            try (PrintWriter out = new PrintWriter(new FileOutputStream(ci.className + "Test.java"))) {

                generateHeads(out, ci);

                for (MethodInfo method : methods) {
                    method.generateTestCode(out, ci.className);
                }

                generateTail(out);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TestCaseAnnotationProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void generateHeads(PrintWriter out, ClassInfo ci) {
        out.printf("package %s;%n%n", ci.packageName);
        out.printf("public class %sTest {%n", ci.className);
        out.printf("\tpublic static void main(String[] args) {%n");
    }

    private static void generateTail(PrintWriter out) {
        out.printf("\t}%n}%n");
        out.flush();
    }

}
