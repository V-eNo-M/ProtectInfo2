/**
 * Created by 1 on 15.11.2015.
 */
public class AesDecrypto {
    private int RoundText = 0;  //глобальный счётчик
    private int RoundKey = 40;  //глобальный счётчик
    private String out = new String("");

    //таблица подмены
    private String[][] tabl = {{"52" ,"09" ,"6a" ,"d5" ,"30" ,"36" ,"a5" ,"38" ,"bf" ,"40" ,"a3" ,"9e" ,"81" ,"f3" ,"d7" ,"fb"},
            {"7c" ,"e3" ,"39" ,"82" ,"9b" ,"2f" ,"ff" ,"87" ,"34" ,"8e" ,"43" ,"44" ,"c4" ,"de" ,"e9" ,"cb"} ,
            {"54" ,"7b" ,"94" ,"32" ,"a6" ,"c2" ,"23" ,"3d" ,"ee" ,"4c" ,"95" ,"0b" ,"42" ,"fa" ,"c3" ,"4e"} ,
            {"08" ,"2e" ,"a1" ,"66" ,"28" ,"d9" ,"24" ,"b2" ,"76" ,"5b" ,"a2" ,"49" ,"6d" ,"8b" ,"d1" ,"25"} ,
            {"72" ,"f8" ,"f6" ,"64" ,"86" ,"68" ,"98" ,"16" ,"d4" ,"a4" ,"5c" ,"cc" ,"5d" ,"65" ,"b6" ,"92"} ,
            {"6c" ,"70" ,"48" ,"50" ,"fd" ,"ed" ,"b9" ,"da" ,"5e" ,"15" ,"46" ,"57" ,"a7" ,"8d" ,"9d" ,"84"} ,
            {"90" ,"d8" ,"ab" ,"00" ,"8c" ,"bc" ,"d3" ,"0a" ,"f7" ,"e4" ,"58" ,"05" ,"b8" ,"b3" ,"45" ,"06"} ,
            {"d0" ,"2c" ,"1e" ,"8f" ,"ca" ,"3f" ,"0f" ,"02" ,"c1" ,"af" ,"bd" ,"03" ,"01" ,"13" ,"8a" ,"6b"} ,
            {"3a" ,"91" ,"11" ,"41" ,"4f" ,"67" ,"dc" ,"ea" ,"97" ,"f2" ,"cf" ,"ce" ,"f0" ,"b4" ,"e6" ,"73"} ,
            {"96" ,"ac" ,"74" ,"22" ,"e7" ,"ad" ,"35" ,"85" ,"e2" ,"fe" ,"37" ,"e8" ,"1c" ,"75" ,"df" ,"6e"} ,
            {"47" ,"f1" ,"1a" ,"71" ,"1d" ,"29" ,"c5" ,"89" ,"6f" ,"b7" ,"62" ,"0e" ,"aa" ,"18" ,"be" ,"1b"} ,
            {"fc" ,"56" ,"3e" ,"4d" ,"c6" ,"d2" ,"79" ,"20" ,"9a" ,"db" ,"c0" ,"fe" ,"78" ,"cd" ,"5a" ,"f4"} ,
            {"1f" ,"dd" ,"a8" ,"33" ,"88" ,"07" ,"c7" ,"31" ,"b1" ,"12" ,"10" ,"59" ,"27" ,"80" ,"ec" ,"5f"} ,
            {"60" ,"51" ,"7f" ,"a9" ,"19" ,"b5" ,"4a" ,"0d" ,"2d" ,"e5" ,"7a" ,"9f" ,"93" ,"c9" ,"9c" ,"ef"} ,
            {"a0" ,"e0" ,"3b" ,"4d" ,"ae" ,"2a" ,"f5" ,"b0" ,"c8" ,"eb" ,"bb" ,"3c" ,"83" ,"53" ,"99" ,"61"} ,
            {"17" ,"2b" ,"04" ,"7e" ,"ba" ,"77" ,"d6" ,"26" ,"e1" ,"69" ,"14" ,"63" ,"55" ,"21" ,"0c" ,"7d"}};

    private String[][] tab2 = {{"63" ,"7c" ,"77" ,"7b" ,"f2" ,"6b" ,"6f" ,"c5" ,"30" ,"01" ,"67" ,"2b" ,"fe" ,"d7" ,"ab" ,"76"},
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
    private char[][] blockKey = new char[4][4];

    private char[][] globalBlockText;
    private char[][] globalBlockKey = new char[4][44];
    
    
    public AesDecrypto(String key, String text) {
        if(text.length()/16 > 0)
            if(text.length()%16 == 0)
                globalBlockText = new char[4][(text.length()/16)*4];
            else
                globalBlockText = new char[4][(text.length()/16)*4+4];
        else globalBlockText = new char[4][4];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.globalBlockKey[i][j] = (char) Integer.parseInt(key.substring((j*8)+(i * 2),(j*8)+(i * 2) + 2).toString(), 16);
                //System.out.println(key.substring((j*8)+(i * 2),(j*8)+(i * 2) + 2));
            }
        }
//        globalBlockKey[0][0] = (char)Integer.parseInt("2b" ,16);
//        globalBlockKey[1][0] = (char)Integer.parseInt("7e" ,16);
//        globalBlockKey[2][0] = (char)Integer.parseInt("15" ,16);
//        globalBlockKey[3][0] = (char)Integer.parseInt("16" ,16);
//        globalBlockKey[0][1] = (char)Integer.parseInt("28" ,16);
//        globalBlockKey[1][1] = (char)Integer.parseInt("ae" ,16);
//        globalBlockKey[2][1] = (char)Integer.parseInt("d2" ,16);
//        globalBlockKey[3][1] = (char)Integer.parseInt("a6" ,16);
//        globalBlockKey[0][2] = (char)Integer.parseInt("ab" ,16);
//        globalBlockKey[1][2] = (char)Integer.parseInt("f7" ,16);
//        globalBlockKey[2][2] = (char)Integer.parseInt("15" ,16);
//        globalBlockKey[3][2] = (char)Integer.parseInt("88" ,16);
//        globalBlockKey[0][3] = (char)Integer.parseInt("09" ,16);
//        globalBlockKey[1][3] = (char)Integer.parseInt("cf" ,16);
//        globalBlockKey[2][3] = (char)Integer.parseInt("4f" ,16);
//        globalBlockKey[3][3] = (char)Integer.parseInt("3c" ,16);
//
//        globalBlockText[0][0] = (char)Integer.parseInt("32" ,16);
//        globalBlockText[1][0] = (char)Integer.parseInt("43" ,16);
//        globalBlockText[2][0] = (char)Integer.parseInt("f6" ,16);
//        globalBlockText[3][0] = (char)Integer.parseInt("a8" ,16);
//        globalBlockText[0][1] = (char)Integer.parseInt("88" ,16);
//        globalBlockText[1][1] = (char)Integer.parseInt("5a" ,16);
//        globalBlockText[2][1] = (char)Integer.parseInt("30" ,16);
//        globalBlockText[3][1] = (char)Integer.parseInt("8d" ,16);
//        globalBlockText[0][2] = (char)Integer.parseInt("31" ,16);
//        globalBlockText[1][2] = (char)Integer.parseInt("31" ,16);
//        globalBlockText[2][2] = (char)Integer.parseInt("98" ,16);
//        globalBlockText[3][2] = (char)Integer.parseInt("a2" ,16);
//        globalBlockText[0][3] = (char)Integer.parseInt("e0" ,16);
//        globalBlockText[1][3] = (char)Integer.parseInt("37" ,16);
//        globalBlockText[2][3] = (char)Integer.parseInt("07" ,16);
//        globalBlockText[3][3] = (char)Integer.parseInt("34" ,16);

        int shet = 0;

        for (int i = 0; i < globalBlockText[0].length; i++) {
            for (int j = 0; j < 4; j++) {
                if(shet < text.length()){
                    globalBlockText[j][i] = text.charAt(shet);
                    shet++;
                }
                else
                    globalBlockText[j][i] = '0';
            }
        }

        mainDecrypto();
    }

    private void mainDecrypto() {
//расширение ключа и забись в blockText из globalBlockText
        buildStart();
        //начало шифрования
        for (int j = 0; j < globalBlockText[0].length ;j+=4) {
            globalTextToText();
            System.out.println("ДЕШИФРОВАНИЕЕЕЕ _____________________________________________________________________________________");
            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0]) + " " + Integer.toHexString(blockText[2][1]) + " " + Integer.toHexString(blockText[2][2]) + " " + Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));

            addRoundKey();
            globalKeyToKey();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            for (int i = 0; i < 9; i++) {
                invShiftRows();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


                SubBytes();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


                addRoundKey();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


                globalKeyToKey();
                mixColums();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));

            }


            invShiftRows();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            SubBytes();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            addRoundKey();


            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            RoundKey = 40;
            globalKeyToKey();
            addToOut();
        }
        WriteToFile();
    }

    public void SubBytes() {
        //подмена значений из блока текста на значения из таблицы
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buff = Integer.toHexString(blockText[j][i]);
                int x;
                int y;
                if(buff.length()<2){
                    x = 0;
                    y = CharToInt(buff.charAt(0));}
                else {
                    x = CharToInt(buff.charAt(0));
                    y = CharToInt(buff.charAt(1));
                }
               // System.out.println(Integer.toHexString(blockText[j][i]));
               // System.out.println(Integer.toHexString(Integer.parseInt(tabl[x][y],16)));
                blockText[j][i] = (char) Integer.parseInt(tabl[x][y],16);
            }
        }
    }

    public int CharToInt(char m) {
        if (m == '0') {
            return 0;
        }
        else if (m == '1') {
            return 1;
        } else if (m == '2') {
            return 2;
        } else if (m == '3') {
            return 3;
        } else if (m == '4') {
            return 4;
        } else if (m == '5') {
            return 5;
        } else if (m == '6') {
            return 6;
        } else if (m == '7') {
            return 7;
        } else if (m == '8') {
            return 8;
        } else if (m == '9') {
            return 9;
        } else if (m == 'a' || m == 'A') {
            return 10;
        } else if (m == 'b' || m == 'B') {
            return 11;
        } else if (m == 'c' || m == 'C') {
            return 12;
        } else if (m == 'd' || m == 'D') {
            return 13;
        } else if (m == 'e' || m == 'E') {
            return 14;
        } else if (m == 'f' || m == 'F') {
            return 15;
        } else return 100500;
    }

    public void addRoundKey() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               // System.out.println("TEXT "+Integer.toHexString(blockText[i][j]));
              //  System.out.println("KEY "+Integer.toHexString(blockKey[i][j]));
                blockText[i][j] = (char) Integer.parseInt(Integer.toHexString(blockText[i][j] ^ blockKey[i][j]),16);
              //  System.out.println(Integer.toHexString(blockText[i][j]));
            }
        }

    }

    private void keySchedule() {
        //расширение ключа для 10 раундов
        char[][] rcon ={
                {0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1b,0x36,},
                {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,},
                {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,},
                {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,}};
        char[] buf = new char[4];
        for (int i = 0; i < 10; i++) {
            buf[0] = globalBlockKey[1][i*4+3];
            buf[1] = globalBlockKey[2][i*4+3];
            buf[2] = globalBlockKey[3][i*4+3];
            buf[3] = globalBlockKey[0][i*4+3];
            for (int j = 0; j < 4; j++) {
                String buff = Integer.toHexString(buf[j]);
                int x;
                int y;
                if(buff.length()<2){
                    x = 0;
                    y = CharToInt(buff.charAt(0));}
                else {
                    x = CharToInt(buff.charAt(0));
                    y = CharToInt(buff.charAt(1));
                }
                buf[j] = (char) Integer.parseInt(tabl[x][y],16);
            }

//            buf[0] = (char)Integer.parseInt("7e",16);
//            globalBlockKey[0][0] = (char) Integer.parseInt("84",16);
            for (int j = 0; j < 4; j++) {
                globalBlockKey[j][i*4+4] =  (char) Integer.parseInt(Integer.toHexString(buf[j] ^ globalBlockKey[j][i*4] ^ rcon[j][i]),16);
                // System.out.println(Integer.toHexString(buf[j] ^ globalBlockKey[j][i*4] ^ rcon[j][i]));
            }

            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    globalBlockKey[k][i*4+4+j] = (char) Integer.parseInt(Integer.toHexString(globalBlockKey[k][i*4+3+j] ^ globalBlockKey[k][i+j]),16);
                    //System.out.println(Integer.toHexString(globalBlockKey[k][i*4+3+j] ^ globalBlockKey[k][i+j]));
                }
            }
        }


    }

    public void invShiftRows() {
        // перетасовка в строках 1,2,3
        // перетасовка строки 1
        char buff1;
        char buff2;
        buff1 = blockText[1][3];
        blockText[1][1] = blockText[1][0];
        blockText[1][2] = blockText[1][1];
        blockText[1][3] = blockText[1][2];
        blockText[1][0] = buff1;

        //перетасовка строки 2
        buff1 = blockText[2][3];
        buff2 = blockText[2][2];

        blockText[2][2] = blockText[2][0];
        blockText[2][3] = blockText[2][1];
        blockText[2][0] = buff2;
        blockText[2][1] = buff1;

        //перестановка 3 строки
        buff1 = blockText[3][0];
        blockText[3][0] = blockText[3][1];
        blockText[3][1] = blockText[3][2];
        blockText[3][2] = blockText[3][3];
        blockText[3][3] = buff1;

    }

    private void WriteToFile() {

        WriteToFile m = new WriteToFile();
        m.write("decrypto",out);
    }

    private void globalKeyToKey() {
        for (int i = RoundKey; i < RoundKey+4; i++) {
            for (int j = 0; j < 4; j++) {
                blockKey[j][i-RoundKey] = globalBlockKey[j][i];
            }
        }
        RoundKey -=4;
    }

    private void globalTextToText() {
        for (int i = RoundText; i < RoundText+4; i++) {
            for (int j =0; j < 4; j++) {
                blockText[j][i-RoundText] = globalBlockText[j][i];
            }
        }
        RoundText +=4;
    }

    private void mixColums() {
        char t1;
        char t2;
        char t3;
        char t4;
        char rez1;
        char rez2;
        char rez3;
        char rez4;
        for (int i = 0; i < 4; i++) {
            //System.out.println(Integer.toHexString(blockText[0][i]));
            //System.out.println(Integer.toHexString(blockText[1][i]));
            //System.out.println(Integer.toHexString(blockText[2][i]));
            //System.out.println(Integer.toHexString(blockText[3][i]));


            //умножение элемента первой строки
            //умножение на 14
            //умнож 2
            if((int) blockText[0][i] >= 128)
                t1 = (char) (((byte)blockText[0][i] << 1) ^ 27);
            else
                t1 = (char)((byte)blockText[0][i] << 1);
            //умнож на 1
                t1 = (char) ((byte)t1^blockText[0][i]);
            //умнож на 2
            if((int) t1 >= 128)
                t1 = (char) (((byte)blockText[0][i] << 1) ^ 27);
            else t1 = (char)((byte)blockText[0][i] << 1);
            //умнож на 1
                t1 = (char) ((byte)t1^blockText[0][i]);
            //умнож на 2
            if((int) t1 >= 128)
                t1 = (char) (((byte)blockText[0][i] << 1) ^ 27);
            else t1 = (char)((byte)blockText[0][i] << 1);


            //Умножение на 11
            //умножение на 2
            if((int) blockText[1][i] >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 2
            if((int) t2 >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 1
                t2 = (char) ((byte)t2^blockText[1][i]);
            //умножение на 2
            if((int) t2 >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 1
            t2 = (char) ((byte)t2^blockText[1][i]);



            //умножение на 13
            //умнож на 2
            if((int) (int) blockText[2][i] >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 1
            t3 = (char)((byte)(t3^blockText[2][i]));
            //умнож на 2
            if((int) (int) t3 >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 2
            if((int) (int) t3 >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 1
            t3 = (char)((byte)(t3^blockText[2][i]));


            //умножение на 9
            //умнож на 2
            if((int) (int) blockText[3][i] >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 2
            if((int) (int) t4 >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 2
            if((int) (int) t4 >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 1
            t4 = (char)((byte)(t4^blockText[3][i]));
            rez1 = (char)((t1^t2^t3^t4)&0xff);


//------------------------------------------------------------------------------------------------------------------

            //умножение элемента второй строки
            //умножение на 9
            //умнож на 2
            if((int) (int) blockText[0][i] >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 2
            if((int) (int) t1 >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 2
            if((int) (int) t1 >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 1
            t1 = (char)((byte)(t1^blockText[0][i]));

            //умножение на 14
            //умнож 2
            if((int) blockText[1][i] >= 128)
                t2 = (char) (((byte)blockText[1][i] << 1) ^ 27);
            else
                t2 = (char)((byte)blockText[1][i] << 1);
            //умнож на 1
            t2 = (char) ((byte)t2^blockText[1][i]);
            //умнож на 2
            if((int) t2 >= 128)
                t2 = (char) (((byte)blockText[1][i] << 1) ^ 27);
            else t2 = (char)((byte)blockText[1][i] << 1);
            //умнож на 1
            t2 = (char) ((byte)t2^blockText[1][i]);
            //умнож на 2
            if((int) t2 >= 128)
                t2 = (char) (((byte)blockText[1][i] << 1) ^ 27);
            else t2 = (char)((byte)blockText[1][i] << 1);


            //Умножение на 11
            //умножение на 2
            if((int) blockText[2][i] >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 2
            if((int) t3 >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 1
            t3 = (char) ((byte)t3^blockText[2][i]);
            //умножение на 2
            if((int) t3 >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 1
            t3 = (char) ((byte)t3^blockText[2][i]);

            //умножение на 13
            //умнож на 2
            if((int) (int) blockText[3][i] >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 1
            t4 = (char)((byte)(t4^blockText[3][i]));
            //умнож на 2
            if((int) (int) t4 >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 2
            if((int) (int) t4 >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 1
            t4 = (char)((byte)(t4^blockText[3][i]));
            rez2 = (char)((t1^t2^t3^t4)&0xff);

//----------------------------------------------------------------------------------------------------------------------------------------------
            //умножение на 13
            //умнож на 2
            if((int) (int) blockText[0][i] >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 1
            t1 = (char)((byte)(t1^blockText[0][i]));
            //умнож на 2
            if((int) (int) t1 >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 2
            if((int) (int) t1 >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 1
            t1 = (char)((byte)(t1^blockText[0][i]));


            //умножение на 9
            //умнож на 2
            if((int) (int) blockText[1][i] >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 2
            if((int) (int) t2 >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 2
            if((int) (int) t2 >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 1
            t2 = (char)((byte)(t2^blockText[1][i]));


            //умножение на 14
            //умнож 2
            if((int) blockText[2][i] >= 128)
                t3 = (char) (((byte)blockText[2][i] << 1) ^ 27);
            else
                t3 = (char)((byte)blockText[2][i] << 1);
            //умнож на 1
            t3 = (char) ((byte)t3^blockText[2][i]);
            //умнож на 2
            if((int) t3 >= 128)
                t3 = (char) (((byte)blockText[2][i] << 1) ^ 27);
            else t3 = (char)((byte)blockText[2][i] << 1);
            //умнож на 1
            t3 = (char) ((byte)t3^blockText[2][i]);
            //умнож на 2
            if((int) t3 >= 128)
                t3 = (char) (((byte)blockText[2][i] << 1) ^ 27);
            else t3 = (char)((byte)blockText[2][i] << 1);


            //Умножение на 11
            //умножение на 2
            if((int) blockText[3][i] >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 2
            if((int) t4 >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 1
            t4 = (char) ((byte)t4^blockText[3][i]);
            //умножение на 2
            if((int) t4 >= 128)
                t4 = (char)((((byte)blockText[3][i] << 1) ^ 27));
            else
                t4 = (char)((byte)(blockText[3][i] << 1));
            //умнож на 1
            t4 = (char) ((byte)t4^blockText[3][i]);
            rez3 = (char)((t1^t2^t3^t4)&0xff);

//----------------------------------------------------------------------------------------------------------------------------------------------------
            //Умножение на 11
            //умножение на 2
            if((int) blockText[0][i] >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 2
            if((int) t1 >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 1
            t1 = (char) ((byte)t1^blockText[0][i]);
            //умножение на 2
            if((int) t1 >= 128)
                t1 = (char)((((byte)blockText[0][i] << 1) ^ 27));
            else
                t1 = (char)((byte)(blockText[0][i] << 1));
            //умнож на 1
            t1 = (char) ((byte)t1^blockText[0][i]);

            //умножение на 13
            //умнож на 2
            if((int) (int) blockText[1][i] >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 1
            t2 = (char)((byte)(t2^blockText[1][i]));
            //умнож на 2
            if((int) (int) t2 >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 2
            if((int) (int) t2 >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27));
            else
                t2 = (char)((byte)(blockText[1][i] << 1));
            //умнож на 1
            t2 = (char)((byte)(t2^blockText[1][i]));

            //умножение на 9
            //умнож на 2
            if((int) (int) blockText[2][i] >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 2
            if((int) (int) t3 >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 2
            if((int) (int) t3 >= 128)
                t3 = (char)((((byte)blockText[2][i] << 1) ^ 27));
            else
                t3 = (char)((byte)(blockText[2][i] << 1));
            //умнож на 1
            t3 = (char)((byte)(t3^blockText[2][i]));

            //умножение на 14
            //умнож 2
            if((int) blockText[3][i] >= 128)
                t4 = (char) (((byte)blockText[3][i] << 1) ^ 27);
            else
                t4 = (char)((byte)blockText[3][i] << 1);
            //умнож на 1
            t4 = (char) ((byte)t4^blockText[3][i]);
            //умнож на 2
            if((int) t4 >= 128)
                t4 = (char) (((byte)blockText[3][i] << 1) ^ 27);
            else t4 = (char)((byte)blockText[3][i] << 1);
            //умнож на 1
            t4 = (char) ((byte)t4^blockText[3][i]);
            //умнож на 2
            if((int) t4 >= 128)
                t4 = (char) (((byte)blockText[3][i] << 1) ^ 27);
            else t4 = (char)((byte)blockText[3][i] << 1);
            rez4 = (char)((t1^t2^t3^t4)&0xff);

            blockText[0][i] = rez1;
            blockText[1][i] = rez2;
            blockText[2][i] = rez3;
            blockText[3][i] = rez4;
        }
    }

    private void addToOut() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                out += blockText[j][i];
            }
        }
    }

    private void buildStart() {
        keySchedule();
        globalKeyToKey();
    }

}
