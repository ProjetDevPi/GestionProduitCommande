/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.CategorieProduit;
import com.mycompany.myapp.entities.FosUser;

import com.mycompany.myapp.entities.Produit;

import com.mycompany.myapp.entities.CommandeProduit;

import com.mycompany.myapp.entities.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceComProd {

  
    ServiceUser s1 = new ServiceUser();
    public ArrayList<CommandeProduit> commandeproduit;
    public ArrayList<CategorieProduit> categorie;
     public ArrayList<Produit> produit;
    public static ServiceComProd instance=null;

    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceComProd() {
         req = new ConnectionRequest();
    }

    public static ServiceComProd getInstance() {
        if (instance == null) {
            instance = new ServiceComProd();
        }
        return instance;
    }



  
          ///////////////////////////Afichage Produit///////////////
          
           public ArrayList<Produit> parseProduit(String jsonText){
        try {
            produit=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> listprod = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : listprod){
                Produit p = new Produit();
               
                  //-----------------------------------CATEGORY----------------------------------------------------
                Map<String, Object> listRecupCategory = null;

              CategorieProduit c = new CategorieProduit();
                if (obj.get("categorie") != null) {

                    listRecupCategory = (Map<String, Object>) obj.get("categorie");

                    p.setCategorie((int) Float.parseFloat(listRecupCategory.get("id").toString()));
                   
                }
               // c=(CategorieProduit)obj.get("categorie");
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(obj.get("categorie").toString()+"ggggggggggg");
                //float categorie =Float.parseFloat(obj.get("categorie").toString());
                
                p.setPrix(Float.parseFloat(obj.get("prix").toString()));
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                p.setId((int)id);
                //p.setCategorie((int)c.getId());
                p.setNom(obj.get("nom").toString());
                
                p.setDescription(obj.get("description").toString());
                p.setNom_image(obj.get("nomImage").toString());
                p.setQuantite((int)quantite);
               
               
                produit.add(p);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        //System.out.println(produit+"listeeeeeeeeee");
        return produit;
    }
           
             public ArrayList<Produit> consulterproduit(){
        String url = "http://localhost/dev/web/app_dev.php/rh/afficherproduit";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }
                 
   ////////////////////////////affichagecommande////////////////
             public ArrayList<CommandeProduit> parseCommande(String jsonText){
        try {
            commandeproduit=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
               
            for(Map<String,Object> obj : list){
                CommandeProduit p = new CommandeProduit();
               
                  //-----------------------------------CATEGORY----------------------------------------------------
                Map<String, Object> listRecupprod = null;

              Produit c = new Produit();
                if (obj.get("produit") != null) {

                    listRecupprod = (Map<String, Object>) obj.get("produit");

                    p.setProduit((int) Float.parseFloat(listRecupprod.get("id").toString()));
                   
                   
                }
             
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                float quantite= Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int)quantite);
                p.setPrixtotal(Float.parseFloat(obj.get("prixtotal").toString()));
                p.setEtat(obj.get("etat").toString());
                p.setPay(obj.get("pay").toString());


                commandeproduit.add(p);
                

                  
            
            }
            
        } catch (IOException ex) {
            
        }
        return commandeproduit;
    }
             
       public ArrayList<CommandeProduit> consultercommande(){ //selon id userrr connecté
              login l =new login();
              l=s1.login();
             
        String url = "http://localhost/dev/web/app_dev.php/rh/affichercmd/"+l.getId_user();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                commandeproduit = parseCommande(new String(req.getResponseData()));
      
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandeproduit;
    }     
       //////////////supprimer commande///////
       public void supprimerproduit(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/dev/web/app_dev.php/rh/supprimercmd/" + id;
        //System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone

    }
       
       
       
       ///////////////////////recherche produit////////
       
      
       
       
       
       
       
       
       
       
       
       
       
       
       
       
      
        /////////////////////////////////////////fonction retourne le produit//////////
        
         public Produit getproduit(int id){
        String url = "http://localhost/dev/web/app_dev.php/rh/find/"+id;
        req.setUrl(url);
        req.setPost(false);
             //System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit.get(0);
    }
         
         
      /*/
         public CategorieProduit getcategorie(int id){
        String url = "http://localhost/ProjetPi/web/app_dev.php/findcat/"+id;
        req.setUrl(url);
        req.setPost(false);
             System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie.get(0);
    }
         
        public ArrayList<CategorieProduit> parseCategorie(String jsonText){
        try {
            categorie=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
               
            for(Map<String,Object> obj : list){
                CategorieProduit p = new CategorieProduit();
               
                  //-----------------------------------CATEGORY----------------------------------------------------
              
                   
                
             
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
              
              
              
                p.setNom(obj.get("nom").toString());
                p.setDescription(obj.get("description").toString());


                categorie.add(p);
                System.out.println(categorie);

                  
            
            }
            
        } catch (IOException ex) {
            
        }
        return categorie;
       
    } 
/*/
         
         
         /////////mail//////////
         
         public void mail(int id){
             //CommandeProduit cp = new CommandeProduit();
               
            ConnectionRequest con = new ConnectionRequest();
                   
                    con.setUrl("http://localhost/dev/web/app_dev.php/rh/mailmob/"+id
                     
                            );


                 System.out.println(con.getUrl());

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                         
                 
                    
                          
        


                        }
          
}
