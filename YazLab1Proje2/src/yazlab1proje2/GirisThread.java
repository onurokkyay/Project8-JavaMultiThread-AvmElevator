/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab1proje2;

import java.util.Random;


public class GirisThread extends Thread {
Random r=new Random();
int kackisi;
int hedefkat;
int yenigelenler;
    @Override
    public void run() {
      int rastgelekat;
        while(true){
           try{
               yenigelenler=r.nextInt(10)+1;
               Avm.katlar.get(0).kisisayisi+=yenigelenler;//random sınıfı
               System.out.println("0.katta şu  kadar kişi var:"+Avm.katlar.get(0).kisisayisi);
               rastgelekat=r.nextInt(4)+1; 
               Avm.katlar.get(0).Kuyruk.add(new Binenler(yenigelenler,rastgelekat));
               
               for(int i=0;i<Avm.katlar.get(0).Kuyruk.size();i++){
                    System.out.println("["+Avm.katlar.get(0).Kuyruk.get(i).kisiler+"] ["+Avm.katlar.get(0).Kuyruk.get(i).kat+"]");
               }
              
               
                 Thread.sleep(500);
//                 System.out.println("500 bekledi");   
                
                 
          
      }
      catch(InterruptedException ex){
          System.err.println(ex);
          
      }  
        }
     
        
    }
    
    
    
}
