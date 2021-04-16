package huyvl.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.rmi.server.LogStream.log;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HUYVU
 */
public class contextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileReader fr = null;
        BufferedReader br = null;
        Map<String, String> file = new HashMap<String, String>();
        ServletContext context = sce.getServletContext();
        String filename = "/WEB-INF/pageIndex.txt";
        String realpath = context.getRealPath("/");
        
        try {
            fr = new FileReader(realpath+filename);
            br = new BufferedReader(fr);
            String line ="";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                if (line.length() >0) {
                    String[] str = line.split("=");
                    file.put(str[0], str[1]);
                }
            }
            context.setAttribute("FILE", file);
            br.close();
            fr.close();
        } catch (Exception e) {
            context.log("contextListener_Exception: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

}
