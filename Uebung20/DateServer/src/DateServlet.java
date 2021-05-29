package Uebung20.DateServer.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "dateservlet", urlPatterns = { "/dateservlpet" })
public class DateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");

        String ipAddresses = "";
        try {
            ipAddresses = NetworkInterface.getNetworkInterfaces().toString();
        } catch (SocketException socketException) {
            System.out.println(socketException.getMessage());
        }
        String localTime = LocalTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("HH:mm:ss")); 
        String localDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)); 

        out.println(ipAddresses);
        out.println(localDate);
        out.println(localTime);
        out.flush();
    }

    public static void main(String[] args) {
        DateServlet dateServlet = new DateServlet(); 
        try {
            dateServlet.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
