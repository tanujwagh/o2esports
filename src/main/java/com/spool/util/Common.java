package com.spool.util;

import com.spool.controller.DashBoard;
import com.spool.types.*;
import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Korrin on 4/5/2017.
 */
public class Common {

    public static final String SPCOOL_DOWNLOAD_URL = "http://o2esports.com/user/file/";
    public static final String COINS_UPDATE_URL = "http://o2esports.com/user/dice/%s/%s/%s/";
    public static final String COINS_GET_URL = "http://o2esports.com/user/coins/%s/";
    private static final String ANSWER_FILENAME = "QAAnswers_%d_%d.bin";
    private static final String DATA_FILENAME = "spcool.csv";


    public static boolean downloadLatestMatchData(){
        try {
            String userHome = System.getProperty("user.home");
            File filename = new File(userHome + File.separator + DATA_FILENAME);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(SPCOOL_DOWNLOAD_URL);
            request.addHeader("content-type", "text/plain");
            request.addHeader("Accept", "text/plain");
            HttpResponse response = client.execute((HttpUriRequest)request);
            System.out.println("\nSending 'POST' request to URL : " + SPCOOL_DOWNLOAD_URL);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            InputStream is = response.getEntity().getContent();
            filename.createNewFile();
            filename.getParentFile().mkdir();
            if (!filename.exists()) {
                filename.createNewFile();
            }
            filename.setExecutable(true);
            filename.setReadable(true);
            filename.setWritable(true);
            FileOutputStream fos = new FileOutputStream(filename);
            int read = 0;
            byte[] buffer = new byte[32768];
            while ((read = is.read(buffer)) > 0) {
                fos.write(buffer, 0, read);
            }
            fos.close();
            is.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean getLatestData(){
        try {
            if (downloadLatestMatchData()) {
                String fileName = System.getProperty("user.home") + File.separator + DATA_FILENAME;
                Stream<String> stream = Files.lines(Paths.get(fileName));
                List<String> lines = stream.collect(Collectors.toList());
                int index = 1;
                String[] keys = null;
                String[] data =  null;
                for(String line : lines){
                    if((index) == 2){
                        keys = line.split(",");
                    }
                    if((index) == 3){
                        data = line.split(",");
                    }
                    index++;
                }
                processData(keys, data);
                checkResult(data, BasicInfo.matchId, QAType.Oddtime - 10);
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void processData(String[] keys, String[] data){
        HeroIDData.init(Arrays.copyOfRange(keys, 247, 360), (Arrays.copyOfRange(data, 247, 360)));
        HeroNameData.init(Arrays.copyOfRange(keys, 0, 10), (Arrays.copyOfRange(data, 0, 10)));
        BasicInfo.init(Arrays.copyOfRange(data, 148, 155));
        QAType.init(Arrays.copyOfRange(data, 155, 180));
        System.out.println();
    }

    public static void main(String[] args) {
        getLatestData();
    }

    private static void checkResult(String[] data, long matchId, int oddTime) {
        try {
            QAAnswers qaAnswers = getQaAnswers(matchId, oddTime);
            if (qaAnswers != null) {
                checkAnswerAndUpdateCoins(1, qaAnswers.getAnswer1(), data);
                checkAnswerAndUpdateCoins(2, qaAnswers.getAnswer2(), data);
                checkAnswerAndUpdateCoins(3, qaAnswers.getAnswer3(), data);
                checkAnswerAndUpdateCoins(4, qaAnswers.getAnswer4(), data);
                updateCoins(DashBoard.uid, Coins.coins + "", Coins.id + "");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void checkAnswerAndUpdateCoins(int questionNumber, QAAnswer answer, String[] data) {
        int questionFor = answer.getQuestionFor();
        int delta = (questionFor / 10) - 1;
        if(answer.getAnswer().equalsIgnoreCase(data[360 + (4 * delta) + (questionNumber - 1)])){
            System.out.println("Updating coins for question : " + questionNumber);
            Coins.coins += (answer.getBid() * answer.getMultiplicationFactor());
        }
    }



    public static boolean saveAnswers(QAAnswers qaAnswers, long matchID, int oddTime){
        try {
            String userhome = System.getProperty("user.home");
            File answerFile = new File(userhome + File.separator + String.format(ANSWER_FILENAME, matchID, oddTime));
            FileOutputStream fos = new FileOutputStream(answerFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(qaAnswers);
            oos.close();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static QAAnswers getQaAnswers(long matchId, int oddTime){
        if(oddTime <= 0){
            System.out.println("First Question - Can not check results");
            return null;
        }
        String userhome = System.getProperty("user.home");
        File answerFile = new File(userhome + File.separator + String.format(ANSWER_FILENAME, matchId, oddTime));
        if(answerFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(answerFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                QAAnswers result = (QAAnswers) ois.readObject();
                ois.close();
                return result;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                answerFile.delete();
            }
        }else{
            System.out.println("No Answers found for Odd Time : " + oddTime);
        }
        return null;
    }

    public static void getCoins(String uid) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(String.format(COINS_GET_URL, uid));
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            System.out.println("Sending 'POST' request to URL : " + String.format(COINS_GET_URL, uid));
            HttpResponse response = client.execute(request);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
            JSONObject jsonObject = new JSONObject(result.toString());
            Coins.coins = Integer.parseInt(jsonObject.getString("coins"));
            Coins.id = Integer.parseInt(jsonObject.getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Boolean updateCoins(String uid, String coins, String id) {
        try {
            BufferedReader br;
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(String.format(COINS_UPDATE_URL, uid, coins, id));
            request.addHeader("content-type", "text/plain");
            request.addHeader("Accept", "text/plain");
            System.out.println("Sending 'POST' request to URL : " + String.format(COINS_UPDATE_URL, uid, coins, id));
            HttpResponse response = client.execute(request);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                new DashBoard().getCoins(uid);
            }
            if ((br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))).readLine() == "Record updated successfully") {
                return true;
            }
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkIfAlreadyAnswered(long matchId, int oddtime) {
        return new File(System.getProperty("user.home") + File.separator + String.format(ANSWER_FILENAME, matchId, oddtime)).exists();
    }
}
