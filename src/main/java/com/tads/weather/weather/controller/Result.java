package com.tads.weather.weather.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tads.weather.weather.Model.JsonModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Result {
    @Value("${auth.key}")
    String key;

    @GetMapping("/result")
    public String result(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {

        String city = req.getParameter("fname");
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            URL url = new URL("http://api.weatherapi.com/v1/current.json?lang=pt&key=" + key + "&q=" + city);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new IOException();
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String lines;
            StringBuilder output = new StringBuilder();
            while ((lines = br.readLine()) != null) {
                output.append(lines);

            }
            conn.disconnect();
            String json = output.toString();

            Gson gson = new Gson();
            JsonModel obj = gson.fromJson(json, JsonModel.class);

            model.put("cidade", obj.location.name);
            model.put("estado", obj.location.region);
            model.put("pais", obj.location.country);
            model.put("condicao", obj.current.condition.text);
            model.put("sensacao", obj.current.feelslike_c);
            model.put("humidade", obj.current.humidity);
            model.put("vento", obj.current.wind_kph);
            if (obj.current.cloud < 50) {
                model.put("foto", "majusun.png");
            } else
                model.put("foto", "majucloud.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "result";
    }
}
