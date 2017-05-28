package com.spool.service;

import com.spool.controller.Const;
import com.spool.controller.DashBoard;
import com.spool.types.QAAnswer;
import com.spool.types.QAAnswers;
import com.spool.model.A;
import com.spool.model.D;
import com.spool.model.Denies;
import com.spool.model.DraftAdvantage;
import com.spool.model.Gpm;
import com.spool.model.Hero;
import com.spool.model.IH;
import com.spool.model.ImageBoxModel;
import com.spool.model.K;
import com.spool.model.Level;
import com.spool.model.LiveAdvantage;
import com.spool.model.Networth;
import com.spool.model.QAModel;
import com.spool.model.TeamFight;
import com.spool.model.TotalAdvantage;
import com.spool.model.Xpm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class Utility {
    public static final String FILE_PATH = "C:\\Users\\jaishil\\desktop\\spcool.csv";
    public static final String QA_FILE_PATH = "C:\\Users\\jaishil\\desktop\\QA.csv";
    private static final String ANSWER_FILENAME = "QAAnswers.bin";
    private static final String DATA_FILENAME = "spcool.csv";
    private Hero hero = new Hero();
    private Networth networth = new Networth();
    private K k = new K();
    private D d = new D();
    private A a = new A();
    private IH ih = new IH();
    private Denies denies = new Denies();
    private Level level = new Level();
    private Gpm gpm = new Gpm();
    private Xpm xpm = new Xpm();
    private DraftAdvantage draftAdvantage = new DraftAdvantage();
    private LiveAdvantage liveAdvantage = new LiveAdvantage();
    private TotalAdvantage totalAdvantage = new TotalAdvantage();
    private Advantage advantage = new Advantage();
    private TeamFight teamFight = new TeamFight();
    private ImageBoxModel imageBoxModel = new ImageBoxModel();
    private QAModel qaModel = new QAModel();
    BufferedReader br;
    private Map<Integer, String> result = null;
    Const const1 = new Const();
    private boolean isAdded = true;
    private BufferedReader buff;

    public BufferedReader getBuff() {
        return this.buff;
    }

    public void setBuff(BufferedReader buff) {
        this.buff = buff;
    }

    public boolean isAdded() {
        return this.isAdded;
    }

    public void setAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    private String[] getRefreshData(boolean isData) throws FileNotFoundException, IOException {
        String line;
        int i = 0;
        String[] data = null;
        BufferedReader br = this.getBuffer();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            if (i == 2 && isData) {
                data = line.split(",");
            }
            if (i == 1 && !isData) {
                data = line.split(",");
            }
            ++i;
        }
        this.checkResult(data);
        return data;
    }

    private void checkResult(String[] data) {
        /*try {
            if (this.result != null) {
                String line;
                int i = 0;
                BufferedReader br = this.getBuffer();
                String[] data = null;
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    if (i == 2) {
                        data = line.split(",");
                        break;
                    }
                    ++i;
                }
                System.out.println(String.valueOf(DashBoard.const1.coins) + "coins before" + data[360]);
                if (this.isAdded()) {
                    DashBoard.const1.coins += 100;
                    this.setAdded(false);
                }
                int k = 0;
                int g = 100;
                while (g < 500) {
                    int j = 1;
                    while (j < 5) {
                        if (this.result.get(g + j) != null && data[360 + ++k] != "Nobody") {
                            if (this.result.get(g + j).split("~")[3].trim().equalsIgnoreCase(data[360 + (j - 1)]) && this.result.get(g + j).split("~")[4].trim().equalsIgnoreCase("0")) {
                                this.const1.setCoins(DashBoard.const1.coins + (int)Math.ceil(Double.parseDouble(this.result.get(g + j).split("~")[2])));
                            } else {
                                this.const1.setCoins(DashBoard.const1.coins - (int)Math.ceil(Double.parseDouble(this.result.get(g + j).split("~")[2])));
                            }
                            this.const1.setCoins(this.const1.getCoins() + (int)Math.ceil(Double.parseDouble(this.result.get(g + j).split("~")[2])));
                            this.result.put(g + j, String.valueOf(this.result.get(g + j)) + "1");
                            System.out.println(String.valueOf(this.const1.coins) + "Coins");
                        }
                        ++j;
                    }
                    g += 100;
                }
                g = this.updateCoins(String.valueOf(DashBoard.const1.uid), String.valueOf(this.const1.getCoins()), String.valueOf(DashBoard.const1.id)).booleanValue() ? 1 : 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            QAAnswers qaAnswers = getQaAnswers();
            if (qaAnswers != null) {
                checkAnswerAndUpdateCoins(1, qaAnswers.getAnswer1(), data);
                checkAnswerAndUpdateCoins(2, qaAnswers.getAnswer2(), data);
                checkAnswerAndUpdateCoins(3, qaAnswers.getAnswer3(), data);
                checkAnswerAndUpdateCoins(4, qaAnswers.getAnswer4(), data);
                updateCoins("" + DashBoard.const1.uid, "" + (int)DashBoard.const1.coins, ""+ DashBoard.const1.id);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void checkAnswerAndUpdateCoins(int questionNumber, QAAnswer answer, String[] data) {
        int questionFor = answer.getQuestionFor();
        int delta = (questionFor / 10) - 1;
        if(answer.getAnswer().equalsIgnoreCase(data[360 + (4 * delta) + (questionNumber - 1)])){
            System.out.println("Updating coins for question : " + questionNumber);
            DashBoard.const1.coins += (answer.getBid() * answer.getMultiplicationFactor());
            DashBoard.coins = "" + DashBoard.const1.coins;
            DashBoard.setUserCoinsLabel();
        }
    }

    public BufferedReader getBuffer() {
        BufferedReader br = null;
        try {
            String userHome = System.getProperty("user.home");
            String outputFolder = String.valueOf(userHome) + "\\Downloads";
            File folder = new File(String.valueOf(outputFolder) + File.separator + "spcool.csv");
            String url = "http://o2esports.com/user/file/";
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "text/plain");
            request.addHeader("Accept", "text/plain");
            HttpResponse response = client.execute((HttpUriRequest)request);
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            InputStream is = response.getEntity().getContent();
            folder.createNewFile();
            folder.getParentFile().mkdir();
            if (!folder.exists()) {
                folder.createNewFile();
            }
            folder.setExecutable(true);
            folder.setReadable(true);
            folder.setWritable(true);
            FileOutputStream fos = new FileOutputStream(folder);
            int read = 0;
            byte[] buffer = new byte[32768];
            while ((read = is.read(buffer)) > 0) {
                fos.write(buffer, 0, read);
            }
            if (new File(String.valueOf(outputFolder) + File.separator + "spcool.csv").exists()) {
                br = new BufferedReader(new FileReader(String.valueOf(outputFolder) + File.separator + "spcool.csv"));
            }
            fos.close();
            is.close();
            this.setBuff(br);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return br;
    }

    public Boolean updateCoins(String uid, String coins, String id) {
        try {
            BufferedReader br;
            String url = "http://o2esports.com/user/dice/" + uid + "/" + coins + "/" + id + "/";
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "text/plain");
            request.addHeader("Accept", "text/plain");
            HttpResponse response = client.execute((HttpUriRequest)request);
            System.out.println("\nSending 'POST' request to URL : " + url);
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

    private HashMap<String, ArrayList<String>> getGraphData(boolean isData) throws FileNotFoundException, IOException {
        String line;
        boolean i = false;
        String[] data = null;
        ArrayList<String> hp = new ArrayList<String>();
        ArrayList<String> ho = new ArrayList<String>();
        int j = 0;
        HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
        BufferedReader br = this.getBuffer();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            if (j > 2 && !(data = line.split(","))[222].equalsIgnoreCase("xyz")) {
                ho.add(data[222]);
                hp.add(data[223]);
            }
            hashmap.put("ho", ho);
            hashmap.put("hp", hp);
            ++j;
        }
        return hashmap;
    }

    private HashMap<String, ArrayList<String>> getGraphData2(boolean isData) throws FileNotFoundException, IOException {
        String line;
        boolean i = false;
        String[] data = null;
        ArrayList<String> ho = new ArrayList<String>();
        ArrayList hp = new ArrayList();
        ArrayList<String> hq = new ArrayList<String>();
        ArrayList<String> hr = new ArrayList<String>();
        ArrayList<String> hs = new ArrayList<String>();
        ArrayList<String> ht = new ArrayList<String>();
        ArrayList<String> hu = new ArrayList<String>();
        int j = 0;
        HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
        String userHome = System.getProperty("user.home");
        String outputFolder = String.valueOf(userHome) + "\\Downloads" + File.separator + "spcool.csv";
        BufferedReader br = new File(outputFolder).exists() ? new BufferedReader(new FileReader(outputFolder)) : this.getBuffer();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            if (j > 2 && !(data = line.split(","))[220].equalsIgnoreCase("xyz")) {
                ho.add(data[222]);
                hq.add(data[224]);
                hr.add(data[225]);
                hs.add(data[226]);
                ht.add(data[227]);
                hu.add(data[228]);
            }
            hashmap.put("ho", ho);
            hashmap.put("hq", hq);
            hashmap.put("hr", hr);
            hashmap.put("hs", hs);
            hashmap.put("ht", ht);
            hashmap.put("hu", hu);
            ++j;
        }
        return hashmap;
    }

    private HashMap<String, ArrayList<String>> getGraphData3(boolean isData) throws FileNotFoundException, IOException {
        String line;
        boolean i = false;
        String[] data = null;
        ArrayList<String> hv = new ArrayList<String>();
        ArrayList<String> hw = new ArrayList<String>();
        ArrayList<String> hx = new ArrayList<String>();
        ArrayList<String> hy = new ArrayList<String>();
        ArrayList<String> hz = new ArrayList<String>();
        ArrayList<String> ia = new ArrayList<String>();
        ArrayList<String> ib = new ArrayList<String>();
        ArrayList<String> ic = new ArrayList<String>();
        ArrayList<String> ho = new ArrayList<String>();
        int j = 0;
        HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
        String userHome = System.getProperty("user.home");
        String outputFolder = String.valueOf(userHome) + "\\Downloads" + File.separator + "spcool.csv";
        BufferedReader br = new File(outputFolder).exists() ? new BufferedReader(new FileReader(outputFolder)) : this.getBuffer();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            if (j > 2 && !(data = line.split(","))[220].equalsIgnoreCase("xyz")) {
                ho.add(data[222]);
                hv.add(data[229]);
                hw.add(data[230]);
                hx.add(data[231]);
                hy.add(data[232]);
                hz.add(data[233]);
                ia.add(data[234]);
                ib.add(data[235]);
                ic.add(data[236]);
            }
            hashmap.put("hv", hv);
            hashmap.put("hw", hw);
            hashmap.put("hx", hx);
            hashmap.put("hy", hy);
            hashmap.put("hz", hz);
            hashmap.put("ia", ia);
            hashmap.put("ib", ib);
            hashmap.put("ic", ic);
            hashmap.put("ho", ho);
            ++j;
        }
        return hashmap;
    }

    public Hero getHeroData() {
        try {
            String[] data = this.getRefreshData(true);
            this.hero.setHero1(Integer.valueOf(data[0]).intValue());
            this.hero.setHero2(Integer.valueOf(data[1]).intValue());
            this.hero.setHero3(Integer.valueOf(data[2]).intValue());
            this.hero.setHero4(Integer.valueOf(data[3]).intValue());
            this.hero.setHero5(Integer.valueOf(data[4]).intValue());
            this.hero.setHero6(Integer.valueOf(data[5]).intValue());
            this.hero.setHero7(Integer.valueOf(data[6]).intValue());
            this.hero.setHero8(Integer.valueOf(data[7]).intValue());
            this.hero.setHero9(Integer.valueOf(data[8]).intValue());
            this.hero.setHero10(Integer.valueOf(data[9]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.hero;
    }

    public Networth getNetworthData() {
        try {
            String[] data = this.getRefreshData(true);
            this.networth.setnetworth1(Integer.valueOf(data[10]).intValue());
            this.networth.setnetworth2(Integer.valueOf(data[11]).intValue());
            this.networth.setnetworth3(Integer.valueOf(data[12]).intValue());
            this.networth.setnetworth4(Integer.valueOf(data[13]).intValue());
            this.networth.setnetworth5(Integer.valueOf(data[14]).intValue());
            this.networth.setnetworth6(Integer.valueOf(data[15]).intValue());
            this.networth.setnetworth7(Integer.valueOf(data[16]).intValue());
            this.networth.setnetworth8(Integer.valueOf(data[17]).intValue());
            this.networth.setnetworth9(Integer.valueOf(data[18]).intValue());
            this.networth.setnetworth10(Integer.valueOf(data[19]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.networth;
    }

    public K getKData() {
        try {
            String[] data = this.getRefreshData(true);
            this.k.setk1(Integer.valueOf(data[20]).intValue());
            this.k.setk2(Integer.valueOf(data[21]).intValue());
            this.k.setk3(Integer.valueOf(data[22]).intValue());
            this.k.setk4(Integer.valueOf(data[23]).intValue());
            this.k.setk5(Integer.valueOf(data[24]).intValue());
            this.k.setk6(Integer.valueOf(data[25]).intValue());
            this.k.setk7(Integer.valueOf(data[26]).intValue());
            this.k.setk8(Integer.valueOf(data[27]).intValue());
            this.k.setk9(Integer.valueOf(data[28]).intValue());
            this.k.setk10(Integer.valueOf(data[29]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.k;
    }

    public D getDData() {
        try {
            String[] data = this.getRefreshData(true);
            this.d.setd1(Integer.valueOf(data[30]).intValue());
            this.d.setd2(Integer.valueOf(data[31]).intValue());
            this.d.setd3(Integer.valueOf(data[32]).intValue());
            this.d.setd4(Integer.valueOf(data[33]).intValue());
            this.d.setd5(Integer.valueOf(data[34]).intValue());
            this.d.setd6(Integer.valueOf(data[35]).intValue());
            this.d.setd7(Integer.valueOf(data[36]).intValue());
            this.d.setd8(Integer.valueOf(data[37]).intValue());
            this.d.setd9(Integer.valueOf(data[38]).intValue());
            this.d.setd10(Integer.valueOf(data[39]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.d;
    }

    public A getAData() {
        try {
            String[] data = this.getRefreshData(true);
            this.a.setA1(Integer.valueOf(data[40]).intValue());
            this.a.setA2(Integer.valueOf(data[41]).intValue());
            this.a.setA3(Integer.valueOf(data[42]).intValue());
            this.a.setA4(Integer.valueOf(data[43]).intValue());
            this.a.setA5(Integer.valueOf(data[44]).intValue());
            this.a.setA6(Integer.valueOf(data[45]).intValue());
            this.a.setA7(Integer.valueOf(data[46]).intValue());
            this.a.setA8(Integer.valueOf(data[47]).intValue());
            this.a.setA9(Integer.valueOf(data[48]).intValue());
            this.a.setA10(Integer.valueOf(data[49]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.a;
    }

    public IH getIHData() {
        try {
            String[] data = this.getRefreshData(true);
            this.ih.setih1(Integer.valueOf(data[50]).intValue());
            this.ih.setih2(Integer.valueOf(data[51]).intValue());
            this.ih.setih3(Integer.valueOf(data[52]).intValue());
            this.ih.setih4(Integer.valueOf(data[53]).intValue());
            this.ih.setih5(Integer.valueOf(data[54]).intValue());
            this.ih.setih6(Integer.valueOf(data[55]).intValue());
            this.ih.setih7(Integer.valueOf(data[56]).intValue());
            this.ih.setih8(Integer.valueOf(data[57]).intValue());
            this.ih.setih9(Integer.valueOf(data[58]).intValue());
            this.ih.setih10(Integer.valueOf(data[59]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.ih;
    }

    public Denies getDeniesData() {
        try {
            String[] data = this.getRefreshData(true);
            this.denies.setdenies1(Integer.valueOf(data[60]).intValue());
            this.denies.setdenies2(Integer.valueOf(data[61]).intValue());
            this.denies.setdenies3(Integer.valueOf(data[62]).intValue());
            this.denies.setdenies4(Integer.valueOf(data[63]).intValue());
            this.denies.setdenies5(Integer.valueOf(data[64]).intValue());
            this.denies.setdenies6(Integer.valueOf(data[65]).intValue());
            this.denies.setdenies7(Integer.valueOf(data[66]).intValue());
            this.denies.setdenies8(Integer.valueOf(data[67]).intValue());
            this.denies.setdenies9(Integer.valueOf(data[68]).intValue());
            this.denies.setdenies10(Integer.valueOf(data[69]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.denies;
    }

    public Level getLevelData() {
        try {
            String[] data = this.getRefreshData(true);
            this.level.setlevel1(Integer.valueOf(data[70]).intValue());
            this.level.setlevel2(Integer.valueOf(data[71]).intValue());
            this.level.setlevel3(Integer.valueOf(data[72]).intValue());
            this.level.setlevel4(Integer.valueOf(data[73]).intValue());
            this.level.setlevel5(Integer.valueOf(data[74]).intValue());
            this.level.setlevel6(Integer.valueOf(data[75]).intValue());
            this.level.setlevel7(Integer.valueOf(data[76]).intValue());
            this.level.setlevel8(Integer.valueOf(data[77]).intValue());
            this.level.setlevel9(Integer.valueOf(data[78]).intValue());
            this.level.setlevel10(Integer.valueOf(data[79]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.level;
    }

    public Gpm getGpmData() {
        try {
            String[] data = this.getRefreshData(true);
            this.gpm.setgpm1(Integer.valueOf(data[80]).intValue());
            this.gpm.setgpm2(Integer.valueOf(data[81]).intValue());
            this.gpm.setgpm3(Integer.valueOf(data[82]).intValue());
            this.gpm.setgpm4(Integer.valueOf(data[83]).intValue());
            this.gpm.setgpm5(Integer.valueOf(data[84]).intValue());
            this.gpm.setgpm6(Integer.valueOf(data[85]).intValue());
            this.gpm.setgpm7(Integer.valueOf(data[86]).intValue());
            this.gpm.setgpm8(Integer.valueOf(data[87]).intValue());
            this.gpm.setgpm9(Integer.valueOf(data[88]).intValue());
            this.gpm.setgpm10(Integer.valueOf(data[89]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.gpm;
    }

    public Xpm getXpmData() {
        try {
            String[] data = this.getRefreshData(true);
            this.xpm.setxpm1(Integer.valueOf(data[90]).intValue());
            this.xpm.setxpm2(Integer.valueOf(data[91]).intValue());
            this.xpm.setxpm3(Integer.valueOf(data[92]).intValue());
            this.xpm.setxpm4(Integer.valueOf(data[93]).intValue());
            this.xpm.setxpm5(Integer.valueOf(data[94]).intValue());
            this.xpm.setxpm6(Integer.valueOf(data[95]).intValue());
            this.xpm.setxpm7(Integer.valueOf(data[96]).intValue());
            this.xpm.setxpm8(Integer.valueOf(data[97]).intValue());
            this.xpm.setxpm9(Integer.valueOf(data[98]).intValue());
            this.xpm.setxpm10(Integer.valueOf(data[99]).intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.xpm;
    }

    public DraftAdvantage getDraftAdvantageData() {
        try {
            String[] data = this.getRefreshData(true);
            this.draftAdvantage.setDisables1(Double.valueOf(data[100]).doubleValue());
            this.draftAdvantage.setDisables2(Double.valueOf(data[101]).doubleValue());
            this.draftAdvantage.setNukes1(Double.valueOf(data[102]).doubleValue());
            this.draftAdvantage.setNukes2(Double.valueOf(data[103]).doubleValue());
            this.draftAdvantage.setPushing1(Double.valueOf(data[104]).doubleValue());
            this.draftAdvantage.setPushing2(Double.valueOf(data[105]).doubleValue());
            this.draftAdvantage.setDefending1(Double.valueOf(data[106]).doubleValue());
            this.draftAdvantage.setDefending2(Double.valueOf(data[107]).doubleValue());
            this.draftAdvantage.setSplitPushing1(Double.valueOf(data[108]).doubleValue());
            this.draftAdvantage.setSplitPushing2(Double.valueOf(data[109]).doubleValue());
            this.draftAdvantage.setrDmg1(Double.valueOf(data[110]).doubleValue());
            this.draftAdvantage.setrDmg2(Double.valueOf(data[111]).doubleValue());
            this.draftAdvantage.setDurability1(Double.valueOf(data[112]).doubleValue());
            this.draftAdvantage.setDurability2(Double.valueOf(data[113]).doubleValue());
            this.draftAdvantage.setMobility1(Double.valueOf(data[114]).doubleValue());
            this.draftAdvantage.setMobility2(Double.valueOf(data[115]).doubleValue());
            this.draftAdvantage.setHealing1(Double.valueOf(data[116]).doubleValue());
            this.draftAdvantage.setHealing2(Double.valueOf(data[117]).doubleValue());
            this.draftAdvantage.setInitiation1(Double.valueOf(data[118]).doubleValue());
            this.draftAdvantage.setInitiation2(Double.valueOf(data[119]).doubleValue());
            this.draftAdvantage.setCounters1(Double.valueOf(data[120]).doubleValue());
            this.draftAdvantage.setCounters2(Double.valueOf(data[121]).doubleValue());
            this.draftAdvantage.setTeamName1(String.valueOf(data[148]));
            this.draftAdvantage.setTeamName2(String.valueOf(data[149]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.draftAdvantage;
    }

    public LiveAdvantage getLiveAdvantageData() {
        try {
            String[] data = this.getRefreshData(true);
            this.liveAdvantage.setTeamFight1(Double.valueOf(data[122]).doubleValue());
            this.liveAdvantage.setTeamFight2(Double.valueOf(data[123]).doubleValue());
            this.liveAdvantage.setPushing1(Double.valueOf(data[124]).doubleValue());
            this.liveAdvantage.setPushing2(Double.valueOf(data[125]).doubleValue());
            this.liveAdvantage.setDefending1(Double.valueOf(data[126]).doubleValue());
            this.liveAdvantage.setDefending2(Double.valueOf(data[127]).doubleValue());
            this.liveAdvantage.setSplitPushing1(Double.valueOf(data[128]).doubleValue());
            this.liveAdvantage.setSplitPushing2(Double.valueOf(data[129]).doubleValue());
            this.liveAdvantage.setTeamName1(String.valueOf(data[148]));
            this.liveAdvantage.setTeamName2(String.valueOf(data[149]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.liveAdvantage;
    }

    public TotalAdvantage getTotalAdvantageData() {
        try {
            String[] data = this.getRefreshData(true);
            int i = 0;
            String[] arrstring = data;
            int n = arrstring.length;
            int n2 = 0;
            while (n2 < n) {
                String str = arrstring[n2];
                if (str.equalsIgnoreCase("xyz")) {
                    System.out.println(i);
                }
                ++i;
                ++n2;
            }
            this.totalAdvantage.setDraftAdvantage1(Double.valueOf(data[130]).doubleValue());
            System.out.println(Double.valueOf(data[130]));
            this.totalAdvantage.setDraftAdvantage2(Double.valueOf(data[131]).doubleValue());
            System.out.println(Double.valueOf(data[131]));
            this.totalAdvantage.setLiveAdvantage1(Double.valueOf(data[132]).doubleValue());
            System.out.println(Double.valueOf(data[132]));
            this.totalAdvantage.setLiveAdvantage2(Double.valueOf(data[133]).doubleValue());
            System.out.println(Double.valueOf(data[133]));
            this.totalAdvantage.setTeamName1(String.valueOf(data[148]));
            this.totalAdvantage.setTeamName2(String.valueOf(data[149]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.totalAdvantage;
    }

    public Advantage getAdvantageData() {
        try {
            String[] data = this.getRefreshData(false);
            HashMap<String, ArrayList<String>> map = this.getGraphData(true);
            double[] adv1 = new double[map.get("ho").size()];
            double[] adv2 = new double[map.get("hp").size()];
            int i = 0;
            int j = 0;
            for (String d2 : map.get("ho")) {
                System.out.println(Double.valueOf(d2) + "+");
                adv1[i] = Double.valueOf(d2);
                ++i;
            }
            for (String d2 : map.get("hp")) {
                System.out.println(Double.valueOf(d2) + "-");
                adv2[j] = Double.valueOf(d2);
                ++j;
            }
            this.advantage.setTeamName1(String.valueOf(data[223]));
            this.advantage.setHo(adv1);
            this.advantage.setHp(adv2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.advantage;
    }

    public Advantage getAdvantageData2() {
        try {
            String[] data = this.getRefreshData(false);
            HashMap<String, ArrayList<String>> map = this.getGraphData2(true);
            double[] adv1 = new double[map.get("ho").size()];
            double[] adv3 = new double[map.get("hq").size()];
            double[] adv4 = new double[map.get("hr").size()];
            double[] adv5 = new double[map.get("hs").size()];
            double[] adv6 = new double[map.get("ht").size()];
            double[] adv7 = new double[map.get("hu").size()];
            int i = 0;
            int j = 0;
            for (String d22222 : map.get("ho")) {
                System.out.println(Double.valueOf(d22222) + "+");
                adv1[i] = Double.valueOf(d22222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222 : map.get("hq")) {
                System.out.println(Double.valueOf(d22222) + "+");
                adv3[i] = Double.valueOf(d22222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222 : map.get("hr")) {
                System.out.println(Double.valueOf(d22222) + "-");
                adv4[j] = Double.valueOf(d22222);
                ++j;
            }
            i = 0;
            j = 0;
            for (String d22222 : map.get("hs")) {
                System.out.println(Double.valueOf(d22222) + "+");
                adv5[i] = Double.valueOf(d22222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222 : map.get("ht")) {
                System.out.println(Double.valueOf(d22222) + "-");
                adv6[j] = Double.valueOf(d22222);
                ++j;
            }
            i = 0;
            j = 0;
            for (String d22222 : map.get("hu")) {
                System.out.println(Double.valueOf(d22222) + "+");
                adv7[i] = Double.valueOf(d22222);
                ++i;
            }
            this.advantage.setTeamName3(String.valueOf(data[224]));
            this.advantage.setTeamName4(String.valueOf(data[225]));
            this.advantage.setTeamName5(String.valueOf(data[226]));
            this.advantage.setTeamName6(String.valueOf(data[227]));
            this.advantage.setTeamName7(String.valueOf(data[228]));
            this.advantage.setHo(adv1);
            this.advantage.setHq(adv3);
            this.advantage.setHr(adv4);
            this.advantage.setHs(adv5);
            this.advantage.setHt(adv6);
            this.advantage.setHu(adv7);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.advantage;
    }

    public Advantage getAdvantageData3() {
        try {
            String[] data = this.getRefreshData(false);
            HashMap<String, ArrayList<String>> map = this.getGraphData3(true);
            double[] adv1 = new double[map.get("hv").size()];
            double[] adv2 = new double[map.get("hw").size()];
            double[] adv3 = new double[map.get("hx").size()];
            double[] adv4 = new double[map.get("hy").size()];
            double[] adv5 = new double[map.get("hz").size()];
            double[] adv6 = new double[map.get("ia").size()];
            double[] adv7 = new double[map.get("ib").size()];
            double[] adv8 = new double[map.get("ic").size()];
            double[] adv9 = new double[map.get("ho").size()];
            int i = 0;
            int j = 0;
            for (String d22222222 : map.get("hv")) {
                adv1[i] = Double.valueOf(d22222222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("hw")) {
                adv2[j] = Double.valueOf(d22222222);
                ++j;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("hx")) {
                adv3[i] = Double.valueOf(d22222222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("hy")) {
                adv4[j] = Double.valueOf(d22222222);
                ++j;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("hz")) {
                adv5[i] = Double.valueOf(d22222222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("ia")) {
                adv6[j] = Double.valueOf(d22222222);
                ++j;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("ib")) {
                adv7[i] = Double.valueOf(d22222222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("ic")) {
                adv8[i] = Double.valueOf(d22222222);
                ++i;
            }
            i = 0;
            j = 0;
            for (String d22222222 : map.get("ho")) {
                adv9[i] = Double.valueOf(d22222222);
                ++i;
            }
            this.advantage.setTeamName1(String.valueOf(data[229]));
            this.advantage.setTeamName2(String.valueOf(data[230]));
            this.advantage.setTeamName3(String.valueOf(data[231]));
            this.advantage.setTeamName4(String.valueOf(data[232]));
            this.advantage.setTeamName5(String.valueOf(data[233]));
            this.advantage.setTeamName6(String.valueOf(data[234]));
            this.advantage.setTeamName7(String.valueOf(data[235]));
            this.advantage.setTeamName8(String.valueOf(data[236]));
            this.advantage.setHv(adv1);
            this.advantage.setHw(adv2);
            this.advantage.setHx(adv3);
            this.advantage.setHy(adv4);
            this.advantage.setHz(adv5);
            this.advantage.setIa(adv6);
            this.advantage.setIb(adv7);
            this.advantage.setIc(adv8);
            this.advantage.setHo(adv9);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.advantage;
    }

    public TeamFight getTeamFightData() {
        try {
            String[] data = this.getRefreshData(true);
            this.teamFight.setDisables1(Double.valueOf(data[134]).doubleValue());
            this.teamFight.setDisables2(Double.valueOf(data[135]).doubleValue());
            this.teamFight.setNukeDmg1(Double.valueOf(data[136]).doubleValue());
            this.teamFight.setNukeDmg2(Double.valueOf(data[137]).doubleValue());
            this.teamFight.setrDmg1(Double.valueOf(data[138]).doubleValue());
            this.teamFight.setrDmg2(Double.valueOf(data[139]).doubleValue());
            this.teamFight.setDurability1(Double.valueOf(data[140]).doubleValue());
            this.teamFight.setDurability2(Double.valueOf(data[141]).doubleValue());
            this.teamFight.setInitiation1(Double.valueOf(data[142]).doubleValue());
            this.teamFight.setInitiation2(Double.valueOf(data[143]).doubleValue());
            this.teamFight.setHealing1(Double.valueOf(data[144]).doubleValue());
            this.teamFight.setHealing2(Double.valueOf(data[145]).doubleValue());
            this.teamFight.setAura1(Double.valueOf(data[146]).doubleValue());
            this.teamFight.setAura2(Double.valueOf(data[147]).doubleValue());
            this.teamFight.setTeamName1(String.valueOf(data[148]));
            this.teamFight.setTeamName2(String.valueOf(data[149]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.teamFight;
    }

    public ImageBoxModel getImageBoxData() {
        try {
            String[] data = this.getRefreshData(true);
            this.imageBoxModel.setImage1(String.valueOf(data[237]));
            this.imageBoxModel.setImage2(String.valueOf(data[238]));
            this.imageBoxModel.setImage3(String.valueOf(data[239]));
            this.imageBoxModel.setImage4(String.valueOf(data[240]));
            this.imageBoxModel.setImage5(String.valueOf(data[241]));
            this.imageBoxModel.setImage6(String.valueOf(data[242]));
            this.imageBoxModel.setImage7(String.valueOf(data[243]));
            this.imageBoxModel.setImage8(String.valueOf(data[244]));
            this.imageBoxModel.setImage9(String.valueOf(data[245]));
            this.imageBoxModel.setImage10(String.valueOf(data[246]));
            this.imageBoxModel.setTeamName1(String.valueOf(data[148]));
            this.imageBoxModel.setTeamName2(String.valueOf(data[149]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.imageBoxModel;
    }

    public QAAnswers getQaAnswers(){
        String userhome = System.getProperty("user.home");
        File answerFile = new File(userhome + File.separator + ANSWER_FILENAME);
        if(answerFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(answerFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                QAAnswers result = (QAAnswers) ois.readObject();
                ois.close();
                return result;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            } finally {
                answerFile.delete();
            }
        }
        return null;
    }

    public boolean saveAnswers(QAAnswers qaAnswers){
        try {
            String userhome = System.getProperty("user.home");
            File answerFile = new File(userhome + File.separator + ANSWER_FILENAME);
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

    public void saveQAData(String answer1, String answer2, String answer3, String answer4, String time) {
        try {
            boolean linenumber = false;
            File file = File.createTempFile("tempfile", ".tmp");
            System.out.println(file.getParentFile());
            String[] data = new String[41];
            int i = 0;
            while (i < 41) {
                data[i] = "";
                ++i;
            }
            if (!file.exists()) {
                file.createNewFile();
                data[0] = "NE";
            } else {
                String line;
                this.br = new BufferedReader(new FileReader(file));
                String[] temporaryData = null;
                while ((line = this.br.readLine()) != null && !line.isEmpty()) {
                    if (linenumber) continue;
                    temporaryData = line.split(",");
                    int i2 = 0;
                    while (i2 < temporaryData.length) {
                        data[i2] = temporaryData[i2];
                        ++i2;
                    }
                }
            }
            int min = 10;
            try {
                min = Integer.valueOf(time);
            }
            catch (NumberFormatException i2) {
                // empty catch block
            }
            this.result = new HashMap<Integer, String>();
            this.result.put(Integer.parseInt(String.valueOf(answer1.split("~")[0]) + 1), String.valueOf(answer1) + "~0");
            this.result.put(Integer.parseInt(String.valueOf(answer2.split("~")[0]) + 2), String.valueOf(answer2) + "~0");
            this.result.put(Integer.parseInt(String.valueOf(answer3.split("~")[0]) + 3), String.valueOf(answer3) + "~0");
            this.result.put(Integer.parseInt(String.valueOf(answer4.split("~")[0]) + 4), String.valueOf(answer4) + "~0");
            int number = min / 10;
            data[4 * (number - 1) + 1] = answer1;
            data[4 * (number - 1) + 2] = answer2;
            data[4 * (number - 1) + 3] = answer3;
            data[4 * (number - 1) + 4] = answer4;
            FileWriter pw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(pw);
            int i3 = 0;
            while (i3 < data.length) {
                bw.write(data[i3]);
                bw.write(",");
                ++i3;
            }
            pw.flush();
            bw.close();
            pw.close();
            this.checkResult(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QAModel getQAFormData() {
        Object ps = null;
        Object pw = null;
        try {
            String[] data = this.getRefreshData(true);
            this.qaModel.setHero1(String.valueOf(data[0]));
            this.qaModel.setHero2(String.valueOf(data[1]));
            this.qaModel.setHero3(String.valueOf(data[2]));
            this.qaModel.setHero4(String.valueOf(data[3]));
            this.qaModel.setHero5(String.valueOf(data[4]));
            this.qaModel.setHero6(String.valueOf(data[5]));
            this.qaModel.setHero7(String.valueOf(data[6]));
            this.qaModel.setHero8(String.valueOf(data[7]));
            this.qaModel.setHero9(String.valueOf(data[8]));
            this.qaModel.setHero10(String.valueOf(data[9]));
            this.qaModel.setTeamName1(String.valueOf(data[148]));
            this.qaModel.setTeamName2(String.valueOf(data[149]));
            this.qaModel.setQuestionFor(String.valueOf(data[155]));
            this.qaModel.setAnswer4_1(String.valueOf(data[156]));
            this.qaModel.setAnswer4_2(String.valueOf(data[157]));
            this.qaModel.setAnswer2_1(String.valueOf(data[158]));
            this.qaModel.setAnswer2_2(String.valueOf(data[159]));
            this.qaModel.setAnswer3_1(String.valueOf(data[160]));
            this.qaModel.setAnswer3_2(String.valueOf(data[161]));
            this.qaModel.setAnswer3_3(String.valueOf(data[162]));
            this.qaModel.setAnswer3_4(String.valueOf(data[163]));
            this.qaModel.setAnswer3_5(String.valueOf(data[164]));
            this.qaModel.setAnswer3_6(String.valueOf(data[165]));
            this.qaModel.setAnswer3_7(String.valueOf(data[166]));
            this.qaModel.setAnswer3_8(String.valueOf(data[167]));
            this.qaModel.setAnswer3_9(String.valueOf(data[168]));
            this.qaModel.setAnswer3_10(String.valueOf(data[169]));
            this.qaModel.setAnswer1_1(String.valueOf(data[170]));
            this.qaModel.setAnswer1_2(String.valueOf(data[171]));
            this.qaModel.setAnswer1_3(String.valueOf(data[172]));
            this.qaModel.setAnswer1_4(String.valueOf(data[173]));
            this.qaModel.setAnswer1_5(String.valueOf(data[174]));
            this.qaModel.setAnswer1_6(String.valueOf(data[175]));
            this.qaModel.setAnswer1_7(String.valueOf(data[176]));
            this.qaModel.setAnswer1_8(String.valueOf(data[177]));
            this.qaModel.setAnswer1_9(String.valueOf(data[178]));
            this.qaModel.setAnswer1_10(String.valueOf(data[179]));
            this.qaModel.setGameTime(String.valueOf(data[150]));
        }
        catch (IOException e) {
            String userHome = System.getProperty("user.home");
            String outputFolder = String.valueOf(userHome) + "\\Downloads";
            try {
                Throwable throwable = null;
                Object var7_10 = null;
                try {
                    FileWriter w = new FileWriter(String.valueOf(outputFolder) + "\\erroro2.log");
                    try {
                        e.printStackTrace(new PrintWriter(new BufferedWriter(w)));
                        FileOutputStream out = new FileOutputStream("erroro21.log");
                        PrintStream ps2 = new PrintStream(out);
                        e.printStackTrace(ps2);
                    }
                    finally {
                        if (w != null) {
                            w.close();
                        }
                    }
                }
                catch (Throwable var7_11) {
                    if (throwable == null) {
                        throwable = var7_11;
                    } else if (throwable != var7_11) {
                        throwable.addSuppressed(var7_11);
                    }
                   
                    try {
                        throw throwable;
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return this.qaModel;
    }
}