/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab1proje2;

import java.util.Random;

/**
 *
 * @author OnurOkyay
 */
public class CikisThread extends Thread{
        @Override
    public void run() {
        int rastgelekisisayisi;
        int rastgelekat;
        int cikissayisi;
        Random r=new Random();
        while(true){
           try{
          Thread.sleep(1000);
         cikissayisi= r.nextInt(5)+1;
         if(cikissayisi>Avm.katlar.get(0).cikiskisisayisi){
             cikissayisi=Avm.katlar.get(0).cikiskisisayisi;
         }
          Avm.semaphoregenel.acquire();
          Avm.katlar.get(0).cikiskisisayisi-=cikissayisi;
          rastgelekat=r.nextInt(4)+1;
          rastgelekisisayisi=r.nextInt(5)+1;
      
          if(Avm.katlar.get(rastgelekat).kisisayisi>0){
              
              Avm.asansördenkisialmasemafor.acquire();
              
          if(Avm.katlar.get(rastgelekat).kisisayisi<=rastgelekisisayisi){
              rastgelekisisayisi=Avm.katlar.get(rastgelekat).kisisayisi;
          }
          
          if(rastgelekisisayisi >0){
              
               Avm.katlar.get(rastgelekat).Kuyruk.add(new Binenler(rastgelekisisayisi,0));
               Avm.katlar.get(rastgelekat).kisisayisi-=rastgelekisisayisi;
          }    
          Avm.asansördenkisialmasemafor.release();
          }
           Avm.semaphoregenel.release();
         
          
      }
      catch(InterruptedException ex){
          System.err.println(ex);
          
      }  
        }
     
        
    }
}
