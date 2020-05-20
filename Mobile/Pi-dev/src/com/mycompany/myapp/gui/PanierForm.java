/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CommandeProduit;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceComProd;
import com.mycompany.myapp.services.ServiceUser;


import java.util.ArrayList;
import java.util.HashSet;




/**
 *
 * @author ASUS
 */
public class PanierForm extends Form{
    
    Form current;
   double totale=0;
   int i=0;
     ServiceComProd serviceTask = new ServiceComProd();
            ArrayList<CommandeProduit> lis = serviceTask.consultercommande(); //liste des Produits
            
            Container cCenter = new Container();
             
                     
    public PanierForm(Form previous) throws ParseException {
        
        
      super(); 
        setTitle("Les achats  ");
       
      
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
        for(CommandeProduit c : lis) {
           
           totale+=c.getPrixtotal();
           System.out.println(totale);
           i++;
           
       }
           String message = "totale est " +totale+" nb produit "+i;   
        Button btnClose = new Button(message);
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
         serviceTask.consultercommande();
                    
        });

        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, cCenter);
      
        
        addComponent(BorderLayout.SOUTH, btnClose);
       
        populateScreen(cCenter);
        

         
       

    }
  private void populateScreen(Container cnt) throws ParseException {
       cnt.removeAll();
       cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
      cnt.setScrollableY(true);
      //System.out.println(lis);
       for(CommandeProduit c : lis) {
           cnt.addComponent(new AccrClient1(c));
           cnt.setScrollableY(true);
           //System.out.println(c.getPrixtotal());
         
           
       }
       cnt.repaint();
    }
  
    }
 class AccrClient1 extends Accordion {
    
       Form current;
      ServiceComProd serviceTask = new ServiceComProd();
      private Resources theme=UIManager.initFirstTheme("/theme_2");

        CommandeProduit cp;
    
            ArrayList<CommandeProduit> lis = serviceTask.consultercommande(); //liste des Produits
     TextField tfnvquantite = new TextField("");
    
        Container cCenter = new Container();
        MultiButton mb;
         Container IMG = new Container();
          Container c2 = new Container();
          int test=0;
          
          
           double totale=0;
   int i=0;
            TextField tfquantitecmd;
        Produit p = new Produit();
       Container c3 = new Container();
       ServiceComProd s1= new ServiceComProd();
        public AccrClient1(CommandeProduit cp) throws ParseException {
            
            super();
            this.cp = cp;
            addContent(
                this.c2 = createHeader(cp), createDetail(cp)
            );
            for(CommandeProduit c : lis) {
           
           totale+=c.getPrixtotal();
           System.out.println(totale);
           i++;
           
       }
        }
        
        
        ///////////  
        public CommandeProduit getClient() {
            return this.cp;
        } 
        
        private Container createHeader(CommandeProduit cp) {
             p=s1.getproduit(cp.getProduit());   
             
    ServiceUser s2 = new ServiceUser();
           login l = new login();
ServiceComProd s1= new ServiceComProd();
l=s2.login(); //traja3l

       

  IMG.setPreferredSize(new Dimension(300, 300));
             
 EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                
                //System.out.println(c.getNom_image());
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://localhost/dev/web/images/"+ p.getNom_image(),"http://localhost/dev/web/images/"+ p.getNom_image());
   
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       IMG.add(img);
                                       IMG.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            img.getParent().revalidate();
               
               
              
        
            //c2.add(tfrecherche);
          
          
                                      










           
           Label s = new Label("Commande de  :"+l.getUsername());
                s.getAllStyles().setFgColor(0xf20d0d) ;
             

           
           c2.add(IMG);
          c2.add(s);
        
            
           return  c2;
          //return mbt;
            
            
        }
        
        private Container createDetail(CommandeProduit cp) throws ParseException {
          Image supp = theme.getImage("supprimer2.png"); 
          Image pay = theme.getImage("pay.png"); 
          Image print= theme.getImage("pdff.png"); 
           Image mod= theme.getImage("modif.png"); 
        
                p=s1.getproduit(cp.getProduit());   
                
               
                      
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
               
                        new Label("Produit:        "),
                       new Label("" +p.getNom()),
                       
                        new Label("Quantite commandée:        "),
                        new Label("" + cp.getQuantite()),
                          new Label("Prix Total                              "),
                        new Label(""+cp.getPrixtotal()),
                        
                      new Label("Etat du commande                            "),
                        new Label(""+cp.getEtat()),
                        new Label("Payement ?                                  "),
                        new Label(""+cp.getPay()),
                         new Label("Nouvelle quantitée "),
                        tfnvquantite
                         
                        
            )
                    
                        
                
            );
               
            Container cButtons = new Container(new GridLayout(1,3));
            Button supprimer = new Button("Annuler");
            supprimer.setIcon(supp);
             
            Button edit=new Button("Modifier");
            
            Button payer=new Button("Payer");
            
            payer.setIcon(pay);
            Button pdf=new Button("pdf");
            
            pdf.setIcon(print);
            
            edit.setIcon(mod);
            
            
          cButtons.addComponent(supprimer);
          cButtons.addComponent(edit);
          cButtons.addComponent(payer);
          cButtons.addComponent(pdf);
       
            supprimer.addActionListener(e -> {
                    serviceTask.supprimerproduit(cp.getId());
                    removeAll();
                  
                   
                     Dialog.show("Success","Commande Annulée",new Command("OK"));
                       
              try {
                  new PanierForm(current).show();
                  //serviceTask.consultercommande();
              } catch (ParseException ex) {
                 
              }
                });
  
       
           
           edit.addActionListener(new ActionListener() {
                 
            @Override
            public void actionPerformed(ActionEvent evt) {


            
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/dev/web/app_dev.php/rh/modifiercmd?"
                            +"id="+cp.getId()
                            +"&quantite="+tfnvquantite.getText()
                        
                    );
                    //System.out.println(con.getUrl());
                     
             con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            
                        
                        ToastBar.showMessage("Commande modifiée avec succées.",FontImage.MATERIAL_DONE);
                            try {
                                new PanierForm(current).show();
                                
                                
                                
                                //serviceTask.consultercommande();
                            } catch (ParseException ex) {
                                
                            }
                         

                        
                        }
                    });
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    
               }

            
                });
            ////////////////////payement///////////
            payer.addActionListener(new ActionListener() {
                 
            @Override
            public void actionPerformed(ActionEvent evt) {


            
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/dev/web/app_dev.php/rh/payercmd?"
                            +"id="+cp.getId()
                            //+"&quantite="
                        
                    );
                    System.out.println(con.getUrl());
                     
             con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            
                        
                        ToastBar.showMessage("Commande Payée avec succées.",FontImage.MATERIAL_DONE);
                        
                                       
                        
                            try {
                                new PanierForm(current).show();
                                
                                
                                
                                
                            } catch (ParseException ex) {
                                
                            }
                         

                        
                        }
                    });
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    
               }

            
                });
            pdf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
           Display.getInstance().setProperty("WebLoadingHidden", "true");

  
  BrowserComponent  browser = new BrowserComponent();

 
        browser.setURL("http://localhost/dev/web/app_dev.php/rh/pdfmob?"+
                "id="+cp.getId());
           Display.getInstance().execute(browser.getURL());


                    }});
           
            
            cDetail.addComponent(BorderLayout.SOUTH, cButtons);
           
            return cDetail;
        }
                   
           


      public void showquantite()
      {
          tfnvquantite.setVisible(true);
          test=1;
      }
      
      
      public void noshowquantite()
      {
          tfnvquantite.setVisible(false);
          test=0;
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
                   
