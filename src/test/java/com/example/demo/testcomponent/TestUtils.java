package com.example.demo.testcomponent;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TestUtils {

  public static String[] allTableNames = {"users", "tasks"};

  /**
   * jsonファイルから読み込んで、文字列として返却する。
   *
   * @param fileName ファイル名
   * @return json文字列
   * @throws IOException
   */
  public static String readJsonFile(String fileName) throws IOException {
    Resource resource = new ClassPathResource(fileName);
    return new String(Files.readAllBytes(resource.getFile().toPath()));
  }
}
