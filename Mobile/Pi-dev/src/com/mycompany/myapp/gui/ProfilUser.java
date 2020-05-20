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
import com.mycompany.myapp.entities.FosUser;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.login;

import com.mycompany.myapp.services.ServiceComProd;
import com.mycompany.myapp.services.ServiceUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;


/**
 *
 * @author bhk
 */
public class ProfilUser extends Form{
     
 private Resources theme=UIManager.initFirstTheme("/theme_2");
          TextField tfrecherche= new TextField();
              ServiceUser fos= new ServiceUser();
            ArrayList<FosUser> lis = fos.profil();
           
            Container cCenter = new Container();
            
                     
    public ProfilUser(Form previous) throws ParseException {
        
        
      super(); 
        setTitle("Profil user ");
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
     
       for(FosUser f : lis) {
           cnt.addComponent(new AccrClientprofil(f));
           cnt.setScrollableY(true);
       }
       cnt.repaint();
    }
  
    }
 class AccrClientprofil extends Accordion {
     
      private Resources theme=UIManager.initFirstTheme("/theme_2");
 ServiceComProd serviceTask = new ServiceComProd();
        FosUser f;
        MultiButton mb;
         Container nom = new Container();
         Container prenom= new Container();
         Container mail = new Container();
         Container adresse= new Container();
         Container telephone= new Container();
         Container label = new Container();
          Container c2 = new Container();
           
              ServiceComProd s1= new ServiceComProd();
              
            
            //CommandeProduit m = new CommandeProduit();
       Container c3 = new Container();
        public AccrClientprofil(FosUser f) throws ParseException {
            super();
            this.f = f;
            add(
                this.c2 = createHeader(f)
            );
        }
        
        public FosUser getClient() {
            return this.f;
        } 
        
        private Container createHeader(FosUser f) {
           ServiceUser s2 = new ServiceUser();
           login l = new login();

l=s2.login(); //traja3l

       
            //IMG.setPreferredSize(new Dimension(700, 700));
             
 EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                
                //System.out.println(c.getNom_image());
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://localhost/dev/web/images/"+ f.getNom_image(),"http://localhost/dev/web/images/"+ f.getNom_image());
   
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       
                                       add(img);
            
               Label s = new Label("Username :     "+l.getUsername());
               
              Label k = new Label("Nom :      "+f.getNom());
                
                    Label kkk=new Label("Prenom :       "+f.getPrenom());
                   
                Label kkkk=new Label("Email :     "+f.getEmail());
                 Label kkkkk=new Label("Adresse:   4 rue bardo ");
                  Label kks=new Label("Numero telephone:   5593175");
                 
                s.getAllStyles().setFgColor(0xff7f00) ;
                
            //c2.add(IMG);
            label.add(s);
           nom.add(k);
          
            prenom.add(kkk);
            
            mail.add(kkkk);
            
            adresse.add(kkkkk);
            
            telephone.add(kks);
            
            c2.add(label);
           c2.add(nom);
             c2.add(prenom);
           c2.add(mail);
            c2.add(adresse);
            c2.add(telephone);
            
            //c2.add(tfrecherche);
          
           return  c2;
                                       //add(img);

   
                
           
          
          
          //return mbt;
            
            
        }
        
       
        }


  

          

      
    
        
                

        

        
        