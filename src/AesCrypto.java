/**
 * Created by 1 on 15.11.2015.
 */
public class AesCrypto {
    private char[] key;
    private char[] text;

    private int RoundText = 0;  //глобальный счётчик
    private int RoundKey = 0;  //глобальный счётчик
    private String[][] tabl = {{"63" ,"7c" ,"77" ,"7b" ,"f2" ,"6b" ,"6f" ,"c5" ,"30" ,"01" ,"67" ,"2b" ,"fe" ,"d7" ,"ab" ,"76"},
                                {"ca" ,"82" ,"c9" ,"7d" ,"fa" ,"59" ,"47" ,"f0" ,"ad" ,"d4" ,"a2" ,"af" ,"9c" ,"a4" ,"72" ,"c0"} ,
                                {"b7" ,"fd" ,"93" ,"26" ,"36" ,"3f" ,"f7" ,"cc" ,"34" ,"a5" ,"e5" ,"f1" ,"71" ,"d8" ,"31" ,"15"} ,
                                {"04" ,"c7" ,"23" ,"c3" ,"18" ,"96" ,"05" ,"9a" ,"07" ,"12" ,"80" ,"e2" ,"eb" ,"27" ,"b2" ,"75"} ,
                                {"09" ,"83" ,"2c" ,"1a" ,"1b" ,"6e" ,"5a" ,"a0" ,"52" ,"3b" ,"d6" ,"b3" ,"29" ,"e3" ,"2f" ,"84"} ,
                                {"53" ,"d1" ,"00" ,"ed" ,"20" ,"fc" ,"b1" ,"5b" ,"6a" ,"cb" ,"be" ,"39" ,"4a" ,"4c" ,"58" ,"cf"} ,
                                {"d0" ,"ef" ,"aa" ,"fb" ,"43" ,"4d" ,"33" ,"85" ,"45" ,"f9" ,"02" ,"7f" ,"50" ,"3c" ,"9f" ,"a8"} ,
                                {"51" ,"a3" ,"40" ,"8f" ,"92" ,"9d" ,"38" ,"f5" ,"bc" ,"b6" ,"da" ,"21" ,"10" ,"ff" ,"f3" ,"d2"} ,
                                {"cd" ,"0c" ,"13" ,"ec" ,"5f" ,"97" ,"44" ,"17" ,"c4" ,"a7" ,"7e" ,"3d" ,"64" ,"5d" ,"19" ,"73"} ,
                                {"60" ,"81" ,"4f" ,"dc" ,"22" ,"2a" ,"90" ,"88" ,"46" ,"ee" ,"b8" ,"14" ,"de" ,"5e" ,"0b" ,"db"} ,
                                {"e0" ,"32" ,"3a" ,"0a" ,"49" ,"06" ,"24" ,"5c" ,"c2" ,"d3" ,"ac" ,"62" ,"91" ,"95" ,"e4" ,"79"} ,
                                {"e7" ,"c8" ,"37" ,"6d" ,"8d" ,"d5" ,"4e" ,"a9" ,"6c" ,"56" ,"f4" ,"ea" ,"65" ,"7a" ,"ae" ,"08"} ,
                                {"ba" ,"78" ,"25" ,"2e" ,"1c" ,"a6" ,"b4" ,"c6" ,"e8" ,"dd" ,"74" ,"1f" ,"4b" ,"bd" ,"8b" ,"8a"} ,
                                {"70" ,"3e" ,"b5" ,"66" ,"48" ,"03" ,"f6" ,"0e" ,"61" ,"35" ,"57" ,"b9" ,"86" ,"c1" ,"1d" ,"9e"} ,
                                {"e1" ,"f8" ,"98" ,"11" ,"69" ,"d9" ,"8e" ,"94" ,"9b" ,"1e" ,"87" ,"e9" ,"ce" ,"55" ,"28" ,"df"} ,
                                {"8c" ,"a1" ,"89" ,"0d" ,"bf" ,"e6" ,"42" ,"68" ,"41" ,"99" ,"2d" ,"0f" ,"b0" ,"54" ,"bb" ,"16"}};

    private char[][] blockText = new char[4][4];
    private String[][] blockKey = new String[4][4];

    private char[][] globalBlockText;
    private String[][] globalBlockKey = new String[4][44];

    public AesCrypto(String key, String text) {
        if(text.length()/16 > 0)
            if(text.length()%16 == 0)
                globalBlockText = new char[4][text.length()/16];
            else
                globalBlockText = new char[4][text.length()/16+4];
        else globalBlockText = new char[4][4];

        this.key = key.toCharArray();
        this.text = text.toCharArray();
        mainCrypto();
//        System.out.println("text = " + text);
//        System.out.println("text.getBytes = " + this.text);
    }

    private void mainCrypto() {
        buildStart();

        initialRound();
    }

    private void initialRound() {
        globalKeyToKey();
        globalTextToText();
        RoundText = 4;
        RoundKey = 4;

        addRoundKey();

    }

    private void addRoundKey() {
        blockText[0][0] = (char)"04".hashCode();
        blockKey[0][0] = "a0";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blockText[j][i] += (char) blockKey[j][i];
                System.out.println(blockKey[j][i].hashCode());
                System.out.println(charToHex(blockText[j][i]));
            }
        }
    }

    private void globalKeyToKey() {
        for (int i = RoundKey; i < RoundKey+4; i++) {
            for (int j = RoundKey; j < RoundKey+4; j++) {
                blockKey[j-RoundKey][i-RoundKey] = globalBlockKey[j][i];
            }
        }
    }

    private void globalTextToText() {
        for (int i = RoundText; i < RoundText+4; i++) {
            for (int j = RoundText; j < RoundText+4; j++) {
                blockText[j-RoundText][i-RoundText] = globalBlockText[j][i];
            }
        }
    }

    private void buildStart() {
        //начинаем делать кей
        int ik = 0;
        String buff = "";
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (key.length > ik) {
                        for (int k = 0; k < 2; k++) {
                            buff += key[ik];
                            ik++;
                        }
                        globalBlockKey[j][i] = buff;
                        buff = "";
                    } else
                        globalBlockKey[j][i] = "00";
                }
            }

        //разбиваем текст на блоки
        int globalI = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (text.length > globalI) {
                    globalBlockText[j][i] = text[globalI];
                    globalI++;
                } else
                    blockText[j][i] = '0';
            }
        }
    }


    private String charToHex(char symbol) {
        StringBuffer sb = new StringBuffer();
            sb.append(Integer.toString((symbol & 0xff) + 0x100, 16).substring(1));
        //System.out.println(sb + "  " + sb.charAt(0));

        return String.valueOf(sb);

    }

    private String charToHex(char[] symbol) {
        StringBuffer sb = new StringBuffer();
        for(char s : symbol)
            sb.append(Integer.toString((s & 0xff) + 0x100, 16).substring(1));
        System.out.println(sb + "  " + sb.charAt(0));

        return String.valueOf(sb);

    }


}
