package vera.utils;


import org.springframework.data.repository.CrudRepository;
import vera.models.Admin;
import vera.models.User;
import vera.models.UserAuthorities;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackUpRepo {

    public static void writeString(String text, String path) {
        File f = new File(path);
        try (
             FileWriter writer = new FileWriter(f, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String repoToString(CrudRepository cr) {
        StringBuilder sb = new StringBuilder();
        List<?> list = (List<?>) cr.findAll();
        list.stream().forEach(i -> sb.append(i.toString()).append('\n'));
        return sb.toString();
    }

    public static List<?> textToList(String path, Class clas) {
        String temp = "";
        File file = new File(path);
        String text = "";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        List list = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(file));
            while ((text = br.readLine()) != null) {
                sb.append(text).append('\n');
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clas.equals(User.class)) {
            list = new LinkedList<User>();
            Pattern pattern2 = Pattern.compile("(.=)(\\d+)(,.)(userAuthorities=)(\\d)(,.)(username=')(.+)(['],.)(password=')(.+)(',.)(mailU=')(.+)(',.)(firstNameU=')(.+)(',.)(lastNameU=')(.+)(',.)(dateRegU=)(.+)(,.)(photoU=')(.+)(')");
            Matcher m2 = pattern2.matcher(sb);
            while (m2.find()) {
                Date datetemp = new Date();

                try {
                    datetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(m2.group(23));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                list.add(new User(Integer.parseInt(m2.group(2)),
                        nullCatch(m2.group(8)),
                        nullCatch(m2.group(11)),
                        new UserAuthorities(Integer.parseInt(m2.group(5))),
                        nullCatch(m2.group(14)),
                        nullCatch(m2.group(17)),
                        nullCatch(m2.group(20)),
                        datetemp,
                        nullCatch(m2.group(26))));
            }

        }else if (clas.equals(Admin.class)) {
            list = new LinkedList<Admin>();
            Pattern pattern2 = Pattern.compile("(Admin[{]idA=)(\\d+)([,].firstNameA=')(.+)(['],.lastNameA=')(.+)(['],.mailA=')(.+)(['],.passA=')(.+)(['],.dateRegA=)(.+)(,.photoA=')(.+)(',.userAuthorities=)(\\d+)(})");
            Matcher m3 = pattern2.matcher(sb);
            while (m3.find()) {

                Date datetemp = new Date();
                try {
                    if(!m3.group(12).equals("null")){
                        datetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(nullCatch(m3.group(12)));
                    }else{
                        datetemp=null;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                list.add(new Admin(Long.parseLong(m3.group(2)),
                        nullCatch(m3.group(4)),
                        nullCatch(m3.group(6)),
                        nullCatch(m3.group(8)),
                        nullCatch(m3.group(10)),
                        datetemp,
                        nullCatch(m3.group(14)),
                        Integer.parseInt(nullCatch(m3.group(16)))));

            }

        }
        System.out.println(list.size());
        return list;

    }

    private static String nullCatch(String s) {
        if (s.equals("null")) {
            return null;
        } else {
            return s;
        }
    }

    public static void main(String[] args) {
        System.out.println(Admin.class.equals(Admin.class));
    }
}


