/**
 * Created by 1 on 15.11.2015.
 */
public class AesCrypto {
    private int RoundText = 0;  //глобальный счётчик
    private int RoundKey = 0;  //глобальный счётчик
    private String out = new String("");

    //таблица подмены
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
    private char[][] blockKey = new char[4][4];

    private char[][] globalBlockText;
    private char[][] globalBlockKey = new char[4][44];

    public AesCrypto(String key, String text) {
        if(text.length()/16 > 0)
            if(text.length()%16 == 0)
                globalBlockText = new char[4][text.length()/16];
            else
                globalBlockText = new char[4][text.length()/16+4];
        else globalBlockText = new char[4][4];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.globalBlockKey[i][j] = this.blockKey[i][j] = (char) Integer.parseInt(key.substring((j*8)+(i * 2),(j*8)+(i * 2) + 2).toString(), 16);
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

        mainCrypto();
//        System.out.println("text = " + text);
//        System.out.println("text.getBytes = " + this.text);
    }

    private void mainCrypto() {
        //расширение ключа и забить в blockText из globalBlockText
        buildStart();
        //начало шифрования
        for (int j = 0; j < globalBlockText[0].length ;j+=4) {
            globalTextToText();
            System.out.println("ШИФРОВАНИЕЕЕЕ _____________________________________________________________________________________");
            addRoundKey();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0]) + " " + Integer.toHexString(blockText[1][1]) + " " + Integer.toHexString(blockText[1][2]) + " " + Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0]) + " " + Integer.toHexString(blockText[3][1]) + " " + Integer.toHexString(blockText[3][2]) + " " + Integer.toHexString(blockText[3][3]));


            globalKeyToKey();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));

            for (int i = 0; i < 9; i++) {
                SubBytes();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


                ShiftRows();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


                mixColums();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


                addRoundKey();
                globalKeyToKey();

                System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
                System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
                System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
                System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));

            }

            SubBytes();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            ShiftRows();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            addRoundKey();

            System.out.println(Integer.toHexString(blockText[0][0]) + " " + Integer.toHexString(blockText[0][1]) + " " + Integer.toHexString(blockText[0][2]) + " " + Integer.toHexString(blockText[0][3]));
            System.out.println(Integer.toHexString(blockText[1][0])+" " + Integer.toHexString(blockText[1][1]) +" "+ Integer.toHexString(blockText[1][2]) +" "+ Integer.toHexString(blockText[1][3]));
            System.out.println(Integer.toHexString(blockText[2][0])+" " + Integer.toHexString(blockText[2][1]) +" "+ Integer.toHexString(blockText[2][2]) +" "+ Integer.toHexString(blockText[2][3]));;
            System.out.println(Integer.toHexString(blockText[3][0])+" " + Integer.toHexString(blockText[3][1]) +" "+ Integer.toHexString(blockText[3][2]) +" "+ Integer.toHexString(blockText[3][3]));


            RoundKey = 0;
            globalKeyToKey();
            addToOut();
        }
        WriteToFile();
    }

    private void addToOut() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                out += blockText[j][i];
            }
        }
    }

    public void addRoundKey() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               // System.out.println("TEXT "+Integer.toHexString(blockText[i][j]));
               // System.out.println("KEY "+Integer.toHexString(blockKey[i][j]));
                blockText[i][j] = (char) Integer.parseInt(Integer.toHexString(blockText[i][j] ^ blockKey[i][j]),16);
               // System.out.println(Integer.toHexString(blockText[i][j]));
            }
        }

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
              //  System.out.println(Integer.toHexString(Integer.parseInt(tabl[x][y],16)));
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
           // System.out.println(Integer.toHexString(blockText[0][i]));
          //  System.out.println(Integer.toHexString(blockText[1][i]));
           // System.out.println(Integer.toHexString(blockText[2][i]));
           // System.out.println(Integer.toHexString(blockText[3][i]));

            //умножение элемента первой строки
            //умножение на 2
            if((int) blockText[0][i] >= 128)
                t1 = (char) (((byte)blockText[0][i] << 1) ^ 27);
            else
                t1 = (char)((byte)blockText[0][i] << 1);
            //умножение на 3
            if((int) blockText[1][i] >= 128)
                t2 = (char)((((byte)blockText[1][i] << 1) ^ 27)^blockText[1][i]);
            else
                t2 = (char)((byte)(blockText[1][i] << 1) ^ blockText[1][i]);
            t3 = blockText[2][i];
            t4 = blockText[3][i];
            rez1 = (char)((t1^t2^t3^t4)&0xff);
            //System.out.println(Integer.toHexString(rez1));

            //умножение элемента второй строки
            //умножение на 1
            t1 =  blockText[0][i];
            //умножение на 2
            if ((int) blockText[1][i] >= 128) {
                t2 = (char)((blockText[1][i] << 1) ^ 27);
            }
            else t2 = (char) (blockText[1][i]<< 1);
            //умножение на 3
            if ((int) blockText[2][i] >= 128) {
                t3 = (char)(((blockText[2][i]<< 1)^27)^blockText[2][i]);
            }
            else t3 = (char)((blockText[2][i]<< 1)^blockText[2][i]);
            //умножение на 1
            t4 = blockText[3][i];
            rez2 = (char)((t1^t2^t3^t4)&0xff);
            //System.out.println(Integer.toHexString(rez2));

            //умножение элемента третьей строки
            //умножение на 1
            t1 = blockText[0][i];
            t2 = blockText[1][i];
            //умножение на 2
            if ((int) blockText[2][i] >= 128) {
                t3 = (char)((blockText[2][i] << 1) ^ 27);
            }
            else t3 = (char) (blockText[2][i]<< 1);
            //умножение на 3
            if ((int) blockText[3][i] >= 128) {
                t4 = (char)(((blockText[3][i]<< 1)^27)^blockText[3][i]);
            }
            else t4 = (char)((blockText[3][i]<< 1)^blockText[3][i]);
            rez3 = (char)((t1^t2^t3^t4)&0xff);
           // System.out.println(Integer.toHexString(rez3));


            //умножение элемента четвёртой строки
            //умножение на 3
            if ((int) blockText[0][i] >= 128) {
                t1 = (char)(((blockText[0][i]<< 1)^27)^blockText[0][i]);
            }
            else t1 = (char)((blockText[0][i]<< 1)^blockText[0][i]);
            //умножение на 1
            t2=blockText[1][i];
            t3=blockText[2][i];
            //умножение на 2
            if ((int) blockText[3][i] >= 128) {
                t4 = (char)((blockText[3][i] << 1) ^ 27);
            }
            else t4 = (char) (blockText[3][i]<< 1);
            rez4 = (char)((t1^t2^t3^t4)&0xff);
           // System.out.println(Integer.toHexString(rez4));

            blockText[0][i] = rez1;
            blockText[1][i] = rez2;
            blockText[2][i] = rez3;
            blockText[3][i] = rez4;
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

    private void globalKeyToKey() {
        for (int i = RoundKey; i < RoundKey+4; i++) {
            for (int j = 0; j < 4; j++) {
                blockKey[j][i-RoundKey] = globalBlockKey[j][i];
            }
        }
        RoundKey +=4;
    }

    private void globalTextToText() {
        for (int i = RoundText; i < RoundText+4; i++) {
            for (int j =0; j < 4; j++) {
                blockText[j][i-RoundText] = globalBlockText[j][i];
            }
        }
        RoundText +=4;
    }

    public void ShiftRows() {
        // перетасовка в строках 1,2,3
        // перетасовка строки 1
        char buff1;
        char buff2;
        buff1 = blockText[1][0];
        blockText[1][0] = blockText[1][1];
        blockText[1][1] = blockText[1][2];
        blockText[1][2] = blockText[1][3];
        blockText[1][3] = buff1;

        //перетасовка строки 2
        buff1 = blockText[2][0];
        buff2 = blockText[2][1];

        blockText[2][0] = blockText[2][2];
        blockText[2][1] = blockText[2][3];
        blockText[2][3] = buff2;
        blockText[2][2] = buff1;

        //перестановка 3 строки
        buff1 = blockText[3][3];
        blockText[3][3] = blockText[3][2];
        blockText[3][2] = blockText[3][1];
        blockText[3][1] = blockText[3][0];
        blockText[3][0] = buff1;

    }

    private void buildStart() {
        keySchedule();
        globalKeyToKey();
    }

    private void WriteToFile() {

        WriteToFile m = new WriteToFile();
        m.write("crypto",out);
    }


}
