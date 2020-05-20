/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CategorieProduit;
import com.mycompany.myapp.entities.CommandeProduit;
import com.mycompany.myapp.entities.Produit;

import com.mycompany.myapp.services.ServiceComProd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;


/**
 *
 * @author bhk
 */
public class AffichageProduit extends Form{
     
 private Resources theme=UIManager.initFirstTheme("/theme_2");
          TextField tfrecherche= new TextField();
              ServiceComProd serviceTask = new ServiceComProd();
            ArrayList<Produit> lis = serviceTask.consulterproduit(); //liste des Produits
           
            Container cCenter = new Container();
            
                     
    public AffichageProduit(Form previous) throws ParseException {
        
        
      super(); 
        setTitle("Produits shop ");
       //add(tfrecherche);
       //cCenter.add(tfrecherche);
     
        Toolbar toolBar = getToolbar();
        toolBar.addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
                   

            
          try {
              populateScreen(cCenter);
               
              cCenter.setScrollableY(true);
                 
              cCenter.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
            cCenter.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
          cCenter.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
          } catch (ParseException ex) {
              
          }
            
        });
        
        
         toolBar.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f,( ActionEvent e) -> {
                   

            
          new Menu();
            
        });
        
        /////////////////recherche///////////////
        
        
         
       
         //toolBar.addCommandToLeftBar("",theme.getImage("back-png-3.png"), e -> {});
      
        
        
        /*/
        toolBar.addMaterialCommandToLeftBar(
                   "", theme.getImage("toolbar-profile-pic.png"), e -> {
          try {
              populateScreen(cCenter);
          } catch (ParseException ex) {
             
          }
        });
            /*/    
        Button btnClose = new Button("Liste des Produits");
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    Display.getInstance().exitApplication();
                    
        });
  
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, cCenter);
      
        
      
        populateScreen(cCenter);
        
  

    }
  private void populateScreen(Container cnt) throws ParseException {
       cnt.removeAll();
       cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
      cnt.setScrollableY(true);
     
       for(Produit c : lis) {
           cnt.addComponent(new AccrClient(c));
           cnt.setScrollableY(true);
       }
       cnt.repaint();
    }
  
    }
 class AccrClient extends Accordion {
     
      private Resources theme=UIManager.initFirstTheme("/theme_2");
 ServiceComProd serviceTask = new ServiceComProd();
        Produit c;
        MultiButton mb;
         Container IMG = new Container();
          Container c2 = new Container();
            TextField recherche;
            TextField tfquantitecmd;
              ServiceComProd s1= new ServiceComProd();
              CategorieProduit cp;
            Produit p = new Produit();
            //CommandeProduit m = new CommandeProduit();
       Container c3 = new Container();
        public AccrClient(Produit c) throws ParseException {
            super();
            this.c = c;
            addContent(
                this.c2 = createHeader(c), createDetail(c)
            );
        }
        
        public Produit getClient() {
            return this.c;
        } 
        
        private Container createHeader(Produit c) {
       
            IMG.setPreferredSize(new Dimension(300, 300));
             
 EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                
                //System.out.println(c.getNom_image());
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://localhost/dev/web/images/"+ c.getNom_image(),"http://localhost/dev/web/images/"+ c.getNom_image());
   
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       IMG.add(img);
                                       IMG.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            img.getParent().revalidate();
               Label s = new Label("Nom Prod :"+c.getNom());
               
                s.getAllStyles().setFgColor(0xff7f00) ;
                
            c2.add(IMG);
            c2.add(s);
            //c2.add(tfrecherche);
          
           return  c2;
                                       //add(img);

   
                
           
          
          
          //return mbt;
            
            
        }
        
        private Container createDetail(Produit c) throws ParseException {
         Image icon = theme.getImage("back-png-3.png"); 
    //cp=s1.getcategorie(p.getCategorie());   
                     
                      
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
               
                       new Label("Categorie:        "),
                       new Label("" +c.getCategorie()),
                        new Label("Description:            "),
                        new Label("" + c.getDescription()),
                          new Label("Prix du produit                           "),
                        new Label(""+c.getPrix()),
                        
                      new Label("Quantite stock                            "),
                        new Label(""+c.getQuantite()),
                        new Label("Quantite commandée                                  "),
                           tfquantitecmd = new TextField("")
                        
                    
                        
                )            
            );
                 //TextField tfrecherche = new TextField();
            Container cButtons = new Container(new GridLayout(1,3));
            Button commander = new Button("commander");
               
            
                    
              cButtons.addComponent(commander);
           
            
         
            //addComponent(tfrecherche);
         //cDetail.addComponent(tfrecherche);
              //commander.setIcon(icon);
             
       
             commander.addActionListener(new ActionListener() {
                 
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               
                    ConnectionRequest con = new ConnectionRequest();
                   
                    con.setUrl("http://localhost/dev/web/app_dev.php/rh/ajoutercmd?"
                            + "id="+c.getId()
                            + "&quantite="+Integer.parseInt(tfquantitecmd.getText())
                       );
                   //serviceTask.mail();
                System.out.println(con.getUrl());
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                               System.out.println("votre commande est ajoutée.");
                           //serviceTask.mail();


        
       
                           ToastBar.showMessage("commande ajoutée",FontImage.MATERIAL_DONE);
                           serviceTask.mail(c.getId());
                        }
                    });
                   
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                        }
     
           
        });
       
           
           
           ///////////////////////////
            
           
            cDetail.addComponent(BorderLayout.SOUTH, cButtons);
           
            return cDetail;
        }


  

        
        public boolean isInteger(String s) {
            try { 
                Integer.parseInt(s); 
            } catch(NumberFormatException | NullPointerException e) { 
                return false; 
            }
            // only got here if we didn't return false
            return true;
        }        

      
 }   
        
                

        

        
        