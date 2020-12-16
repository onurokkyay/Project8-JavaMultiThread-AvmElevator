/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab1proje2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsansörThread extends Thread {

    String name;
    public boolean mod = false;
    public boolean kat1yolcusu = false;
    public boolean kat2yolcusu = false;
    public boolean kat3yolcusu = false;
    public boolean kat4yolcusu = false;
    public boolean inisyolcusu = false;
    public int kat = 0;
    public int hedefkat;
    public String yön = "yukari";
    public int kapasite = 0;
    public Semaphore semaforkat0, semaforkat1yukari, semaforkat2yukari, semaforkat3yukari, semaforkat4yukari, semaforkat1asagi, semaforkat2asagi, semaforkat3asagi, semaforkat4asagi;
    ArrayList<Binenler> icindekiler = new ArrayList<Binenler>();

    @Override
    public void run() {
        int a = 0;
        while (true) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(AsansörThread.class.getName()).log(Level.SEVERE, null, ex);
            } //Bu kısım olmazsa while çalışmıyor,sout da koyabilirsin veya böyle bir şeyler.
            if (mod == true) {
                //System.out.println("İFİN İCİ NAME:"+this.name);
                try {
                    //Thread.sleep(200);
                    //AsansörHareket();
                    AsansörKontrollüHareket();

                } catch (InterruptedException ex) {
                    System.err.println(ex);

                }
            }

        }

    }

    public AsansörThread(String name) {
        this.name = name;

    }

    public void AsansörKontrollüHareket() throws InterruptedException {

        if (this.kat == 0 && this.yön == "yukari" && Avm.katlar.get(0).Kuyruk.size() > 0) {
            Avm.semaphoregenel.acquire();
            //yolcu al
            int eklenecekkisi;
            while (kapasite < 10 && Avm.katlar.get(0).Kuyruk.size() > 0) {
//                System.out.println("Kapasite:"+kapasite);
                if (kapasite + Avm.katlar.get(0).Kuyruk.get(0).kisiler > 10) {
                    eklenecekkisi = 10 - kapasite;
                    icindekiler.add(new Binenler(eklenecekkisi, Avm.katlar.get(0).Kuyruk.get(0).kat));
                    kapasite += eklenecekkisi;
                    Avm.katlar.get(0).kisisayisi -= eklenecekkisi;
                    Avm.katlar.get(0).Kuyruk.get(0).kisiler -= eklenecekkisi;
                } else {
                    icindekiler.add(Avm.katlar.get(0).Kuyruk.get(0));
                    kapasite += Avm.katlar.get(0).Kuyruk.get(0).kisiler;
                    Avm.katlar.get(0).kisisayisi -= Avm.katlar.get(0).Kuyruk.get(0).kisiler;
                    Avm.katlar.get(0).Kuyruk.remove(0);
                }
            }
//            System.out.println("Yukarı çıkıyorum 0.katta Şu kadar kişi var:"+Avm.katlar.get(0).kisisayisi+" Asansörün Kapasitesi"+kapasite);,
 Avm.semaphoregenel.release();
        }

        for (int i = 0; i < icindekiler.size(); i++) {
            if (icindekiler.get(i).kat == 1) {
                kat1yolcusu = true;
            }
            if (icindekiler.get(i).kat == 2) {
                kat2yolcusu = true;
            }
            if (icindekiler.get(i).kat == 3) {
                kat3yolcusu = true;
            }
            if (icindekiler.get(i).kat == 4) {
                kat4yolcusu = true;
            }
        }

        //Artık içindekilerin hangi kata gideceğini bliyoruz.
        if (kat1yolcusu = true) {
            this.hedefkat=1;
            Thread.sleep(200);
             Avm.semaphoregenel.acquire();
            this.yön = "yukari";
            kat = 1; //Kat 0 dan kat 1 e geldik.         
            //Kat 1 e yolcuları bırak,eğer diğer katlarda bırakacağın varsa yukarı devam et,veya üst katlardan aşağı inmek isteyen yolcu varsa.(?)  

            for (int i = 0; i < icindekiler.size(); i++) {
                if (icindekiler.get(i).kat == kat) {
                    Avm.katlar.get(kat).kisisayisi += icindekiler.get(i).kisiler;
                    kapasite -= icindekiler.get(i).kisiler;

                    icindekiler.remove(i);
                    i--;
                }

            }

            kat1yolcusu = false;
 Avm.semaphoregenel.release();
            if (kat2yolcusu == false && kat3yolcusu == false && kat4yolcusu == false) {
                //Eğer yukarı gidecek yolcu yoksa aşağı inecek yolcu var mı diye kontrol et.
                if (Avm.katlar.get(4).Kuyruk.size() > 0 && this.kapasite < 10) {//Demek ki aşağı inecek yolcu var.O zaman kat 4 e git
                     this.hedefkat=4;
                    Thread.sleep((4-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 4;
                    //Kattan alma işlemi yap ve yavaşça kat 0 a doğru ilerle.
                    int asagieklenecekkisi;

                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                   
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(3).Kuyruk.size() > 0 && this.kapasite < 10) {
                    this.hedefkat=3;
                    Thread.sleep(Math.abs(3-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 3;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(2).Kuyruk.size() > 0 && this.kapasite < 10) {
                    this.hedefkat=2;
                     Thread.sleep(Math.abs(2-kat)*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 2;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }

                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(1).Kuyruk.size() > 0 && this.kapasite < 10) {
                    this.hedefkat=1;
                      Thread.sleep(Math.abs(1-kat)*200);
                       Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 1;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                 if(icindekiler.size()==0 || kapasite==0){
                     this.hedefkat=0;
                   Thread.sleep(200);
                    
                    this.yön = "yukari";
                    this.kat = 0;
               }

                if (icindekiler.size() > 0 || this.kapasite == 10) {//Kat 0 a gidip yolcuları bırak.
                     this.hedefkat=0;
                     Thread.sleep(kat*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "yukari";
                    this.kat = 0;
                    //Bırakma işlemleri
                    Avm.katlar.get(0).cikiskisisayisi += kapasite;
                    icindekiler.clear();
                    kapasite = 0;
                    System.out.println("En alta geldi yolcuları bıraktık");
                    System.out.println("" + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi + " Asansörün Kapasitesi" + kapasite);
                    Avm.semaphoregenel.release(); 
                }
                
              
                  
            }
                     
        }
        if (kat2yolcusu = true) {
             this.hedefkat=2;
            Thread.sleep(Math.abs(2-kat)*200);
             Avm.semaphoregenel.acquire();
            this.yön = "yukari";
            kat = 2;
            for (int i = 0; i < icindekiler.size(); i++) {
                if (icindekiler.get(i).kat == kat) {
                    Avm.katlar.get(kat).kisisayisi += icindekiler.get(i).kisiler;
                    kapasite -= icindekiler.get(i).kisiler;

                    icindekiler.remove(i);
                    i--;
                }

            }
            
            kat2yolcusu = false;
             Avm.semaphoregenel.release();
            if (kat3yolcusu == false && kat4yolcusu == false) {
                 if (Avm.katlar.get(4).Kuyruk.size() > 0 && this.kapasite < 10) {//Demek ki aşağı inecek yolcu var.O zaman kat 4 e git
                      this.hedefkat=4;
                    Thread.sleep((4-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 4;
                    //Kattan alma işlemi yap ve yavaşça kat 0 a doğru ilerle.
                    int asagieklenecekkisi;

                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                   
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(3).Kuyruk.size() > 0 && this.kapasite < 10) {
                     this.hedefkat=3;
                    Thread.sleep(Math.abs(3-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 3;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(2).Kuyruk.size() > 0 && this.kapasite < 10) {
                     this.hedefkat=2;
                     Thread.sleep(Math.abs(2-kat)*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 2;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }

                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(1).Kuyruk.size() > 0 && this.kapasite < 10) {
                     this.hedefkat=1;
                      Thread.sleep(Math.abs(1-kat)*200);
                       Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 1;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                
                if(icindekiler.size()==0 || kapasite==0){
                    this.hedefkat=0;
                   Thread.sleep(400);
                    
                    this.yön = "yukari";
                    this.kat = 0;
               }
                
                if (icindekiler.size() > 0 || this.kapasite == 10) {//Kat 0 a gidip yolcuları bırak.
                        this.hedefkat=0;
                     Thread.sleep(kat*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "yukari";
                    this.kat = 0;
                    //Bırakma işlemleri
                    Avm.katlar.get(0).cikiskisisayisi += kapasite;
                    icindekiler.clear();
                    kapasite = 0;
                    System.out.println("En alta geldi yolcuları bıraktık");
                    System.out.println("" + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi + " Asansörün Kapasitesi" + kapasite);
                     Avm.semaphoregenel.release();
                }
                
                
                  
            }

            
            
        }
        if (kat3yolcusu = true) {
             this.hedefkat=3;
            Thread.sleep(Math.abs(3-kat)*200);
             Avm.semaphoregenel.acquire();
            this.yön = "yukari";
            kat = 3;
            for (int i = 0; i < icindekiler.size(); i++) {
                if (icindekiler.get(i).kat == kat) {
                    Avm.katlar.get(kat).kisisayisi += icindekiler.get(i).kisiler;
                    kapasite -= icindekiler.get(i).kisiler;

                    icindekiler.remove(i);
                    i--;
                }

            }
            kat3yolcusu = false;
             Avm.semaphoregenel.release();
            if (kat4yolcusu == false) {
                    //Eğer yukarı gidecek yolcu yoksa aşağı inecek yolcu var mı diye kontrol et.
                if (Avm.katlar.get(4).Kuyruk.size() > 0 && this.kapasite < 10) {//Demek ki aşağı inecek yolcu var.O zaman kat 4 e git
                       this.hedefkat=4;
                    Thread.sleep((4-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 4;
                    //Kattan alma işlemi yap ve yavaşça kat 0 a doğru ilerle.
                    int asagieklenecekkisi;

                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                   
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(3).Kuyruk.size() > 0 && this.kapasite < 10) {
                           this.hedefkat=3;
                    Thread.sleep(Math.abs(3-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 3;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(2).Kuyruk.size() > 0 && this.kapasite < 10) {
                           this.hedefkat=2;
                     Thread.sleep(Math.abs(2-kat)*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 2;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }

                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(1).Kuyruk.size() > 0 && this.kapasite < 10) {
                           this.hedefkat=1;
                      Thread.sleep(Math.abs(1-kat)*200);
                       Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 1;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                
                if(icindekiler.size()==0 || kapasite==0){
                           this.hedefkat=0;
                   Thread.sleep(600);
                    
                    this.yön = "yukari";
                    this.kat = 0;
               }

                if (icindekiler.size() > 0 || this.kapasite == 10) {//Kat 0 a gidip yolcuları bırak.
                           this.hedefkat=0;
                     Thread.sleep(kat*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "yukari";
                    this.kat = 0;
                    //Bırakma işlemleri
                    Avm.katlar.get(0).cikiskisisayisi += kapasite;
                    icindekiler.clear();
                    kapasite = 0;
                    System.out.println("En alta geldi yolcuları bıraktık");
                    System.out.println("" + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi + " Asansörün Kapasitesi" + kapasite);
                    Avm.semaphoregenel.release(); 
                }
                  
            }

        }
        
        if (kat4yolcusu = true) {
             this.hedefkat=4;
            Thread.sleep(Math.abs(4-kat)*200);
             Avm.semaphoregenel.acquire();
            this.yön = "yukari";
            kat = 4;
            for (int i = 0; i < icindekiler.size(); i++) {
                if (icindekiler.get(i).kat == kat) {
                    Avm.katlar.get(kat).kisisayisi += icindekiler.get(i).kisiler;
                    kapasite -= icindekiler.get(i).kisiler;

                    icindekiler.remove(i);
                    i--;
                }

            }
            kat4yolcusu = false;
             Avm.semaphoregenel.release();
            
            //Önce bırakma işlemlerini yap
            //Aşağı doğru in
                //Eğer yukarı gidecek yolcu yoksa aşağı inecek yolcu var mı diye kontrol et.
                if (Avm.katlar.get(4).Kuyruk.size() > 0 && this.kapasite < 10) {//Demek ki aşağı inecek yolcu var.O zaman kat 4 e git
                       this.hedefkat=4;
                    Thread.sleep((4-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 4;
                    //Kattan alma işlemi yap ve yavaşça kat 0 a doğru ilerle.
                    int asagieklenecekkisi;

                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                   
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(3).Kuyruk.size() > 0 && this.kapasite < 10) {
                     this.hedefkat=3;
                    Thread.sleep(Math.abs(3-kat)*200);
                     Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 3;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(2).Kuyruk.size() > 0 && this.kapasite < 10) {
                     this.hedefkat=2;
                     Thread.sleep(Math.abs(2-kat)*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 2;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }

                     Avm.semaphoregenel.release();
                }
                if (Avm.katlar.get(1).Kuyruk.size() > 0 && this.kapasite < 10) {
                     this.hedefkat=1;
                      Thread.sleep(Math.abs(1-kat)*200);
                       Avm.semaphoregenel.acquire();
                    this.yön = "asagi";
                    this.kat = 1;
                    int asagieklenecekkisi;
                    while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                        System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);

                        if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                            asagieklenecekkisi = 10 - kapasite;
                            icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                            Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                            kapasite += asagieklenecekkisi;
                            //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                        } else {
                            icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                            kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                            Avm.katlar.get(kat).Kuyruk.remove(0);
                        }

                    }
                     Avm.semaphoregenel.release();
                }
                
                if(icindekiler.size()==0 || kapasite==0){
                     this.hedefkat=0;
                   Thread.sleep(800);
                    
                    this.yön = "yukari";
                    this.kat = 0;
               }

                if (icindekiler.size() > 0 || this.kapasite == 10) {//Kat 0 a gidip yolcuları bırak.
                     this.hedefkat=0;
                     Thread.sleep(kat*200);
                      Avm.semaphoregenel.acquire();
                    this.yön = "yukari";
                    this.kat = 0;
                    //Bırakma işlemleri
                    Avm.katlar.get(0).cikiskisisayisi += kapasite;
                    icindekiler.clear();
                    kapasite = 0;
                    System.out.println("En alta geldi yolcuları bıraktık");
                    System.out.println("" + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi + " Asansörün Kapasitesi" + kapasite);
                    Avm.semaphoregenel.release(); 
                }
                  
        }

    }

    public void AsansörHareket() throws InterruptedException {
        Avm.semaphoregenel.acquire();
        if (yön == "asagi" && kat == 0) {

            Avm.katlar.get(0).cikiskisisayisi += kapasite;
            icindekiler.clear();
            yön = "yukari";
            hedefkat = 1;
            kapasite = 0;
            System.out.println("En alta geldi yolcuları bıraktık");
            System.out.println("" + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi + " Asansörün Kapasitesi" + kapasite);

        }

        if (yön == "yukari" && kat == 0 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

            Avm.semaphorekat0.acquire();
            int eklenecekkisi;
            while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {
//                System.out.println("Kapasite:"+kapasite);
                if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                    eklenecekkisi = 10 - kapasite;
                    icindekiler.add(new Binenler(eklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                    kapasite += eklenecekkisi;
                    Avm.katlar.get(0).kisisayisi -= eklenecekkisi;
                    Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= eklenecekkisi;

                } else {
                    icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                    kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                    Avm.katlar.get(0).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                    Avm.katlar.get(kat).Kuyruk.remove(0);

                }
            }
            hedefkat = 1;
            kat++;
            System.out.println("Yukarı çıkıyorum 0.katta Şu kadar kişi var:" + Avm.katlar.get(0).kisisayisi + " Asansörün Kapasitesi" + kapasite);

            Avm.semaphorekat0.release();

        }

        if (yön == "yukari" && kat != 0) {

            if (kat == 1) {

                Avm.semaphorekat1yukari.acquire();

            } else if (kat == 2) {
                Avm.semaphorekat2yukari.acquire();
            } else if (kat == 3) {
                Avm.semaphorekat3yukari.acquire();
            } else if (kat == 4) {
                Avm.semaphorekat4yukari.acquire();
            }

            System.out.println("Yukari cikiyorum");
            for (int i = 0; i < icindekiler.size(); i++) {
                if (icindekiler.get(i).kat == kat) {
                    Avm.katlar.get(kat).kisisayisi += icindekiler.get(i).kisiler;
                    kapasite -= icindekiler.get(i).kisiler;

                    icindekiler.remove(i);
                    i--;
                }

            }
            System.out.println("Yukarı çıkıyorum " + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi + " Asansörün Kapasitesi" + kapasite);
            hedefkat = kat + 1;

            if (kat == 1) {
                Avm.semaphorekat1yukari.release();
            } else if (kat == 2) {
                Avm.semaphorekat2yukari.release();
            } else if (kat == 3) {
                Avm.semaphorekat3yukari.release();
            } else if (kat == 4) {
                Avm.semaphorekat4yukari.release();
            }

            kat++;
        }

        if (kat == 5) {
            System.out.println("5.kata geldim");
            yön = "asagi";

            kat--;
        }

        if (yön == "asagi" && kat != 0) {

            if (kat == 1) {
                Avm.semaphorekat1asagi.acquire();
            } else if (kat == 2) {
                Avm.semaphorekat2asagi.acquire();
            } else if (kat == 3) {
                Avm.semaphorekat3asagi.acquire();
            } else if (kat == 4) {
                Avm.semaphorekat4asagi.acquire();
            }
            hedefkat = kat - 1;
            if (Avm.katlar.get(kat).Kuyruk.size() <= 0) {
                if (kat == 1) {
                    Avm.semaphorekat1asagi.release();
                } else if (kat == 2) {
                    Avm.semaphorekat2asagi.release();
                } else if (kat == 3) {
                    Avm.semaphorekat3asagi.release();
                } else if (kat == 4) {
                    Avm.semaphorekat4asagi.release();
                }

                kat--;
            } else {
                System.out.println("Aşağı iniyorum " + kat + ".katta Şu kadar kişi var:" + Avm.katlar.get(kat).kisisayisi);

                int asagieklenecekkisi;
                Avm.asansördenkisialmasemafor.acquire();
                while (kapasite < 10 && Avm.katlar.get(kat).Kuyruk.size() > 0) {

                    System.out.println("Cikmak icin bekleyenlerin sayisi:" + Avm.katlar.get(kat).Kuyruk.get(0).kisiler);
                    System.out.println("Burda kaldım");
                    if (kapasite + Avm.katlar.get(kat).Kuyruk.get(0).kisiler > 10) {
                        asagieklenecekkisi = 10 - kapasite;
                        icindekiler.add(new Binenler(asagieklenecekkisi, Avm.katlar.get(kat).Kuyruk.get(0).kat));
                        Avm.katlar.get(kat).Kuyruk.get(0).kisiler -= asagieklenecekkisi;
                        kapasite += asagieklenecekkisi;
                        //   Avm.katlar.get(kat).kisisayisi -= asagieklenecekkisi;
                    } else {
                        icindekiler.add(Avm.katlar.get(kat).Kuyruk.get(0));
                        kapasite += Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                        //   Avm.katlar.get(kat).kisisayisi -= Avm.katlar.get(kat).Kuyruk.get(0).kisiler;
                        Avm.katlar.get(kat).Kuyruk.remove(0);
                    }

                }
                Avm.asansördenkisialmasemafor.release();
                System.out.println("Asansörün Kapasitesi" + kapasite);
                if (kat == 1) {
                    Avm.semaphorekat1asagi.release();
                } else if (kat == 2) {
                    Avm.semaphorekat2asagi.release();
                } else if (kat == 3) {
                    Avm.semaphorekat3asagi.release();
                } else if (kat == 4) {
                    Avm.semaphorekat4asagi.release();
                }
                kat--;
            }

        }
        Avm.semaphoregenel.release();
    }

}
