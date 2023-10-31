package baseball;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test extends HttpServlet {
    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/plain"); // レスポンスのコンテンツタイプを設定
        response.setCharacterEncoding("UTF-8"); // 文字エンコーディングを設定

        // レスポンスに「こんにちは」というテキストを書き込む
        response.getWriter().write("こんにちは");
    }
}
