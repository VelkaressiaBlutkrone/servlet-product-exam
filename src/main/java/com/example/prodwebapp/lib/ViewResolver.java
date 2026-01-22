package com.example.prodwebapp.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import jakarta.servlet.ServletException;

public class ViewResolver {

    /**
     * 지정된 뷰 이름에 해당하는 Mustache 템플릿을 로드하고 컴파일하여 View 객체를 반환합니다.
     *
     * @param viewName 템플릿 파일명 (확장자 제외)
     * @return 컴파일된 Mustache 템플릿을 포함한 View 객체
     * @throws ServletException 템플릿 파일을 찾을 수 없는 경우
     * @throws IOException      템플릿 파일을 읽는 중 I/O 오류가 발생한 경우
     */
    public static View render(String viewName) throws ServletException, IOException {
        // 템플릿 파일 경로 생성 (templates/ 디렉토리 내의 .mustache 파일)
        String resourcePath = "templates/" + viewName + ".mustache";
        // 클래스 로더를 통해 리소스 스트림 가져오기
        InputStream in = ViewResolver.class.getClassLoader()
                .getResourceAsStream(resourcePath);

        // 템플릿 파일이 존재하지 않는 경우 예외 발생
        if (in == null) {
            throw new ServletException("Template not found: " + resourcePath);
        }

        // UTF-8 인코딩으로 템플릿 파일을 읽어 Mustache 템플릿으로 컴파일
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            Template template = Mustache.compiler().compile(reader);
            return new View(template);
        }
    }
}
