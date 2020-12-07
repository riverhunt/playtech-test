package org.aut;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.nio.file.FileSystems;
import java.util.List;

public class RunTestng {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        String path = FileSystems.getDefault().getPath("src/test/resources/testng.xml").toString();
        suites.add(path);
        testng.setTestSuites(suites);
        testng.run();
    }
}
