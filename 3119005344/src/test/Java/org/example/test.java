package org.example;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

public class test {
    @Test
    public void addTest() {
        String[] s = {"text\\orig.txt", "text\\orig_0.8_add.txt", "text\\ans.txt"};
        try {
            File file = new File(s[2]);
            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) throw new Exception();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write("与orig_0.8_add.txt重复率: ");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.main(s);
    }

    @Test
    public void delTest() {
        String[] s = {"text\\orig.txt", "text\\orig_0.8_del.txt", "text\\ans.txt"};
        try {
            File file = new File(s[2]);
            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) throw new Exception();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write("与orig_0.8_del.txt重复率: ");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.main(s);
    }

    @Test
    public void dis_1Test() {
        String[] s = {"text\\orig.txt", "text\\orig_0.8_dis_1.txt", "text\\ans.txt"};
        try {
            File file = new File(s[2]);
            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) throw new Exception();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write("与orig_0.8_dis_1.txt重复率: ");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.main(s);
    }

    @Test
    public void dis_10Test() {
        String[] s = {"text\\orig.txt", "text\\orig_0.8_dis_10.txt", "text\\ans.txt"};
        try {
            File file = new File(s[2]);
            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) throw new Exception();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write("与orig_0.8_dis_10.txt重复率: ");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.main(s);
    }

    @Test
    public void dis_15Test() {
        String[] s = {"text\\orig.txt", "text\\orig_0.8_dis_15.txt", "text\\ans.txt"};
        try {
            File file = new File(s[2]);
            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) throw new Exception();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write("与orig_0.8_dis_15.txt重复率: ");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.main(s);
    }

    @Test
    public void sameTest() {
        String[] s = {"text\\orig.txt", "text\\orig.txt", "text\\ans.txt"};
        try {
            File file = new File(s[2]);
            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) throw new Exception();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            fileWriter.write("与orig.txt的重复率: ");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.main(s);
    }

    @Test
    public void emptyTest() {
        String s = "text\\orig.txt";
        String t = "text\\empty.txt";
        String ansPath = "text\\ans.txt";
        Solve.solve(s, t, ansPath);
    }

    @Test
    public void filePathTest() {
        String s = "text\\orig.tx";
        String t = "text\\o.txt";
        String ansPath = "text\\ans.txt";
        Solve.solve(s, t, ansPath);
    }

}
