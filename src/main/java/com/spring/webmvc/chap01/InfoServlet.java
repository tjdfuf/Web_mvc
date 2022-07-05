package com.spring.webmvc.chap01;

/*
    # 서블릿: http 요청과 응답 데이터를 쉽게 처리할 수 있도록 도와주는 자바의 API
*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

    public InfoServlet() {
        System.out.println("infoServlet constructor call!!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("info request");

        // 요청 정보 받기
        Double height = Double.parseDouble(req.getParameter("height"));
        Double weight = Double.parseDouble(req.getParameter("weight"));

        // 응답 정보 생성하기
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter w = resp.getWriter();

        w.write("<html>");
        w.write("<body>");
        w.write("<h1>당신의 키는 " + height + "</h1>");
        w.write("<h2>당신의 몸무게는 " + weight + "</h2>");
        double bmi = calcBMI(height, weight);
        w.write("<h3>당신의 BMI는 " + calcBMI(height,weight) + "입니다.");

        if (bmi > 23.0) {
            w.write("<p>과체중입니다.</p>");
        } else if (bmi < 18.5) {
            w.write("<p>저체중입니다.</p>");
        } else {
            w.write("<p>정상입니다.</p>");
        }


        w.write("</body>");
        w.write("</html>");

    }

    private double calcBMI(double cm, double kg) {
        double m = cm/100;
        double bmi = kg/ (m*m);
        return bmi;
    }

}
