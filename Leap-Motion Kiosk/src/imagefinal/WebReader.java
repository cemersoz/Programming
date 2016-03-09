/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefinal;
import java.net.*;
import java.io.*;
/**
 *
 * @author cem
 */
public class WebReader {
    public int a=1;
    public WebReader() throws IOException{
        URL oracle = new URL("https://www.google.com.tr/search?q=istanbul+weather&oq=istanbu&aqs=chrome.1.69i60j0l2j69i57j69i59j0.61389j0j4&sourceid=chrome&es_sm=122&ie=UTF-8");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
            a++;
            if(inputLine.contains("<div class=\"vk_bk sol-tmp\""))
                break;
        }
        in.close();
    }
    public void read() throws IOException{
        System.out.println();
    }
}

