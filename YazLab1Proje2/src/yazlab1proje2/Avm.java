/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab1proje2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author OnurOkyay
 */
public class Avm {

    public static int ToplamMüsteri = 0;
    public static Semaphore semaphorekat0 = new Semaphore(1);
    public static Semaphore semaphorekat1yukari = new Semaphore(1);
    public static Semaphore semaphorekat1asagi = new Semaphore(1);
     public static Semaphore semaphorekat2yukari = new Semaphore(1);
    public static Semaphore semaphorekat2asagi = new Semaphore(1);
     public static Semaphore semaphorekat3yukari = new Semaphore(1);
    public static Semaphore semaphorekat3asagi = new Semaphore(1);
     public static Semaphore semaphorekat4yukari = new Semaphore(1);
    public static Semaphore semaphorekat4asagi = new Semaphore(1);
     public static Semaphore semaphoregenel = new Semaphore(1);
     public static Semaphore asansördenkisialmasemafor= new Semaphore(1);
    public static GirisThread giristhread = new GirisThread();
    public static CikisThread cikisthread = new CikisThread();
    public static KontrolThread kontrolthread = new KontrolThread();
    public static AsansörThread asansörthread1 = new AsansörThread("Asansör1");
    public static AsansörThread asansörthread2 = new AsansörThread("Asansör2");
    public static AsansörThread asansörthread3 = new AsansörThread("Asansör3");
    public static AsansörThread asansörthread4 = new AsansörThread("Asansör4");
    public static AsansörThread asansörthread5 = new AsansörThread("Asansör5");
    public static ArrayList<Kat> katlar=new ArrayList<Kat>();
     public static ArrayList<AsansörThread> asansörler=new ArrayList<AsansörThread>();
    public static Arayüz arayüz=new Arayüz();
    
          
    public static void main(String[] args) throws InterruptedException {
          
            katlar.add(new Kat());
            katlar.add(new Kat());
            katlar.add(new Kat());
            katlar.add(new Kat());
            katlar.add(new Kat());
            asansörler.add(asansörthread1);
            asansörler.add(asansörthread2);
            asansörler.add(asansörthread3);
           asansörler.add(asansörthread4);
            asansörler.add(asansörthread5);
            
            asansörthread1.mod=true;
            GirisThread giris=new GirisThread();
            giris.start();
            CikisThread cikis=new CikisThread();
            cikis.start();
            KontrolThread kontrol=new KontrolThread();
            kontrol.start();
            
           asansörthread1.start();
         
            asansörthread2.start();
        
            asansörthread3.start();
       
            asansörthread4.start();
           
            asansörthread5.start();
         arayüz.setVisible(true);
       
            while(true){
             Thread.sleep(100);
             ArayüzDoldur();       
        
    }       
       
    }

    public static void ArayüzDoldur() throws InterruptedException{
        //Arayüz doldururken listelere ulaşmaya çalıştığı için hata veriyor.Mecburen semaphore koyuyoruz
        semaphoregenel.acquire();
        int icindekikisisayisi = 0;
        int icindekikisisayisi1=0;
        int icindekikisisayisi2=0;
        int icindekikisisayisi3=0;
        int icindekikisisayisi4=0;
        int kuyruktakikisisayisi1=0;
        int kuyruktakikisisayisi2=0;
        int kuyruktakikisisayisi3=0;
        int kuyruktakikisisayisi4=0;
        int toplamkisisayisi1=0;
        int toplamkisisayisi2=0;
        int toplamkisisayisi3=0;
        int toplamkisisayisi4=0;
        String icindekilerstring = "";
        String icindekilerstringgiris= "";
        String icindekilerstringgiris1= "";
        String icindekilerstringgiris2= "";
        String icindekilerstringgiris3= "";
         String icindekilerstringgiris4= "";
          arayüz.AktifMi.setText(Boolean.toString(asansörthread1.mod));
          arayüz.ModDurumu.setText(Boolean.toString(asansörthread1.mod));
          arayüz.HangiKat.setText(Integer.toString(asansörthread1.kat));
          arayüz.HedefKat.setText(Integer.toString(asansörthread1.hedefkat));
          arayüz.Yön.setText(asansörthread1.yön);
          arayüz.Kapasite.setText("10");
        
          for(int i=0;i<asansörthread1.icindekiler.size();i++){
              icindekikisisayisi+=asansörthread1.icindekiler.get(i).kisiler;
              icindekilerstring+=" ["+asansörthread1.icindekiler.get(i).kisiler+","+asansörthread1.icindekiler.get(i).kat+"] ";
          }
          
          arayüz.BinenKisiSayisi.setText(Integer.toString(icindekikisisayisi));
          arayüz.icindekiler.setText(icindekilerstring);
          
          if(asansörthread2.mod){
            icindekikisisayisi = 0;
         icindekilerstring = "";
          arayüz.AktifMi1.setText(Boolean.toString(asansörthread2.mod));
          arayüz.ModDurumu1.setText(Boolean.toString(asansörthread2.mod));
          arayüz.HangiKat1.setText(Integer.toString(asansörthread2.kat));
          arayüz.HedefKat1.setText(Integer.toString(asansörthread2.hedefkat));
          arayüz.Yön1.setText(asansörthread2.yön);
          arayüz.Kapasite1.setText("10");
          for(int i=0;i<asansörthread2.icindekiler.size();i++){
              icindekikisisayisi+=asansörthread2.icindekiler.get(i).kisiler;
              icindekilerstring+=" ["+asansörthread2.icindekiler.get(i).kisiler+","+asansörthread2.icindekiler.get(i).kat+"] ";
          }
          arayüz.BinenKisiSayisi1.setText(Integer.toString(icindekikisisayisi));
          arayüz.icindekiler1.setText(icindekilerstring);  
          }
          else {
          arayüz.AktifMi1.setText(Boolean.toString(asansörthread2.mod));
          arayüz.ModDurumu1.setText(Boolean.toString(asansörthread2.mod));
          }
          
           if(asansörthread3.mod){
            icindekikisisayisi = 0;
         icindekilerstring = "";
          arayüz.AktifMi2.setText(Boolean.toString(asansörthread3.mod));
          arayüz.ModDurumu2.setText(Boolean.toString(asansörthread3.mod));
          arayüz.HangiKat2.setText(Integer.toString(asansörthread3.kat));
          arayüz.HedefKat2.setText(Integer.toString(asansörthread3.hedefkat));
          arayüz.Yön2.setText(asansörthread3.yön);
          arayüz.Kapasite2.setText("10");
          for(int i=0;i<asansörthread3.icindekiler.size();i++){
              icindekikisisayisi+=asansörthread3.icindekiler.get(i).kisiler;
              icindekilerstring+=" ["+asansörthread3.icindekiler.get(i).kisiler+","+asansörthread3.icindekiler.get(i).kat+"] ";
          }
          arayüz.BinenKisiSayisi2.setText(Integer.toString(icindekikisisayisi));
          arayüz.icindekiler2.setText(icindekilerstring);  
          }
          else {
          arayüz.AktifMi2.setText(Boolean.toString(asansörthread3.mod));
          arayüz.ModDurumu2.setText(Boolean.toString(asansörthread3.mod));
          }
            
         if(asansörthread4.mod){
            icindekikisisayisi = 0;
         icindekilerstring = "";
          arayüz.AktifMi3.setText(Boolean.toString(asansörthread4.mod));
          arayüz.ModDurumu3.setText(Boolean.toString(asansörthread4.mod));
          arayüz.HangiKat3.setText(Integer.toString(asansörthread4.kat));
          arayüz.HedefKat3.setText(Integer.toString(asansörthread4.hedefkat));
          arayüz.Yön3.setText(asansörthread4.yön);
          arayüz.Kapasite3.setText("10");
          for(int i=0;i<asansörthread4.icindekiler.size();i++){
              icindekikisisayisi+=asansörthread4.icindekiler.get(i).kisiler;
              icindekilerstring+=" ["+asansörthread4.icindekiler.get(i).kisiler+","+asansörthread4.icindekiler.get(i).kat+"] ";
          }
          arayüz.BinenKisiSayisi3.setText(Integer.toString(icindekikisisayisi));
          arayüz.icindekiler3.setText(icindekilerstring);  
          }
          else {
          arayüz.AktifMi3.setText(Boolean.toString(asansörthread4.mod));
          arayüz.ModDurumu3.setText(Boolean.toString(asansörthread4.mod));
          }
         
           if(asansörthread5.mod){
            icindekikisisayisi = 0;
         icindekilerstring = "";
          arayüz.AktifMi4.setText(Boolean.toString(asansörthread5.mod));
          arayüz.ModDurumu4.setText(Boolean.toString(asansörthread5.mod));
          arayüz.HangiKat4.setText(Integer.toString(asansörthread5.kat));
          arayüz.HedefKat4.setText(Integer.toString(asansörthread5.hedefkat));
          arayüz.Yön4.setText(asansörthread5.yön);
          arayüz.Kapasite4.setText("10");
          for(int i=0;i<asansörthread5.icindekiler.size();i++){
              icindekikisisayisi+=asansörthread5.icindekiler.get(i).kisiler;
              icindekilerstring+=" ["+asansörthread5.icindekiler.get(i).kisiler+","+asansörthread5.icindekiler.get(i).kat+"] ";
          }
          arayüz.BinenKisiSayisi4.setText(Integer.toString(icindekikisisayisi));
          arayüz.icindekiler4.setText(icindekilerstring);  
          }
          else {
          arayüz.AktifMi4.setText(Boolean.toString(asansörthread5.mod));
          arayüz.ModDurumu4.setText(Boolean.toString(asansörthread5.mod));
          }
          arayüz.Cikis.setText(Integer.toString(katlar.get(0).cikiskisisayisi));
          arayüz.KuyrukKisiSayisi.setText(Integer.toString(katlar.get(0).kisisayisi)); //0.kata gelenlerin sayısı
          
          for(int i=0;i<Avm.katlar.get(0).Kuyruk.size();i++){
      
              icindekilerstringgiris+=" ["+Avm.katlar.get(0).Kuyruk.get(i).kisiler+","+Avm.katlar.get(0).Kuyruk.get(i).kat+"] ";
              
          }
               arayüz.girisicindekiler.setText(icindekilerstringgiris);
          if(Avm.katlar.get(1).Kuyruk.size()>0){
               for(int i=0;i<Avm.katlar.get(1).Kuyruk.size();i++){
       kuyruktakikisisayisi1+=Avm.katlar.get(1).Kuyruk.get(i).kisiler;
              icindekilerstringgiris1+=" ["+Avm.katlar.get(1).Kuyruk.get(i).kisiler+","+Avm.katlar.get(1).Kuyruk.get(i).kat+"] ";
          }
               arayüz.girisicindekiler1.setText(icindekilerstringgiris1);
         }
          else {
                       arayüz.girisicindekiler1.setText("");
                  }
              
                if(Avm.katlar.get(2).Kuyruk.size()>0){
               for(int i=0;i<Avm.katlar.get(2).Kuyruk.size();i++){
                    kuyruktakikisisayisi2+=Avm.katlar.get(2).Kuyruk.get(i).kisiler;
              icindekilerstringgiris2+=" ["+Avm.katlar.get(2).Kuyruk.get(i).kisiler+","+Avm.katlar.get(2).Kuyruk.get(i).kat+"] ";
          }
               arayüz.girisicindekiler2.setText(icindekilerstringgiris2);
                }
                else {
                       arayüz.girisicindekiler2.setText("");
                  }
                 if(Avm.katlar.get(3).Kuyruk.size()>0){
               for(int i=0;i<Avm.katlar.get(3).Kuyruk.size();i++){
                    kuyruktakikisisayisi3+=Avm.katlar.get(3).Kuyruk.get(i).kisiler;
              icindekilerstringgiris3+=" ["+Avm.katlar.get(3).Kuyruk.get(i).kisiler+","+Avm.katlar.get(3).Kuyruk.get(i).kat+"] ";
          }
               arayüz.girisicindekiler3.setText(icindekilerstringgiris3);
                 }
                 else {
                       arayüz.girisicindekiler3.setText("");
                  }
                  if(Avm.katlar.get(4).Kuyruk.size()>0){
               for(int i=0;i<Avm.katlar.get(4).Kuyruk.size();i++){
               kuyruktakikisisayisi4+=Avm.katlar.get(4).Kuyruk.get(i).kisiler;
              icindekilerstringgiris4+=" ["+Avm.katlar.get(4).Kuyruk.get(i).kisiler+","+Avm.katlar.get(4).Kuyruk.get(i).kat+"] ";
          }
               
               arayüz.girisicindekiler4.setText(icindekilerstringgiris4);
                  }
                  else {
                       arayüz.girisicindekiler4.setText("");
                  }
                          toplamkisisayisi1=katlar.get(1).kisisayisi+kuyruktakikisisayisi1;
                          toplamkisisayisi2=katlar.get(2).kisisayisi+kuyruktakikisisayisi2;
                          toplamkisisayisi3=katlar.get(3).kisisayisi+kuyruktakikisisayisi3;
                          toplamkisisayisi4=katlar.get(4).kisisayisi+kuyruktakikisisayisi4;
                        
           arayüz.ToplamKisiSayisi.setText(Integer.toString(toplamkisisayisi1));
            arayüz.ToplamKisiSayisi1.setText(Integer.toString(toplamkisisayisi2));
             arayüz.ToplamKisiSayisi2.setText(Integer.toString(toplamkisisayisi3));
              arayüz.ToplamKisiSayisi3.setText(Integer.toString(toplamkisisayisi4));

             for(int i=0;i<Avm.katlar.get(1).Kuyruk.size();i++){
              icindekikisisayisi1+=Avm.katlar.get(1).Kuyruk.get(i).kisiler;
             
          }   
              
              
              arayüz.KuyrukKisiSayisi1.setText(Integer.toString(icindekikisisayisi1));
              
               for(int i=0;i<Avm.katlar.get(2).Kuyruk.size();i++){
              icindekikisisayisi2+=Avm.katlar.get(2).Kuyruk.get(i).kisiler;
             
          }   
              
              
              arayüz.KuyrukKisiSayisi2.setText(Integer.toString(icindekikisisayisi2));
              
               for(int i=0;i<Avm.katlar.get(3).Kuyruk.size();i++){
              icindekikisisayisi3+=Avm.katlar.get(3).Kuyruk.get(i).kisiler;
             
          }   
              
              
              arayüz.KuyrukKisiSayisi3.setText(Integer.toString(icindekikisisayisi3));
              
               for(int i=0;i<Avm.katlar.get(4).Kuyruk.size();i++){
              icindekikisisayisi4+=Avm.katlar.get(4).Kuyruk.get(i).kisiler;
             
          }   
              
              
              arayüz.KuyrukKisiSayisi4.setText(Integer.toString(icindekikisisayisi4));
              semaphoregenel.release();
          }
          
       
}
