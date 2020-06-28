package com.atwzh.sell;


import com.atwzh.sell.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
public class TextTest {

    public void fun() {
        List<String> msisdns = new ArrayList<String>();
        try {
            String txtFile = FileUtil.readTxtFile("c.txt", "utf-8");
            String[] split = txtFile.split("\n");
            msisdns= Arrays.asList(split);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun1() throws IOException {
        // 记录创建时间为当前时间
//        Set<String> strings = Charset.availableCharsets().keySet();
//        for(String s : strings) {
//            System.out.println(s);
//        }
//
//        Charset charset = Charset.defaultCharset();
//        System.out.println(charset);

        PrintWriter logWriter = null;
        Date createDate = new Date();
        String fileName = "C:\\Users\\Administrator\\Desktop\\1.txt";
        String content = new String("aaaa".getBytes(), "GB2312");
        try
        {
            // 生成文件对象，如果文件不存在，要创建新文件。
            File file = new File(fileName);

            if (!file.exists() && file.createNewFile())
            {
                // 上面方法有返回值，findBugs会报警，所以采用空实现的形式解决
                ;
            }

            // 获得当前文件大小
            long fileSize = file.length();
            file = null;

            // 打开输出
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, true),"GB2312");
            logWriter = new PrintWriter(osw, true);
//            logWriter = new PrintWriter(new FileWriter(fileName, true), true);

        }
        catch (IOException e)
        {
            e.printStackTrace();
            logWriter = null;
        }

        logWriter.print(content);
//        logWriter.print("中文");
        logWriter.flush();
    }


    @Test
    public void fun3() {
        List<String> result = new ArrayList<>();
        String str1 = "Abc";
        String str2 = "测试";
//        result.add(str1);
        result.add(str2);
        String fileName = "b";

        String filePath = "C:\\Users\\Administrator\\Desktop\\txt\\";

        BufferedWriter out = null;
        try {
            if (result != null && !result.isEmpty() && StringUtils.isNotEmpty(fileName)) {
                fileName += "_" /*+ System.currentTimeMillis()*/ + ".txt";
                File pathFile = new File(filePath);
                if(!pathFile.exists()){
                    pathFile.mkdirs();
                }
                String relFilePath = filePath + File.separator + fileName;
                File file = new File(relFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), "GBK"));
//                //标题头
//                out.write("curr_time,link_id,travel_time,speed,reliabilitycode,link_len,adcode,time_stamp,state,public_rec_time,ds");
//                out.newLine();
                for (String info : result) {

                    out.write(info);
                    out.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void fun4(){
        List<String> result = new ArrayList<>();
        String str1 = "Abc";
        String str2 = "摆脱贫困";
//        result.add(str1);
        result.add(str2);
        String fileName = "a";

        String filePath = "C:\\Users\\Administrator\\Desktop\\txt\\";
        PrintWriter logWriter = null;
        BufferedWriter out = null;
        try {
            if (result != null && !result.isEmpty() && StringUtils.isNotEmpty(fileName)) {
                fileName += "_" /*+ System.currentTimeMillis()*/ + ".txt";
                File pathFile = new File(filePath);
                if(!pathFile.exists()){
                    pathFile.mkdirs();
                }
                String relFilePath = filePath + File.separator + fileName;
                File file = new File(relFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
//                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, true),"GBK");
                logWriter = new PrintWriter(new FileWriter(fileName, true), true);
                for (String info : result) {

                    logWriter.print(info);
                }
                logWriter.flush();
                logWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


        }

    }

    @Test
    public void fun5(){
        String month = "201813";
        fixedIllegalMonth(month);
        System.out.println(month);
    }




    /**
     * 纠正非法月份
     *
     * @param month 月份
     */
    private void fixedIllegalMonth(String month) {
        boolean flag = isValidDate(month);
        if (!flag) {
            month = "";
        }
    }

    /**
     * 判断年月字符串是否是合法日期
     *
     * @param time 月份字符串,yyyymm
     * @return 如果合法, 返回true.否则, 返回false
     */
    private boolean isValidDate(String time) {
        int monthSize = 6;
        if (time.length() != monthSize) {
            return false;
        }
        String monthString = time.substring(4, 6);
        try {
            int month = Integer.parseInt(monthString);
            if (month < 0 || month > 12) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Test
    public void fun6() throws IOException {
        String filePath = "C:\\Users\\Administrator\\Desktop\\2.txt";
        File file=new File(filePath);
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
        StringBuffer sb = new StringBuffer();
        String line=null;
        while((line=br.readLine())!=null){
            if(!line.trim().equals("")){
                String regex = "[0-9]";
                boolean flag = Pattern.matches(regex, line);
                if(!flag) {
                    System.out.println(line);
                }
                sb.append(line).append("\n");
            }
        }


        File filename = new File("C:\\Users\\Administrator\\Desktop\\11.txt");

        FileWriter fw = new FileWriter(filename);

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"UTF-8")));
        out.write(sb.toString());
        out.flush();
        out.close();
    }


    @Test
    public void fun7() {

        String str = "Rsp_7317349000369_OrdSub_20191106111509_BBOSS.020";
        System.out.println(str.substring(str.indexOf('_') + 1));
    }
}
