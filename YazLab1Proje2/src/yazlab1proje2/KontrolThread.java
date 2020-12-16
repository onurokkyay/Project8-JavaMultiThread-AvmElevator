/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab1proje2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OnurOkyay
 */
public class KontrolThread extends Thread {
    int toplamkisisayisi;
    public static int asansorsayisi=1;
        @Override
    public void run() {
        
        while(true){
            try {
               Thread.sleep(100);
            } catch (InterruptedException ex) {
               Logger.getLogger(KontrolThread.class.getName()).log(Level.SEVERE, null, ex);
          }
            try {
                Avm.semaphoregenel.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(KontrolThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            toplamkisisayisi=Avm.katlar.get(0).kisisayisi;
            for(int i=0;i<Avm.katlar.get(1).Kuyruk.size();i++){
                toplamkisisayisi+=Avm.katlar.get(1).Kuyruk.get(i).kisiler;
            }
             for(int i=0;i<Avm.katlar.get(2).Kuyruk.size();i++){
                 
                toplamkisisayisi+=Avm.katlar.get(2).Kuyruk.get(i).kisiler;
            }
              for(int i=0;i<Avm.katlar.get(3).Kuyruk.size();i++){
                toplamkisisayisi+=Avm.katlar.get(3).Kuyruk.get(i).kisiler;
            }
               for(int i=0;i<Avm.katlar.get(4).Kuyruk.size();i++){
                toplamkisisayisi+=Avm.katlar.get(4).Kuyruk.get(i).kisiler;
            }
                 Avm.semaphoregenel.release();
                
                
         //   System.out.println("Toplam kişi sayisi:"+toplamkisisayisi);
         
           try{
                
//               if(toplamkisisayisi>=20){
//                   Avm.asansörthread2.mod=true;
//                 
//               }
//               else {
//                    Avm.asansörthread2.mod=false;
//               }
//               if(toplamkisisayisi>=40){
//                   Avm.asansörthread3.mod=true;
//                  
//                   
//               }
//                else {
//                    Avm.asansörthread3.mod=false;
//               }
//               if(toplamkisisayisi>=80){
//                   Avm.asansörthread4.mod=true;
//                  
//               }
//                else {
//                    Avm.asansörthread4.mod=false;
//               }
//               if(toplamkisisayisi>=160){
//                   Avm.asansörthread5.mod=true;
//                   
//               }
//                else {
//                   
//                    Avm.asansörthread5.mod=false;
//               }
//                Thread.sleep(0);
            if(toplamkisisayisi>=20 &&  Avm.asansörthread2.mod==false){
                Avm.asansörthread2.mod=true;
                Thread.sleep(500);
            }
            if(toplamkisisayisi>=20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==false){
                Avm.asansörthread3.mod=true;
                 Thread.sleep(500);
            }
             if(toplamkisisayisi>=20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==true && Avm.asansörthread4.mod==false){
                Avm.asansörthread4.mod=true;
                 Thread.sleep(500);
            }
              if(toplamkisisayisi>=20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==true && Avm.asansörthread4.mod==true && Avm.asansörthread5.mod==false){
                Avm.asansörthread5.mod=true;
                 Thread.sleep(500);
            }
              
              if(toplamkisisayisi<20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==true && Avm.asansörthread4.mod==true && Avm.asansörthread5.mod==true){
                Avm.asansörthread5.mod=false;
                 Thread.sleep(500);
            }
               if(toplamkisisayisi<20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==true && Avm.asansörthread4.mod==true && Avm.asansörthread5.mod==false){
                Avm.asansörthread4.mod=false;
                 Thread.sleep(500);
            }
                if(toplamkisisayisi<20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==true && Avm.asansörthread4.mod==false && Avm.asansörthread5.mod==false){
                Avm.asansörthread3.mod=false;
                 Thread.sleep(500);
            }
                if(toplamkisisayisi<20 &&  Avm.asansörthread2.mod==true && Avm.asansörthread3.mod==false && Avm.asansörthread4.mod==false && Avm.asansörthread5.mod==false){
                Avm.asansörthread2.mod=false;
                 Thread.sleep(500);
            }          
       
      }
      catch(InterruptedException ex){
          System.err.println(ex);
          
      }  
      
        }
     
        
    }
}
