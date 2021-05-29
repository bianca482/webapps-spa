package com.example.server.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "dateservlet", urlPatterns = { "/dateservlet" })
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

        try {
            String jsonString = new JSONObject()
                .put("ip_adressen", ipAddresses)
                .put("datum", localDate)
                .put("uhrzeit", localTime)
                .toString();
            JSONObject json = new JSONObject(jsonString);
            out.println(json);
            out.flush();
        } catch (JSONException e) {
            out.println(e);
        }
    }  
}
