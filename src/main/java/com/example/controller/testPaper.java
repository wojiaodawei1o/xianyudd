package com.example.controller;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class testPaper {
    private static String  userNameAndPassWord;

    static String[] m_SysUser = {
            "张三1 123",
            "张三2 123",
            "张三3 123",
            "李四1 123",
            "李四1 123",
            "李四1 123",
            "王五1 123",
            "王五1 123",
            "王五1 123"
    };

    public static void main(String[] args) {
        System.out.println("请输入账号密码");
        int size = Integer.parseInt(Login());
        Create(size);
    }

    public static String CreateFuHao(){
        Random r = new Random();
        int ran1 = r.nextInt(8);
        if(ran1 == 0 || ran1 == 4 || ran1 == 5){
            return "+";
        }
        if(ran1 == 1 || ran1 == 6 || ran1 == 7){
            return "-";
        }
        if(ran1 == 2){
            return "*";
        }
        if(ran1 == 3){
            return "/";
        }
        return "";
    }

    /**
     * 判断是否重复
     * @return
     */
//    public boolean isRepeat(String subject,String grade){
//        boolean flag = false;
//        String teacherName = userNameAndPassWord.substring(0,3);//获得老师姓名
//        //小学
//        switch (grade){
//            case "xiaoxue":
//
//                break;
//        }
//
//
//    }

    public static void getDirectory(Path path) {
        try (DirectoryStream<Path> children = Files.newDirectoryStream(path)) {
            if (children != null) {
                for (Path child : children) {
                    if (Files.isDirectory(child)) {
                        // System.out.println("Dir==>"+child.toAbsolutePath());
                        getDirectory(child);
                    } else {
                        if (child.toString().matches(".+\\.txt"))
                            System.out.println("file.txt==>" + child.toAbsolutePath());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得文件内容
     * @param file
     * @return
     */
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String CreateXiaoxue(int num){
        Random r = new Random();
        String str = "";
        //循环生成多少题
        for(int i = 0 ; i < num ; i++){
            String mathStr = "";
            String no = i+1+". ";
            mathStr += no;
            //随机操作数 2~5
            int ran1 = r.nextInt(5)+1;
            //防止出现多个括号
            boolean flag = true;
            for(int j = 0 ; j < ran1 ; j++){
                String num1 = r.nextInt(101) + "";
                String fuhao1 = CreateFuHao();
                //加括号 两个操作数就不要加括号
                if(ran1 > 2){
                    if(j == 0){
                        int ran2 = r.nextInt(2);
                        if(ran2 == 1){
                            mathStr += "(";
                        }
                    }
                }

                mathStr += num1;

                if(ran1 > 2) {
                    if (j != 0 && flag) {
                        int ran2 = r.nextInt(2);
                        if (ran2 == 0) {
                            flag = false;
                            mathStr += ")";
                        }
                    }
                }
                if(j == ran1-1){
                    mathStr += "=";
                }else{
                    mathStr += fuhao1;
                }
            }
            //只有单个括号的时候去除
            if(!mathStr.contains(")") && mathStr.contains("(")){
                mathStr = mathStr.replace("(","");
            }
            if(!mathStr.contains("(") && mathStr.contains(")")){
                mathStr = mathStr.replace(")","");
            }
            //判断是否重复
            if(str.contains(mathStr)){
                i--;
            }else{
                mathStr += "\r\n";
                str += mathStr;
            }
        }
        return str;
    }

    public static String CreateChuzhong(int num){
        Random r = new Random();
        String str = "";
        //循环生成多少题
        for(int i = 0 ; i < num ; i++){
            String mathStr = "";
            String no = i+1+". ";
            mathStr += no;
            //随机操作数 2~5
            int ran1 = r.nextInt(5)+1;
            //防止出现多个括号
            boolean flag = true;
            //防止不出现平方或者更号
            boolean flag2 = true;
            for(int j = 0 ; j < ran1 ; j++){
                String num1 = r.nextInt(101) + "";
                String fuhao1 = CreateFuHao();
                //加括号 两个操作数就不要加括号
                if(ran1 > 2){
                    if(j == 0){
                        int ran2 = r.nextInt(2);
                        if(ran2 == 1){
                            mathStr += "(";
                        }
                    }
                }

                int ran3;
                //不是最后一个正常计算逻辑
                if(j != ran1-1){
                    ran3 = r.nextInt(3);
                }else{
                    //如果是最后一个了还没有出现平方更号，就给最后以数字加入
                    if(j == ran1-1  && flag2){
                        ran3 = r.nextInt(2);
                    }else{
                        ran3 = r.nextInt(3);
                    }
                }
                if(ran3 == 0){
                    flag2 = false;
                    mathStr += "√"+num1;
                }else if(ran3 == 1){
                    flag2 = false;
                    mathStr += num1 + "²";
                }else{
                    mathStr += num1;
                }



                if(ran1 > 2) {
                    if (j != 0 && flag) {
                        int ran2 = r.nextInt(2);
                        if (ran2 == 0) {
                            flag = false;
                            mathStr += ")";
                        }
                    }
                }
                if(j == ran1-1){
                    mathStr += "=";
                }else{
                    mathStr += fuhao1;
                }
            }
            //只有单个括号的时候去除
            if(!mathStr.contains(")") && mathStr.contains("(")){
                mathStr = mathStr.replace("(","");
            }
            if(!mathStr.contains("(") && mathStr.contains(")")){
                mathStr = mathStr.replace(")","");
            }
            //判断是否重复
            if(str.contains(mathStr)){
                i--;
            }else{
                mathStr += "\r\n";
                str += mathStr;
            }
        }
        return str;
    }

    public static String CreateGaozhong(int num){
        Random r = new Random();
        String str = "";
        //循环生成多少题
        for(int i = 0 ; i < num ; i++){
            String mathStr = "";
            String no = i+1+". ";
            mathStr += no;
            //随机操作数 2~5
            int ran1 = r.nextInt(5)+1;
            //防止出现多个括号
            boolean flag = true;
            //防止不出现平方或者更号
            boolean flag2 = true;
            for(int j = 0 ; j < ran1 ; j++){
                String num1 = r.nextInt(101) + "";
                String fuhao1 = CreateFuHao();
                //加括号 两个操作数就不要加括号
                if(ran1 > 2){
                    if(j == 0){
                        int ran2 = r.nextInt(2);
                        if(ran2 == 1){
                            mathStr += "(";
                        }
                    }
                }

                int ran3;
                //不是最后一个正常计算逻辑
                if(j != ran1-1){
                    ran3 = r.nextInt(5);
                }else{
                    //如果是最后一个了还没有出现sin cos tan，就给最后以数字加入
                    if(j == ran1-1  && flag2){
                        ran3 = r.nextInt(3);
                    }else{
                        ran3 = r.nextInt(5);
                    }
                }
                if(ran3 == 0){
                    flag2 = false;
                    mathStr += "sin"+num1;
                }else if(ran3 == 1){
                    flag2 = false;
                    mathStr += "cos"+num1;
                }else if(ran3 == 2){
                    flag2 = false;
                    mathStr += "tan"+num1;
                }else{
                    mathStr += num1;
                }



                if(ran1 > 2) {
                    if (j != 0 && flag) {
                        int ran2 = r.nextInt(2);
                        if (ran2 == 0) {
                            flag = false;
                            mathStr += ")";
                        }
                    }
                }
                if(j == ran1-1){
                    mathStr += "=";
                }else{
                    mathStr += fuhao1;
                }
            }
            //只有单个括号的时候去除
            if(!mathStr.contains(")") && mathStr.contains("(")){
                mathStr = mathStr.replace("(","");
            }
            if(!mathStr.contains("(") && mathStr.contains(")")){
                mathStr = mathStr.replace(")","");
            }
            //判断是否重复
            if(str.contains(mathStr)){
                i--;
            }else{
                mathStr += "\r\n";
                str += mathStr;
            }
        }
        return str;
    }

    public static void CreateFile(String directory,String filename,int size){
        File file=new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2=new File(directory,filename);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //向指定文件中写入文字
        FileWriter fileWriter;
        try {
            String str = directory+"/"+filename;
            fileWriter = new FileWriter(str);
            //使用缓冲区比不使用缓冲区效果更好，因为每趟磁盘操作都比内存操作要花费更多时间。
            //通过BufferedWriter和FileWriter的连接，BufferedWriter可以暂存一堆数据，然后到满时再实际写入磁盘
            //这样就可以减少对磁盘操作的次数。如果想要强制把缓冲区立即写入,只要调用writer.flush();这个方法就可以要求缓冲区马上把内容写下去
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            String content = "";
            if(userNameAndPassWord.contains("张三")){
                content = CreateXiaoxue(size);
            }
            if(userNameAndPassWord.contains("李四")){
                content = CreateChuzhong(size);
            }
            if(userNameAndPassWord.contains("王五")){
                content = CreateGaozhong(size);
            }
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void Create(int size){
        if(size > 30 || size < 10){
            System.out.println("请输入10~30的整数");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();
            if(num.contains("切换为")){
                ChangeSysuser(num);
            }else {
                Create(Integer.parseInt(num));
            }
        }else{
            //生成题目
            System.out.println("正在生成");
            String date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
            //小学
            if(userNameAndPassWord.contains("张三")){
                String directory = "../小学试卷/"+userNameAndPassWord.substring(0,3);
                String filename = date+".txt";
                CreateFile(directory,filename,size);
            }
            //初中
            if(userNameAndPassWord.contains("李四")){
                String directory = "../初中试卷/"+userNameAndPassWord.substring(0,3);
                String filename = date+".txt";
                CreateFile(directory,filename,size);
            }
            //高中
            if(userNameAndPassWord.contains("王五")){
                String directory = "../高中试卷/"+userNameAndPassWord.substring(0,3);
                String filename = date+".txt";
                CreateFile(directory,filename,size);
            }
        }
    }

    /**
     * 登录
     */
    public static String Login(){
        if(ValidUser()){
            String xx = "";
            if(userNameAndPassWord.contains("张三")){
                xx = "小学";
            }
            if(userNameAndPassWord.contains("李四")){
                xx = "初中";
            }
            if(userNameAndPassWord.contains("王五")){
                xx = "高中";
            }
            System.out.println("准备生成"+xx+"数学题目，请输入生成题目数量(输入-1将退出当前用户，重新登录)：");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();
            if("-1".equals(num)){
                System.out.println("请输入账号密码");
                Login();
            }else if(num.contains("切换为")){
                return ChangeSysuser(num)+"";
            }else{
                return num;
            }
        }
        return "0";
    }

    /**
     * 验证账号密码是否正确
     * @return
     */
    public static boolean ValidUser(){
        Scanner in = new Scanner(System.in);
        userNameAndPassWord = in.nextLine();
        //判断输入的账号密码是不是正确
        boolean flag = false;
        for(String str:m_SysUser){
            if(userNameAndPassWord.equals(str)){
                return true;
            }
        }
        if(flag){
            return true;
        }else{
            System.out.println("请输入正确的用户名、密码");
            return ValidUser();
        }
    }


    public static int ChangeSysuser(String msg){
        boolean flag = false;
        String xx = "";
        if("切换为小学".equals(msg)){
            userNameAndPassWord = "张三1";
            flag = true;
            xx = "小学";
        }else if ("切换为初中".equals(msg)){
            userNameAndPassWord = "李四1";
            flag = true;
            xx = "初中";
        }else if ("切换为高中".equals(msg)){
            userNameAndPassWord = "王五1";
            flag = true;
            xx = "高中";
        }else{
            System.out.println("请输入小学、初中和高中三个选项中的一个");
            Scanner in = new Scanner(System.in);
            ChangeSysuser("切换为"+in.nextLine());
        }

        if(flag){
            System.out.println("准备生成"+xx+"数学题目，请输入生成题目数量(输入-1将退出当前用户，重新登录)：");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();
            if("-1".equals(num)){
                System.out.println("请输入账号密码");
                Login();
            }else if(num.contains("切换为")){
                ChangeSysuser(num);
            }else{
                Create(Integer.parseInt(num));
                return Integer.parseInt(num);
            }
        }
        return 0;
    }

}
